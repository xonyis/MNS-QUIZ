import AuthService from '../auth/auth-service';

let user = null;
try {
  const userString = localStorage.getItem('user');
  if (userString) {
    user = JSON.parse(userString);
  }
} catch (e) {
  console.error('Erreur de parsing JSON pour l\'utilisateur:', e);
  localStorage.removeItem('user'); // Suppression de la donnée invalide
  user = null;
}

const token = localStorage.getItem('token');

const initialState = user 
  ? { status: { loggedIn: true }, user, token }
  : { status: { loggedIn: false }, user: null, token: null };

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }, user) {
      return AuthService.login(user).then(
        data => {
            console.log(data);
          const user = data;
          

          localStorage.setItem('user', JSON.stringify(user));
          localStorage.setItem('token', token);
          
          commit('loginSuccess', { user, token });
          return Promise.resolve(user);
        },
        error => {
          commit('loginFailure');
          return Promise.reject(error);
        }
      );
    },
    logout({ commit }) {
      AuthService.logout();
      commit('logout');
    },
    register({ commit }, user) {
      return AuthService.register(user).then(
        response => {
          commit('registerSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('registerFailure');
          return Promise.reject(error);
        }
      );
    },
    storeToken({ commit }, token) {
      localStorage.setItem('token', token);
      commit('storeToken', token);
    }
  },
  mutations: {
    loginSuccess(state, { user, token }) {
      state.status.loggedIn = true;
      state.user = user;
      state.token = token;
    },
    loginFailure(state) {
      state.status.loggedIn = false;
      state.user = null;
      state.token = null;
    },
    logout(state) {
      state.status.loggedIn = false;
      state.user = null;
      state.token = null;
    },
    registerSuccess(state) {
      state.status.loggedIn = false;
    },
    registerFailure(state) {
      state.status.loggedIn = false;
    },
    storeToken(state, token) {
      state.token = token;
    }
  }
};
