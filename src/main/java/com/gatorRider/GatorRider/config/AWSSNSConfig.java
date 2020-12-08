package com.gatorRider.GatorRider.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SetSMSAttributesRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSSNSConfig {
    private final String ACCESS_KEY = "AKIAJOUVQNNKWYWKOUHQ";
    private final String SECRET_KEY = "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";

    @Primary
    @Bean
    public AmazonSNSClient getSnsClient() {
        SetSMSAttributesRequest setSMSAttributesRequest = new SetSMSAttributesRequest()
                .addAttributesEntry("DefaultSMSType", "Transactional");
        AmazonSNSClient amazonSNSClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY)))
                .build();
        amazonSNSClient.setSMSAttributes(setSMSAttributesRequest);

        return amazonSNSClient;
    }
}
