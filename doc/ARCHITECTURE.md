# 📄 ARCHITECTURE.md

---

## 1. 아키텍처 개요

- Clean Architecture 기반
- 멀티플랫폼 대응 (Flutter)

---

## 2. 레이어 구조

### Presentation Layer
- UI (Widget)
- ViewModel / State

### Domain Layer
- UseCase
- Entity
- Repository Interface

### Data Layer
- Repository Implementation
- Local Data Source (DB)
- Remote Data Source (추후 확장)

---

## 3. 데이터 흐름

UI → ViewModel → UseCase → Repository → DataSource

---

## 4. 주요 기술

- Flutter
- 상태관리 (Riverpod / Bloc 등)
- SQLite (로컬 DB)
- Clean Architecture

---

## 5. 패키지 구조

lib/
├── presentation/
├── domain/
├── data/
├── core/
