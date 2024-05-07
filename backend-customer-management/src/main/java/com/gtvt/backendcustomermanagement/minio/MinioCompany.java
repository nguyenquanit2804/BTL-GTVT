package com.gtvt.backendcustomermanagement.minio;


import com.gtvt.backendcustomermanagement.model.response.GetMultipartFileResponse;
import com.gtvt.backendcustomermanagement.utils.CommonUtil;
import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class MinioCompany {

    @Value("${minio.quay.user}")
    private String username;

    @Value("${minio.quay.password}")
    private String password;

    @Value("${minio.quay.endpoint}")
    private String endpoint;

    @Value("${minio.quay.bucketName}")
    private String bucketName;

    @Autowired
    MinioClient minioClient;

    public MinioCompany() {
    }

    @PostConstruct
    @SneakyThrows
    public void init() {
        minioClient = MinioClient.builder()
                .credentials(username, password)
                .endpoint(endpoint)
                .build();

        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        }
    }


    /**
     * Đẩy dữ liệu vào MinIO
     *
     * @param file     Dữ liệu cần đẩy dạng mutilpartfile
     * @param fileName Tên file muốn lưu.
     *                 Cấu trúc face/DateToday/ramdomUUID
     * @return ID của ảnh, dùng để lấy ảnh ra khi cần.
     */
    @SneakyThrows
    public String putObject(MultipartFile file, String fileName) {

        String fileType = "";
        InputStream inputStream;
        inputStream = file.getInputStream();
        fileType = "image/png";
        try {
            fileType = file.getOriginalFilename().split("\\.")[1];
        }
        catch (Exception e){
        }

        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, file.getSize(), -1)
                        .contentType(fileType)
                        .build());

        return fileName;
    }

    /**
     * Đẩy dữ liệu vào MinIO.
     *
     * @param file     Dữ liệu cần đẩy dang inputstream.
     * @param fileName Tên file muốn lưu.
     *                 Cấu trúc face/DateToday/randomUUID.
     * @return ID của ảnh, dùng để lấy ảnh ra khi cần.
     */
    @SneakyThrows
    public String putObject(InputStream file, String fileName) {

        String fileType = "";
        InputStream inputStream;
        inputStream = file;
        fileType = "image/png";

        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, file.available(), -1)
                        .contentType(fileType)
                        .build());

        return fileName;
    }

    @SneakyThrows
    public String putObject(String base64, String fileName) {

        String fileType = "";
        GetMultipartFileResponse getMultipartFileResponse = CommonUtil.base64toMultipartFile(base64, UUID.randomUUID().toString());
        InputStream inputStream;
        inputStream = getMultipartFileResponse.getMultipartFile().getInputStream();
        fileType = "image/png";

        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType(fileType)
                        .build());

        getMultipartFileResponse.getFile().delete();

        return fileName;
    }

    /**
     * Lấy hình ảnh dạng URL
     *
     * @param fileName ID của ảnh.
     * @return URL hình ảnh.
     */
    @SneakyThrows
    public String getPreviewFileUrl(String fileName) {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileName)
                        .expiry(2, TimeUnit.HOURS)
                        .build());
    }

    /**
     * Xóa ảnh/tài liệu.
     *
     * @param fileName ID của ảnh.
     */
    @SneakyThrows
    public void removeObject(String fileName) {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build());
    }

    /**
     * ấy ảnh/tài liệu dạng inputstream.
     *
     * @param fileName ID của ảnh.
     * @return inputstream của ảnh.
     */
    @SneakyThrows
    public InputStream getObjectResponse(String fileName) {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build());
    }

}
