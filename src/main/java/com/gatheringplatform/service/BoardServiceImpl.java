package com.gatheringplatform.service;

import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.RequestException;
import com.gatheringplatform.util.AwsS3;
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
}
