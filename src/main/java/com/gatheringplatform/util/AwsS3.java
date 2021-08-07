package com.gatheringplatform.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
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

    public String upload(MultipartFile multipartFile) throws IOException {
        String filename = UUID.randomUUID() + multipartFile.getOriginalFilename();
        //TODO 메타데이터에 대해서 알아보기 new ObjectMetadata();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        PutObjectRequest request = new PutObjectRequest(bucket+"/thumbnail", filename, multipartFile.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(request);

        return amazonS3.getUrl(bucket, filename).toString();
    }

}
