<template>
    <div class="modal fade" tabindex="-1" :id="modalId">
      <div class="modal-dialog  modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ title }}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <slot :data="data"></slot>
          </div>
          <div v-if="footerDisplay" class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" @click="confirmAction">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import bootstrapBundleMin from 'bootstrap/dist/js/bootstrap.bundle.min'

  export default {
    name: 'ModalComponent',
    props: {
      title: {
        type: String,
        required: true
      },
      modalId: {
        type: String,
        required: true
      },
      data: {
        type: Object,
        default: () => ({})
      },
      footerTrue: {
        type: Boolean,
        default: true
      },
    },
    methods: {
      confirmAction() {
        this.$emit('confirm', this.data);
        this.closeModal();
      },
      closeModal() {
        const modalElement = document.getElementById(this.modalId);
        const modalInstance = bootstrapBundleMin.Modal.getInstance(modalElement);
        modalInstance.hide();
      }
    }
  };
  </script>
  