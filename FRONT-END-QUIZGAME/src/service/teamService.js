import axios from 'axios';
const API_URL = 'http://localhost:8080/api/teams/';

axios.defaults.withCredentials = true;

class TeamService {
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

    getByName(teamName) {
        return axios.get(API_URL + 'teamByName/' + teamName)
    }

    removeUserFromTeam(teamId, userId) {
        return axios.post(API_URL + teamId + '/removeUser/' + userId)
    }

    addUserToTeam(teamId, userName) {
        return axios.post(API_URL + teamId + '/addUser/' + userName)
    }

    createTeam(teamName) {
        return axios.post(API_URL + 'create', { name: teamName })
    }

    deleteTeam(teamId) {
        return axios.delete(API_URL + 'delete/' + teamId)
    }
}

export default new TeamService();