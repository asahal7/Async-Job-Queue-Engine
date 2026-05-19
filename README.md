# Async Job Queue Engine

A distributed job processing system built in Java and Spring Boot, designed around the guarantees required for reliable async execution — idempotent processing, formal job state management, and at-least-once delivery semantics.

---

## Architecture

Two-service architecture communicating via RabbitMQ:

```
┌─────────────────┐        ┌─────────────┐        ┌──────────────────┐
│   API Service   │───────▶│  RabbitMQ   │───────▶│  Worker Service  │
│  (Job Submit)   │        │   (Queue)   │        │  (Job Processor) │
└─────────────────┘        └─────────────┘        └──────────────────┘
         │                                                  │
         └──────────────┬───────────────────────────────────┘
                        ▼
                  ┌──────────────┐
                  │  PostgreSQL  │
                  │ (Job State)  │
                  └──────────────┘
```

**API Service** — accepts job submission requests, persists initial job state, and publishes messages to RabbitMQ.

**Worker Service** — listens for messages on the queue, processes jobs, and updates job state in PostgreSQL.

---

## Job State Machine

Every job transitions through a formal state machine:

```
PENDING ──▶ PROCESSING ──▶ COMPLETED
                │
                └──────────▶ FAILED
```

State is persisted in PostgreSQL, ensuring durability across service restarts.

---

## Key Engineering Decisions

**Idempotent job processing** — each job carries a unique ID. The worker checks for existing state before processing, making redelivered messages safe to handle without side effects.

**At-least-once delivery** — RabbitMQ is configured to redeliver unacknowledged messages. Combined with idempotency, this guarantees every job is processed without risk of duplication.

**Decoupled services** — the API service never calls the worker directly. All communication flows through the queue, allowing each service to scale and fail independently.

**Durable job state** — PostgreSQL acts as the source of truth for job state, not the queue. This means job status is queryable and survives broker restarts.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Messaging | RabbitMQ |
| Database | PostgreSQL |
| ORM | Spring Data JPA |
| Orchestration | Docker Compose |

---

## Running Locally

**Prerequisites:** Docker and Docker Compose installed.

```
git clone https://github.com/asahal7/Async-Job-Queue-Engine.git
cd Async-Job-Queue-Engine
docker-compose -f infrastructure/docker/docker-compose.yml up --build
```

All services and infrastructure spin up via Docker Compose. No manual database or broker setup required.

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/jobs` | Submit a new job |
| GET | `/jobs/{id}` | Get job status by ID |

**POST `/jobs` request body:**
```json
{
  "type": "your-job-type",
  "payload": "your-job-payload"
}
```

---

## Project Structure

```
├── api-service/                    # Job submission and REST API
├── worker-service/                 # Job processing and queue listeners
└── infrastructure/
    └── docker/
        └── docker-compose.yml      # Full local orchestration
```

---

## Connect

- LinkedIn: [abdimaalik-sahal](https://linkedin.com/in/abdimaalik-sahal-33bbab336/)
- GitHub: [asahal7](https://github.com/asahal7)
