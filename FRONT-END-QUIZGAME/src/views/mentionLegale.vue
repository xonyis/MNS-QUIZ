<template>

    <div class="container-fluid">
        <NavBarComponent></NavBarComponent>
<div class=" d-flex align-items-center flex">
    <div class="card col-10 m-auto p-3">
            <h1>Mentions Légales</h1>
    <p>
      <strong>Éditeur du site :</strong><br>
      Nom de l'entreprise<br>
      Adresse de l'entreprise<br>
      Téléphone : 01 23 45 67 89<br>
      Email : contact@mns-quiz.fr
    </p>

    <p>
      <strong>Hébergement :</strong><br>
      Nom de l'hébergeur : OVH<br>
      Adresse de l'hébergeur : 2, rue Kellermann, 59100 Roubaix.<br>
    </p>

    <p>
      <strong>Responsabilité :</strong><br>
      L'éditeur ne saurait être tenu responsable en cas d'erreurs, d'inexactitudes ou d'omissions dans les informations fournies sur le site.
    </p>

    <p>
      <strong>Données personnelles :</strong><br>
      Les informations collectées sur ce site sont destinées exclusivement à l'usage interne de l'entreprise et ne seront pas partagées sans consentement préalable.
    </p>
        </div>
    </div>
</div>

</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';

export default {
    data(){
        return {
            
        }
    }, 
    components: {
        NavBarComponent
    }
}
</script>
<style scoped>
.flex {
    height: 100vh;
}
</style>