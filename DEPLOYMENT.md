# 🚀 Deployment Guide

This guide provides step-by-step instructions for deploying the NewBuy E-Commerce Backend to various platforms.

## 📋 Table of Contents

- [🐳 Docker Deployment](#-docker-deployment)
- [☁️ Render Deployment](#️-render-deployment)
- [🌐 Heroku Deployment](#-heroku-deployment)
- [☁️ AWS EC2 Deployment](#️-aws-ec2-deployment)
- [🔧 Environment Configuration](#-environment-configuration)
- [📊 Monitoring & Health Checks](#-monitoring--health-checks)

---

## 🐳 Docker Deployment

### 1. Create Dockerfile

The project already includes a `Dockerfile`:

```dockerfile
FROM openjdk:17-jdk-slim

# Create app directory
WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/in-0.0.1-SNAPSHOT.jar"]
```

### 2. Build Docker Image

```bash
# Build the image
docker build -t newbuy-ecommerce:latest .

# Or with specific tag
docker build -t newbuy-ecommerce:v1.0.0 .
```

### 3. Run with Docker

```bash
# Run with environment variables
docker run -d \
  --name newbuy-app \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/ecommerce \
  -e SPRING_DATASOURCE_USERNAME=your_username \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  -e SPRING_MAIL_USERNAME=your_email@gmail.com \
  -e SPRING_MAIL_PASSWORD=your_app_password \
  newbuy-ecommerce:latest
```

### 4. Docker Compose Setup

Create `docker-compose.yml`:

```yaml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ecommerce
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=password
      - SPRING_MAIL_USERNAME=${MAIL_USERNAME}
      - SPRING_MAIL_PASSWORD=${MAIL_PASSWORD}
    volumes:
      - ./logs:/app/logs

  mysql:
    image: mysql:8.0
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=password
      - MYSQL_DATABASE=ecommerce
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
```

Run with Docker Compose:
```bash
docker-compose up -d
```

---

## ☁️ Render Deployment

### 1. Connect Repository

1. Go to [Render Dashboard](https://dashboard.render.com/)
2. Click "New" → "Web Service"
3. Connect your GitHub repository

### 2. Configure Service

**Basic Settings:**
- **Name**: `newbuy-ecommerce-api`
- **Environment**: `Docker`
- **Region**: Choose closest to your users
- **Branch**: `main`

**Build & Deploy:**
- **Dockerfile Path**: `./Dockerfile`
- **Build Command**: (Leave empty for Docker)
- **Start Command**: (Leave empty for Docker)

### 3. Environment Variables

Add these environment variables in Render dashboard:

```
PORT=8080
SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/database_name
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=your_email@gmail.com
SPRING_MAIL_PASSWORD=your_app_password
SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true
```

### 4. Database Setup (Render PostgreSQL)

If using Render's managed PostgreSQL:

1. Create PostgreSQL instance in Render
2. Update `pom.xml` to include PostgreSQL dependency:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

3. Update database URL format:
```
SPRING_DATASOURCE_URL=postgres://username:password@host:port/database_name
```

### 5. Deploy

Click "Create Web Service" to deploy. Render will:
1. Build the Docker image
2. Deploy to their infrastructure
3. Provide a public URL

---

## 🌐 Heroku Deployment

### 1. Prepare for Heroku

Create `system.properties`:
```
java.runtime.version=17
```

Create `Procfile`:
```
web: java -Dserver.port=$PORT -jar target/in-0.0.1-SNAPSHOT.jar
```

### 2. Heroku CLI Setup

```bash
# Install Heroku CLI
# https://devcenter.heroku.com/articles/heroku-cli

# Login to Heroku
heroku login

# Create Heroku app
heroku create newbuy-ecommerce-api

# Add JawsDB MySQL addon
heroku addons:create jawsdb:kitefin

# Or use Heroku Postgres
heroku addons:create heroku-postgresql:mini
```

### 3. Configure Environment Variables

```bash
# Set environment variables
heroku config:set SPRING_MAIL_USERNAME=your_email@gmail.com
heroku config:set SPRING_MAIL_PASSWORD=your_app_password

# For JawsDB MySQL (get URL from dashboard)
heroku config:set SPRING_DATASOURCE_URL=mysql://username:password@host:port/database

# For Heroku Postgres
heroku config:set SPRING_DATASOURCE_URL=${DATABASE_URL}
```

### 4. Deploy

```bash
# Deploy to Heroku
git add .
git commit -m "Prepare for Heroku deployment"
git push heroku main

# Open the app
heroku open

# View logs
heroku logs --tail
```

---

## ☁️ AWS EC2 Deployment

### 1. Launch EC2 Instance

1. Launch Ubuntu 22.04 LTS instance
2. Configure security group:
   - SSH (22) from your IP
   - HTTP (80) from anywhere
   - HTTPS (443) from anywhere
   - Custom TCP (8080) from anywhere

### 2. Server Setup

```bash
# Connect to instance
ssh -i your-key.pem ubuntu@your-ec2-ip

# Update system
sudo apt update && sudo apt upgrade -y

# Install Java 17
sudo apt install openjdk-17-jdk -y

# Install Maven
sudo apt install maven -y

# Install MySQL
sudo apt install mysql-server -y
sudo mysql_secure_installation

# Install Nginx (optional, for reverse proxy)
sudo apt install nginx -y
```

### 3. Application Deployment

```bash
# Clone repository
git clone https://github.com/klu-2100031674/Ecommers-backend.git
cd Ecommers-backend

# Build application
mvn clean package -DskipTests

# Create systemd service
sudo nano /etc/systemd/system/newbuy-app.service
```

Service file content:
```ini
[Unit]
Description=NewBuy E-Commerce API
After=network.target

[Service]
Type=simple
User=ubuntu
WorkingDirectory=/home/ubuntu/Ecommers-backend
ExecStart=/usr/bin/java -jar target/in-0.0.1-SNAPSHOT.jar
Restart=always
RestartSec=10
Environment=SPRING_PROFILES_ACTIVE=production
Environment=SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/ecommerce
Environment=SPRING_DATASOURCE_USERNAME=your_username
Environment=SPRING_DATASOURCE_PASSWORD=your_password

[Install]
WantedBy=multi-user.target
```

### 4. Start Service

```bash
# Reload systemd
sudo systemctl daemon-reload

# Enable and start service
sudo systemctl enable newbuy-app
sudo systemctl start newbuy-app

# Check status
sudo systemctl status newbuy-app
```

### 5. Nginx Reverse Proxy (Optional)

```bash
# Configure Nginx
sudo nano /etc/nginx/sites-available/newbuy-app
```

Nginx configuration:
```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

```bash
# Enable site
sudo ln -s /etc/nginx/sites-available/newbuy-app /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

---

## 🔧 Environment Configuration

### Production Configuration

Create `application-production.properties`:

```properties
# Server Configuration
server.port=${PORT:8080}
server.servlet.context-path=/api/v1

# Database Configuration
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Security Configuration
security.jwt.secret=${JWT_SECRET:your-super-secret-key}
security.jwt.expiration-time=3600000

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Logging Configuration
logging.level.com.newbuy.in=INFO
logging.level.org.springframework.security=WARN
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
logging.file.name=logs/newbuy-app.log
```

### Environment Variables Template

```bash
# Database
export DATABASE_URL="jdbc:mysql://localhost:3306/ecommerce"
export DB_USERNAME="your_username"
export DB_PASSWORD="your_password"

# Security
export JWT_SECRET="your-super-secret-key-should-be-at-least-256-bits-long"

# Email
export MAIL_USERNAME="your_email@gmail.com"
export MAIL_PASSWORD="your_app_password"

# Application
export SPRING_PROFILES_ACTIVE="production"
export PORT="8080"
```

---

## 📊 Monitoring & Health Checks

### Health Check Endpoints

Add to `application.properties`:
```properties
# Actuator endpoints
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
management.info.env.enabled=true
```

### Health Check Script

```bash
#!/bin/bash

# health-check.sh
URL="${1:-http://localhost:8080}"

echo "🔍 Checking application health..."

# Basic connectivity
if curl -f -s "$URL/public/users/page" > /dev/null; then
    echo "✅ Application is responding"
else
    echo "❌ Application is not responding"
    exit 1
fi

# Actuator health check (if enabled)
if curl -f -s "$URL/actuator/health" | grep -q '"status":"UP"'; then
    echo "✅ Health check passed"
else
    echo "⚠️ Health check failed"
fi

# Database connectivity test
if curl -f -s "$URL/public/products/get-all?size=1" > /dev/null; then
    echo "✅ Database connection working"
else
    echo "❌ Database connection failed"
    exit 1
fi

echo "🎉 All checks passed!"
```

### Monitoring with SystemD

Create monitoring script:
```bash
# /usr/local/bin/monitor-newbuy.sh
#!/bin/bash

SERVICE_NAME="newbuy-app"
LOG_FILE="/var/log/newbuy-monitor.log"

if ! systemctl is-active --quiet $SERVICE_NAME; then
    echo "$(date): Service $SERVICE_NAME is down, restarting..." >> $LOG_FILE
    systemctl restart $SERVICE_NAME
    sleep 30
    
    if systemctl is-active --quiet $SERVICE_NAME; then
        echo "$(date): Service $SERVICE_NAME restarted successfully" >> $LOG_FILE
    else
        echo "$(date): Failed to restart $SERVICE_NAME" >> $LOG_FILE
    fi
fi
```

Add to crontab:
```bash
# Check every 5 minutes
*/5 * * * * /usr/local/bin/monitor-newbuy.sh
```

---

## 🔒 SSL/TLS Configuration

### Let's Encrypt with Certbot

```bash
# Install Certbot
sudo apt install certbot python3-certbot-nginx -y

# Get SSL certificate
sudo certbot --nginx -d your-domain.com

# Auto-renewal
sudo crontab -e
# Add: 0 12 * * * /usr/bin/certbot renew --quiet
```

### SSL Nginx Configuration

```nginx
server {
    listen 80;
    server_name your-domain.com;
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name your-domain.com;

    ssl_certificate /etc/letsencrypt/live/your-domain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/your-domain.com/privkey.pem;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

---

## 🐛 Troubleshooting

### Common Issues

1. **Port already in use**
   ```bash
   # Find process using port 8080
   sudo lsof -i :8080
   # Kill the process
   sudo kill -9 <PID>
   ```

2. **Database connection issues**
   ```bash
   # Test database connection
   mysql -h localhost -u username -p database_name
   ```

3. **Memory issues**
   ```bash
   # Set JVM memory options
   export JAVA_OPTS="-Xmx512m -Xms256m"
   java $JAVA_OPTS -jar target/in-0.0.1-SNAPSHOT.jar
   ```

4. **Log analysis**
   ```bash
   # View application logs
   tail -f logs/newbuy-app.log
   
   # View system logs
   sudo journalctl -u newbuy-app -f
   ```

---

**🎉 Congratulations!** Your NewBuy E-Commerce Backend is now deployed and running in production!