import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import World from "./components/World";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import UserInfo from "./components/UserInfo";

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        {path: '/', component: Hello},
        {path: '/world', component: World},
        {path: '/login', component: Login},
        {path: '/signup', component: SignUp},
        {path: '/user', component: UserInfo},
        // otherwise redirect to home
        {path: '*', redirect: '/'}
    ]
});
//
// router.beforeEach((to, from, next) => {
//     if (to.matched.some(record => record.meta.requiresAuth)) {
//         // this route requires auth, check if logged in
//         // if not, redirect to login page.
//         if (!store.getters.isLoggedIn) {
//             next({
//                 path: '/login'
//             })
//         } else {
//             next();
//         }
//     } else {
//         next(); // make sure to always call next()!
//     }
// });

export default router;