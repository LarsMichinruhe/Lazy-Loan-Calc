# Lazy Loan Calculator

## Overview
The Lazy Loan Calculator is a Spring Boot application designed to calculate loan details based on user input. It provides a RESTful API that accepts loan requests and returns calculated loan responses.

## Features
- **RESTful API**: Exposes endpoints to calculate loan details.
- **Cross-Origin Resource Sharing (CORS)**: Configured to allow requests from `http://localhost:4200`.
- **JSON Support**: Consumes and produces JSON data.

## Technologies Used
- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Gradle**: Build automation tool.
- **Spring Web**: For building web applications and RESTful services.


## Getting Started

### Prerequisites
- Java 11 or higher
- Gradle
- Git

### Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-username/your-repository.git
   cd your-repository
   ```

2. **Build the project:**
   ```sh
   gradle build
   ```

3. **Run the application:**
   ```sh
   gradle bootRun
   ```

### API Endpoints
- **Calculate Loan**: `POST /api/loan/calculate`
  - **Request Body**: `LoanRequest` (JSON)
  - **Response Body**: `LoanResponse` (JSON)

## Example Request
curl -X POST http://localhost:8080/api/loan/calculate \ -H "Content-Type: application/json" \ -d '{ "amount": 10000, "term": 12, "interestRate": 5.0 }'



## Example Response
{ "monthlyPayment": 856.07, "totalPayment": 10272.84, "totalInterest": 272.84 }
