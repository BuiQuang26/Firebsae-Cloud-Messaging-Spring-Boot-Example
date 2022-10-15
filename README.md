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
