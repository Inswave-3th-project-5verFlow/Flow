package com.demo.proworks.common.s3;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class S3Uploader {

    @Resource
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    public String upload(MultipartFile file, String dirName) throws Exception {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = dirName + "/" + storedFileName;

        try (InputStream inputStream = file.getInputStream()) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            amazonS3.putObject(bucketName, s3Key, inputStream, metadata);
        }

        return s3Key;
    }

    public String upload(File file, String dirName) {
        String storedFileName = UUID.randomUUID().toString() + ".jpg";
        String s3Key = dirName + "/" + storedFileName;

        amazonS3.putObject(bucketName, s3Key, file);

        return s3Key;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) return "";
        int lastDotIndex = fileName.lastIndexOf(".");
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}
