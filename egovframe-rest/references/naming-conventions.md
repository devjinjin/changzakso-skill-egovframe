# 네이밍 컨벤션

전자정부 표준프레임워크 관례 기반 네이밍. 버전 공통으로 적용한다.

## 패키지 구조

```
egovframework.{업무영역}.{기능}
└─ 또는 4.x 신규 패키지: kr.go.{기관}.{업무영역}.{기능}  (조직 표준에 따름)
```

기능 단위 하위 패키지 권장 구조(REST 백엔드):

```
egovframework.example.sample
├── web         // Controller
├── service     // Service 인터페이스, VO
│   └── impl    // ServiceImpl, DAO
└── mapper      // (4.x) MyBatis Mapper 인터페이스
```

> `Egov` 접두사는 전자정부 샘플/공통 컴포넌트 관례다. 사내 표준이 별도 접두사를 쓰면 그것을 따른다.
> 아래 표는 전자정부 샘플 관례(`Egov` 접두사) 기준이다.

## 클래스 네이밍

| 종류 | 패턴 | 예 |
|---|---|---|
| Controller | `Egov{기능}Controller` | `EgovSampleController` |
| Service 인터페이스 | `Egov{기능}Service` | `EgovSampleService` |
| ServiceImpl | `Egov{기능}ServiceImpl` | `EgovSampleServiceImpl` |
| DAO (3.x) | `Egov{기능}DAO` | `EgovSampleDAO` |
| Mapper (4.x) | `Egov{기능}Mapper` | `EgovSampleMapper` |
| VO/DTO | `{도메인}VO`, `{도메인}DefaultVO` | `SampleVO`, `SampleDefaultVO` |
| 검색 조건 VO | `{도메인}DefaultVO` 또는 `SearchVO` | `SampleDefaultVO` |

## 메서드 네이밍 (CRUD)

서비스/DAO 메서드는 일관된 동사로 시작한다.

| 동작 | 패턴 | 예 |
|---|---|---|
| 목록 조회 | `select{도메인}List` | `selectSampleList` |
| 목록 카운트 | `select{도메인}ListTotCnt` | `selectSampleListTotCnt` |
| 단건 조회 | `select{도메인}` | `selectSample` |
| 등록 | `insert{도메인}` | `insertSample` |
| 수정 | `update{도메인}` | `updateSample` |
| 삭제 | `delete{도메인}` | `deleteSample` |

## Mapper XML / SQL ID

- 파일명: `Egov{기능}_SQL_{DB}.xml` 또는 `Egov{기능}_SQL.xml`
- namespace: Mapper 인터페이스 FQCN(4.x) 또는 DAO 식별자.
- SQL id: 서비스/DAO 메서드명과 일치시킨다(`selectSampleList` 등).

## REST URL 규칙

- 리소스 복수형 명사 사용: `/sample`, `/sample/{id}`
- HTTP 메서드로 동작 구분: GET(조회) / POST(등록) / PUT(수정) / DELETE(삭제)
- 컨트롤러 클래스에 공통 prefix를 `@RequestMapping("/sample")`로 둔다.
