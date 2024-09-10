<template>
    <NavBarComponent></NavBarComponent>
    <div class="p-3">
        <div class="card col-8 m-auto  p-2">
            <TableComponent :columns="columns" :data="this.quizs" class="border">
                <template #actions="{ item }">
                    <button class="btn btn-primary" @click="playQuiz(item)"><i class="bi bi-pencil"></i> Participer </button>
                </template>
            </TableComponent>
        </div>
    </div>
</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import QuizComponent from '@/components/quiz/QuizComponent.vue';
import quizService from '@/service/quizService';
import TableComponent from '@/components/TableComponent.vue';
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
        console.log(this.quizs);
    },
    
    methods: {
        async fetchQuizs() {
            try {
              const response = await quizService.getPublic();
              this.quizs = response.data;
              console.log(this.quizs);
            } catch (error) {
              console.error('Erreur lors de la récupération des utilisateurs:', error);
              // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
            }
        },
        playQuiz(item) {
            router.push('playQuiz')
            console.log(item);
            localStorage.setItem('quizPlay', JSON.stringify(item));
            
        }
    },
    components: {
        NavBarComponent,
        QuizComponent,
        TableComponent
    }, 
}
</script>