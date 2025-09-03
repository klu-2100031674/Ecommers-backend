#!/bin/bash

# 🚀 NewBuy E-Commerce Backend Startup Script
# This script helps you quickly start the application

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Banner
echo -e "${BLUE}"
echo "╔═══════════════════════════════════════╗"
echo "║    NewBuy E-Commerce Backend         ║"
echo "║    🚀 Quick Start Script             ║"
echo "╚═══════════════════════════════════════╝"
echo -e "${NC}"

# Check if Java is installed
print_status "Checking Java installation..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
    if [ "$JAVA_VERSION" -ge 17 ]; then
        print_success "Java $JAVA_VERSION is installed ✓"
    else
        print_error "Java 17 or higher is required. Found Java $JAVA_VERSION"
        echo "Please install Java 17 or higher from: https://adoptium.net/"
        exit 1
    fi
else
    print_error "Java is not installed!"
    echo "Please install Java 17 or higher from: https://adoptium.net/"
    exit 1
fi

# Check if Maven is installed
print_status "Checking Maven installation..."
if command -v mvn &> /dev/null; then
    MAVEN_VERSION=$(mvn -version | head -n 1 | cut -d' ' -f3)
    print_success "Maven $MAVEN_VERSION is installed ✓"
else
    print_error "Maven is not installed!"
    echo "Please install Maven from: https://maven.apache.org/install.html"
    exit 1
fi

# Check if MySQL is running (optional check)
print_status "Checking MySQL connection..."
if command -v mysql &> /dev/null; then
    print_success "MySQL client is available ✓"
else
    print_warning "MySQL client not found. Make sure MySQL server is running."
fi

# Build the project
print_status "Building the application..."
if mvn clean package -DskipTests; then
    print_success "Application built successfully ✓"
else
    print_error "Build failed!"
    exit 1
fi

# Check if application.properties exists
if [ ! -f "src/main/resources/application.properties" ]; then
    print_warning "application.properties not found!"
    print_status "Creating sample configuration..."
    
    mkdir -p src/main/resources/
    cat > src/main/resources/application.properties << EOF
# Server Configuration
server.port=8080

# Database Configuration (Update these values)
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Email Configuration (Update these values)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
EOF
    
    print_warning "Please update database and email configurations in src/main/resources/application.properties"
    echo "Press any key to continue with the current configuration..."
    read -n 1 -s
fi

# Start the application
print_status "Starting the application..."
echo -e "${GREEN}"
echo "╔═══════════════════════════════════════╗"
echo "║  🚀 Starting NewBuy E-Commerce API    ║"
echo "║                                       ║"
echo "║  Server will be available at:         ║"
echo "║  http://localhost:8080                ║"
echo "║                                       ║"
echo "║  Health Check:                        ║"
echo "║  GET /public/users/page               ║"
echo "║                                       ║"
echo "║  API Documentation: README.md         ║"
echo "║  Postman Collection: postman_collection.json ║"
echo "║                                       ║"
echo "║  Press Ctrl+C to stop the server     ║"
echo "╚═══════════════════════════════════════╝"
echo -e "${NC}"

# Run the application
exec java -jar target/in-0.0.1-SNAPSHOT.jar