<template>
      <Login/>
  </template>
  
  <script>
  import UserService from '../auth/user.service.js';
import Login from './Login.vue';
  
  export default {
    name: "Home",
    data() {
      return {
        content: "",
      };
    },
    components: {
      Login
    },
    mounted() {
      UserService.getPublicContent().then(
        (response) => {
          this.content = response.data;
        },
        (error) => {
          this.content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
  };
  </script>