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

    getPublic() {
        return axios.get(API_URL + 'public');
    }

    getByTeamId(teamId) {
        return axios.get(API_URL + teamId + '/findQuizByTeamId')
    }

    addQuizz(quiz, teamName) {
        console.log(JSON.parse(quiz));
        return axios.post(API_URL + 'createQuizz', quiz, {
            headers: {
                "Content-Type": "application/json"
            },
            params: { teamName: teamName }
        });

    }

    updateQuiz(id, quiz) {
        console.log(quiz);
        return axios.put(API_URL + id, quiz, {
            headers: {
                "Content-Type": "application/json"
            }
        })
    }

    deleteQuiz(quizId) {
        return axios.delete(API_URL + 'delete/' + quizId)
    }

    updateScore(userId, quizId, score) {

        console.log(userId);
        return axios.post(API_URL + quizId + '/updateScore', null, {
            params: {
                userId: userId,
                score: score
            },
            withCredentials: true,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }


}

export default new QuizService();