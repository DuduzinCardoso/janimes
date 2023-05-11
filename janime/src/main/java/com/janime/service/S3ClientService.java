package com.janime.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3ClientService {

    String uploadFile(MultipartFile file);
}
