<template>
    <div class="container">
      <header class="jumbotron">
        <h3>{{ content }}</h3>
      </header>
    </div>
  </template>
  
  <script>
  import UserService from "../auth/user.service";
  
  export default {
    name: "User",
    data() {
      return {
        content: "",
      };
    },
    mounted() {
      UserService.getUserBoard().then(
        (response) => {
          this.content = response.data;
          console.log(this.content);
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