# 📄 ERD.md

---

## 🧭 Entity Relationship Diagram (ERD)

```mermaid
erDiagram

    REQUEST ||--o{ REQUEST_LOG : has
    INCIDENT ||--o{ INCIDENT_LOG : has

    REQUEST {
        LONG id PK
        STRING title
        STRING description
        STRING requester
        STRING status
        STRING priority
        LONG createdAt
        LONG updatedAt
    }

    REQUEST_LOG {
        LONG id PK
        LONG requestId FK
        STRING content
        STRING author
        STRING status
        LONG createdAt
    }

    INCIDENT {
        LONG id PK
        STRING title
        STRING description
        STRING status
        STRING severity
        LONG createdAt
        LONG resolvedAt
    }

    INCIDENT_LOG {
        LONG id PK
        LONG incidentId FK
        STRING content
        STRING author
        LONG createdAt
    }

```

---

## 📌 설명

- Request ↔ RequestLog : 1:N 관계
- Incident ↔ IncidentLog : 1:N 관계
- 로그는 append-only 구조로 설계
- 상태 변화 및 작업 과정은 로그로 추적 가능

---

## 🚀 확장 고려

추후 아래 엔티티 확장 가능:

- USER
- ATTACHMENT
- NOTIFICATION
- HISTORY

---

## 📌 한 줄 요약

> 유지보수 업무의 요청과 장애를 중심으로, 로그 기반 이력 관리 구조를 시각적으로 표현한 ERD
