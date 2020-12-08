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
    private final String ACCESS_KEY = "AKIAIGBPF2UIUJOFM6HQ";
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
