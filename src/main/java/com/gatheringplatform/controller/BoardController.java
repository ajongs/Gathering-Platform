package com.gatheringplatform.controller;

import com.gatheringplatform.annotation.Auth;
import com.gatheringplatform.domain.Board;
import com.gatheringplatform.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    //게시물 섬네일 업로드 api
    @Auth
    @ApiOperation(value = "섬네일 업로드",notes = "아마존 S3 서버에 이미지파일을 업로드하고 해당 url을 반환 받습니다.\n" +
            "반환 받은 url은 게시판 등록 api의 thumbnail 항목에 입력하셔야 합니다.\n확장자는 jpeg, png만 가능합니다.\n" +
            "사진 파일의 최대 크기는 10MB입니다.",authorizations = @Authorization(value = "JWT"))
    @PostMapping(value="/thumbnail")
    public ResponseEntity uploadThumbnail(MultipartFile multipartFile) throws IOException {
        return new ResponseEntity(boardService.uploadThumbNail(multipartFile), HttpStatus.OK);
    }
    @Auth
    @ApiOperation(value = "텍스트에디터에서 사용될 이미지 업로드",notes = "아마존 S3 서버에 이미지파일을 업로드하고 해당 url을 반환 받습니다.\n" +
            "반환 받은 url은 텍스트에디터에서 img태그의 src로 사용될 것입니다.\n한 사진 파일의 최대크기는 10MB" +
            "\n요청가능한 사진 파일 최대 개수는 5장입니다.",authorizations = @Authorization(value = "JWT"))
    @PostMapping(value="/images")
    public ResponseEntity uploadImages(MultipartFile[] files) throws IOException {
        return new ResponseEntity(boardService.uploadImages(files), HttpStatus.OK);
    }
    //텍스트 에디터 및 게시판 등록관련 api
    @Auth
    @ApiOperation(value = "게시물 등록 API",notes = "게시물을 등록하는 api입니다. content는 html 원본그대로 전달해주시면 됩니다.",authorizations = @Authorization(value = "JWT"))
    @PostMapping()
    public ResponseEntity insertBoard(@RequestBody Board board) throws IOException {
        return new ResponseEntity(boardService.insertBoard(board), HttpStatus.OK);
    }

    //게시판 조회 api, 페이징 처리
    @GetMapping(value = "/{category}/{pageNum}")
    @ApiOperation(value = "게시판 리스트 조회",notes = "요청하신 카테고리에 해당하는 게시물 리스트를 조회하는 api입니다.\n페이지당 10개의 목록을 보여줍니다.")
    public ResponseEntity getBoardList(@PathVariable String category, @PathVariable long pageNum){
        return new ResponseEntity(boardService.getBoardList(category, pageNum), HttpStatus.OK);
    }
    //게시물 상세 조회 api
    @GetMapping(value = "/{board_id}")
    @ApiOperation(value = "게시물 상세조회 api", notes = "요청하신 id 값의 게시물의 상세정보를 조회하는 api입니다.")
    public ResponseEntity getBoard(@PathVariable long board_id){
        return new ResponseEntity(boardService.getBoard(board_id), HttpStatus.OK);
    }
    //게시물 수정
    @Auth
    @ApiOperation(value = "게시물 내용 수정 api", notes = "게시물 내용을 수정합니다.", authorizations = @Authorization(value = "JWT"))
    @PutMapping(value = "/{board_id}")
    public ResponseEntity modifyBoard(@PathVariable long board_id, @RequestBody Board board){
        return new ResponseEntity(boardService.modifyBoard(board_id, board), HttpStatus.OK);
    }
    //게시물 삭제
    @Auth
    @ApiOperation(value = "게시물 삭제 api", notes = "게시물을 삭제합니다.", authorizations = @Authorization(value = "JWT"))
    @DeleteMapping(value = "/{board_id}")
    public ResponseEntity deleteBoard(@PathVariable long board_id){
        return new ResponseEntity(boardService.deleteBoard(board_id), HttpStatus.OK);
    }
}
