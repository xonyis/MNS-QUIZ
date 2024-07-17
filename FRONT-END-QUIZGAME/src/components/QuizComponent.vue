<template>
    <div class="quizzes">
      <h2>Quizzes</h2>
      <div v-for="quiz in quizzes" :key="quiz.id">
        <h3>{{ quiz.title }}</h3>
        <div v-for="question in quiz.questions" :key="question.id">
          <p>{{ question.text }}</p>
          <template v-if="question.type === 'REGULAR'">
            <ul>
              <li v-for="choice in getShuffledChoices(question.choices)" :key="choice.id">
                <button  :class="{'btn btn-success': choice.correct, 'btn btn-danger': !choice.correct}" >{{ choice.text }}</button>
              </li>
            </ul>
          </template>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        quizzes: [],
        showResult: true,
      };
    },
    mounted() {
      this.fetchQuizzes();
    },
    methods: {
      async fetchQuizzes() {
        try {
          const response = await fetch('http://localhost:8080/api/quiz/all');
          if (!response.ok) {
            throw new Error('Failed to fetch quizzes');
          }
          const data = await response.json();
          console.log(data);
          this.quizzes = data;
        } catch (error) {
          console.error(error);
        }
      },
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
  /* Ajoutez des styles CSS supplémentaires si nécessaire */
  </style>
  