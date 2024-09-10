<template>
    <NavBarComponent/>
    <div class="col-10 m-auto">
      <h2>Editer un questionnaire</h2>
      <form @submit.prevent="submitForm">
        <!-- Titre du questionnaire -->
        <div>
          <label for="title">Titre du questionnaire :</label>
          {{ this.quiz.title }}
          <input type="text" v-model="this.quiz.title" id="title" required />
        </div>
  
        <!-- Questions -->
        <div v-for="(question, qIndex) in quiz.questions" :key="qIndex" class="question-block">
          <h3>Question {{ qIndex + 1 }}</h3>
  
          <div>
            <label for="questionText">Texte de la question :</label>
            <input type="text" v-model="question.text" required />
          </div>
  
          <div>
            <label for="questionType">Type de question :</label>
            <select v-model="question.type">
              <option value="REGULAR">REGULAR</option>
            </select>
          </div>
  
          <!-- Choix de réponses -->
          <div v-for="(choice, cIndex) in question.choices" :key="cIndex">
            <label>Choix {{ cIndex + 1 }} :</label>
            <input type="text" v-model="choice.text" required />
            <label>
              <input type="checkbox" v-model="choice.correct" /> Correct ?
            </label>
            <button type="button" @click="removeChoice(qIndex, cIndex)">Supprimer choix</button>
          </div>
          <button type="button" @click="addChoice(qIndex)">Ajouter un choix</button>
  
          <hr />
          <button type="button" @click="removeQuestion(qIndex)">Supprimer question</button>
        </div>
        <button type="button" @click="addQuestion">Ajouter une question</button>
  
        <hr />
  
        <!-- Bouton soumettre -->
        <button type="submit" @click="updateQuizz">Soumettre le questionnaire</button>
      </form>
  
      
  
      <div v-if="submitted">
        <h2>Objet généré :</h2>
      </div>
    </div>
  </template>
  
  <script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import quizService from '../../service/quizService.js'
import router from '@/router/index.js';


  export default {
    data() {
      return {
        quiz: {
          title: "",
          questions: [],
        },
        submitted: false,
      };
    },
    mounted() {
        const storedObject = localStorage.getItem('quizEdit');
        if (storedObject) {
            
          this.quiz = JSON.parse(storedObject);
          
        }
    },
    
    components: {
        NavBarComponent
    },
    methods: {
      addQuestion() {
        this.quiz.questions.push({
          text: "",
          type: "REGULAR",
          choices: [
            { text: "", correct: false },
            { text: "", correct: false },
            { text: "", correct: false },
            { text: "", correct: false },
          ],
        });
      },
      log(){
        console.log(this.quizId);
      },
      removeQuestion(index) {
        this.quiz.questions.splice(index, 1);
      },
      addChoice(questionIndex) {
        this.quiz.questions[questionIndex].choices.push({
          text: "",
          correct: false,
        });
      },
      removeChoice(questionIndex, choiceIndex) {
        this.quiz.questions[questionIndex].choices.splice(choiceIndex, 1);
      },
      async updateQuizz() {
        this.submitted = true;
        console.log("Quiz Object:", this.quiz);
        // Ici, vous pouvez envoyer les données au serveur ou faire autre chose avec l'objet quiz
        
        try {
          const response = await quizService.updateQuiz(this.quiz.id,JSON.stringify(this.quiz));
          
          console.log(response);
          router.push("gestionQuiz")
        } catch (error) {
          console.error('Erreur lors de la :', error);
          // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
        }
      },
    },
  };
  </script>
  
  <style>
  .question-block {
    margin-bottom: 20px;
  }
  </style>
  