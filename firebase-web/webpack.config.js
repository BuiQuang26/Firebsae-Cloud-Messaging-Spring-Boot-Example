const path = require("path");

module.exports = {
    entry: {
        "/dist/bundle": "./src/index.js",
        "/dist/firebase-messaging-sw": "./src/firebase-messaging-sw.js",
    },
    output: {
        path: __dirname,
        filename: "[name].js",
    },
    watch: true,
    mode: "development",
};
