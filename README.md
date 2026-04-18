# Netflix Data Analytics & Automation Pipeline

A full-stack data engineering project that automates the ingestion, processing, and visualization of global Netflix content datasets.

## 🚀 Overview
This project demonstrates a modular architecture for data automation:
1.  **Backend (Java/Spring Boot)**: Handles the core data modeling and business logic for content classification.
2.  **Dashboard (Python/Streamlit)**: Provides an interactive UI for real-time data visualization and trend analysis.
3.  **Database (H2/SQL)**: Ensures persistent storage of uploaded records for historical tracking.

---

## 🛠️ Tech Stack
- **Languages**: Java (JDK 17), Python 3.x
- **Frameworks**: Spring Boot, Spring Data JPA
- **Libraries**: Pandas (Data Processing), Plotly (Visuals), OpenCSV
- **Database**: H2 / SQLite
- **Tools**: Maven, Streamlit

---

## 📂 Project Structure
```text
netflix-analytics-project/
├── backend-engine/         # Java Spring Boot source code
│   ├── src/main/java       # JPA Entities and REST Controllers
│   └── pom.xml             # Dependency Management
├── analytics-dashboard/    # Python Streamlit application
│   └── app.py              # UI and Visualization logic
└── data/                   # Sample CSV datasets
