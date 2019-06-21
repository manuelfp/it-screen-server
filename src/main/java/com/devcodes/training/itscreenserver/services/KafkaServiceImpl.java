package com.devcodes.training.itscreenserver.services;

import com.devcodes.training.itscreenserver.jpa.Screen;
import com.devcodes.training.itscreenserver.jpa.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.dst}")
    private String topicName;

    @Override
    public void sendMessage(String message, String topicDst) {
        kafkaTemplate.send(topicDst, message);
    }

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    @KafkaListener(topics = "${kafka.topic.own}")
    public void listenWithHeaders(@Payload String message) {

        String idScreen = message.split("screen:")[1];
        Screen screen = new Screen(idScreen,idScreen,new Date());
        screenRepository.save(screen);
        System.out.println("Received Message: " + message);
    }

    @Override
    public void sendMessageToAll(String message) {
        kafkaTemplate.send(topicName, message);
    }
}
