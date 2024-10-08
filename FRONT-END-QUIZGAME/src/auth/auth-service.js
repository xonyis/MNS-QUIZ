import axios from 'axios';

const API_URL = 'http://localhost:8080/api/';

class AuthService {
    login(user) {
        return axios
            .post(API_URL + 'auth/signin', {
                username: user.username,
                password: user.password
            }, {
                withCredentials: true
            })
            .then(response => {
                // Supposons que vous avez reçu une réponse axios contenant les en-têtes
                const headers = response.headers;
                console.log('cookie', response.headers);

                // Extraction du cookie de la réponse
                const setCookieHeader = headers['set-cookie']; // 'set-cookie' contient une liste de cookies

                // Si 'set-cookie' est défini dans les en-têtes
                if (setCookieHeader) {
                    // Boucle sur tous les cookies dans 'set-cookie' pour extraire le cookie JWT
                    setCookieHeader.forEach(cookie => {
                        const jwtCookie = cookie.split(';')[0]; // Extrait le nom et la valeur du cookie
                        const [name, value] = jwtCookie.split('=');
                        if (name.trim() === 'quizGame') { // Remplacez 'nom_du_cookie' par le nom de votre cookie contenant le JWT
                            const jwtToken = value.trim(); // Extrait la valeur du cookie (le JWT)
                            // Stockez le token JWT dans le localStorage
                            localStorage.setItem('jwtToken', jwtToken);
                            console.log('Token JWT stocké dans le localStorage avec succès:', jwtToken);
                        }
                    });
                } else {
                    console.error('Aucun cookie de réponse trouvé.');
                    localStorage.setItem('user', JSON.stringify(response.data))
                    let test = localStorage.getItem('user')
                    console.log(JSON.parse(test));
                }

                // Stockage de l'utilisateur sous forme de chaîne JSON
                if (response.data) {
                    localStorage.setItem('user', response.data);

                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');

    }

    register(user) {
        return axios.post(API_URL + 'auth/signup', {
            username: user.username,
            email: user.email,
            password: user.password
        }, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }

    authHeader() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user && user.accessToken) {
            return { Authorization: 'Bearer ' + user.accessToken };
        } else {
            return {};
        }
    }

    getProtectedData() {
        return axios.get(API_URL + 'protected/resource', { headers: this.authHeader() });
    }

    setCookie(response) {
        const headers = response.headers;
        console.log(headers);
        const setCookieHeader = headers['Set-Cookie'];

        if (setCookieHeader) {
            setCookieHeader.forEach(cookie => {
                const jwtCookie = cookie.split(';')[0];
                const [name, value] = jwtCookie.split('=');
                if (name.trim() === 'quizGame') {
                    const jwtToken = value.trim();
                    localStorage.setItem('jwtToken', jwtToken);
                    console.log('Token JWT stocké dans le localStorage avec succès:', jwtToken);
                }
            });
        } else {
            console.error('Aucun cookie de réponse trouvé.');
        }
    }
}

export default new AuthService();