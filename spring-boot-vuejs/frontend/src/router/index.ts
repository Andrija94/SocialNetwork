import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Home from '../components/Home.vue'


import store from '../store'
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
import Posts from "@/components/Posts.vue";

const routes: Array<RouteRecordRaw> = [
    {path: '/', component: Login},
    {path: '/home/:username', component: Home},
    {path: '/register', component: Register},
    {path: '/post/:username', component: Posts},
    // otherwise redirect to home
    {path: '/:pathMatch(.*)*', redirect: '/'}
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL), // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes
});


router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (!store.getters.isLoggedIn) {
            next({
                path: '/login'
            })
        } else {
            next();
        }
    } else {
        next(); // make sure to always call next()!
    }
});

export default router;