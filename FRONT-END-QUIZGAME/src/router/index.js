import { createRouter, createWebHistory } from 'vue-router'
import Home from "../components/Home.vue";
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";
import Signup from "../components/Signup.vue";
import ProfileView from '../views/ProfileView.vue';

// lazy-loaded
const BoardAdmin = () =>
    import ("../components/EcranAdmin.vue")
const BoardModerator = () =>
    import ("../components/EcranModo.vue")
const BoardUser = () =>
    import ("../components/EcranUser.vue")
const GestionUser = () =>
    import ("../views/GestionUser.vue")

const routes = [{
        path: "/",
        name: "home",
        component: Home,
    },
    {
        path: "/home",
        component: Home,
    },
    {
        path: "/login",
        component: Login,
    },
    {
        path: "/signup",
        component: Signup,
    },
    {
        path: "/register",
        component: Register,
    },
    {
        path: "/profile",
        name: "profile",
        // lazy-loaded
        component: ProfileView,
    },
    {
        path: "/admin",
        name: "admin",
        // lazy-loaded
        component: BoardAdmin,
    },
    {
        path: "/mod",
        name: "moderator",
        // lazy-loaded
        component: BoardModerator,
    },
    {
        path: "/user",
        name: "user",
        // lazy-loaded
        component: BoardUser,
    },
    {
        path: "/gestionUser",
        name: "user",
        // lazy-loaded
        component: GestionUser,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router