# 📌 MaintLog Android

유지보수 업무에서 발생하는 요청사항, 장애, 작업 이력을 체계적으로 관리하기 위한 Android 기반 업무 관리 시스템

---

## 🧭 Overview

MaintLog는 유지보수 과정에서 발생하는 다양한 업무를  
하나의 시스템에서 관리할 수 있도록 설계된 앱입니다.

기존의 메신저, 엑셀, 문서 기반 관리 방식에서 벗어나  
**요청 → 처리 → 이력 관리**의 흐름을 구조적으로 관리하는 것을 목표로 합니다.

---

## ✨ Key Features (MVP)

- 요청사항 등록 / 조회 / 상태 변경
- 작업 로그 기록 및 이력 관리
- 장애 등록 / 조회 / 상태 관리
- 상태 기반 필터 및 검색 기능
- 로컬 DB 기반 데이터 관리

---

## 🏗 Architecture

본 프로젝트는 **Clean Architecture + MVVM 패턴**을 기반으로 구성되어 있습니다.

### Layer 구조

- **Presentation**
  - Activity / Fragment
  - ViewModel
  - UI State 관리

- **Domain**
  - UseCase
  - Entity (Model)
  - Repository Interface

- **Data**
  - Local (Room)
  - Repository 구현체
  - Mapper

👉 플랫폼(Android)에 종속되지 않는 Domain Layer를 통해  
향후 확장 가능한 구조를 고려했습니다.

---

## 🛠 Tech Stack

- **Language**: Kotlin
- **Architecture**: Clean Architecture, MVVM
- **DI**: Hilt
- **Async**: Coroutine, StateFlow
- **Database**: Room
- **UI**: ViewBinding (or DataBinding)
- **Navigation**: Navigation Component

---

## 📁 Project Structure

```
app/
├── data/
├── domain/
├── presentation/
├── core/
└── di/
```

---

## 📄 Documents

- Project Plan: docs/PROJECT_PLAN.md
- Feature Spec: docs/FEATURE_SPEC.md
- Architecture: docs/ARCHITECTURE.md

---

## 🚀 MVP Scope

- 요청사항 관리 (등록 / 조회 / 상태 변경)
- 작업 로그 관리
- 장애 관리
- 검색 및 필터
- 로컬 데이터 저장

---

## 🔥 Future Work

- 파일 첨부 기능
- 알림 기능 (상태 변경)
- 사용자 권한 관리
- 통계 및 대시보드
- Web 관리자 시스템 확장

---

## 📌 Key Points

- 단순 CRUD가 아닌 **업무 흐름 중심 설계**
- 상태 기반 관리 구조 적용
- 작업 이력(로그) 중심 데이터 모델 설계
- 확장 가능한 Clean Architecture 적용

---

## 👨‍💻 Author

- Android Developer Portfolio Project
