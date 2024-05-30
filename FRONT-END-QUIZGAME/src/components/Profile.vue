
<template>
    <div class="container">
      <div class="card-wrapper">
      <div class="card-container">
        <div class="profil-pics-container" >
          <div class="profil-pics">
            <img v-if="user.img" :src="user.img" alt="profil-picture">
            <img v-else src="../assets/profils-picture.svg" alt="profil-picture">
          </div>
        </div>
        
      
        <div class="info-container">
          <button @click="test()" style="float: right;">Log-out</button><br>
          <p class="username">
            <strong>Hey </strong>
            {{ user.username }} !
          </p>
          <p>
            <strong>Email :</strong>
            {{ user.email}}
          </p>
          <strong v-if="user.roles">Votre poste :</strong>
          <ul>
            <li v-for="role in currentUser.user.roles" :key="role" style="list-style: none;">{{role}}</li>
          </ul>
          
        </div>
        
      </div>
      <button v-if="user" @click="testeur" >Créer un quizz</button>
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
      }
    },
    mounted() {
      if (!this.currentUser) {
        
        this.$router.push('/login');
      }
    },
    methods:{
        test(){
          authService.logout()

          this.$router.push('/');
        
        },
        
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
}

.card-wrapper {
  padding: 3em;
  border-radius: 17.5px;
  color: #000;
  width: 50vw;
  height: 80vh;
  background: #fff;
  
}

.card-container {
  display: flex;
  justify-content: space-between;
}

.profil-pics {
  width: 150px;
  height: 150px;
  text-align: center;
}

.profil-pics:hover {
  opacity: .7;
  transition: all .3s ease-in-out ;
}

.profil-pics img {
  height: 100%; 
  width: 100%;
  border-radius: 100px;
}

.info-container {
  font-size: 1em;
  width: 60%;
  padding: 1em;
}



.username {
  font-size: 1.5em;
}
</style>