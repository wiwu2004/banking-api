# 💳 Banking API - Spring Boot

## 📖 Description

REST API developed in **Java with Spring Boot** to simulate core banking operations such as account creation, deposits, withdrawals, and transfers.

This project also implements **JWT authentication and role-based authorization (USER / ADMIN)** to ensure secure access to endpoints.

---

## 🚀 Features

### 💰 Banking Operations

* Create account
* List all accounts
* Get account by ID
* Deposit funds
* Withdraw funds
* Transfer between accounts

### 🔐 Security

* User registration and login
* JWT-based authentication
* Role-based authorization (**USER / ADMIN**)
* Protected endpoints (e.g., DELETE only for ADMIN)

---

## 🛠️ Technologies Used

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* PostgreSQL (or H2 for testing)
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
security     → JWT + filters + config  
exception    → Global error handling  
```

---

## 🔐 Authentication & Authorization

The API uses **JWT tokens** for authentication.

### 📌 Authentication flow:

1. User registers
2. User logs in
3. API returns a JWT token
4. Token must be sent in requests:

```
Authorization: Bearer YOUR_TOKEN
```

---

### 👤 Roles

* `ROLE_USER` → basic access
* `ROLE_ADMIN` → full access

---

### 🔒 Access Rules

| Endpoint              | Access       |
| --------------------- | ------------ |
| `/auth/**`            | Public       |
| `/contas/**`          | USER / ADMIN |
| `DELETE /contas/{id}` | ADMIN only   |

---

## ⚙️ Validation and Error Handling

* Input validation using `@Valid`
* Global exception handling with `@RestControllerAdvice`
* Standardized API responses
* Proper HTTP status codes (200, 201, 403, 404, etc.)

---

## 🔐 Environment Variables

This project uses environment variables for sensitive data:

```
DB_USERNAME=your_database_user
DB_PASSWORD=your_database_password
JWT_SECRET=your_secret_key
```

---

## ▶️ How to Run the Project

### 1. Clone the repository

```
git clone https://github.com/wiwu2004/banking-api.git
cd banking-api
```

---

### 2. Configure environment variables

On Windows:

```
setx DB_USERNAME your_user
setx DB_PASSWORD your_password
setx JWT_SECRET your_secret
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

## 🧪 Example Endpoints

### 🔐 Auth

```
POST /auth/register
POST /auth/login
```

---

### 💰 Accounts

```
GET /contas
GET /contas/{id}
POST /contas
POST /contas/{id}/deposito
POST /contas/{id}/saque
POST /contas/transferencia
DELETE /contas/{id} (ADMIN only)
```

---

## 📌 Notes

* This project is for learning purposes
* Follows REST API best practices
* Demonstrates real-world security with JWT
* Can be extended with features like transactions, logs, or auditing

---

## 👨‍💻 Author

Developed by **Willian Wu**
