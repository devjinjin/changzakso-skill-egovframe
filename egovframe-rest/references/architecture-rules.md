# 전자정부 표준프레임워크 적용기준 (아키텍처 규칙)

버전 공통으로 적용되는 핵심 규칙과 자기 점검 체크리스트. 이 문서는 항상 참조한다.

## 1. 레이어드 아키텍처 규칙

전자정부 표준프레임워크 실행환경은 화면처리 / 업무처리 / 데이터처리 / 연계처리 / 공통기반 /
배치처리 레이어로 구성된다. REST 백엔드 개발 시 직접 관련된 레이어는 다음 3개다.

- **화면처리(web)** = Controller. REST에서는 HTTP 진입점. 요청 파라미터 바인딩/검증,
  Service 호출, 응답(JSON) 반환만 담당한다. **비즈니스 로직을 두지 않는다.**
- **업무처리(service)** = Service 인터페이스 + ServiceImpl. 실제 비즈니스 로직, 트랜잭션 경계.
- **데이터처리(dao/mapper)** = DAO 또는 MyBatis Mapper. DB 접근만 담당.

규칙: 레이어를 건너뛰지 않는다. Controller → Service → DAO 순서를 지키며,
Controller가 DAO를 직접 호출하지 않는다.

## 2. 서비스 아키텍처 규칙

가장 자주 위반되는 규칙이다. 적용점검에서 Service 클래스가 검출되지 않으면 위반 처리된다.

- 비즈니스 구현 클래스는 `xxxServiceImpl` 명명을 따른다.
- 모든 ServiceImpl은 대응하는 **인터페이스 `xxxService`를 구현**한다(1:1).
  다형성 활용 여부와 무관하게 인터페이스를 작성한다.
- ServiceImpl은 **`EgovAbstractServiceImpl`을 상속**한다(3.0 이상).
  - 3.x 패키지: `org.egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl`
  - 4.x 패키지: `org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl`
- 스프링 빈으로 등록한다: `@Service("sampleService")`.

```java
@Service("sampleService")
public class EgovSampleServiceImpl extends EgovAbstractServiceImpl
        implements EgovSampleService {
    // ...
}
```

## 3. 데이터 액세스 아키텍처 규칙

- DB 접근은 DAO 또는 MyBatis Mapper를 통해서만 한다. ServiceImpl이 직접 JDBC를 쓰지 않는다.
- **SQL은 자바 코드에서 분리**하여 매퍼 XML에 작성한다.
- 3.x: DAO는 `EgovAbstractDAO`(또는 `EgovAbstractMapper`)를 상속하고 `@Repository`로 등록.
- 4.x: MyBatis Mapper 인터페이스에 `@Mapper`를 선언(권장). DAO 패턴도 가능.
- 적용점검에서 데이터 액세스 규칙 위반 시 DAO 클래스가 검출되지 않아 함께 위반 처리될 수 있다.

## 4. 실행환경 변경 금지 규칙

- 다운로드한 전자정부 실행환경 라이브러리(jar)를 인위적으로 변경하지 않는다.
- 실행환경을 변경/확장하고 싶으면 **상속을 통한 확장**을 사용한다.
- Spring Framework / Spring Boot은 필요 시 **패치(patch) 버전 한정**으로 적용기준보다 최신을
  쓸 수 있으나 근거 제시가 필요하다. (예: 보안 패치)

## 5. 실행환경 활용 규칙

- 실행환경 라이브러리가 실제로 의존성에 존재해야 한다(이름만 흉내 내지 않는다).
- 프로젝트 전반에 동일한 실행환경 버전과 아키텍처 규칙이 일관되게 적용되어야 한다.

## REST 추가 권장사항

- 응답은 일관된 결과 포맷을 사용한다(예: `resultCode`, `resultMessage`, `result` 래퍼).
- 입력 검증은 Bean Validation(`@Valid` + 제약 애너테이션)을 사용한다.
- 예외는 전자정부 예외 처리 체계(`EgovBizException`, `processException`, `leaveaTrace`)를 활용한다.
- ID 생성이 필요하면 `EgovIdGnrService`(채번 서비스)를 사용한다.

## 자기 점검 체크리스트

생성한 코드를 아래 항목으로 점검한다. 하나라도 No면 수정한다.

- [ ] ServiceImpl이 대응 인터페이스(`xxxService`)를 구현하는가?
- [ ] ServiceImpl이 `EgovAbstractServiceImpl`을 (해당 버전 패키지로) 상속하는가?
- [ ] `@Service("...")`로 빈 등록되었는가?
- [ ] Controller가 Service를 **인터페이스 타입**으로 주입받는가? (Impl 타입 직접 주입 금지)
- [ ] Controller에 비즈니스 로직이 섞여 있지 않은가?
- [ ] DB 접근이 DAO/Mapper를 통하는가? SQL이 매퍼 XML로 분리되었는가?
- [ ] 패키지/클래스/파일 네이밍이 `naming-conventions.md`를 따르는가?
- [ ] 실행환경 라이브러리를 수정하지 않고 상속으로만 확장했는가?
- [ ] 프로젝트 내 버전(3.x/4.x)이 일관되는가?
