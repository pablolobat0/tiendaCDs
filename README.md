# Tienda de CDs - Online CD Store

Tienda de CDs is a full-stack web application developed in Java using Servlets and JSP. It allows users to browse CDs, add them to a shopping cart, and complete purchases. The application uses PostgreSQL as the database and is containerized with Docker for easy deployment.

## Features

- **User Authentication**:
  - Secure user registration and login.
  - Password hashing for secure storage.
  - Session management.

- **CD Management**:
  - Browse available CDs with details (name, artist, price).
  - Add CDs to the shopping cart.
  - Remove CDs from the shopping cart.

- **Shopping Cart**:
  - View items in the cart.
  - Proceed to checkout and confirm purchases.

- **Security**:
  - Password hashing using bcrypt.
  - CSRF protection and secure session handling.

- **User Interface**:
  - Modern and responsive design using Bootstrap 5.
  - Intuitive navigation and forms.

## Technologies Used

- **Backend**: Java Servlets, JSP
- **Frontend**: HTML, CSS, Bootstrap 5
- **Database**: PostgreSQL
- **Containerization**: Docker, Docker Compose
- **Password Hashing**: bcrypt

## Setup

### Prerequisites

- Docker and Docker Compose installed.
- Create a `.env` file in the root directory with the following content: POSTGRES_PASSWORD, POSTGRES_DB

### Steps to Run the Application

1. **Clone the repository**:
   ```bash
   git clone https://github.com/pablolobat0/tiendaCDs.git
   cd tiendaCDs
   docker-compose up
   ```

The app will be deployed in http://localhost:8080/tienda
