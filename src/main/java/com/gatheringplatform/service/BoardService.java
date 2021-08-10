package com.gatheringplatform.service;

import com.gatheringplatform.domain.Board;
import com.gatheringplatform.domain.BoardPlan;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface BoardService {
    public Map<String, String> uploadThumbNail(MultipartFile multipartFile) throws IOException;
    public String insertBoard(Board board) throws IOException;
    public String insertPlan(BoardPlan boardPlan);
}
