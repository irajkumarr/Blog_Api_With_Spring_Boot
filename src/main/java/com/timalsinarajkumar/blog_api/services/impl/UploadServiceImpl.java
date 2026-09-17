package com.timalsinarajkumar.blog_api.services.impl;

import com.cloudinary.utils.ObjectUtils;
import com.timalsinarajkumar.blog_api.config.CloudinaryConfig;
import com.timalsinarajkumar.blog_api.exception.BadRequestException;
import com.timalsinarajkumar.blog_api.services.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadServiceImpl implements UploadService {

    private final CloudinaryConfig cloudinaryConfig;

    public UploadServiceImpl(CloudinaryConfig cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Cannot upload an empty file");
        }

        //  Validate file size (e.g., limit to 5MB)
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new BadRequestException("File size exceeds the 5MB limit");
        }

        //  Validate content type (Ensure it's an image)
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new BadRequestException("Only image files are allowed");
        }

        //  Proceed with Cloudinary Upload
        Map<?, ?> uploadResult = cloudinaryConfig.cloudinary()
                .uploader()
                .upload(file.getBytes(), ObjectUtils.asMap("folder", "springboot_uploads"));

        return uploadResult.get("secure_url").toString();
    }
}
