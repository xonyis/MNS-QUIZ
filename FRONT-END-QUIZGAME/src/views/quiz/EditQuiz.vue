<template>
    <NavBarComponent/>
    <div class="col-10 m-auto">
      <h2>Editer un questionnaire</h2>
      <form @submit.prevent="submitForm" class="card mt-5 col-8 m-auto">
        <!-- Titre du questionnaire -->
        <div class="card-header">
         <h4 class="mt-1 mb-2">Créer un quiz</h4>

        </div>
        <div class="card-body p-5">


          <div class="mb-3 ">
            <label for="title" class="form-label">Titre du quiz :</label>
            <input type="text" class="form-control" v-model="quiz.title" id="title" required />
          </div>

          <div class="mb-3 form-check">
            <label v-if="quiz.visibility" for="isPublic" class="form-check-label">Public</label>
            <label v-else  for="isPublic" class="form-check-label">Privé</label>
            <input type="checkbox" id="isPublic" class="form-check-input" v-model="quiz.visibility" /> 
          </div>

          <div class="mb-3 " v-if="!quiz.visibility">
            <label for="teamName" class="form-label">Nom de l'équipe :</label>
            <input type="text" class="form-control" v-model="teamName" id="teamName" required />
          </div>
        

          <!-- Questions -->
          <div v-for="(question, qIndex) in quiz.questions" :key="qIndex" class="question-block  card p-3">
            <h3>Question {{ qIndex + 1 }}</h3>
          
            <div class="mb-3 col-8">
              <label for="questionText" class="form-label">Texte de la question :</label>
              <input type="text" class="form-control" id="questionText" v-model="question.text" required />
            </div>
          
            <div class="mb-3  col-4 ">
              <label for="questionType"  class="form-label">Type de question :</label>
              <select v-model="question.type" class="form-select"  id="questionType">
                <option value="REGULAR">REGULAR</option>
              </select>
            </div>
          
            <!-- Choix de réponses -->
            <div v-for="(choice, cIndex) in question.choices" :key="cIndex" class="mb-3">
              <div class="d-flex justify-content-between mb-1">
                <label>Choix {{ cIndex + 1 }} : </label> 
                <i class="bi bi-dash-circle text-danger" @click="removeChoice(qIndex, cIndex)"></i>

              </div>


              <input type="text" class="form-control " v-model="choice.text" required />
            
              <div class="mb-3 mt-1 form-check">
                <input type="checkbox" id="isCorrect" class="form-check-input " v-model="choice.correct" /> 
                <label v-if="choice.correct" for="" class="form-check-label">Correct</label>
                <label v-else for="" class="form-check-label">Faux</label>

              </div>

            </div>


            <button type="button" class="btn btn-primary col-4" @click="addChoice(qIndex)"><i class="bi bi-plus-circle"></i> &nbsp; Ajouter un choix</button>

            <hr />
            <button type="button" class="btn btn-danger col-5" @click="removeQuestion(qIndex)"><i class="bi bi-dash-circle"></i> &nbsp; Supprimer la question</button>
          </div>
          <button type="button" class="btn btn-primary col-5" @click="addQuestion"><i class="bi bi-plus-circle"></i> &nbsp; Ajouter une question </button>
        
          <hr />
        
          <!-- Bouton soumettre -->
          <button type="submit" class="btn btn-primary" @click="updateQuizz">Soumettre le questionnaire</button>
        </div>
      </form>
    </div>
  </template>
  
  <script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import quizService from '../../service/quizService.js'
import router from '@/router/index.js';
import teamService from '@/service/teamService';

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
            { text: null, correct: false },
            { text: null, correct: false },
            { text: null, correct: false },
            { text: null, correct: false },
          ],
        });
      },
      removeQuestion(index) {
        this.quiz.questions.splice(index, 1);
      },
      addChoice(questionIndex) {
        this.quiz.questions[questionIndex].choices.push({
          text: null,
          correct: false,
        });
      },
      removeChoice(questionIndex, choiceIndex) {
        this.quiz.questions[questionIndex].choices.splice(choiceIndex, 1);
      },
      validateForm() {
        
        if (this.quiz.questions.length === 0) {
          alert("Il est nécessaire d'avoir au moins une question pour créer un quiz ")
          return false
        }
        if (!this.quiz.title) {
          alert("Il est nécessaire d'avoir un titre pour le quiz ")
          return false
        }


        
        for (let index = 0; index < this.quiz.questions.length; index++) {
          const element = this.quiz.questions[index];
          console.log(element);
           let hasCorrectAnswer = element.choices.some(obj => obj.correct);
            let allHaveText = element.choices.every(obj => obj.text);

            if (!hasCorrectAnswer) {
              console.log('Au moins un objet a la propriété isCorrect à true.');
              alert(`Il est nécessaire d'avoir au moins une réponse de vrai pour créer une question `)
              return false;

            } 

            if (!allHaveText) {
              alert(`Toutes les choix doivent avoir un texte`)
              return false;

            }

            if (!element.text) {
              alert(`Toutes les questions doivent avoir un texte`)
              return false;

            }
        }
        return true;
      },
      async updateQuizz() {
        if (!this.quiz.visibility) {
          try {
            const _response = await teamService.getByName(this.teamName)
            console.log(_response);
          } catch (error) {
            console.error('Erreur lors de la récupération de léquipe :', error);
            return alert("Erreur lors de la récupération de l'équipe ")
          }
        }


        this.submitted = this.validateForm();
        
        console.log("Quiz Object:", this.quiz);
        
        console.log(this.submitted);
        
        if (this.submitted) {
          try {
          const response = quizService.updateQuiz(this.quiz.id,JSON.stringify(this.quiz));
          
          console.log(response);
          router.push("gestionQuiz")

        } catch (error) {
          console.error('Erreur lors de la :', error);
          // Gérer l'erreur ici, par exemple afficher un message à l'utilisateur
          return error
        }
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
  