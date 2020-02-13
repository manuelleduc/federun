<template>
    <div>
        <h1>Login</h1>
        <p>Does not have an account? <router-link to="/signup">Sign Up</router-link></p>
        <form>
            <p><label>Username <input type="text"/></label></p>
            <p><label>Password <input type="password"/></label></p>
            <p><input type="button" value="Submit"/></p>
        </form>
    </div>
</template>

<script>
    export default {
        name: "Login"
    }
</script>

<style scoped>

</style>