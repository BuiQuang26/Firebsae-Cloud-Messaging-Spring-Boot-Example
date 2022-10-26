import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { firebaseConfig, vapidKey } from "../firebase.config";

initializeApp(firebaseConfig);

//init firebase app
const app = initializeApp(firebaseConfig);

// FCM
// Get registration token. Initially this makes a network call, once retrieved
// subsequent calls to getToken will return from cache.
const messaging = getMessaging(app);

getToken(messaging, { vapidKey })
    .then((currentToken) => {
        if (currentToken) {
            // Send the token to your server and update the UI if necessary
            // ...
            console.log({ currentToken });
        } else {
            // Show permission request UI
            console.log(
                "No registration token available. Request permission to generate one."
            );
            // ...
        }
    })
    .catch((err) => {
        console.log("An error occurred while retrieving token. ", err);
        // ...
    });

//handle form submit
const formSendMessage = document.getElementById("form-send-message");
const formSendNoti = document.getElementById("form-send-noti");

formSendMessage.addEventListener("submit", (e) => {
    e.preventDefault();

    const messageData = {
        "device token": formSendMessage.device_token.value,
        mesage: formSendMessage.message.value,
    };

    console.log(messageData);

    messaging;
});

formSendNoti.addEventListener("submit", (e) => {
    e.preventDefault();

    const notiData = {
        device_token: formSendNoti.device_token.value,
        title: formSendNoti.noti_title.value,
        content: formSendNoti.noti_content.value,
    };

    console.log(notiData);
});

onMessage(messaging, (payload) => {
    console.log(payload);
});
