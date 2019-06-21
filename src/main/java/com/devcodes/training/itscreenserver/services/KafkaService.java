package com.devcodes.training.itscreenserver.services;

public interface KafkaService {

    void sendMessage(String message, String topicDst);

    void listenWithHeaders(String message);

    void sendMessageToAll(String message);

}
