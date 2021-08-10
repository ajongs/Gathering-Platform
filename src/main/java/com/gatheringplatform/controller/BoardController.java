package com.gatheringplatform.controller;

import com.gatheringplatform.domain.Board;
import com.gatheringplatform.exception.DefaultException;
import com.gatheringplatform.format.DefaultResponse;
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
    @PostMapping(value="/thumbnail")
    public ResponseEntity uploadThumbnail(MultipartFile multipartFile) throws IOException {
        return new ResponseEntity(boardService.uploadThumbNail(multipartFile), HttpStatus.OK);
    }
    //텍스트 에디터 및 게시판 등록관련 api
    @PostMapping(value = "/")
    public ResponseEntity insertBoard(@RequestBody Board board){
        return new ResponseEntity("", HttpStatus.OK);
    }
}
