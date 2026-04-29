# 🚀 BFHL Hierarchy Processor

## 🌟 Overview
**BFHL Hierarchy Processor** is a high-performance, full-stack application designed to process complex string-based edge relationships. It validates input, detects duplicate edges, identifies cyclic dependencies, and visualizes data as nested tree structures. 

Built for the **SRM Full-Stack Challenge**, this project showcases a seamless integration between a robust Spring Boot backend and a modern, glassmorphic frontend.

---

## ✨ Key Features
- **🛡️ Intelligent Validation**: Automated filtering of invalid edge formats, self-loops, and multi-character nodes.
- **🔄 Cycle Detection**: Advanced DFS-based cycle detection for identifying strongly connected components.
- **🌳 Nested Visualization**: Recursive construction of hierarchical tree structures with depth calculation.
- **🎨 Premium UI**: A stunning dark-mode interface featuring glassmorphism, smooth animations, and responsive design.
- **⚙️ Global CORS**: Pre-configured for easy deployment and cross-origin communication.

---

## 🛠️ Tech Stack

### Backend
- **Java 17+** & **Spring Boot 3.2.5**
- **Maven** for dependency management
- **RESTful API** architecture

### Frontend
- **HTML5** & **Vanilla CSS3** (Modern Glassmorphism)
- **JavaScript (ES6+)** with Fetch API
- **Google Fonts** (Outfit)


---

## 🚀 Getting Started

### Prerequisites
- **Java 17** or higher
- **Node.js** (for serving the frontend)
- **Git**

### 1. Clone the Repository
```bash
git clone https://github.com/Rajan3103/SRM_Full-Stack_Challenge.git
cd SRM_Full-Stack_Challenge
```

### 2. Run the Backend
Use the included Maven Wrapper to start the server:
```powershell
# Set JAVA_HOME if needed
$env:JAVA_HOME = "C:\Program Files\Java\jdk-23"

# Start the app
.\mvnw.cmd spring-boot:run
```
The API will be live at `http://localhost:8080/bfhl`.

### 3. Run the Frontend
Serve the frontend using `http-server`:
```powershell
cd frontend
npx http-server -p 3000
```
Open [http://localhost:3000](http://localhost:3000) in your browser.

---

## 📝 API Reference
### Process Hierarchy
`POST /bfhl`

**Request Body:**
```json
{
  "data": ["A->B", "A->C", "B->D"]
}
```

**Response Body:**
```json
{
  "user_id": "karthig_Rajan_S_31032005",
  "hierarchies": [
    {
      "node": "A",
      "children": [
        { "node": "B", "children": [...] },
        { "node": "C", "children": [] }
      ]
    }
  ],
  "invalid_entries": [],
  "duplicate_edges": [],
  "summary": {
    "total_nodes": 4,
    "total_edges": 3,
    "is_cyclic": false
  }
}
```

---

## 👤 Author
**Karthig Rajan S**
- **Email**: kr4505@srmist.edu.in
- **Roll No**: RA2311003050204
- **LinkedIn**: [Karthig Rajan S](https://www.linkedin.com/in/karthig-rajan-s/)

---
*Developed for Bajaj Finserv Health Limited*
