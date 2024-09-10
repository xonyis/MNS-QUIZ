<template>
    <NavBarComponent></NavBarComponent>
    <div class="p-3">
        <div class="card col-8 m-auto  p-2">
            <div class="col-12 p-2">
                <RouterLink class=" btn btn-primary col-4 float-end" to="/createQuiz">Créer un quiz</RouterLink>
            </div>
            <TableComponent :columns="columns" :data="this.quizs" class="border">
                <template #actions="{ item }">
                    <button class="btn btn-primary" @click="editQuiz(item)"><i class="bi bi-pencil"></i> Edit</button>
                <button @click="deleteQuiz(item.id)" class="btn btn-danger">
                    <i class="bi bi-trash"></i> Delete
                </button>
                </template>
            </TableComponent>
        </div>
    </div>
</template>
<script>
import { RouterLink } from 'vue-router';

import NavBarComponent from '../components/NavBarComponent.vue'
import TableComponent from '@/components/TableComponent.vue';
import quizService from '../service/quizService.js'
import router from '@/router';
export default {
    data() {
        return {
            quizs:null,
            columns: [
              { key: 'id', label: 'ID' },
              { key: 'title', label: "Nom du quiz" },

              { key: 'actions', label: 'Actions' } // Include the actions column here

            ],
        }
    },
    mounted() {
        this.fetchQuizs()
    },
    methods: {
        async fetchQuizs() {
        try {
          const response = await quizService.getAll();
          this.quizs = response.data;
          console.log(this.quizs);
        } catch (error) {
          console.error('Erreur lors de la récupération des utilisateurs:', error);
          // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
        }
        },
        async deleteQuiz(quizId) {
            try {
              const response = await quizService.deleteQuiz(quizId);
              window.location.reload()
            } catch (error) {
              console.error('Erreur lors de la récupération des utilisateurs:', error);
              // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
            }
        },
        editQuiz(item) {
            router.push('editQuiz')
            console.log(item);
            localStorage.setItem('quizEdit', JSON.stringify(item));
            
        }
    },
    components: {
        NavBarComponent,
        TableComponent
    },
    
}
</script>