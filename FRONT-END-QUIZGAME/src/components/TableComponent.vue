<template>
  <table class="table table-striped-columns">
      <thead>
          <tr>
              <th v-for="column in displayColumns" :key="column.key" :style="getColumnStyle(column)">{{ column.label }}</th>
              <th v-if="hasActions">Actions</th>
          </tr>
      </thead>
      <tbody>
          <tr v-for="item in data" :key="item.id">
              <td v-for="column in displayColumns" :key="column.key" v-html="formatData(item, column)" :style="getColumnStyle(column)"></td>
              <td v-if="hasActions">
            
                  <slot name="actions" :item="item"></slot>
              </td>
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
  computed: {
      displayColumns() {
          return this.columns.filter(column => column.key !== 'actions');
      },
      hasActions() {
          return this.columns.some(column => column.key === 'actions');
      }
  },
  methods: {
      formatData(item, column) {
          if (column.formatter) {
              return column.formatter(item[column.key]);
          }
          return item[column.key];
      },
      getColumnStyle(column) {
            return column.width ? { width: column.width } : {};
        }
  }
};
</script>

<style scoped>
/* Add any additional styles if needed */
</style>
