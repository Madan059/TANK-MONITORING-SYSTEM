# 💧 Smart Tank Monitoring System

## 🚀 Overview

A full-stack IoT-based application that simulates real-time water tank monitoring.
The system processes sensor readings, applies business logic, and displays live updates on a dashboard.

---

## ✨ Features

* 📡 Real-time water level monitoring
* ⚙️ Automated motor control logic
* ⚠️ Sensor error detection
* 🔄 Live frontend updates (every 5 seconds)
* 💾 Data persistence using database
* 🎯 Visual tank UI for better representation

---

## 🧠 Business Logic

### Water Level Classification

* **NORMAL** → Level > 70
* **LOW** → Level between 30 and 70
* **CRITICAL** → Level < 30

### Motor Logic

* ✅ Motor **ON** → If device is ACTIVE and last 3 readings are below 30
* ❌ Motor **OFF** → If level is NORMAL or device is INACTIVE

### Sensor Error Detection

* ⚠️ If the same reading repeats 5 times → `SENSOR_ERROR`

---

## 🛠 Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* MySQL

### Frontend

* React (Vite)
* JavaScript
* HTML / CSS

---

## 📁 Project Structure

tank-monitoring-system/
│
├── backend/   # Spring Boot application
├── frontend/  # React (Vite) application
└── README.md

---

## ▶️ How to Run the Project

### 🔹 Backend Setup

```bash
cd backend
mvn spring-boot:run
```

Runs on:

```
http://localhost:8080
```

---

### 🔹 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Runs on:

```
http://localhost:5173
```

---

## 🔗 API Endpoints

### ➤ Add Sensor Reading

```
POST /reading
```

Request Body:

```json
{
  "level": 50
}
```

---

### ➤ Get Latest Reading

```
GET /latest
```

---

## 🧪 Testing Approach

* ✔ Tested backend APIs using browser console (manual API calls)
* ✔ Used fixed input sequences to validate:

  * Motor ON condition
  * Motor OFF condition
  * Sensor error detection
* ✔ Used random values for real-time simulation

---

## 💡 Key Highlights

* Implemented real-time polling using React
* Designed clean service-layer business logic
* Simulated IoT behavior using JavaScript
* Built responsive UI with dynamic tank visualization

---

## 📸 Demo

👉 (Add screenshot here)

---

## 🚀 Future Improvements

* Add authentication & user roles
* Deploy backend and frontend online
* Add charts for historical data visualization
* Implement automated unit testing (JUnit)

---

## 👨‍💻 Author

**Madan Gopal Gupta**

* Java Full Stack Developer
* Skilled in Spring Boot, React, and MERN Stack

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
