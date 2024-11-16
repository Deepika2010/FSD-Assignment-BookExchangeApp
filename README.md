# Book Exchange Platform - Backend

This is the backend service for the Book Exchange Platform, built using **Spring Boot**. It handles user authentication, book listing management, and search functionality.

## Features

- Secure user authentication (register, login, logout, password reset).
- Add, edit, delete, and manage book listings.
- Search for books by title, author, genre, and location.
- Paginated or incremental search results for efficient performance.

---

## Prerequisites

- Java 17 or later
- Maven 3.6+
- PostgreSQL (or another database of your choice)

---

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/book-exchange-backend.git
   cd book-exchange-backend

## Database configuration 
spring.datasource.url=jdbc:postgresql://localhost:5432/book_exchange
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## Postman collection 
API Endpoints
User Authentication
POST /api/auth/register - Register a new user.
POST /api/auth/login - Authenticate a user and return a token.
POST /api/auth/logout - Log out the current user.
POST /api/auth/reset-password - Send a password reset link.
Book Listings
POST /api/books - Add a new book listing.
PUT /api/books/{id} - Update a book listing.
DELETE /api/books/{id} - Delete a book listing.
GET /api/books - Get all book listings for the logged-in user.
Book Search
GET /api/books/search - Search for books with optional filters:
Query Parameters:
title - Search by title.
author - Search by author.
genre - Search by genre.
location - Filter by location.
