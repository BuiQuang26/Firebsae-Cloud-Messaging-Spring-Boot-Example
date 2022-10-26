import { onBackgroundMessage } from "firebase/messaging/sw";
import { initializeApp } from "firebase/app";
import { getMessaging } from "firebase/messaging/sw";
import { firebaseConfig } from "../firebase.config";

initializeApp(firebaseConfig);

const messaging = getMessaging();

console.log(self);

// onBackgroundMessage(messaging, (payload) => {
//     console.log("Received background message ", payload);

//     const data = payload.data;
// });

// console.log("-----------------------");
// console.log(self.registration);
