<template>
    <div>
        <button class="btn btn-primary float-end mb-3" @click="showModal('addParticipant')"><i class="bi bi-plus-circle"></i> Ajouter un participant</button>
        <TableComponent v-if="data.userCount>0" :columns="columns" :data="data.users" class="border">
            <template #actions="{ item }">
                <div class="d-flex justify-content-center">
                    <button @click="removeUser(item)" class="btn btn-danger" data-bs-dismiss="modal">
                    <i class="bi bi-trash"></i> Delete
                    </button> 
                </div>               
            </template>
        </TableComponent>

        <ModalComponent title="Ajouter un Participant" modalId="addParticipant" >
            <div class="form-group">
              <label for="exampleInputEmail1">Nom d'utilisateur :</label>
              <input type="email" class="form-control" id="exampleInputEmail1" v-model="userToAdd" aria-describedby="emailHelp" placeholder="Nom d'utilisateur">
            </div>
            <div>
                <button @click="addUser(this.userToAdd)" class="btn btn-primary mt-3 float-end">Ajouter un utilisateur</button>
            </div>
        </ModalComponent> 
    </div>
</template>
<script>

import TableComponent from '../components/TableComponent.vue'
import teamService from '../service/teamService.js'
import ModalComponent from '../components/ModalComponent.vue'
import bootstrapBundleMin from 'bootstrap/dist/js/bootstrap.bundle.min'
import InputComponent from './input/InputComponent.vue'

export default {
    data() {
        return {
            userToAdd:null,
            columns: [
              { key: 'username', label: "Nom" ,width: '350px' },
              { key: 'actions', label: 'Actions', width: '70px'}
            ],
        }
    },
    components: {
        TableComponent,
        ModalComponent,
        InputComponent
    },
    props: {
        data: {
            type: Object,
            default: () => ({})
        }
    },
    methods: {
        showModal(modalId) {
          const modalElement = document.getElementById(modalId);
          const modalInstance = new bootstrapBundleMin.Modal(modalElement);
          modalInstance.show();
        },
        closeModal(val){
            console.log(val);
        },
        async addUser() {
            try {
              const response = await teamService.addUserToTeam(this.data.id, this.userToAdd);
              this.userToAdd = null
              window.location.reload()
            } catch (error) {
              console.error('Erreur lors de la récupération des utilisateurs:', error);
              // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
            }
        },
        async removeUser(item){
            try {
              const response = await teamService.removeUserFromTeam(this.data.id, item.id);
                window.location.reload()
            } catch (error) {
              console.error('Erreur lors de la récupération des utilisateurs:', error);
              // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
            }
        }
    }

}
</script>
<style scoped>


</style>