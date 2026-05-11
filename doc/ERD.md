# 📄 ERD.md

---

## 🧭 1. 개요

본 문서는 MaintLog Android 앱의 1차 MVP 기준 Entity Relationship Diagram을 정의한다.

ERD 설계 기준:
- 요청사항과 장애를 독립 도메인으로 분리
- 현재 상태와 작업 이력을 별도 테이블로 관리
- 설정 및 검색 이력은 독립 테이블로 관리
- 화면 요구사항과 데이터 흐름을 반영한 구조로 설계

---

## 🗂 2. Entity Relationship Diagram

```mermaid
erDiagram
    REQUESTS ||--o{ REQUEST_LOGS : has
    INCIDENTS ||--o{ INCIDENT_LOGS : has

    REQUESTS {
        LONG id PK
        STRING title
        STRING description
        STRING requester_name
        STRING status
        STRING priority
        LONG created_at
        LONG updated_at
        LONG completed_at
        BOOLEAN is_deleted
    }

    REQUEST_LOGS {
        LONG id PK
        LONG request_id FK
        STRING log_type
        STRING content
        STRING author_name
        STRING from_status
        STRING to_status
        LONG created_at
    }

    INCIDENTS {
        LONG id PK
        STRING title
        STRING description
        STRING cause
        STRING impact_scope
        STRING status
        STRING severity
        STRING reporter_name
        LONG occurred_at
        LONG resolved_at
        LONG updated_at
        BOOLEAN is_deleted
    }

    INCIDENT_LOGS {
        LONG id PK
        LONG incident_id FK
        STRING log_type
        STRING content
        STRING author_name
        STRING from_status
        STRING to_status
        LONG created_at
    }

    APP_SETTINGS {
        INT id PK
        STRING user_name
        STRING default_request_sort
        STRING default_incident_sort
        BOOLEAN use_sample_data
        LONG updated_at
    }

    SEARCH_HISTORY {
        LONG id PK
        STRING keyword
        LONG searched_at
    }

---

## 🔗 3. 관계 정의
    3.1 requests ↔ request_logs
        requests 1 : N request_logs
        하나의 요청사항은 여러 개의 작업 로그를 가질 수 있다
    3.2 incidents ↔ incident_logs
        incidents 1 : N incident_logs
        하나의 장애는 여러 개의 조치 로그를 가질 수 있다
    3.3 app_settings
        앱 전역 설정을 관리하는 독립 테이블
        단일 레코드 구조로 사용
    3.4 search_history
        검색 기록을 관리하는 독립 테이블
        최근 검색어 기능 확장 시 활용 가능

---

## 🛠 4. 설계 포인트
    4.1 현재 상태와 이력 분리
        요청/장애의 현재 상태는 메인 테이블에서 관리
        상태 변경 및 작업 과정은 로그 테이블에 누적 기록
    4.2 요청 / 장애 구조 통일
        요청 로그와 장애 로그의 컬럼 구조를 유사하게 유지
        구현 및 유지보수 효율을 높임
    4.3 독립 설정 관리
        앱 설정은 별도 테이블에서 관리하여 화면과 분리
        설정값 유지 및 초기화에 유리
    4.4 검색 UX 확장 고려
        최근 검색어 기능을 위한 search_history 테이블 별도 정의
        1차 MVP에서는 선택 적용 가능

---

## 📱 5. 화면 연계 관점
    홈 화면
        requests
        incidents
    요청사항 목록 / 상세 / 등록
        requests
        request_logs
    장애 목록 / 상세 / 등록
        incidents
        incident_logs
    검색 화면
        requests
        incidents
        search_history
    설정 화면
        app_settings

---

## 🚀 6. 향후 확장 가능 구조

현재 ERD는 1차 MVP 기준이며, 향후 다음 엔티티 확장이 가능하다.
    ATTACHMENTS
    USERS
    NOTIFICATIONS
    AUDIT_HISTORY

확장 방향 예시:
    파일 첨부 기능 추가 시 ATTACHMENTS
    사용자 권한 관리 시 USERS
    상태 변경 알림 기능 시 NOTIFICATIONS
    세부 변경 이력 추적 시 AUDIT_HISTORY

## 📌 7. 한 줄 요약
    MaintLog의 ERD는 요청사항과 장애를 중심으로 현재 상태와 처리 로그를 분리하고, 설정 및 검색 이력까지 고려한 업무 흐름 기반 구조로 설계한다.