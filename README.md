# ClauseClear

**Understand the fine print before you agree.**

ClauseClear is an AI-powered web application that simplifies lengthy Terms & Conditions and Privacy Policies into concise, easy-to-understand explanations using Java, Spring Boot, and Google's Gemini API.

## Overview

Online agreements often contain important information about subscriptions, cancellation policies, refunds, data collection, and user obligations. ClauseClear helps users identify these important conditions without having to interpret the entire document themselves.

Paste the text of an agreement and receive a concise analysis highlighting important terms, conditions worth checking, and an overall takeaway.

## Features

- **AI-powered analysis:** Uses Google's Gemini API to analyze agreement text.
- **Key clause identification:** Highlights important costs, subscriptions, automatic renewals, cancellation deadlines, refund restrictions, and privacy conditions when present.
- **Plain-English explanations:** Makes complex contractual language easier to understand.
- **Things to check:** Identifies important missing details or conditions worth reviewing.
- **Concise results:** Produces a structured response designed for quick reading.
- **Web interface:** Provides a browser-based interface backed by a Spring Boot application.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend development |
| Spring Boot | Application framework and REST API |
| Google Gemini API | AI-powered document analysis |
| Google GenAI Java SDK | Gemini integration |
| Gradle | Build and dependency management |
| HTML, CSS, JavaScript | Frontend interface |

## How It Works

1. The user provides Terms & Conditions or Privacy Policy text through the web interface.
2. The frontend sends the text to the Spring Boot backend.
3. The backend passes the text to Gemini with instructions to identify important conditions and explain them clearly.
4. The application returns a concise analysis to the user.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21
- A Google Gemini API key
- Git

### 1. Clone the repository

```bash
git clone https://github.com/ashwaniy-sketcher/ClauseClear.git
cd ClauseClear
```

### 2. Configure your API key

Set the `GEMINI_API_KEY` environment variable before starting the application.

**Windows PowerShell:**

```powershell
$env:GEMINI_API_KEY="YOUR_GEMINI_API_KEY"
```

Replace the placeholder with your own API key. This sets the variable for the current PowerShell session.

Alternatively, configure `GEMINI_API_KEY` in your IDE's run configuration.

**Security:** Never commit your actual API key to GitHub or place it directly in your source code.

### 3. Run the application

On Windows:

```powershell
.\gradlew.bat bootRun
```

On macOS or Linux:

```bash
./gradlew bootRun
```

Once the application starts, open:

http://localhost:8080

### 4. Analyze a document

Use the web interface to submit agreement text, or call the REST endpoint directly.

**Endpoint:** `POST /analyze`

**Example using PowerShell:**

```powershell
Invoke-RestMethod `
  -Uri "http://localhost:8080/analyze" `
  -Method Post `
  -ContentType "text/plain" `
  -Body "We collect your email and device information."
```

The endpoint returns a text response containing the AI-generated analysis.

## Project Structure

```text
ClauseClear/
├── src/
│   ├── main/
│   │   ├── java/com/example/clauseclear/
│   │   │   ├── ClauseClearApplication.java
│   │   │   ├── DocumentController.java
│   │   │   └── DocumentAnalysisService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           ├── index.html
│   │           ├── style.css
│   │           └── script.js
│   └── test/
├── gradle/
├── build.gradle
├── gradlew
└── README.md
```

## Current Status

The application has a working Spring Boot endpoint integrated with Gemini AI. The endpoint has been tested locally and returned a successful HTTP 200 response.

ClauseClear is an ongoing project, and additional testing and improvements are planned.

## Future Improvements

- Structured analysis with categorized clauses and references to the original text.
- PDF upload and document extraction.
- Automated tests for API behavior and error handling.
- Deployment so users can access the application online.
- Further improvements to analysis reliability and user experience.

## Disclaimer

ClauseClear is an informational tool, not a substitute for professional legal advice. AI-generated explanations may be incomplete or incorrect. Always consult the original agreement before making decisions.

## Author

**Ashwaniy Patel**

GitHub: [@ashwaniy-sketcher](https://github.com/ashwaniy-sketcher)

---

If you find this project useful, consider giving the repository a ⭐ on GitHub.
