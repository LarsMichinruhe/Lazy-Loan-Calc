# Lazy Loan Calculator

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

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
   git clone https://github.com/LarsMichinruhe/Lazy-Loan-Calc.git
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
curl -X POST http://localhost:8080/api/loan/calculate \ -H "Content-Type: application/json" \ -d '{ "principal": 10000, "annualInterestRate": 5.0, "years": 1, "monthlyPayment": 856.07 }'



## Example Response
{ "monthlyPayment": 856.07, "totalInterest": 272.84, "amortizationSchedule": [ { "month": 1, "principalPayment": 814.07, "interestPayment": 42.00, "remainingBalance": 9185.93 }, … ] }

