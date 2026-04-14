# Mutual Fund Management System

A full-stack web application to manage mutual fund investments, built using **React (Vite + Tailwind CSS)** for the frontend and **Spring Boot (Java)** for the backend.

---

## Features

### User Features

* Login
* View available mutual funds
* Buy & Sell funds
* Track portfolio
* View transaction history

### Backend Features

* REST APIs using Spring Boot
* JWT-based authentication (if implemented)
* Database integration (MySQL/PostgreSQL)
* Layered architecture (Controller → Service → Repository)

---

## Tech Stack

### Frontend

* React (Vite)
* Tailwind CSS
* Axios (for API calls)

### Backend

* Java
* Spring Boot
* Spring Security
* JPA / Hibernate
* Maven

### Tools

* Git & GitHub
* Postman (API testing)

---

## Project Structure

```
mutualfundsystem/
│
├── frontend/        # React frontend
│   ├── src/
│   ├── public/
│   └── package.json
│
├── backend/         # Spring Boot backend
│   ├── src/
│   ├── pom.xml
│   └── application.properties
│
├── README.md
└── .gitignore
```

---

## Setup Instructions

###  1. Clone Repository

```bash
git clone https://github.com/gadekarrahul1924/mutualfundsystem.git
cd mutualfundsystem
```

---

### 🔹 2. Run Frontend

```bash
cd frontend
npm install
npm run dev
```

App runs on: http://localhost:5173/

---

### 🔹 3. Run Backend

```bash
cd backend
./mvnw spring-boot:run
```

👉 Backend runs on: http://localhost:8081/

---

## 🔗 API Endpoints (Sample)

| Method | Endpoint       | Description      |
| ------ | -------------- | ---------------- |
| GET    | /api/funds     | Get all funds    |
| POST   | /api/buy       | Buy mutual fund  |
| POST   | /api/sell      | Sell mutual fund |
| GET    | /api/portfolio | View portfolio   |

---

## 🛡️ Configuration

Update backend config:

```
backend/src/main/resources/application.properties
```

Example:
server.port=8081

spring.datasource.url=jdbc:postgresql://localhost:5432/mutualfunds
spring.datasource.username=postgres
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

##  Testing

* Use Postman to test backend APIs
* Use browser for frontend testing

---

##  Future Enhancements

* Admin dashboard
* Real-time fund price updates
* Payment gateway integration
* Charts & analytics

---

##  Author

**Rahul Gadekar**

---

##  License

This project is licensed under the MIT License.

---

## Support

If you like this project, give it a ⭐ on GitHub!

---
