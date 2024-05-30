
<template>
  <div class="container">
    <div class="parent">
      <div class="div1">
        <div class="profil-pics" >
          <img v-if="user.img" :src="user.img" alt="profil-picture">
          <img v-else src="../assets/profils-picture.svg" alt="profil-picture">
        </div>
      </div>
      <div class="div2">
        
        <button @click="logout">Log-out</button>
        <p id="hello">Hey {{ user.username }} !</p>
      </div>
      <div class="div3">
        <p>Votre poste :</p>
        <ul>
            <li v-for="role in currentUser.user.roles" :key="role" style="list-style: none;">{{role}}</li>
          </ul>
      </div>
      <div class="div4">d </div>
    </div>
  </div>
</template>
  <script>
import authService from '@/auth/auth-service';
  export default {
    name: 'Profile',
    data(){
      return {

      }
    },
    computed: {
      currentUser() {
        return this.$store.state.auth;
      },
      user(){
        return this.currentUser.user
      },
      role() {
        return this.user.roles
      }
    },
    mounted() {
      if (!this.currentUser) {
        
        this.$router.push('/login');
      }
    },
    methods:{
        logout(){
          authService.logout()
          this.$store.dispatch("auth/logout").then(
          () => {
            this.$router.push("/login");
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
        checkAdminRole(){
          return this.role.includes('ROLE_ADMIN');
        }
    }
  };
  </script>
<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background: #BDB2FF;
  width: 100vw;
  height: 100vh;
  color: black;
}
.parent {
display: grid;
grid-template-columns: repeat(3, 1fr);
grid-template-rows: repeat(4, 1fr);
grid-column-gap: 15px;
grid-row-gap: 20px;
height: 85vh;
width: 50vw;
}

.parent div {
  background: #fff;
  border-radius: 10px;
  padding: 1em;
}

.div1 {
  grid-area: 1 / 1 / 2 / 2;
  
}
.div2 { 
  grid-area: 1 / 2 / 2 / 4;
  
}

.div2 button {
  float: right;
}

#hello {
  font-size: 1.5em;
  font-weight: 500;
}

.div3 { grid-area: 2 / 1 / 5 / 2; }
.div4 { grid-area: 2 / 2 / 5 / 4; }

.profil-pics {
  padding: 1.5em;
  border-radius: 17.5px;

  text-align: center;
  background: #fff;
  
}

.profil-pics img {
  width: 150px;
  height: 150px;
  border-radius: 100px;
  border: 3px solid;
}

.profil-pics img:hover {
  opacity: .7;
  transition: all .3s ease-in-out ;
}

</style>