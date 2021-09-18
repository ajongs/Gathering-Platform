package com.gatheringplatform.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class AwsS3 {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, Boolean isThumbnail) throws IOException {
        String filename = UUID.randomUUID() + multipartFile.getOriginalFilename();
        //TODO 메타데이터에 대해서 알아보기 new ObjectMetadata();
        // cloud Front 사용시 설정해줘야 할것있음
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        String bucketName;
        if(isThumbnail){
            bucketName = bucket +"/thumbnail";
        }
        else{
            bucketName = bucket;
        }
        PutObjectRequest request = new PutObjectRequest(bucketName, filename, multipartFile.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(request);

        return amazonS3.getUrl(bucketName, filename).toString();
    }
    public String deleteImages(String filepath){
        return null;
    }
}
