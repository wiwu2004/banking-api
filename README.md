# 💳 Banking API - Spring Boot

## 📖 Description

REST API developed in **Java with Spring Boot** to simulate core banking operations such as account creation, deposits, withdrawals, and transfers.

---

## 🚀 Features

* Create account
* List all accounts
* Get account by ID
* Deposit funds
* Withdraw funds
* Transfer between accounts

---

## 🛠️ Technologies Used

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* PostGreSQL
* Maven
* Swagger (OpenAPI)
* HATEOAS

---

## 📂 Project Structure

```
controller   → API endpoints  
service      → Business rules  
repository   → Data access layer  
model        → Entities  
dto          → Data transfer objects  
exception    → Error handling  
```

---

## ⚙️ Validation and Error Handling

* Input validation using `@Valid`
* Global exception handling with `@RestControllerAdvice`
* Standardized error responses

---

## 🔐 Environment Variables

This project uses environment variables for sensitive data:

```
DB_USERNAME=your_database_user
DB_PASSWORD=your_database_password
```

---

## ▶️ How to Run the Project

### 1. Clone the repository

```
git clone https://github.com/wiwu2004/banking-api.git
cd banking-api
```

### 2. Configure environment variables

On Windows:

```
setx DB_USERNAME your_user
setx DB_PASSWORD your_password
```

---

### 3. Run the application

```
mvn spring-boot:run
```

---

## 📡 API Documentation

After running the project, access Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

## 📌 Notes

* This project is for learning purposes
* Can be easily adapted to use PostgreSQL instead of H2
* Environment variables are used to protect sensitive data

---

## 👨‍💻 Author

Developed by **wiwu2004 or Willian Wu**
