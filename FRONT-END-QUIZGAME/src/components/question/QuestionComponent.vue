<template>
    <div class="card col-5 m-auto">
       <div class="card-header">
        <div class="d-flex">

        </div>
        <p class=" p-0 m-0" >Question : {{ currentQuestionIndex +1 }} / {{ quiz.questions.length }}</p>
        <h3>{{ currentQuestion.text}}</h3>
       </div>
       <div v-if="!isFinish" class="card-body">
            <template v-if="currentQuestion.type === 'REGULAR'">
                <div class="parent-choices">
                    <div class="col-12 choices-container"  v-for="choice in currentQuestion.choices" :key="choice.id">
                        <div class="col-12 d-flex align-items-center justify-content-center" :class="'div'+choice.id">
                            <button v-if="this.role.includes('ROLE_ADMIN') " class="col-8" @click="changeQuestion(choice)"  :class="{'btn btn-success': choice.correct, 'btn btn-danger': !choice.correct}" >{{ choice.text }}</button>
                            <button v-else-if="showResult" class="col-8 " :class="{'btn btn-success': choice.correct, 'btn btn-danger': !choice.correct}" >{{ choice.text }}</button>
                            <button v-else class="col-8 btn btn-danger" @click="changeQuestion(choice)" >{{ choice.text }}</button>

                        </div>
                    </div>
                </div>
            
            </template>
       </div>
       <div v-else class="card-body">
            <h3 class="text-center mb-5 ">Vous avez terminé ce quiz <br> Bravoo !!! </h3>
            <h4 class="text-center mb-5 ">Votre Score : {{ score }}/ {{ quiz.questions.length }}</h4>
            <div class="col-12 d-flex justify-content-around mb-5">
                <router-link class="btn btn-primary"  to="/QuizView">Vers les Quiz Public</router-link>
                <button class="btn btn-primary" @click="reloadPage()"><i class="bi bi-arrow-clockwise"></i></button>
                <router-link class="btn btn-primary"  to="/home">Vers votre Profil</router-link>
            </div>
            
        </div>
      </div>
</template>
<script>
import router from '@/router';
import quizService from '@/service/quizService';


 export default {
    mounted() {
        const storedObject = localStorage.getItem('quizPlay');
        if (storedObject) {
            
          this.quiz = JSON.parse(storedObject);
            this.currentQuestion = this.quiz.questions[this.currentQuestionIndex]
        }
        this.showResult = this.role.includes('ROLE_ADMIN')
    },
    watch: {
        currentQuestion(){
            this.currentQuestion.choices = this.getShuffledChoices(this.currentQuestion.choices)
        }
    },
    data() {
      return {
        score: 0,
        isFinish: false,
        currentQuestionIndex: 0,
        currentQuestion: {},
        quiz: {
            id: null,
          title: "",
          questions: [],
        },
        
        showResult: false,

      };
    },
    
    methods: {
      reloadPage() {
      window.location.reload();
      },
      getShuffledChoices(choices) {
        let shuffled = choices.slice(); // Copie du tableau original
        for (let i = shuffled.length - 1; i > 0; i--) {
          const j = Math.floor(Math.random() * (i + 1));
          [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
        }
        return shuffled;
      },
      changeQuestion(choice) {
        
        if (choice.correct) {
            console.log("Bonne réponse !");
            if (this.score != this.quiz.questions.length) {
                this.score++
            }
        } else {
            console.log("Mauvaise réponse !");
        }

        if (this.role.includes('ROLE_ADMIN') === false) {
            this.showResult = true; 

            // Après 2 secondes, passer à la question suivante
            setTimeout(() => {
                this.showResult = false; // Masquer le résultat après 2 secondes
                this.currentQuestionIndex++; // Passer à la question suivante
                

                if (this.currentQuestionIndex == this.quiz.questions.length ) {
                    this.setScore()
                    console.log(this.score);
                    
                    return this.isFinish = true
                } else {

                    this.currentQuestion = this.quiz.questions[this.currentQuestionIndex]    
                    
                    console.log(this.currentQuestionIndex);

                    return this.currentQuestionIndex

                }
            }, 1500);
            
            
        } else {
            if (this.currentQuestionIndex === this.quiz.questions.length - 1) {
                this.setScore()
                return this.isFinish = true
            } else {
                
                this.currentQuestion = this.quiz.questions[this.currentQuestionIndex+1]    
                this.currentQuestionIndex++
                console.log(this.currentQuestionIndex);
                
                return this.currentQuestionIndex

            }
        }     
      },
        setScore() {
            this._score = (this.score / this.quiz.questions.length ) * 100;

            quizService.updateScore(this.user.id, this.quiz.id, this._score + "%")
        },
    },
    computed: {
      currentUser() {
        return this.$store.state.auth;
      },
      user(){
        return this.currentUser.user
      },
      role() {
        return this.user.roles
      },
    },
  };
</script>
<style>
.choices-container {
    height: 5em;
    display: flex;
    align-items: center;
    
    justify-content: center;
}

.parent-choices {
display: grid;
grid-template-columns: repeat(2, 1fr);
grid-template-rows: repeat(2, 1fr);
grid-column-gap: 0px;
grid-row-gap: 0px;
}

.div1 { grid-area: 1 / 1 / 2 / 2; }
.div2 { grid-area: 1 / 2 / 2 / 3; }
.div3 { grid-area: 2 / 1 / 3 / 2; }
.div4 { grid-area: 2 / 2 / 3 / 3; }
</style>