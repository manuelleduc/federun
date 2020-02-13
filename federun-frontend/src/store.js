import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        token: null,
        user: null,
    },
    mutations: {
        signup_success(state, payload) {
            state.token = payload.token;
        },
        user_success(state, payload) {
            state.user = payload.user
        },
        user_error(state) {
            state.user = null;
        }
    },
    actions: {
        signup({commit}, {login, email, password}) {
            return new Promise((resolve, reject) => {
                api.createUser(login, email, password)
                    .then(response => {
                        if (response.status === 201) {
                            console.log(response);
                            commit('signup_success', {
                                token: response.data.token,
                            })
                        }
                        resolve(response)
                    })
                    .catch(error => {
                        reject('Sign Up Error')
                    })
            })
        },
        user({commit}) {
            return new Promise((resolve, reject) => {
                api.getUser(this.state.token)
                    .then(response => {
                        if (response.status === 200) {
                            commit('user_success', {
                                user: response.data
                            })
                        }
                        resolve(response)
                    }).catch(error => {
                    commit('user_error');
                    reject('Get User Error')
                })
            })
        }
    },
    getters: {}
})