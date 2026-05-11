# AGENTS.md

## Project
MaintLog Android는 유지보수 업무에서 발생하는 요청사항, 장애, 작업 로그를 관리하는 Android 네이티브 포트폴리오 프로젝트입니다.

## Primary Goal
Android 개발자 포트폴리오용 프로젝트로, Clean Architecture + MVVM 기반의 구조적 설계를 우선합니다.

## Important Documents
Before implementing code, read these documents:

- docs/PROJECT_PLAN.md
- docs/FEATURE_SPEC.md
- docs/DATA_MODEL.md
- docs/ERD.md
- docs/WIREFRAME.md
- README.md

## Tech Stack
- Kotlin
- Android Native
- MVVM
- Clean Architecture
- Hilt
- Room
- Coroutine / Flow
- ViewBinding or DataBinding
- Navigation Component

## Architecture Rules
- Separate Presentation, Domain, and Data layers.
- Domain layer must not depend on Android framework classes.
- Use Repository interfaces in the Domain layer.
- Implement Repository classes in the Data layer.
- UI logic belongs in ViewModel, not Fragment or Activity.
- DB schema must follow docs/DATA_MODEL.md and docs/ERD.md.

## Implementation Rules
- Do not start from UI first.
- Start from domain models, Room entities, DAO, repository, use cases, then UI.
- State changes must create logs.
- Use soft delete with is_deleted instead of physical delete.
- Keep request and incident structures consistent where possible.

## Naming Rules
- Request-related classes use `Request` prefix.
- Incident-related classes use `Incident` prefix.
- UI state classes end with `UiState`.
- ViewModel classes end with `ViewModel`.

## Commit Style
Use conventional commit style:

- feat:
- fix:
- refactor:
- docs:
- chore:
- test:

## Do Not
- Do not introduce Flutter code.
- Do not use server API until local Room-based MVP is complete.
- Do not skip documentation consistency checks.