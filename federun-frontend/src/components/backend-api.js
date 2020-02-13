import axios from 'axios'

const AXIOS = axios.create({
    baseURL: `/api`,
    timeout: 1000
});

export default {
    createUser(login, email, password) {
        return AXIOS.post('/user/', {
            username: login,
            email,
            password
        });
    },
    getUser(token) {
        return AXIOS.get('/user/', {
            headers: {
                'Authorization': `Token ${token}`
            }
        });
    }
}


