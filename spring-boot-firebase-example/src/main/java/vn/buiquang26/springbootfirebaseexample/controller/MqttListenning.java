package vn.buiquang26.springbootfirebaseexample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;

@Component
public class MqttListenning {

    private final IMqttClient mqttClient;

    public MqttListenning(IMqttClient mqttClient, FirebaseApp firebaseApp) {
        this.mqttClient = mqttClient;
    }

    @Bean
    public void MqttListenner() throws MqttException {
        mqttClient.connect();
        mqttClient.subscribe("dt/#", (topic, message) -> {
            try {
                System.out.println(topic);
                Map data = new ObjectMapper().readValue(message.getPayload(), Map.class);
                data.put("time", new Timestamp(System.currentTimeMillis()).toInstant().toString());

                if(topic.startsWith("dt/ev")){
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference reference = firebaseDatabase.getReference("server/device/bp");
                    reference.child((String) data.get("serial_number")).setValueAsync(data);
                }else if(topic.startsWith("dt/bp")){
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference reference = firebaseDatabase.getReference("server/device/ev");
                    reference.child((String) data.get("serial_number")).setValueAsync(data);
                }else if(topic.startsWith("dt/swapping/bss")){
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference reference = firebaseDatabase.getReference("server/device/bss");
                    reference.setValueAsync(data);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}
