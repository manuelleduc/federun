<template>
    <div>
        <div v-if="!isAuthenticated">Not logged in.</div>
        <div v-if="isAuthenticated">
            Logged in!
            <p>{{user}}</p>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: "UserInfo",
        data() {
            return {}
        },
        computed: {
            ...mapState({
                user: state => state.user,
            }),
            isAuthenticated: function () {
                return this.user !== null;
            }
        },
        mounted() {
            this.$store.dispatch("user")
        }
    }
</script>

<style scoped>

</style>