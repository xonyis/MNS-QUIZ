<template>
    <div class="col-12">
        <Field name="username" placeholder=" " type="text" class="input" />
        <span class="placeholder">Username</span>
    </div>
</template>
  
  <script>
  import { Form, Field, ErrorMessage } from "vee-validate";
  import * as yup from "yup";
  
  export default {
    name: "Signup",
    components: {
      Form,
      Field,
      ErrorMessage,
    },
    data() {
      const schema = yup.object().shape({
        username: yup.string().required("Username is required!"),
        password: yup.string().required("Password is required!"),
      });
  
      return {
        loading: false,
        message: "",
        schema,
      };
    },
    computed: {
      loggedIn() {
        return this.$store.state.auth.status.loggedIn;
      },
    },
    methods: {
      handleLogin(user) {
        this.loading = true;
        
        this.$store.dispatch("auth/login", user).then(
          () => {
            this.$router.push("/profile");
          },
          (error) => {
            this.loading = false;
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
          }
        );
      },
    },
  };
  </script>
  
  <style scoped>
  
  main {
    background-image: url('https://media.routard.com/image/60/2/metz.1560602.jpg');
    
    height: 100vh;
    width: 100vw;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
    
    
}

.card {
    background: rgba(245, 245, 245, 0.667);
    width: 32.5vw;
    height: 70vh;
    border-radius: 5%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 5em;
}
.card img {
    width: 75%;
}

.logo-mns {
    display: flex;
    flex-direction: column;
    align-items: center;
    color: grey;
    font-family: var(--font);
}

.logo-mns p {
  margin-top: .5em;
  font-family: var(--font);
}
.form-connection {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 30vw;
    margin-top: 2em;
}

form {
  font-family: var(--font);
    height: max-content;
    width: 100%;
    gap: 20px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}

.input-container {
 width: 100%;
 height: 25vh;
 display: flex;
 flex-direction: column;
 align-items: center;
 justify-content: space-around;
}

.input-wrapper {
  position: relative;
  width: 60%;
}
.input {
  height: 50px;
  font-size: 16px;
  padding: 11px 15px;
  border-radius: 4px;
  border: 1px solid #CBD8E3;
  width: 100%;
  outline: none;
  box-sizing: border-box;
  background-color: #ffffff8f;
  
}
.placeholder {
  pointer-events: none;
  position: absolute;
  font-weight: 400;
  top: 12px;
  left: 8px;
  padding: 0 8px;
  background-color: rgba(245, 245, 245, 0);
  border-radius: 5px;
  transition: transform 250ms cubic-bezier(0.4,0,0.2,1), opacity 250ms cubic-bezier(0.4,0,0.2,1);
  color: grey;
}

.input:not(:placeholder-shown).input:not(:focus) + .placeholder {
  transform: scale(.75) translateY(-45px) translateX(-15%);
  color: var(--orange-mns);
  transition: .2s ease;
}
.input:not(:placeholder-shown).input:not(:focus) {
  border-color: var(--orange-mns);
  transition: .2s ease;
}
.input:focus {
  
  border-color: var(--orange-mns);
} 

.input:focus + .placeholder {
  transform: scale(.75) translateY(-45px) translateX(-15%);
  color: var(--orange-mns);
  border-color: var(--orange-mns);
  transition: .2s ease;
} 

.input:invalid:not(:placeholder-shown){
  transition: .2s ease;
  border-color:#F52C5C;
}

.input:invalid:not(:placeholder-shown) + .placeholder{
  transition: .2s ease;
  color:#F52C5C;
}
.input:invalid:not(:placeholder-shown).input:not(:focus) + .placeholder {
  transform: scale(.75) translateY(-36px) translateX(-15%);
  color: #F52C5C;
  transition: .2s ease;
}
/* input {
    font-size: 1.2em;
    width: 60%;
    font-family: var(--font);
    font-weight: bold;
    border: none;
    border-radius: 7.5px;
    padding: .5rem 1.5rem;
} */

  button {
      padding: .7rem 1.5rem;
      border: none;
      border-radius: 7.5px;
      background-color: var(--orange-mns);
      font-size: 1.5em;
      font-family: var(--font);
      font-weight: bold;
      color: whitesmoke;
  }

  button:hover {
      padding:.7rem 2rem;
      transition: 0.4s;

  }

  .btnSignIn {
    color: var(--orange-mns);
    font-weight: 600;
    font-size: 1.2em;
  }
  
  .error-feedback {
    color: red;
  }
  </style>