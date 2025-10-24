
# Ttc

A Task Tracker client that helps you to organize your days!


## Prerequisites
- Java 17+
- Maven
- Docker
- PostgreSQL
## Stack used
- Languages
    >Java 21
- Framework
    >Springboot
    >>Spring Security
    >Hibernate
    >>Docker
- Tools
    >Maven
    >>MapStruct
- Database
    >PostgresSQL
# Project Structure

```text
.
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    └── main
        ├── java/
        │   └── com/
        │       └── ttc/
        │           └── app/
        │               ├── config/
        │               ├── controller/
        │               ├── converter/
        │               ├── dto/
        │               │   ├── task/
        │               │   ├── taskDefinition/
        │               │   └── user/
        │               ├── entity/
        │               ├── mapper/
        │               ├── repository/
        │               │   └── Specification/
        │               ├── security/
        │               │   └── beans/
        │               ├── service/
        │               └── util/
        │                   ├── constants/
        │                   └── filters/
        └── resources/
        
```
## Features

- Add your tasks 
- Personalize your private categories
- Modify the tasks previously added
- Search your tasks with specific filters
- Delete tasks and categories


## Run Locally

Clone the project

```bash
  git clone https://github.com/Alessandro-Stefan/Ttc-SpringBoot.git
```

Go to the project directory

```bash
  cd Ttc-SpringBoot
```

Install dependencies

```bash
  mvn clean install
```

Start the server 
- DEV profile

```bash
  mvn spring-boot:run -Dspring.profiles.active=DEV
```

- PROD profile 

```bash
    
```


## Lessons Learned

This project allowed me to learn how to implement the Jwt Authentication and to gain a deep and solid understanding of the entire Spring ecosystem, Hibernate and APIRESTful design.


## Future Features
- Implementation of the UI (ASAP)
- Program tasks and calendar integration
- You will be able to show your progresses with your friends
- Communication with friends
## Authors

[@Alessandro-Stefan](https://github.com/Alessandro-Stefan)


## Feedback

If you have any feedback, please reach out to us at alessandrostefan047@gmail.com



