<template>
  <div class="quizzes">
    <h2>{{ quiz.title }}</h2>
    <div>
      <h3>{{ quiz.questions.length }} Questions</h3>
      
      <QuestionComponent/>
      
    </div>
  </div>
</template>

<script>
import QuestionComponent from '../question/QuestionComponent.vue';


export default {
  data() {
    return {
      quiz: {
        title: "",
        questions: [],
      },
      showResult: true,
    };
  },
  mounted() {
      const storedObject = localStorage.getItem('quizPlay');
      if (storedObject) {
          
        this.quiz = JSON.parse(storedObject);
        
      }
  },
  components: {
    QuestionComponent
  },
  methods: {
    getShuffledChoices(choices) {
      let shuffled = choices.slice(); // Copie du tableau original
      for (let i = shuffled.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
      }
      return shuffled;
    }
  }
};
</script>

<style>
</style>
