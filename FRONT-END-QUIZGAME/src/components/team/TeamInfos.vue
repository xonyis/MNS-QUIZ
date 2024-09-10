<template>
    <div class="col-12 d-flex justify-content-between g-5">
        <div class="col-3">
            <TableComponent :columns="columns" :data="team.users" class="border" ></TableComponent>
        </div>
        <div class="col-6">
            <TableComponent :columns="quizColumns" :data="team.quizzes" class="border">
                <template #actions="{ item }" >
                    
                    <button class="btn btn-primary" data-bs-dismiss="modal" @click="playQuiz(item)"><i class="bi bi-pencil"></i> Participer </button>
                </template>
            </TableComponent>
        </div>
    </div>
</template>
<script>
import TableComponent from '../TableComponent.vue'
import router from '@/router';

export default {
    data() {
        return {
            columns: [
              { key: 'username', label: "Membres"},
            ],
            quizColumns: [
              { key: 'title', label: "Quiz de l'équipe"},
              { key: 'actions',},
            ],
            quizList: []
        }
    },
    props: {
        team: {
            type: Object,
            required: true,
        },
    },
    components: {
        TableComponent
    },
    methods: {
        playQuiz(item) {
            router.push('playQuiz')
            console.log(item);
            localStorage.setItem('quizPlay', JSON.stringify(item));
            
        }
    }

}
</script>