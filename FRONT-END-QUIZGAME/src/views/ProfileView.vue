
<template>
  <NavBarComponent></NavBarComponent>
  <div class="container">
    <div class="parent">
      <div class="div1">
        <div class="profil-pics">
          <img v-if="user.img" :src="user.img" alt="profil-picture">
          <img v-else src="../assets/profils-picture.svg" alt="profil-picture">
        </div>
      </div>
      <div class="div2">
        
        <button @click="logout">Log-out</button>
        <button @click="testo()">test</button>
        <p>Niveau :</p><br><br>
        <p>Votre poste : {{ role[0] }}</p>
      </div>
      <div class="div3 p-0">
        <div class="action-container ">
          <p class="m-0 fs-3" id="hello">Hey {{ user.username }} !</p>
          <hr>
          <RouterLink v-if="checkAdminRole()">Gestion des quizz</RouterLink>
          <RouterLink v-if="checkAdminRole()" to="/gestionUser">Gestion des utilisateurs</RouterLink>
          <RouterLink v-if="checkAdminRole()">Créer un quizz</RouterLink>
          <RouterLink v-if="checkAdminRole()" to="/gestionEquipe">Gestion les équipes</RouterLink>
          <RouterLink v-if="checkAdminRole()">Gestion des succès</RouterLink>

        </div>
      </div>
      <div class="div4">
        <p>Vos Équipes :</p><br>
        afficher les équipe de l'user permettre d'accèder au quizz de la team, nbr de quizz, nbr de prsn dans l'equipe
      </div>
    </div>
  </div>
</template>
  <script>
import authService from '@/auth/auth-service';
import NavBarComponent from '../components/NavBarComponent.vue'
import { RouterLink } from 'vue-router';
  export default {
    name: 'Profile',
    data(){
      return {
        test:null
      }
    },
    components: {
      NavBarComponent
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
        },
        testo(){
          console.log(this.currentUser.user.roles);
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
  font-size: 1.2em;
  color: #fff;
  background: var(--orange-mns);
  padding: .3em 1em;
  border-radius: 7px;
  font-weight: 600;
  border: 2px solid var(--orange-mns);
  transition: all .5s ease-in-out;
}

.div2 button:hover {
  background: #00000000;
  color: black;
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
  transition: all .3s ease-in-out ;
}

.profil-pics img:hover {
  opacity: .7;

}


.action-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  text-align: center;
}

.action-container a{
background: var(--orange-mns);
border: 2px solid var(--orange-mns);
color: white;
padding:  .2em; 
font-weight: 600;
font-size: .8em;
border-radius: 5px;
text-align: center;
text-decoration: none;
transition: all .3s ease-in-out ;

}

.action-container a:hover {
  background: #00000000;
  color: black;
}

</style>