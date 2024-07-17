import axios from 'axios';
const API_URL = 'http://localhost:8080/api/quiz/';

axios.defaults.withCredentials = true;

class QuizService {
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

    getAll() {
        return axios.get(API_URL + 'all');
    }

    deleteQuiz(quizId) {
        return axios.delete(API_URL +'delete/'+quizId)
    }

   
}

export default new QuizService();