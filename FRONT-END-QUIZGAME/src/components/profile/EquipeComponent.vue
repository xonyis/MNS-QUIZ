<template>
    <div class="p-0">
        <TableComponent :columns="columns" :data="teams" class="border">
            <template #actions="{ item }">
                <button @click="openEditModal(item)" class="btn btn-primary ">
                    <i class="bi bi-search"></i>
                </button>
            </template>
        </TableComponent>
        <ModalComponent :title="selectedTeam.name" modalId="editTeamModal" :data="selectedTeam" @confirm="editTeam">
            <!-- Modal content goes here -->
            <TeamInfos :team="selectedTeam"/>
        </ModalComponent>
    </div>
</template>
<script>
import EditTeam from '../EditTeam.vue'
import TableComponent from '../TableComponent.vue'
import ModalComponent from '../ModalComponent.vue'
import TeamInfos from '../team/TeamInfos.vue'
import bootstrapBundleMin from 'bootstrap/dist/js/bootstrap.bundle.min'

export default {
    data(){
        return {
            selectedTeam:{name:null},
            userTeam:null,
            columns: [
              { key: 'name', label: "Nom de l'equipe",   width: '80%', },
              { key: 'actions'},
            ],
        }
    },
    props: {
        teams: {
            type: Array,
            required: true,
        },
    },
    components: {
      TableComponent,
      ModalComponent,
      EditTeam,
      TeamInfos
    },
    mounted() {
        console.log(this.teams);
    },
    methods: {
        openEditModal(team) {
            this.selectedTeam = { ...team,
                userCount: team.users.length };
                console.log(this.selectedTeam); // Clone the team data
            this.showModal('editTeamModal');
        },
        showModal(modalId) {
          const modalElement = document.getElementById(modalId);
          const modalInstance = new bootstrapBundleMin.Modal(modalElement);
          modalInstance.show();
        },
    },
    
}
</script>