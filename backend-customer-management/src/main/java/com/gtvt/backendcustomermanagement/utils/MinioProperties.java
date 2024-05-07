package com.gtvt.backendcustomermanagement.utils;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tpb.minio.access")
public class MinioProperties {

    @Value("${minio.quay.user}")
    private String username;

    @Value("${minio.quay.password}")
    private String password;

    /**
     * It's a URL, domain name ,IPv4 perhaps IPv6 Address ")
     */
    @Value("${minio.quay.endpoint}")
    private String endpoint;

    /**
     * //"TCP/IP Port number "
     */
    private Integer port;

    /**
     * //"accessKey Similar to user ID, Used to uniquely identify your account "
     */
    private String accessKey;

    /**
     * //"secretKey It's the password for your account "
     */
    private String secretKey;

    /**
     * //" If it is true, It uses https instead of http, The default value is true"
     */
    private boolean secure;

    /**
     * //" Default bucket "
     */
    private String bucketName;

    /**
     * The maximum size of the picture
     */
    private long imageSize;

    /**
     * Maximum size of other files
     */
    private long fileSize;

    private String url;


    /**
     * From the official website   Construction method , I just climbed the official website  （ The dog's head lives ） *  This is   The class that the client operates on
     */
    @Bean("beanMinio")
    MinioClient minioClient() {
        MinioClient minioClient =
                MinioClient.builder()
                        .credentials(username, password)
                        .endpoint(endpoint)
                        .build();
        return minioClient;
    }
}