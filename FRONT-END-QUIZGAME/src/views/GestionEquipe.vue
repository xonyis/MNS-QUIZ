<template>
    <NavBarComponent></NavBarComponent>
    <div class="p-3">
        <div class="card col-7 p-1 m-auto">
            <button class=" btn btn-primary mb-3 col-3" @click="showModal('createTeamModal')">Créer une Équipe <i class="bi bi-plus-circle"></i></button>

            <TableComponent :columns="columns" :data="teams" class="border">
            <template #actions="{ item }">
                <button @click="openEditModal(item)" class="btn btn-primary">
                    <i class="bi bi-pencil"></i> Edit
                </button>
                <button @click="deleteTeam(item.id)" class="btn btn-danger">
                    <i class="bi bi-trash"></i> Delete
                </button>
            </template>
        </TableComponent>
        </div>
        <ModalComponent title="Ajouter une Équipe" modalId="createTeamModal" @confirm="createTeam(teamToAdd)">
            <div class="form-group">
              <label for="exampleInputEmail1">Nom de l'équipe :</label>
              <input type="email" class="form-control" id="exampleInputEmail1" v-model="teamToAdd" aria-describedby="emailHelp" placeholder="Nom de l'équipe">
            </div>
        </ModalComponent>
        <ModalComponent :title="selectedTeam.name" modalId="editTeamModal" :data="selectedTeam" @confirm="editTeam">
            <!-- Modal content goes here -->
            <EditTeam :data="selectedTeam"/>
        </ModalComponent>

        
    </div>
</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue'
import TableComponent from '../components/TableComponent.vue'
import ModalComponent from '../components/ModalComponent.vue'
import EditTeam from '../components/EditTeam.vue'
import teamService from '../service/teamService.js'
import bootstrapBundleMin from 'bootstrap/dist/js/bootstrap.bundle.min'
export default {
    data(){
        return {
            teams: null,
            teamToAdd: null,
            selectedTeam: {},
            columns: [
              { key: 'id', label: 'ID' },
              { key: 'name', label: "Nom de l'equipe" },
              { key: 'userCount', label: "Nombre de participants" },
              { key: 'actions', label: 'Actions' } // Include the actions column here
            ],
        }
    },
    mounted() {
        this.fetchTeams()
    },
    components : {
        NavBarComponent,
        TableComponent,
        ModalComponent,
        EditTeam
    },
    methods: {
        async fetchTeams() {
        try {
          const response = await teamService.getAll();
          this.teams = response.data;
          this.teams = response.data.map(team => ({
                        ...team,
                        userCount: team.users.length
                    }));
        } catch (error) {
          console.error('Erreur lors de la récupération des utilisateurs:', error);
          // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
        }
        },
        async createTeam() {
            try {
            console.log(this.teamToAdd);
              const response = await teamService.createTeam(this.teamToAdd);
              this.teamToAdd = null
              window.location.reload()
            } catch (error) {
              console.error('Erreur lors de la récupération des utilisateurs:', error);
              // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
            }
        },
        async deleteTeam(teamId) {
            try {
              const response = await teamService.deleteTeam(teamId);
              window.location.reload()
            } catch (error) {
              console.error('Erreur lors de la récupération des utilisateurs:', error);
              // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
            }
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
}
</script>