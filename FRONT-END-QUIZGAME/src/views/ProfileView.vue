
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
        <p>Niveau :</p><br><br>
        <p>Votre poste : {{ role }}</p>
      </div>
      <div class="div3 p-0">
        <div class="action-container ">
          <p class="m-0 fs-3" id="hello">Hey {{ user.username }} !</p>
          <div class="p-0" v-if="checkAdminRole()">
            <hr>
            <RouterLink v-if="checkAdminRole()" to="/gestionQuiz">Gestion des quizz</RouterLink>
            <RouterLink v-if="checkAdminRole()" to="/gestionUser">Gestion des utilisateurs</RouterLink>
            <RouterLink v-if="checkAdminRole()" to="/gestionEquipe">Gestion les équipes</RouterLink>
            <RouterLink v-if="checkAdminRole()">Gestion des succès</RouterLink>

          </div>
        </div>
      </div>
      <div class="div4">
        <p class="fs-4">Vos Équipes :</p>
          <EquipeComponent v-if="this.selectedTeam" :teams="this.selectedTeam"/>
      </div>
    </div>
  </div>

</template>
  <script>
import authService from '@/auth/auth-service';
import NavBarComponent from '../components/NavBarComponent.vue'
import TableComponent from '../components/TableComponent.vue'
import EquipeComponent from '../components/profile/EquipeComponent.vue'
import { RouterLink } from 'vue-router';
import ModalComponent from '../components/ModalComponent.vue'

import bootstrapBundleMin from 'bootstrap/dist/js/bootstrap.bundle.min'
import userService from '@/auth/user.service';

  export default {
    name: 'Profile',
    data(){
      return {
        test:null,
        selectedTeam: null,
        userTeam:[],
        columns: [
              { key: 'name', label: "Nom de l'equipe" },
              { key: 'userCount', label: "Nombre de participants" },
              { key: 'actions', label: 'Actions' } // Include the actions column here
            ],
      }
    },
    mounted() {
      if (!this.currentUser) {
        this.$router.push('/login');
      }
      this.getTeam()
    },
    components: {
      EquipeComponent,
      NavBarComponent,
      TableComponent,
      ModalComponent
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
      },
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
        async getTeam(){
          console.log(this.currentUser.user.id);
          let response = await userService.getTeam(this.currentUser.user.id)
          this.selectedTeam = response.data 
          console.log(this.selectedTeam);
        },
        showModal(modalId) {
          const modalElement = document.getElementById(modalId);
          const modalInstance = new bootstrapBundleMin.Modal(modalElement);
          modalInstance.show();
        },
        openEditModal(team) {
      this.selectedTeam = { ...team }; // Clone the team data
      this.showModal('editTeamModal');
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
  font-size: 1em;
  color: #fff;
  background: var(--orange-mns);
  padding: .3em .7em;
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
  height: max-content;
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

.action-container div {
  display: flex;
  flex-direction: column;
  gap: 1em;
}

.action-container a:hover {
  background: #00000000;
  color: black;
}

</style>