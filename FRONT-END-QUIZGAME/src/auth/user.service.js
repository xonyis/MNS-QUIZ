import axios from 'axios';
import AuthService from './auth-service';

const API_URL = 'http://localhost:8080/api/test/';

class UserService {
    constructor() {
        this.init();
    }

    init() {
        axios.defaults.baseURL = API_URL;

        axios.interceptors.request.use(
            config => {
                const user = JSON.parse(localStorage.getItem('user'));
                if (user && user.accessToken) {
                    config.headers.Authorization = 'Bearer ' + user.accessToken;
                }
                return config;
            },
            error => {
                return Promise.reject(error);
            }
        );
    }

    getPublicContent() {
        return axios.get(API_URL + 'all');
    }

    getUserBoard() {
        return axios.get(API_URL + 'user');
    }

    getModeratorBoard() {
        return axios.get(API_URL + 'mod', );
    }

    getAdminBoard() {
        return axios.get(API_URL + 'admin');
    }
}

export default new UserService();