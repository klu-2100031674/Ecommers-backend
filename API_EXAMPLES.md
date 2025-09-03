# 🚀 API Examples with cURL Commands

This document provides practical examples for testing the NewBuy E-Commerce API using cURL commands.

## 🔧 Setup

Set your base URL as an environment variable:
```bash
export BASE_URL="http://localhost:8080"
```

---

## 🏠 Health Check

```bash
curl -X GET "$BASE_URL/public/users/page"
```

**Expected Response:**
```
"check ramesh"
```

---

## 👤 User Registration Flow

### 1. Check Email Availability

```bash
curl -X GET "$BASE_URL/public/users/check-email?email=john@example.com"
```

**Success Response:**
```json
{
  "sts": true,
  "data": null,
  "message": "User is not registered"
}
```

### 2. Generate OTP for Registration

```bash
curl -X POST "$BASE_URL/public/users/generate-otp" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "mobileNumber": "1234567890",
    "password": "securePassword123"
  }'
```

**Success Response:**
```json
{
  "sts": true,
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "mobileNumber": "1234567890",
    "otp": "123456"
  },
  "message": "OTP generated successfully"
}
```

### 3. Verify OTP & Complete Registration

```bash
curl -X POST "$BASE_URL/public/users/verify-otp?email=john@example.com&otp=123456"
```

---

## 🔑 Authentication

### User Login

```bash
curl -X POST "$BASE_URL/public/users/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "securePassword123"
  }'
```

**Success Response:**
```json
{
  "sts": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2NDA5OTUyMDAsImV4cCI6MTY0MDk5ODgwMH0...",
    "email": "john@example.com",
    "name": "John Doe",
    "admin": "false"
  },
  "message": "Login successful"
}
```

**Save the JWT token for authenticated requests:**
```bash
export JWT_TOKEN="eyJhbGciOiJIUzI1NiJ9..."
```

---

## 🔄 Password Reset Flow

### 1. Request Password Reset OTP

```bash
curl -X GET "$BASE_URL/public/users/forget-password?email=john@example.com"
```

### 2. Verify Password Reset OTP

```bash
curl -X POST "$BASE_URL/public/users/forget-password-verify-otp" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "otp": "654321"
  }'
```

### 3. Update Password

```bash
curl -X POST "$BASE_URL/public/users/forget-password-update" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "newPassword": "newSecurePassword123"
  }'
```

---

## 👤 User Profile Management (Protected)

### Get User Profile

```bash
curl -X GET "$BASE_URL/api/users/get-profile-details/john@example.com" \
  -H "Authorization: Bearer $JWT_TOKEN"
```

**Success Response:**
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

### Update User Profile

```bash
curl -X PATCH "$BASE_URL/api/users/john@example.com/update-profile" \
  -H "Authorization: Bearer $JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Updated",
    "number": "0987654321",
    "address_Line_1": "456 Oak Ave",
    "address_Line_2": "Suite 2A",
    "postcode": "54321",
    "state": "CA",
    "city": "San Francisco",
    "country": "USA"
  }'
```

---

## 🛍️ Products

### Get All Products (Public)

```bash
curl -X GET "$BASE_URL/public/products/get-all?page=0&size=10&sortBy=name&sortDir=asc"
```

**Success Response:**
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
        "images": []
      }
    ],
    "totalElements": 1,
    "totalPages": 1,
    "size": 10,
    "number": 0
  },
  "message": "Products fetched successfully"
}
```

---

## 👑 Admin Operations (Protected)

### Admin Login

```bash
curl -X POST "$BASE_URL/public/users/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@example.com",
    "password": "adminPassword123"
  }'
```

**Save admin JWT token:**
```bash
export ADMIN_JWT_TOKEN="eyJhbGciOiJIUzI1NiJ9..."
```

### Add New Product (Admin Only)

```bash
curl -X POST "$BASE_URL/admin/insert" \
  -H "Authorization: Bearer $ADMIN_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Premium Water Bottle",
    "type": "750ml",
    "color": "Silver",
    "price": 35.99,
    "stockQuantity": 50,
    "description": "Premium stainless steel water bottle with temperature retention"
  }'
```

**Success Response:**
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

## 🛒 Shopping Cart Management (Protected)

### Add Product to Cart

```bash
curl -X POST "$BASE_URL/api/cart/add?userId=1&productId=1&quantity=2" \
  -H "Authorization: Bearer $JWT_TOKEN"
```

**Success Response:**
```json
{
  "sts": true,
  "data": {
    "id": 1,
    "userId": 1,
    "productId": 1,
    "quantity": 2
  },
  "message": "Product added to cart successfully"
}
```

### Get User's Cart

```bash
curl -X GET "$BASE_URL/api/cart/user/1" \
  -H "Authorization: Bearer $JWT_TOKEN"
```

**Success Response:**
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
    "quantity": 2
  }
]
```

### Remove Item from Cart

```bash
curl -X DELETE "$BASE_URL/api/cart/1" \
  -H "Authorization: Bearer $JWT_TOKEN"
```

**Success Response:**
```
"Item removed from cart successfully"
```

---

## 🧪 Testing Scripts

### Complete Registration & Login Flow

```bash
#!/bin/bash

# Set base URL
BASE_URL="http://localhost:8080"
EMAIL="testuser@example.com"
PASSWORD="testPassword123"

echo "🚀 Starting complete user flow test..."

# 1. Check email availability
echo "1. Checking email availability..."
curl -s -X GET "$BASE_URL/public/users/check-email?email=$EMAIL" | jq '.'

# 2. Generate OTP for registration
echo "2. Generating OTP for registration..."
OTP_RESPONSE=$(curl -s -X POST "$BASE_URL/public/users/generate-otp" \
  -H "Content-Type: application/json" \
  -d "{
    \"name\": \"Test User\",
    \"email\": \"$EMAIL\",
    \"mobileNumber\": \"1234567890\",
    \"password\": \"$PASSWORD\"
  }")

echo $OTP_RESPONSE | jq '.'
OTP=$(echo $OTP_RESPONSE | jq -r '.data.otp')

# 3. Verify OTP
echo "3. Verifying OTP: $OTP"
curl -s -X POST "$BASE_URL/public/users/verify-otp?email=$EMAIL&otp=$OTP" | jq '.'

# 4. Login user
echo "4. Logging in user..."
LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/public/users/login" \
  -H "Content-Type: application/json" \
  -d "{
    \"email\": \"$EMAIL\",
    \"password\": \"$PASSWORD\"
  }")

echo $LOGIN_RESPONSE | jq '.'
JWT_TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.data.token')

# 5. Get user profile
echo "5. Getting user profile..."
curl -s -X GET "$BASE_URL/api/users/get-profile-details/$EMAIL" \
  -H "Authorization: Bearer $JWT_TOKEN" | jq '.'

echo "✅ User flow test completed!"
```

### Product Management Test

```bash
#!/bin/bash

BASE_URL="http://localhost:8080"
ADMIN_EMAIL="admin@example.com"
ADMIN_PASSWORD="adminPassword123"

echo "🛍️ Testing product management..."

# 1. Admin login
echo "1. Admin login..."
ADMIN_LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/public/users/login" \
  -H "Content-Type: application/json" \
  -d "{
    \"email\": \"$ADMIN_EMAIL\",
    \"password\": \"$ADMIN_PASSWORD\"
  }")

ADMIN_JWT_TOKEN=$(echo $ADMIN_LOGIN_RESPONSE | jq -r '.data.token')

# 2. Add new product
echo "2. Adding new product..."
curl -s -X POST "$BASE_URL/admin/insert" \
  -H "Authorization: Bearer $ADMIN_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "type": "1L",
    "color": "Blue",
    "price": 29.99,
    "stockQuantity": 100,
    "description": "Test product description"
  }' | jq '.'

# 3. Get all products
echo "3. Getting all products..."
curl -s -X GET "$BASE_URL/public/products/get-all?page=0&size=5" | jq '.'

echo "✅ Product management test completed!"
```

---

## 📊 Response Status Codes

| Status Code | Description | Example |
|-------------|-------------|---------|
| 200 | Success | User logged in successfully |
| 400 | Bad Request | Invalid email format or missing fields |
| 401 | Unauthorized | Invalid JWT token |
| 403 | Forbidden | User doesn't have admin privileges |
| 404 | Not Found | User or product not found |
| 500 | Internal Server Error | Database connection issue |

---

## 🔍 Debugging Tips

### Enable verbose output in cURL:
```bash
curl -v -X GET "$BASE_URL/public/users/page"
```

### Save response to file:
```bash
curl -X GET "$BASE_URL/public/products/get-all" -o products.json
```

### Test with different content types:
```bash
curl -X POST "$BASE_URL/public/users/login" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"email":"test@example.com","password":"test123"}'
```

### Check response headers:
```bash
curl -I -X GET "$BASE_URL/public/users/page"
```

---

**💡 Tip:** Use `jq` for pretty-printing JSON responses:
```bash
curl -s "$BASE_URL/public/products/get-all" | jq '.'
```