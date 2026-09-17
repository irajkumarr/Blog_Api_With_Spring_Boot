package com.timalsinarajkumar.blog_api.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String uploadImage(MultipartFile file) throws IOException;
}
