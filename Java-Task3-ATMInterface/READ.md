# Java ATM Interface

A console-based ATM simulation developed in Java as part of the OASIS Infobyte Java Development Internship.

## 📌 Project Overview

This project simulates the basic functionality of an ATM machine using Java and Object-Oriented Programming principles.

Users can securely log in using an account number and PIN and perform common banking operations such as checking their balance, depositing money, withdrawing cash, transferring money, viewing transaction history, and changing their PIN.

The project also includes an ATM cash inventory system that simulates the physical cash available inside the ATM.

## 🚀 Features

### 🔐 Authentication
- Account number and PIN based login
- Maximum 3 login attempts
- Invalid credential validation
- Secure PIN change functionality
- Logout functionality

### 💰 Banking Operations
- Balance inquiry
- Cash deposit
- Cash withdrawal
- Money transfer between accounts
- Insufficient balance validation
- Prevention of transfers to the same account

### 📜 Transaction Management
- Transaction history
- Unique transaction ID for every transaction
- Transaction type
- Transaction amount
- Balance after transaction
- Date and time of transaction

### 🏧 ATM Cash Management
- Physical ATM cash inventory
- INR 500, INR 200 and INR 100 denominations
- ATM cash availability checking
- Automatic note dispensing
- ATM cash inventory updated after withdrawals
- ATM cash status display

### 🛡️ Input Validation
- Invalid menu input handling
- Invalid amount handling
- Positive amount validation
- Withdrawal denomination validation
- PIN format validation
- Account existence validation

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- ArrayList
- HashMap
- Scanner
- LocalDateTime
- UUID
- Java Console

## 📂 Project Structure

```text
Java-Task3-ATMInterface/
│
├── src/
│   ├── Main.java
│   ├── ATM.java
│   ├── Account.java
│   ├── Bank.java
│   ├── Transaction.java
│   └── ATMInventory.java
│
├── screenshots/
│
└── README.md