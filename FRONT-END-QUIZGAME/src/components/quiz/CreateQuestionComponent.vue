<template>
    <main class="border">
        <input type="text" placeholder="question" class="col-4" v-model="newQuestion.text"> <br>
            <input type="text" placeholder="type" class="col-4" v-model="newQuestion.type"> 

            <div class="choices-form col-6 mt-3 border">
                <input type="text" placeholder="choix 1" class="col-6" v-model="newChoice.text"> 
                <input type="checkbox" v-model="newChoice.isCorrect">
                <button class=" btn btn-primary" @click="addChoice">ajouter</button>
            </div>
        <button  class="mt-3 btn btn-primary">Enregister </button>    
    </main>
</template>
<script>
export default {
    data(){
        return {
            newQuestion: {
            text: null,
            type: null,
            choices: []
        },
        newChoice: {
            text: null,
            isCorrect: false
        }
        }
    },
    methods: {
        addChoice() {
        if (this.newQuestion.choices.length < 4) {
            this.newQuestion.choices.push(this.newChoice)
        } else {
            console.log("il ne peut y avoir plus de 4 choix par questions ");
        }
        this.newChoice =  { text: null, isCorrect: false}
        console.log(this.newQuestion);
    }
    }
}
</script>