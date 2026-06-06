---
name: egovframe-rest
agent: developer
triggers:
  - 전자정부
  - egovframe
  - egov
  - 표준프레임워크
  - 공공
description: >-
  한국 전자정부 표준프레임워크(eGovFrame) 규약에 맞춰 REST API 백엔드 코드를 생성·검토한다.
  전자정부 프레임워크, 표준프레임워크, eGovFrame, egovframe 프로젝트에서 Controller / Service /
  ServiceImpl / DAO / Mapper / VO 를 만들거나, 게시판·CRUD·REST API·공통 컴포넌트를 개발하거나,
  적용기준(아키텍처 규칙)에 맞는지 검토할 때 반드시 이 스킬을 사용한다. 사용자가 명시적으로
  "전자정부 규약대로", "표준프레임워크로" 라고 말하지 않더라도, 프로젝트가 eGovFrame 기반이거나
  org.egovframe / egovframework 패키지, EgovAbstractServiceImpl, EgovAbstractDAO 가 보이면 사용한다.
  3.x(전통 Spring MVC + XML)와 4.x(Spring Boot) 두 버전을 모두 지원한다.
---

# 전자정부 표준프레임워크 REST 개발 스킬

이 스킬은 코딩 에이전트가 **일반적인 Spring 관례가 아니라 전자정부 표준프레임워크(eGovFrame)의
적용기준**에 맞춰 REST API 백엔드 코드를 생성하도록 만든다.

가만 두면 에이전트는 "Spring스럽지만 전자정부 규약은 안 맞는" 코드를 만든다(예: ServiceImpl이
`EgovAbstractServiceImpl`을 상속하지 않음, 인터페이스 없이 Service 구현, DAO 규약 무시).
이 스킬의 목적은 그 간극을 메우는 것이다. 적용점검(호환성 확인) 통과를 목표로 한다.

## 작업 절차

### 0단계 — 버전 판별 (가장 먼저)

3.x와 4.x는 사실상 다른 프레임워크다. 한 코드에 둘을 섞으면 안 된다. 다음 순서로 판별한다.

1. **프로젝트에서 자동 판별**을 먼저 시도한다.
   - `build.gradle` / `pom.xml`에 `spring-boot` 스타터가 있고 실행환경 의존성이
     `org.egovframe.rte.*` 이면 → **4.x**
   - `web.xml` + `dispatcher-servlet.xml` 등 XML 설정이 있고 실행환경 의존성이
     `org.egovframework.rte.*` 이면 → **3.x**
   - (실행환경 3.10 이하 버전은 `egovframework.rte`, 그 이후는 `egovframe.rte`로 패키지가 바뀌었다.)
2. 판별이 안 되면 사용자에게 **딱 한 번** 묻는다: "전자정부 표준프레임워크 3.x(전통 Spring MVC)인가요, 4.x(Spring Boot)인가요?"
3. 버전을 확정한 뒤에만 진행한다.

판별 결과에 따라 **해당 버전의 reference와 templates만** 읽는다:

- **3.x** → `references/v3-rest.md` + `templates/v3/`
- **4.x** → `references/v4-rest.md` + `templates/v4/`

버전 무관 공통 규칙은 항상 `references/architecture-rules.md`와
`references/naming-conventions.md`를 따른다.

### 1단계 — 레이어 구조 확정

전자정부 프레임워크는 레이어드 아키텍처를 강제한다. REST 백엔드 기준 레이어:

```
web        (Controller)   ← HTTP/REST 진입점. 비즈니스 로직 없음
service    (Service 인터페이스)
service.impl (ServiceImpl) ← 비즈니스 로직. EgovAbstractServiceImpl 상속
service.impl 또는 mapper   (DAO / MyBatis Mapper) ← 데이터 액세스
service (VO/DTO)           ← 계층 간 데이터 운반 객체
```

레이어를 건너뛰지 않는다(Controller가 직접 DAO를 호출하지 않는다).

### 2단계 — 컴포넌트 생성

요청(예: "Sample 게시판 CRUD REST API 만들어줘")에 대해 다음 컴포넌트를 **세트로** 생성한다.
각 컴포넌트는 해당 버전 `templates/`의 골격을 채워 만든다.

| 컴포넌트 | 파일명 예 | 핵심 규약 |
|---|---|---|
| Controller | `EgovSampleController.java` | `@RestController`, `@RequestMapping`, Service는 인터페이스 타입으로 주입 |
| Service 인터페이스 | `EgovSampleService.java` | 모든 ServiceImpl은 1:1 대응 인터페이스를 구현 |
| ServiceImpl | `EgovSampleServiceImpl.java` | `@Service`, **`EgovAbstractServiceImpl` 상속** |
| DAO / Mapper | `EgovSampleDAO.java` 또는 `EgovSampleMapper.java` | 3.x: `EgovAbstractDAO` / 4.x: `@Mapper` 인터페이스 |
| VO | `SampleVO.java`, `SampleDefaultVO.java` | 계층 간 데이터 운반, `Serializable` |
| Mapper XML | `EgovSample_SQL.xml` | SQL은 자바 코드에서 분리, 매퍼 XML에 작성 |

### 3단계 — 자기 점검

생성 후 `references/architecture-rules.md`의 체크리스트로 위반 여부를 점검한다.

## 절대 규칙 (버전 공통)

이 규칙들은 적용점검(호환성 확인)에서 위반 시 탈락 사유가 된다. 어기지 않는다.

- **실행환경 변경 금지**: 다운로드한 전자정부 실행환경 라이브러리를 임의로 수정하지 않는다.
  확장이 필요하면 **상속**으로 처리한다.
- **서비스 아키텍처 규칙**: 비즈니스 클래스는 `xxxServiceImpl` 형태로, 대응 인터페이스
  `xxxService`를 구현하고 `EgovAbstractServiceImpl`을 상속한다.
- **데이터 액세스 규칙**: DAO/Mapper를 통해서만 DB에 접근한다. SQL은 매퍼 XML로 분리한다.
- **의존성 출처**: 실행환경 라이브러리는 Maven Central이 아니라 전자정부 전용 저장소
  (`https://maven.egovframe.go.kr/...` 또는 사내 Nexus)에서 받는다.
- **버전 혼용 금지**: 한 프로젝트 안의 모든 코드는 동일한 실행환경 버전과 아키텍처 규칙을 따른다.

## 참고 파일 안내

- `references/architecture-rules.md` — 적용기준(아키텍처 규칙) 상세 + 점검 체크리스트. **항상 참조.**
- `references/naming-conventions.md` — 패키지/클래스/파일/메서드 네이밍. **항상 참조.**
- `references/v3-rest.md` — 3.x(XML 설정, 전통 Spring MVC) REST 상세. 버전이 3.x일 때만.
- `references/v4-rest.md` — 4.x(Spring Boot) REST 상세. 버전이 4.x일 때만.
- `templates/v3/`, `templates/v4/` — 버전별 코드 골격.

> 주의: 전자정부 표준프레임워크는 버전이 계속 갱신된다. 정확한 라이브러리 버전·적용기준 세부는
> 공식 포털(egovframe.go.kr)의 최신 "적용기준/호환성 가이드" 문서로 검증하는 것이 좋다.
