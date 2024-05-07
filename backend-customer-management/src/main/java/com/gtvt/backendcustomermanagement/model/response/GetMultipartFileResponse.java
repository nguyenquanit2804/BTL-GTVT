package com.gtvt.backendcustomermanagement.model.response;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class GetMultipartFileResponse {
    private MultipartFile multipartFile;
    private File file;
}
