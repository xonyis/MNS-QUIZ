import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import store from "./store";



// Importer les fichiers CSS de Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css';

// Importer les fichiers JavaScript de Bootstrap
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap-icons/font/bootstrap-icons.css'; // Import Bootstrap Icons CSS


import router from './router'

const app = createApp(App)
app.use(router)
app.use(store)

app.mount('#app')