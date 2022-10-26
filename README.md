# Firebsae-Cloud-Messaging-Spring-Boot-Example

[docs](https://firebase.google.com/docs/cloud-messaging)


## Initialize Firebase Project

Get started [here](https://firebase.google.com/)

## Firebase Web client

### Code Structure

```text

├───dist
|       index.html
|       bundle.js
|       firebase-messaging-sw.js
└───src
|        index.js
|        firebase-messaging-sw.js
|    firebase.config.js
|    ...

```

### 1. Install Modules

```shell
$ cd firebase-web
$ npm i 
```

### 2. Create file `firebase.config.js`

```js
module.exports = {
    firebaseConfig: {
        apiKey: "your_apiKey",
        authDomain: "your_authDomain",
        projectId: "your_projectId",
        storageBucket: "your_storageBucket",
        messagingSenderId: "your_messagingSenderId",
        appId: "your_appId",
    },
    vapidKey: "your_vapidKey",
};
```

> firebaseConfig : firebase console > create web app

> get vapidKey: firebase console > Project settings > cloud messaging > generate Key pair

### Build Web App by WebPack

```shell
$ npm run build
```

### Run WebApp

```sheel
# install serve
$ npm i serve

#run web
$ serve dist/
```

## Spring Boot Server- Firebase admin

### Firebase Dependency

```xml
<dependency>
    <groupId>com.google.firebase</groupId>
    <artifactId>firebase-admin</artifactId>
    <version>9.0.0</version>
</dependency>
```

### Firebase Messaging Config

```java
@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("firebase-adminsdk.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
```

### Send notification example `java`

```java
Message message = Message.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(content).build())
                .setToken(device_token)
                .build();
        return FirebaseMessaging.getInstance().send(message1);
```

### Send message example `java`

```java
Message message1 = Message.builder()
                .putData("message", message)
                .putData("time",LocalDateTime.now().toString())
                .setToken(device_token)
                .build();

return FirebaseMessaging.getInstance().send(message1);
```

### Subscribe the client app to a topic

```java
// These registration tokens come from the client FCM SDKs.
List<String> registrationTokens = Arrays.asList(
    "YOUR_REGISTRATION_TOKEN_1",
    // ...
    "YOUR_REGISTRATION_TOKEN_n"
);

// Subscribe the devices corresponding to the registration tokens to the
// topic.
TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
    registrationTokens, topic);
// See the TopicManagementResponse reference documentation
// for the contents of response.
System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");
```

### Unsubscribed the client app to a topic

```java
// These registration tokens come from the client FCM SDKs.
List<String> registrationTokens = Arrays.asList(
    "YOUR_REGISTRATION_TOKEN_1",
    // ...
    "YOUR_REGISTRATION_TOKEN_n"
);

// Unsubscribe the devices corresponding to the registration tokens from
// the topic.
TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(
    registrationTokens, topic);
// See the TopicManagementResponse reference documentation
// for the contents of response.
System.out.println(response.getSuccessCount() + " tokens were unsubscribed successfully");
```
