<template>
    <main>
        <div class="row">
            <div class="col-10 mt-5 m-auto card p-0">
                <div class="card-header">
                    Tableau des utilisateurs
                </div>
                <TableComponent :columns="columns" :data="users" />

                
            </div>
            <QuizComponent/>
        </div>

    </main>
</template>
<script>
import userService from '@/auth/user.service';
import TableComponent from '../components/TableComponent.vue'
import QuizComponent from '../components/QuizComponent.vue'
export default {
    data(){
        return {
            users: null,
            columns: [
              { key: 'id', label: 'ID' },
              { key: 'username', label: 'Username' },
              { key: 'email', label: 'Email' },
              { key: 'role', label: 'Role'},
            ],
        }
    },
    mounted() {
        this.fetchUsers()
    },
    components : {
        TableComponent,
        QuizComponent
    },
    methods: {
        async test() {
      try {
        const response = await userService.getAdminBoard();
        console.log(response);
      } catch (error) {
        console.error('Erreur lors de la récupération des utilisateurs:', error);
        // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
      }
    },
        async fetchUsers() {
      try {
        const response = await userService.getAllUser();
        this.users = response.data;
        console.log(this.users);
      } catch (error) {
        console.error('Erreur lors de la récupération des utilisateurs:', error);
        // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
      }
    }
    }
}
</script>
<style scoped>
main {
    background: #BDB2FF;
    height: 100vh;
    width: 100vw;
}
</style>