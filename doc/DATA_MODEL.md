# 📄 DATA_MODEL.md

---

## 🧭 1. 개요

본 문서는 MaintLog Android 앱의 1차 MVP 기준 데이터 모델을 정의한다.

본 프로젝트의 데이터 구조는 다음 원칙을 따른다.

- 요청사항과 장애를 각각 독립된 업무 도메인으로 관리한다
- 현재 상태는 메인 테이블에서 관리한다
- 상태 변경 및 처리 과정은 로그 테이블에 누적 기록한다
- 홈, 목록, 상세, 검색, 설정 화면에서 필요한 데이터를 기준으로 설계한다

---

## 🧱 2. 테이블 구성

1차 MVP 기준 주요 테이블은 다음과 같다.

- requests
- request_logs
- incidents
- incident_logs
- app_settings
- search_history (선택)

---

## 3. 테이블 상세 정의

### 3.1 requests

요청사항 메인 테이블

| 컬럼명 | 타입 | 설명 | 비고 |
|--------|------|------|------|
| id | Long | 요청 ID | PK, Auto Increment |
| title | String | 요청 제목 | NOT NULL |
| description | String | 요청 내용 | NOT NULL |
| requester_name | String | 요청자명 | NOT NULL |
| status | String | 요청 상태 | NOT NULL |
| priority | String | 우선순위 | NOT NULL |
| created_at | Long | 등록 일시 | NOT NULL |
| updated_at | Long | 수정 일시 | NOT NULL |
| completed_at | Long? | 완료 일시 | Nullable |
| is_deleted | Boolean | 삭제 여부 | 기본값 false |

#### 용도
- 홈 화면의 최근 요청사항
- 요청 목록 화면
- 요청 상세 화면
- 검색 결과 화면

#### 비고
- 실제 삭제 대신 soft delete 방식 사용
- 요청 상태의 현재값은 본 테이블에서 관리

---

### 3.2 request_logs

요청사항 작업 로그 테이블

| 컬럼명 | 타입 | 설명 | 비고 |
|--------|------|------|------|
| id | Long | 로그 ID | PK, Auto Increment |
| request_id | Long | 요청 ID | FK |
| log_type | String | 로그 유형 | NOT NULL |
| content | String | 로그 내용 | NOT NULL |
| author_name | String | 작성자명 | NOT NULL |
| from_status | String? | 변경 전 상태 | Nullable |
| to_status | String? | 변경 후 상태 | Nullable |
| created_at | Long | 생성 일시 | NOT NULL |

#### 용도
- 요청 상세 화면의 작업 로그 목록
- 상태 변경 이력 조회
- 처리 과정 기록

#### log_type 예시
- COMMENT
- STATUS_CHANGED
- SYSTEM

#### 비고
- 상태 변경 시 request_logs에 자동 기록 가능
- append-only 구조로 운영

---

### 3.3 incidents

장애 메인 테이블

| 컬럼명 | 타입 | 설명 | 비고 |
|--------|------|------|------|
| id | Long | 장애 ID | PK, Auto Increment |
| title | String | 장애 제목 | NOT NULL |
| description | String | 장애 내용 | NOT NULL |
| cause | String? | 장애 원인 | Nullable |
| impact_scope | String? | 영향 범위 | Nullable |
| status | String | 장애 상태 | NOT NULL |
| severity | String | 장애 중요도 | NOT NULL |
| reporter_name | String | 등록자명 | NOT NULL |
| occurred_at | Long | 발생 일시 | NOT NULL |
| resolved_at | Long? | 해결 일시 | Nullable |
| updated_at | Long | 수정 일시 | NOT NULL |
| is_deleted | Boolean | 삭제 여부 | 기본값 false |

#### 용도
- 홈 화면의 최근 장애
- 장애 목록 화면
- 장애 상세 화면
- 검색 결과 화면

#### 비고
- 장애 특성상 원인(cause), 영향 범위(impact_scope) 필드 분리
- 현재 장애 상태는 본 테이블에서 관리

---

### 3.4 incident_logs

장애 조치 로그 테이블

| 컬럼명 | 타입 | 설명 | 비고 |
|--------|------|------|------|
| id | Long | 로그 ID | PK, Auto Increment |
| incident_id | Long | 장애 ID | FK |
| log_type | String | 로그 유형 | NOT NULL |
| content | String | 조치 내용 | NOT NULL |
| author_name | String | 작성자명 | NOT NULL |
| from_status | String? | 변경 전 상태 | Nullable |
| to_status | String? | 변경 후 상태 | Nullable |
| created_at | Long | 생성 일시 | NOT NULL |

#### 용도
- 장애 상세 화면의 조치 로그 목록
- 장애 상태 변경 이력
- 장애 대응 과정 기록

#### log_type 예시
- COMMENT
- STATUS_CHANGED
- SYSTEM

#### 비고
- request_logs와 동일 패턴으로 관리
- 구조를 통일하면 구현과 유지보수에 유리

---

### 3.5 app_settings

앱 설정 테이블

| 컬럼명 | 타입 | 설명 | 비고 |
|--------|------|------|------|
| id | Int | 설정 ID | PK, 고정값 1 |
| user_name | String | 사용자명 | NOT NULL |
| default_request_sort | String | 요청 기본 정렬값 | NOT NULL |
| default_incident_sort | String | 장애 기본 정렬값 | NOT NULL |
| use_sample_data | Boolean | 샘플 데이터 사용 여부 | 기본값 false |
| updated_at | Long | 수정 일시 | NOT NULL |

#### 용도
- 설정 화면
- 기본 정렬값 유지
- 초기 샘플 데이터 사용 여부 관리

#### 비고
- 단일 레코드 구조로 사용
- SharedPreferences로도 가능하지만, 문서상 명시적 데이터 구조를 위해 테이블로 정의

---

### 3.6 search_history (선택)

검색 이력 테이블

| 컬럼명 | 타입 | 설명 | 비고 |
|--------|------|------|------|
| id | Long | 검색 ID | PK, Auto Increment |
| keyword | String | 검색어 | NOT NULL |
| searched_at | Long | 검색 시각 | NOT NULL |

#### 용도
- 검색 화면의 최근 검색어
- 검색 UX 개선

#### 비고
- 1차 MVP에서 생략 가능
- 최근 검색어 기능이 필요할 경우 추가

---

## 4. 상태값 정의

### 4.1 RequestStatus

| 값 | 설명 |
|----|------|
| REGISTERED | 등록 |
| IN_PROGRESS | 진행중 |
| COMPLETED | 완료 |
| HOLD | 보류 |
| CANCELED | 취소 |

---

### 4.2 RequestPriority

| 값 | 설명 |
|----|------|
| LOW | 낮음 |
| MEDIUM | 중간 |
| HIGH | 높음 |

---

### 4.3 IncidentStatus

| 값 | 설명 |
|----|------|
| OPEN | 발생 |
| IN_PROGRESS | 조치중 |
| RESOLVED | 해결 |
| CLOSED | 종료 |

---

### 4.4 IncidentSeverity

| 값 | 설명 |
|----|------|
| LOW | 낮음 |
| MEDIUM | 중간 |
| HIGH | 높음 |
| CRITICAL | 치명 |

---

### 4.5 LogType

| 값 | 설명 |
|----|------|
| COMMENT | 일반 메모 |
| STATUS_CHANGED | 상태 변경 |
| SYSTEM | 시스템 생성 로그 |

---

## 🔗 5. 관계 정의

### 5.1 requests ↔ request_logs
- requests 1 : N request_logs
- 하나의 요청사항은 여러 개의 작업 로그를 가진다

### 5.2 incidents ↔ incident_logs
- incidents 1 : N incident_logs
- 하나의 장애는 여러 개의 조치 로그를 가진다

### 5.3 app_settings
- 독립 테이블
- 앱 전역 설정 1건 관리

### 5.4 search_history
- 독립 테이블
- 검색 이력 관리

---

## 📱 6. 화면별 데이터 사용 매핑

### 홈 화면
- requests
- incidents

사용 목적:
- 상태별 건수 집계
- 최근 요청/장애 목록 조회

---

### 요청 목록 화면
- requests

사용 목적:
- 상태 필터
- 정렬
- 목록 조회

---

### 요청 상세 화면
- requests
- request_logs

사용 목적:
- 요청 기본 정보 조회
- 작업 로그 목록 조회
- 상태 변경 이력 확인

---

### 요청 등록/수정 화면
- requests

사용 목적:
- 신규 등록
- 기존 요청 수정

---

### 장애 목록 화면
- incidents

사용 목적:
- 상태/중요도 필터
- 목록 조회

---

### 장애 상세 화면
- incidents
- incident_logs

사용 목적:
- 장애 기본 정보 조회
- 원인/영향 범위 확인
- 조치 로그 조회
- 상태 변경 이력 확인

---

### 장애 등록/수정 화면
- incidents

사용 목적:
- 신규 장애 등록
- 기존 장애 수정

---

### 검색 화면
- requests
- incidents
- search_history (선택)

사용 목적:
- 통합 검색
- 최근 검색어

---

### 설정 화면
- app_settings

사용 목적:
- 사용자명 저장
- 기본 정렬값 저장
- 샘플 데이터 설정

---

## 🛠 7. 설계 포인트

### 7.1 현재 상태와 이력 분리
- 현재 상태는 메인 테이블(requests, incidents)에서 관리
- 변경 이력은 로그 테이블(request_logs, incident_logs)에 별도 저장

### 7.2 append-only 로그 구조
- 로그는 수정/삭제보다 누적 저장을 기본으로 한다
- 작업 흐름 추적에 유리하다

### 7.3 요청/장애 구조 통일
- request_logs와 incident_logs 구조를 최대한 동일하게 유지
- 개발 생산성과 유지보수성을 높인다

### 7.4 화면 중심 설계 반영
- 각 테이블은 실제 화면 요구사항을 기준으로 정의
- 과도한 정규화보다 구현 효율을 우선

---

## 🚀 8. 향후 확장 가능 항목

향후 아래 테이블 확장이 가능하다.

- attachments
- users
- notifications
- audit_history

### 확장 예시
- 파일 첨부 기능 추가 시 attachments
- 사용자 권한 관리 시 users
- 상태 변경 알림 기능 시 notifications
- 상세 변경 추적 시 audit_history

---

## 📌 9. 한 줄 요약

> MaintLog의 데이터 모델은 요청사항과 장애를 중심으로 현재 상태와 처리 이력을 분리하여 관리하는 업무 흐름 기반 구조로 설계한다.