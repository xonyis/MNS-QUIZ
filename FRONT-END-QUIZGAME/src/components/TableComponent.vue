<template>
    <table class="table table-striped-columns">
      <thead>
        <tr >
          <th v-for="column in columns" :key="column.key">{{ column.label }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in data" :key="item.id">
          <td v-for="column in columns" :key="column.key" v-html="formatData(item, column)"></td>
        </tr>
      </tbody>
    </table>
  </template>
  
  <script>
  export default {
    name: 'TableComponent',
    props: {
      columns: {
        type: Array,
        required: true
      },
      data: {
        type: Array,
        required: true
      }
    },
    methods: {
      formatData(item, column) {
        if (column.formatter) {
          return column.formatter(item[column.key]);
        }
        return item[column.key];
      }
    }
  };
  </script>
  
  <style scoped>
  /* Ajoutez des styles supplémentaires ici si nécessaire */
  </style>
  