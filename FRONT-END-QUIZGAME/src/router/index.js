import { createRouter, createWebHistory } from 'vue-router'
import Home from "../components/Home.vue";
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";
import Signup from "../components/Signup.vue";
import ProfileView from '../views/ProfileView.vue';
import EditQuiz from '@/views/quiz/EditQuiz.vue';
import QuizzView from '@/views/quiz/QuizzView.vue';
import QuizComponent from '@/components/quiz/QuizComponent.vue';

// lazy-loaded
const BoardAdmin = () =>
    import ("../components/EcranAdmin.vue")
const BoardModerator = () =>
    import ("../components/EcranModo.vue")
const BoardUser = () =>
    import ("../components/EcranUser.vue")
const GestionUser = () =>
    import ("../views/GestionUser.vue")
const GestionEquipe = () =>
    import ("../views/GestionEquipe.vue")
const GestionQuiz = () =>
    import ("../views/GestionQuiz.vue")
const CreateQuiz = () =>
    import ("../views/quiz/CreateQuiz.vue")

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
    {
        path: "/gestionEquipe",
        name: "equipe",
        // lazy-loaded
        component: GestionEquipe,
    },
    {
        path: "/gestionQuiz",
        name: "gestionQuiz",
        // lazy-loaded
        component: GestionQuiz,
    },
    {
        path: "/createQuiz",
        name: "createQuiz",
        // lazy-loaded
        component: CreateQuiz,
    },
    {
        path: "/editQuiz",
        name: "editQuiz",
        // lazy-loaded
        component: EditQuiz,

    },
    {
        path: "/quizView",
        name: "quizView",
        // lazy-loaded
        component: QuizzView,
    },
    {
        path: "/playQuiz",
        name: "playQuiz",
        // lazy-loaded
        component: QuizComponent,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router