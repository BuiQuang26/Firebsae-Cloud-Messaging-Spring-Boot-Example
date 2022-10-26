package vn.buiquang26.springbootfirebaseexample.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MqttConfig {
    @Bean
    public IMqttClient mqttClient() throws MqttException {
        String publisherId = UUID.randomUUID().toString();
        return new MqttClient("tcp://selex.site:1883", publisherId, new MemoryPersistence());
    }
}

