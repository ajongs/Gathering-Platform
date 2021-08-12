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

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    //게시물 섬네일 업로드 api
    @Auth
    @ApiOperation(value = "섬네일 업로드",notes = "아마존 S3 서버에 이미지파일을 업로드하고 해당 url을 반환 받습니다.\n" +
            "반환 받은 url은 게시판 등록 api의 thumbnail 항목에 입력하셔야 합니다.",authorizations = @Authorization(value = "JWT"))
    @PostMapping(value="/thumbnail")
    public ResponseEntity uploadThumbnail(MultipartFile multipartFile) throws IOException {
        return new ResponseEntity(boardService.uploadThumbNail(multipartFile), HttpStatus.OK);
    }
    @Auth
    @ApiOperation(value = "텍스트에디터에서 사용될 이미지 업로드",notes = "아마존 S3 서버에 이미지파일을 업로드하고 해당 url을 반환 받습니다.\n" +
            "반환 받은 url은 텍스트에디터에서 img태그의 src로 사용될 것입니다.",authorizations = @Authorization(value = "JWT"))
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
    @GetMapping()
    @ApiOperation(value = "미완성 **호출하지마세요**",notes = "")
    public ResponseEntity getBoardList(){
        return new ResponseEntity(boardService.getBoardList(), HttpStatus.OK);
    }
}
