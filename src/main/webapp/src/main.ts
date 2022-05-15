import {createApp} from "vue";
import {createPinia} from "pinia";
import type {RouteRecordRaw} from 'vue-router'
import {createRouter, createWebHashHistory} from "vue-router";
import App from "./App.vue";
import Home from "./components/Home.vue";
import About from "./components/About.vue";
import Upload from './components/Upload.vue';

const routes: RouteRecordRaw[] = [
    {path: "/", name: "Home", component: Home},
    {path: "/upload", name: "Upload", component: Upload},
    {path: "/about", name: "About", component: About},
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

const app = createApp(App);

app.config.performance = true;

app.use(createPinia());
app.use(router);

app.mount("#app");
