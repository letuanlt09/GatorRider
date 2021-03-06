package com.gatorRider.GatorRider.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.gatorRider.GatorRider.Model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class NotificationService implements org.hibernate.service.Service {
    @Autowired
    private AmazonSNSClient amazonSNSClient;

    String TOPIC_ARN = "arn:aws:sns:us-east-1:901231007750:SNSTest";

    public String notifyRegistrationBySMS(String phoneNumber) throws URISyntaxException {
        SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, "SMS",
                this.formatPhoneNumber(phoneNumber));
        amazonSNSClient.subscribe(subscribeRequest);
        return "You just signed up with Gator Ride! To confirm the subscription, check your phone : " + phoneNumber;
    }

    private String formatPhoneNumber(String phoneNumber) {
        return "+1" + phoneNumber;
    }

    public String sendSMSToOne(Driver driver, String message) {
        PublishRequest publishRequest = new PublishRequest().withPhoneNumber(this.formatPhoneNumber(driver.getPhone()))
                .withMessage(message);
        amazonSNSClient.publish(publishRequest);

        return "Notification send successfully";
    }

    public String publishSMSToAll(String message) {
        PublishRequest publishRequest = new PublishRequest().withTopicArn(TOPIC_ARN)
                .withMessage(message);
        amazonSNSClient.publish(publishRequest);

        return "Notification send successfully";
    }
}
