package com.gatheringplatform.service;

import com.gatheringplatform.domain.Board;
import com.gatheringplatform.format.DefaultResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BoardService {
    public Map<String, String> uploadThumbNail(MultipartFile multipartFile) throws IOException;
    public Map<String, String> uploadImages(MultipartFile[] multipartFiles) throws IOException;
    public DefaultResponse insertBoard(Board board) throws IOException;
    public List<Board> getBoardList(int pageNum);
}
