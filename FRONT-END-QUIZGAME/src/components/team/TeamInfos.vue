<template>
    <div class="col-5">
        <TableComponent :columns="columns" :data="team.users" class="border"></TableComponent>
    </div>
</template>
<script>
import TableComponent from '../TableComponent.vue'

export default {
    data() {
        return {
            columns: [
              { key: 'username', label: "Membres de l'équipe"},
            ],
        }
    },
    props: {
        team: {
            type: Array,
            required: true,
        },
    },
    components: {
        TableComponent
    }
}
</script>