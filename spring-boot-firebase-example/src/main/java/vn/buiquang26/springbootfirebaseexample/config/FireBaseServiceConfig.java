package vn.buiquang26.springbootfirebaseexample.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FireBaseServiceConfig {

    private final FirebaseApp firebaseApp;

    public FireBaseServiceConfig(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
    }

    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {

        return FirebaseMessaging.getInstance(firebaseApp);

    }
}
