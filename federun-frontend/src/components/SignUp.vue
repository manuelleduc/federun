<template>
    <div>
        <h1>Sign Up</h1>
        <p v-if="error">HAS ERRORS</p>
        <p>Already have an account?
            <router-link to="/login">Login</router-link>
        </p>
        <form @submit.prevent="callLogin()">
            <p><label>Login <input type="text" v-model="login"/></label></p>
            <p><label>Email <input type="email" v-model="email"/></label></p>
            <p><label>Password <input type="password" v-model="password"/></label></p>
            <p>
                <b-btn variant="success" type="submit">Submit</b-btn>
            </p>
        </form>
    </div>
</template>

<script>
    export default {
        name: "SignUp",
        data() {
            return {
                login: '',
                email: '',
                password: '',
                errors: []
            }
        },
        computed: {
            error: function () {
                return this.errors.length > 0
            }
        },
        methods: {
            callLogin() {
                this.errors = [];
                this.$store.dispatch("signup", {
                    login: this.login,
                    email: this.email,
                    password: this.password,
                }).then(() => {
                    this.$router.push('/')
                }).catch(error => {
                    this.errors.push(error)
                })
            }
        }
    }
</script>

<style scoped>

</style>