package com.gatheringplatform.controller;

import com.gatheringplatform.annotation.Auth;
import com.gatheringplatform.domain.Board;
import com.gatheringplatform.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    //게시물 섬네일 업로드 api
    @Auth
    @PostMapping(value="/thumbnail")
    public ResponseEntity uploadThumbnail(MultipartFile multipartFile) throws IOException {
        return new ResponseEntity(boardService.uploadThumbNail(multipartFile), HttpStatus.OK);
    }
    //텍스트 에디터 및 게시판 등록관련 api
    @Auth
    @PostMapping(value = "/")
    public ResponseEntity insertBoard(@RequestBody Board board) throws IOException {
        return new ResponseEntity(boardService.insertBoard(board), HttpStatus.OK);
    }
    //게시판 조회 api, 페이징 처리
}
