# Post Rocket

This project lets you generate cohesive and compelling Twitter narratives in seconds, using Spring Boot and OpenAI API, for effortless event promotion, product launches, and more.
Simply provide details about your event, product launch, or topic, and this web app crafts a compelling Twitter thread to boost your reach.

In the future, the project can also guide users, especially social media marketers, through the process of creating compelling and informative Facebook posts and relevant AI-generated visuals.


### Technical Details

#### Backend Development

The application leverages Spring MVC, a Model-View-Controller (MVC) framework within Spring Boot, to handle user interactions and manage the overall application flow.

The project integrates with the OpenAI GPT-3.5 Turbo model to generate tweets.
The Chat model analyzes the user's input about their event or product details and crafts an engaging Twitter narrative.

#### Frontend Development
The frontend is built using Spring MVC for handling user interactions and Thymeleaf for server-side templating.

#### Data Management

Spring Data JPA and MySQL to store generated Twitter threads and user data.

Spring Security is implemented to prioritize data security. It enforces authentication and authorization protocols, guaranteeing that only authorized users can access and manage their information.