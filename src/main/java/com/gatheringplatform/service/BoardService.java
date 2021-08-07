package com.gatheringplatform.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface BoardService {
    public Map<String, String> uploadThumbNail(MultipartFile multipartFile) throws IOException;
}
