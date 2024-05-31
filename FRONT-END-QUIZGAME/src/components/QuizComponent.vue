<template>
    <div class="quizzes">
      <h2>Quizzes</h2>
      <div v-for="quiz in quizzes" :key="quiz.id">
        <h3>{{ quiz.title }}</h3>
        <div v-for="question in quiz.questions" :key="question.id">
          <p>{{ question.text }}</p>
          <template v-if="question.type === 'QCM'">
            <ul>
              <li v-for="choice in question.choices" :key="choice.id">{{ choice.text }}</li>
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
        quizzes: []
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
      }
    }
  };
  </script>
  
  <style>
  /* Ajoutez des styles CSS supplémentaires si nécessaire */
  </style>
  