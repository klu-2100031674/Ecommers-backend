# 🛍️ NewBuy E-Commerce Backend

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.8+-red.svg)
![JWT](https://img.shields.io/badge/JWT-Authentication-yellow.svg)

*A robust, scalable e-commerce backend API built with Spring Boot*

</div>

## 📋 Table of Contents

- [🚀 Features](#-features)
- [🛠️ Technology Stack](#️-technology-stack)
- [📦 Installation & Setup](#-installation--setup)
- [⚙️ Configuration](#️-configuration)
- [🔐 Authentication & Security](#-authentication--security)
- [📚 API Documentation](#-api-documentation)
- [💾 Database Schema](#-database-schema)
- [🚀 Deployment](#-deployment)
- [🧪 Testing](#-testing)
- [🤝 Contributing](#-contributing)

## 🚀 Features

### ✨ Core Features
- 🔐 **JWT-based Authentication & Authorization**
- 📧 **Email OTP Verification System**
- 👤 **User Profile Management**
- 🛍️ **Product Catalog Management**
- 🛒 **Shopping Cart Functionality**
- 👑 **Admin Panel for Product Management**
- 🔒 **Role-based Access Control (User/Admin)**
- 📱 **RESTful API Design**
- 🔄 **Password Reset Functionality**

### 🛡️ Security Features
- JWT token-based authentication
- Password encryption
- Role-based authorization
- Email verification for account activation
- Secure password reset flow

## 🛠️ Technology Stack

| Category | Technology |
|----------|------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.5.5 |
| **Security** | Spring Security + JWT |
| **Database** | MySQL 8.0 |
| **ORM** | JPA/Hibernate |
| **Build Tool** | Maven |
| **Email Service** | Spring Mail (Gmail SMTP) |
| **Validation** | Jakarta Validation |
| **Authentication** | JSON Web Tokens (JWT) |

## 📦 Installation & Setup

### Prerequisites
- ☕ Java 17 or higher
- 🗄️ MySQL 8.0+
- 📦 Maven 3.8+
- 📧 Gmail account for email services

### 1. Clone the Repository
```bash
git clone https://github.com/klu-2100031674/Ecommers-backend.git
cd Ecommers-backend
```

### 2. Database Setup
```sql
CREATE DATABASE ecommerce;
USE ecommerce;
-- Tables will be auto-created by Hibernate
```

### 3. Configure Application Properties
Update `src/main/resources/application.properties`:
```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### 4. Build and Run
```bash
# Build the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## ⚙️ Configuration

### Environment Variables
For production deployment, use environment variables:
- `PORT`: Server port (default: 8080)
- `DATABASE_URL`: MySQL connection string
- `JWT_SECRET`: Secret key for JWT token signing
- `MAIL_USERNAME`: Email service username
- `MAIL_PASSWORD`: Email service password

## 🔐 Authentication & Security

### JWT Token Structure
```json
{
  "sub": "user@example.com",
  "roles": ["USER"] // or ["ADMIN"]
  "iat": 1640995200,
  "exp": 1640998800
}
```

### Authorization Headers
Include JWT token in requests:
```http
Authorization: Bearer <your-jwt-token>
```

### User Roles
- **USER**: Can manage profile, view products, manage cart
- **ADMIN**: Full access including product management

## 📚 API Documentation

### Base URL
```
http://localhost:8080
```

---

## 🔓 Public Endpoints (No Authentication Required)

### 🏠 Health Check
```http
GET /public/users/page
```
**Response:**
```json
"check ramesh"
```

---

### 👤 User Registration Flow

#### 1. Check Email Availability
```http
GET /public/users/check-email?email=user@example.com
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": null,
  "message": "User is not registered"
}
```

**Error Response (400):**
```json
{
  "sts": false,
  "data": null,
  "message": "User is already registered"
}
```

#### 2. Generate OTP for Registration
```http
POST /public/users/generate-otp
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "mobileNumber": "1234567890",
  "password": "securePassword123"
}
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "mobileNumber": "1234567890",
    "otp": "123456",
    "createdAt": "2024-01-01T10:30:00"
  },
  "message": "OTP generated successfully"
}
```

#### 3. Verify OTP & Complete Registration
```http
POST /public/users/verify-otp?email=john@example.com&otp=123456
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": null,
  "message": "Account created successfully"
}
```

---

### 🔑 User Authentication

#### Login
```http
POST /public/users/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "securePassword123"
}
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "email": "john@example.com",
    "name": "John Doe",
    "admin": "false"
  },
  "message": "Login successful"
}
```

**Error Response (400):**
```json
{
  "sts": false,
  "data": null,
  "message": "Login failed"
}
```

---

### 🔄 Password Reset Flow

#### 1. Request Password Reset OTP
```http
GET /public/users/forget-password?email=john@example.com
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": null,
  "message": "OTP sent to your email"
}
```

#### 2. Verify Password Reset OTP
```http
POST /public/users/forget-password-verify-otp
Content-Type: application/json

{
  "email": "john@example.com",
  "otp": "654321"
}
```

#### 3. Update Password
```http
POST /public/users/forget-password-update
Content-Type: application/json

{
  "email": "john@example.com",
  "newPassword": "newSecurePassword123"
}
```

---

## 🔒 Protected Endpoints (Authentication Required)

### 👤 User Profile Management

#### Get User Profile
```http
GET /api/users/get-profile-details/john@example.com
Authorization: Bearer <jwt-token>
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "number": "1234567890",
    "active": true,
    "address_Line_1": "123 Main St",
    "address_Line_2": "Apt 4B",
    "postcode": "12345",
    "state": "NY",
    "city": "New York",
    "country": "USA",
    "admin": "false"
  },
  "message": "user profile fetched successfully"
}
```

#### Update User Profile
```http
PATCH /api/users/john@example.com/update-profile
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "name": "John Updated",
  "number": "0987654321",
  "address_Line_1": "456 Oak Ave",
  "address_Line_2": "Suite 2A",
  "postcode": "54321",
  "state": "CA",
  "city": "San Francisco",
  "country": "USA"
}
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": {
    "id": 1,
    "name": "John Updated",
    "email": "john@example.com",
    // ... updated fields
  },
  "message": "Profile updated successfully"
}
```

---

### 🛍️ Product Management

#### Get All Products (Public)
```http
GET /public/products/get-all?page=0&size=10&sortBy=name&sortDir=asc
```

**Query Parameters:**
- `page` (default: 0): Page number
- `size` (default: 10): Items per page
- `sortBy` (default: id): Sort field
- `sortDir` (default: asc): Sort direction

**Success Response (200):**
```json
{
  "sts": true,
  "data": {
    "content": [
      {
        "id": 1,
        "name": "Water Bottle",
        "type": "1 Liter",
        "color": "Blue",
        "price": 25.99,
        "stockQuantity": 100,
        "description": "High-quality water bottle",
        "images": [
          {
            "id": 1,
            "imageUrl": "https://example.com/image1.jpg",
            "altText": "Water Bottle Front View"
          }
        ]
      }
    ],
    "totalElements": 50,
    "totalPages": 5,
    "size": 10,
    "number": 0
  },
  "message": "Products fetched successfully"
}
```

---

## 👑 Admin Endpoints (Admin Role Required)

### Add New Product
```http
POST /admin/insert
Authorization: Bearer <admin-jwt-token>
Content-Type: application/json

{
  "name": "Premium Water Bottle",
  "type": "750ml",
  "color": "Silver",
  "price": 35.99,
  "stockQuantity": 50,
  "description": "Premium stainless steel water bottle with temperature retention"
}
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": {
    "id": 2,
    "name": "Premium Water Bottle",
    "type": "750ml",
    "color": "Silver",
    "price": 35.99,
    "stockQuantity": 50,
    "description": "Premium stainless steel water bottle with temperature retention",
    "images": []
  },
  "message": "Product saved successfully"
}
```

---

### 🛒 Shopping Cart Management

#### Add Product to Cart
```http
POST /api/cart/add?userId=1&productId=1&quantity=2
Authorization: Bearer <jwt-token>
```

**Success Response (200):**
```json
{
  "sts": true,
  "data": {
    "id": 1,
    "userId": 1,
    "productId": 1,
    "quantity": 2,
    "addedAt": "2024-01-01T10:30:00"
  },
  "message": "Product added to cart successfully"
}
```

#### Get User's Cart
```http
GET /api/cart/user/1
Authorization: Bearer <jwt-token>
```

**Success Response (200):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "product": {
      "id": 1,
      "name": "Water Bottle",
      "price": 25.99
    },
    "quantity": 2,
    "addedAt": "2024-01-01T10:30:00"
  }
]
```

#### Remove Item from Cart
```http
DELETE /api/cart/1
Authorization: Bearer <jwt-token>
```

**Success Response (200):**
```
"Item removed from cart successfully"
```

---

## 🚨 Error Handling

### Common Error Responses

#### 400 - Bad Request
```json
{
  "sts": false,
  "data": null,
  "message": "Invalid input data"
}
```

#### 401 - Unauthorized
```json
{
  "sts": false,
  "data": null,
  "message": "Authentication required"
}
```

#### 403 - Forbidden
```json
{
  "sts": false,
  "data": null,
  "message": "Access denied"
}
```

#### 404 - Not Found
```json
{
  "sts": false,
  "data": null,
  "message": "Resource not found"
}
```

---

## 💾 Database Schema

### Users Table
```sql
CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL,
  Mobile_Number VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  emp_active BOOLEAN NOT NULL DEFAULT TRUE,
  user_Address_line1 VARCHAR(255),
  user_Address_line2 VARCHAR(255),
  pincode VARCHAR(10),
  state VARCHAR(50),
  city VARCHAR(50),
  Country VARCHAR(50),
  admin VARCHAR(10) NOT NULL DEFAULT 'false'
);
```

### Products Table
```sql
CREATE TABLE products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  color VARCHAR(255) NOT NULL,
  price DOUBLE NOT NULL,
  stock_quantity INT NOT NULL,
  description TEXT NOT NULL
);
```

### Cart Table
```sql
CREATE TABLE cart (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);
```

---

## 🚀 Deployment

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/in-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Render Deployment
1. Connect your GitHub repository to Render
2. Set environment variables in Render dashboard
3. Deploy using the provided Dockerfile

### Environment Variables for Production
```bash
PORT=8080
DATABASE_URL=jdbc:mysql://your-db-host:3306/database_name
JWT_SECRET=your-super-secret-key
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password
```

---

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Test Coverage
- Unit tests for service layer
- Integration tests for API endpoints
- Security tests for authentication

---

## 📊 API Testing with Postman

### Import Collection
1. Download the Postman collection from the repository
2. Import into Postman
3. Set up environment variables:
   - `base_url`: http://localhost:8080
   - `jwt_token`: Your JWT token after login

---

## 🔄 Development Workflow

### 1. Setup Development Environment
```bash
git clone <repository-url>
cd Ecommers-backend
mvn clean install
```

### 2. Database Migration
```bash
# Auto-handled by Hibernate DDL
# Tables created automatically on first run
```

### 3. Run in Development Mode
```bash
mvn spring-boot:run
# Application runs with hot reload enabled
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**NewBuy Team**
- GitHub: [@klu-2100031674](https://github.com/klu-2100031674)
- Email: mattajithendra07@gmail.com

---

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- MySQL for the robust database
- JWT.io for token authentication
- All contributors who helped build this project

---

<div align="center">

**Made with ❤️ for the e-commerce community**

⭐ **If you find this project helpful, please give it a star!** ⭐

</div>