# Post Rocket

Generate cohesive and compelling Twitter narratives in seconds using [Spring AI](https://docs.spring.io/spring-ai/reference/index.html) and [OpenAI API](https://openai.com/api/) for effortless event promotion, product launches, and more.
Simply provide details about your event, product, or topic, and this web app crafts a compelling Twitter thread to boost your reach.

In the future, the project can also guide users, especially social media marketers, through the process of creating compelling and informative Facebook posts and relevant AI-generated visuals.

### Video Walkthrough


https://github.com/johnpaulfernandez/PostRocket/assets/25147160/dcbd1d3f-f92d-469b-ba02-c5aa082e62c3


### Technical Details

#### Backend Development

The application leverages Spring MVC, a Model-View-Controller (MVC) framework within Spring Boot, to handle user interactions and manage the overall application flow.

The project integrates with the OpenAI GPT-3.5 Turbo model to generate tweets.
The Chat model analyzes the user's input about their event or product details and crafts an engaging Twitter narrative.

#### Frontend Development
The frontend is built using Spring MVC for handling user interactions and Thymeleaf for server-side templating.

#### Data Management

Spring Data JPA and MySQL is used in this project to store generated Twitter threads and user data.

Lastly, Spring Security is implemented to enforce authentication and authorization protocols, guaranteeing that only authorized users can access and manage their information.


<ins>Database schema diagram</ins>

<img width="786" alt="DB Diagram" src="https://github.com/johnpaulfernandez/PostRocket/assets/25147160/7d338bb0-a86a-40a7-9478-534abd1ece74">

