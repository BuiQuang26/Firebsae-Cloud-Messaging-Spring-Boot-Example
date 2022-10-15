package vn.buiquang26.springbootfirebaseexample.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final FirebaseMessaging firebaseMessaging;

    public MessageController(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    @PostMapping("/notification")
    public String sendMessage(String message, String topic) throws FirebaseMessagingException {
        Message message1 = Message.builder()
                .putData("title", "Message from Spring")
                .putData("message", message)
                .putData("time",LocalDateTime.now().toString())
                .setTopic(topic)
                .build();
        return firebaseMessaging.send(message1);
    }

    @PostMapping("sub/topic")
    public String subTopic(String registration_token, String topic) throws FirebaseMessagingException {
        List<String> registrationTokens = Arrays.asList(
                registration_token
        );
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
                registrationTokens, topic);

        return response.getSuccessCount() + " tokens were subscribed successfully";
    }

}
