package com.leanplatform.MentorshipPlatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("{aws.secretKey}")
    private String secret;

    @Bean
    public S3Client getClient(){

        S3Client s3Client=S3Client.builder().region(Region.AP_SOUTH_1).credentialsProvider(new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {

                AwsCredentials awsCredentials=new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return "AKIASGTR35FCAZUSYC4W";
                    }

                    @Override
                    public String secretAccessKey() {
                        return "60bKMV8SINlE5KtzVBpy3NE+wnfo+6gXYWpcJw6b";
                    }
                };

                return awsCredentials;
            }
        }).build();

        return s3Client;
    }
}
