# QuizApp
Welcome to the Quiz Master Spring Boot project. This application is designed to streamline the process of creating, managing, and conducting quizzes. With its robust endpoints, users can create, update, read, and delete questions, organize them into categories, generate quizzes with random questions, and submit their quiz answers. Quiz Master offers a seamless experience for quiz creators and participants alike, ensuring an engaging and efficient quiz-taking environment.

## Getting Started

1. **Clone the repository**
   ```shell
   git clone https://github.com/KARMOUNI/spring-app.git
   ```
   Alternatively, you can download the project as a ZIP file and extract it.

2. **Docker & Docker Compose**
The project uses Docker and Docker Compose to simplify the deployment and execution of services. This ensures a consistent and reproducible configuration of the application in containers.
   ```shell
   docker-compose up -d --build
   ``` 
3. .**Or Import Project**
   Open your favorite IDE (e.g Intellij IDEA, STS, VSCode) and import the project as a Maven project.

4. **Run the Application**
   Run the Spring Boot application using your IDE.The application will start on http://localhost:8080

## Endpoints

The QuizApp project provides the following endpoints:

### Question Endpoints

- `@PostMapping("/new")`: POST a question.
- `@GetMapping("/api-question/{questionId}")`: Get a question by ID.
- `@GetMapping("/api-question/all")`: Get a list of all questions.
- `@GetMapping("/api-question/category/{category}")`: Get questions by category.
- `@PutMapping("/api-question/{questionId}")`: Create a new question.
- `@DeleteMapping("/api-question/{questionId}")`: Delete a question by ID.

### Quiz Endpoints

- `@GetMapping("/all")`: Get all quiz.
- `@GetMapping("/{quizId}")`: Get a Quiz by id.
- `@PostMapping("/new")`: POST a Quiz.

Optional:
- `@GetMapping("/questionofquiz/{quizId}")`: Get Question by id quiz.
- `@PostMapping("/{quizName}/{category}/{noOfQuestions}")`: POST.
- `@PostMapping("/submit/{quizId}")`: Updat Quiz.

## Usage

To interact with the QuizApp, you can use tools like [Postman, Swwager](https://www.postman.com/)

