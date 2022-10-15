import { initializeApp } from "firebase/app";
import { getFirestore, collection, getDocs } from "firebase/firestore";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { firebaseConfig, vapidKey } from "../firebase.config";

initializeApp(firebaseConfig);

//init firebase app
const app = initializeApp(firebaseConfig);

//init services
const db = getFirestore();

//collection ref
const collectionRef = collection(db, "users");

getDocs(collectionRef)
    .then((snapshot) => {
        const docs = snapshot.docs;
        docs.forEach((doc) => {
            console.log({ ...doc.data(), userId: doc.id });
        });
    })
    .catch((error) => {
        console.log(error);
    });

// FCM
// Get registration token. Initially this makes a network call, once retrieved
// subsequent calls to getToken will return from cache.
const messaging = getMessaging(app);

getToken(messaging, { vapidKey })
    .then((currentToken) => {
        if (currentToken) {
            // Send the token to your server and update the UI if necessary
            // ...
            console.log(currentToken);
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

onMessage(messaging, (payload) => {
    console.log("Message received. ", payload);
    // ...
});
