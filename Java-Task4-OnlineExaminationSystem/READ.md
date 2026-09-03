# Java Online Examination System

A GUI-based online examination system developed in Java as part of the OASIS Infobyte Java Development Internship.

## 📌 Project Overview

This project simulates an online examination platform using Java Swing and Object-Oriented Programming principles.

Students can log in, update their profile, attend a multiple-choice examination, navigate between questions, track the remaining time, submit their examination, and view their final result.

The system includes automatic submission when the examination timer expires and confirmation dialogs for important actions such as submission, logout, and closing the examination.

## 🚀 Features

### 🔐 Student Login
- Username and password authentication
- Empty field validation
- Invalid credential validation
- Exit confirmation

### 👤 Student Profile
- Student name
- Email address
- Phone number
- Profile validation
- Profile update confirmation
- Start examination option
- Logout functionality

### 📝 Online Examination
- Multiple-choice questions
- Four options for every question
- One question displayed at a time
- Next and Previous navigation
- Selected answers are preserved while navigating
- Answered question counter
- Five-minute countdown timer
- Automatic submission when time expires
- Manual submission confirmation
- Warning for unanswered questions

### 📊 Result Management
- Total score
- Percentage calculation
- Performance evaluation
- Student information display
- Take examination again
- Logout functionality

### 🛡️ Validation & Safety
- Empty input validation
- Email validation
- 10-digit phone number validation
- Submission confirmation
- Logout confirmation
- Examination close confirmation
- Automatic submission after timeout

## 🛠️ Technologies Used

- Java
- Java Swing
- Object-Oriented Programming (OOP)
- Java Collections Framework
- ArrayList
- Swing Timer
- Event Handling
- Exception Handling

## 📂 Project Structure

```text
Java-Task4-OnlineExaminationSystem/
│
├── Screenshots/
│   ├── 1.png
│   ├── 2.png
│   ├── 3.png
│   ├── 4.png
│   ├── 5.png
│
├── Src/
│   ├── Main.java
│   ├── LoginFrame.java
│   ├── ProfileFrame.java
│   ├── ExamFrame.java
│   ├── ResultFrame.java
│   ├── Question.java
│   └── QuestionBank.java
│
└── README.md