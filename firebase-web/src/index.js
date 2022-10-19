import { initializeApp } from "firebase/app";
import { getFirestore, collection, getDocs } from "firebase/firestore";
import { getMessaging, getToken } from "firebase/messaging";
import { firebaseConfig, vapidKey } from "../firebase.config";
import { getDatabase, ref, set, onValue, update } from "firebase/database";

initializeApp(firebaseConfig);

//init firebase app
const app = initializeApp(firebaseConfig);

//init services
const firestore = getFirestore();

//collection ref
const collectionRef = collection(firestore, "users");

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

const database = getDatabase();

const autoSendSpeed = () => {
    setInterval(() => {
        set(ref(database, "vehicle/" + form.sn.value), {
            sn: form.sn.value,
            speed: Math.random(),
        })
            .then(() => {
                console.log("send speed");
            })
            .catch((error) => {
                console.log(error);
            });
    }, 1000);
};

//realTime database
const formUpload = document.getElementById("form-upload");
const formRead = document.getElementById("form-read");

formUpload.addEventListener("submit", (e) => {
    e.preventDefault();

    let key = formUpload.key.value;
    let value = formUpload.value.value;

    update(ref(database, formUpload.topic.value + "/" + key), {
        value,
    })
        .then(() => {
            form.speed.value = null;
        })
        .catch((error) => {
            console.log(error);
        });
});

formRead.addEventListener("submit", (e) => {
    e.preventDefault();
    onValue(ref(database, "vehicle/cm001"), (snapshot) => {
        const data = snapshot.val();
        console.info(data);
    });
});
