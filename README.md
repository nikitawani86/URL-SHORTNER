# URL Shortener Service 🚀

A simple and efficient **URL Shortener backend system** built using **Spring Boot, PostgreSQL, Redis, and Docker**.

The system converts long URLs into short, shareable links and supports fast redirection using caching.

---

## ⚙️ Tech Stack

* Java 21
* Spring Boot
* PostgreSQL
* Redis
* Docker
* Maven

---

## ✨ Features

* 🔗 Shorten long URLs into unique short codes
* ⚡ Fast redirection using Redis cache
* 🗄️ Persistent storage using PostgreSQL
* 🔁 Supports 301/302 redirects
* 📊 Click tracking (counts number of redirects)
* 🐳 Containerized using Docker & Docker Compose

---

## 🚀 How to Run

### 1. Clone the project

```bash
git clone https://github.com/your-username/url-shortener.git
cd url-shortener
```

---

### 2. Run using Docker Compose

```bash
docker compose up --build
```

---

## 🌐 Access

* Application: `http://localhost:8080`
* PostgreSQL: `localhost:5432`
* Redis: `localhost:6379`

---

## 📡 API Endpoints

### Shorten URL

```http
POST /api/shorten
```

**Request:**

```json
{
  "url": "https://example.com"
}
```

**Response:**

```json
{
  "shortUrl": "http://localhost:8080/abc123"
}
```

---

### Redirect URL

```http
GET /{shortCode}
```

Redirects to original URL.

---

## 📊 Click Tracking

* Each redirect increments click count
* Helps track URL usage

---

## 🐳 Architecture

```
Spring Boot App
   ↓
Redis (Cache)
   ↓
PostgreSQL (Database)
```

---

## 👨‍💻 Author

Nikita Wani
Backend Developer | Spring Boot | Microservices Learner

---

⭐ If you like this project, give it a star!
