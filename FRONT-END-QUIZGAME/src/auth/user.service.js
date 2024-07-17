import axios from 'axios';
import AuthService from './auth-service';
const API_URL = 'http://localhost:8080/api/users/';
const API_URL_TEST = 'http://localhost:8080/api/test/';
axios.defaults.withCredentials = true;

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
        return axios.get(API_URL_TEST + 'all');
    }

    getUserBoard() {
        return axios.get(API_URL_TEST + 'user');
    }

    getModeratorBoard() {
        return axios.get(API_URL_TEST + 'mod', );
    }

    getAdminBoard() {
        return axios.get(API_URL_TEST + 'admin');
    }

    getAllUser() {
        return axios.get(API_URL + 'getall')
    }

    async getTeam(id){
        return axios.get(API_URL + id + '/getTeam')
    }
}

export default new UserService();