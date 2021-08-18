package com.gatheringplatform.service;

import com.gatheringplatform.domain.Board;
import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.RequestException;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.mapper.BoardMapper;
import com.gatheringplatform.util.AwsS3;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    AwsS3 awsS3;

    @Autowired
    UserService userService;

    @Autowired
    BoardMapper boardMapper;

    @Value("${categories}")
    String[] categories;

    @Override
    public Map<String, String> uploadThumbNail(MultipartFile multipartFile) throws IOException {
        if(multipartFile==null){
            throw new RequestException(ErrorEnum.THUMBNAIL_NULL);
        }
        else if(!multipartFile.getContentType().equals("image/jpeg") && !multipartFile.getContentType().equals("image/png")){
            throw new RequestException(ErrorEnum.INVALID_THUMBNAIL);
        }
        Map<String, String> url = new HashMap<>();
        url.put("Thumbnail_URL", awsS3.upload(multipartFile, true));
        return url;
    }

    @Override
    public Map<String, String> uploadImages(MultipartFile[] files) throws IOException {
        if(files==null){
            throw new RequestException(ErrorEnum.IMAGES_NULL);
        }
        Map<String, String> url = new HashMap<>();
        for(MultipartFile file : files){
            if(!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")){
                throw new RequestException(ErrorEnum.INVALID_IMAGE);
            }
            url.put(file.getOriginalFilename(), awsS3.upload(file,false));
        }

        return url;
    }

    @Override
    public DefaultResponse insertBoard(Board board) throws IOException {
        //TODO 1. 토큰에서 nickname 뽑아오고, insert to Board DB
        board.setAuthor(userService.getLoginNickname());
        System.out.println("Author : "+board.getAuthor());
        boardMapper.insertBoard(board);

        /*
        //TODO 2. html 파싱해서 <img> 태그 리스트로 변환
        Map<Long, String> imageList = new HashMap<>();

        Document document = Jsoup.parse(board.getContent());
        Elements images = document.getElementsByTag("img");

        if(images.size()>0){
            for(Element image : images ){
                imageList.put(id ,image.attr("src"));
            }
        }
        //TODO 3. s3파일에 이미지 리스트 올린 후 url 리스트 반환


        //TODO 4. 이미지 리스트 image 테이블에 insert
        boardMapper.insertImages(imageList);
        */
        return new DefaultResponse("게시물이 성공적으로 등록되었습니다.", HttpStatus.OK);
    }

    @Override
    public List<Board> getBoardList(String category, long pageNum) {
        //카테고리가 유효하지 않을때
        Boolean validCategory = false;
        for(String c : categories){
            if(category.equals(c)){
                validCategory=true;
                break;
            }
        }
        if(!validCategory){
            throw new RequestException(ErrorEnum.INVALID_CATEGORY);
        }

        //해당 페이지가 존재하지 않을때
        if(pageNum==0){
            throw new RequestException(ErrorEnum.NON_EXISTED_PAGE);
        }

        long startIndex = (pageNum-1)*10;

        //DB에 담겨진 인덱스를 초과한 페이지를 요청했을 때
        if(boardMapper.countBoardByCategory(category) < startIndex){
            throw new RequestException(ErrorEnum.NON_EXISTED_PAGE);
        }

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("category", category);
        parameter.put("startIndex", startIndex);
        return boardMapper.getBoardList(parameter);
    }

    @Override
    public Board getBoard(long board_id) {
        Board board = boardMapper.getBoard(board_id);
        //삭제된 페이지이거나 id값이 존재하지 않는 경우
        if(board==null){
            throw new RequestException(ErrorEnum.DELETED_PAGE);
        }
        return board;
    }

    @Override
    public DefaultResponse modifyBoard(long board_id, Board board) {
        //권한검사
        verifyAuthorization(board_id);

        board.setId(board_id);
        boardMapper.modifyBoard(board);

        return new DefaultResponse("게시물이 성공적으로 변경되었습니다.", HttpStatus.OK);
    }

    @Override
    public DefaultResponse deleteBoard(long board_id) {
        //권한 검사
        verifyAuthorization(board_id);

        boardMapper.deleteBoard(board_id);
        return new DefaultResponse("게시물이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }

    private void verifyAuthorization(long board_id){
        String boardAuthor = boardMapper.getAuthor(board_id);
        String loginNickname = userService.getLoginNickname();

        if(!boardAuthor.equals(loginNickname)){
            throw new RequestException(ErrorEnum.UNAUTHORIZED);
        }
    }
}
