package com.socical.network.configs;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MinioConfig {

    @Value("${minio.access.name}")
    private String accessKey;

    @Value("${minio.access.secret}")
    private String accessSecret;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.bucket.name}")
    private String bucket;

    @Bean
    public MinioClient generateMinioClient() {
        try {
            MinioClient minioClient = new MinioClient.Builder()
                .endpoint(minioUrl)
                .credentials(accessKey, accessSecret)
                .build();
            return minioClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
