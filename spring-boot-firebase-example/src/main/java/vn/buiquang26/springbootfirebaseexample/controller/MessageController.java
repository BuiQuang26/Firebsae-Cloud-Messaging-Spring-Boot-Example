package vn.buiquang26.springbootfirebaseexample.controller;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("")
public class MessageController {

    @PostMapping("/notification")
    public String sendMessage(String device_token, String title, String content) throws FirebaseMessagingException {
        Message message1 = Message.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(content).build())
                .setToken(device_token)
                .build();
        return FirebaseMessaging.getInstance().send(message1);
    }

    @PostMapping("/message")
    public String subTopic(String device_token, String message) throws FirebaseMessagingException {
        Message message1 = Message.builder()
                .putData("message", message)
                .putData("time",LocalDateTime.now().toString())
                .setToken(device_token)
                .build();

        return FirebaseMessaging.getInstance().send(message1);
    }

}
