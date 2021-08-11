package com.gatheringplatform.service;

import com.gatheringplatform.domain.Board;
import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.RequestException;
import com.gatheringplatform.mapper.BoardMapper;
import com.gatheringplatform.util.AwsS3;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    AwsS3 awsS3;

    @Autowired
    UserService userService;

    @Autowired
    BoardMapper boardMapper;

    @Override
    public Map<String, String> uploadThumbNail(MultipartFile multipartFile) throws IOException {
        if(multipartFile==null){
            throw new RequestException(ErrorEnum.THUMBNAIL_NULL);
        }
        else if(!multipartFile.getContentType().equals("image/jpeg") && !multipartFile.getContentType().equals("image/png")){
            throw new RequestException(ErrorEnum.INVALID_THUMBNAIL);
        }
        Map<String, String> url = new HashMap<>();
        url.put("URL", awsS3.upload(multipartFile));
        return url;
    }

    @Override
    public String insertBoard(Board board) throws IOException {
        //TODO 1. 토큰에서 nickname 뽑아오고, insert to Board DB
        board.setAuthor(userService.getLoginNickname());
        long id = boardMapper.insertBoard(board);

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
        return "게시물이 성공적으로 등록되었습니다.";
    }

}
