# 4.x (Spring Boot) REST 상세

전자정부 표준프레임워크 4.x 기반 REST API 백엔드 개발 시 참조. 버전이 4.x일 때만 읽는다.

## 기술 스택 기준선

- 실행환경 패키지 접두사: `org.egovframe.rte.*` (3.10 이하의 `egovframework.rte`에서 변경됨)
- Spring Boot 2.x 계열(4.0/4.1 기준) 이상, Spring Framework 5.x, JDK 11 / 17
- 빌드: Gradle(`build.gradle`) 또는 Maven(`pom.xml`). 의존성은 전자정부 전용 저장소에서 수신.
- 설정: **Spring Boot 자동설정 + `application.yml`** (XML 최소화)
- 영속성: MyBatis Spring Boot Starter, `@Mapper` 인터페이스
- 배포: 실행 가능 Jar(내장 Tomcat)

## 핵심 차이점 (3.x 대비)

- 진입점은 `@SpringBootApplication` 메인 클래스. `web.xml`/`dispatcher-servlet.xml` 불필요.
- 전자정부 부트 초기화 설정(예: `EgovBootApplication`/초기화 구성)을 적용한다.
- REST는 `@RestController`가 자연스럽다(별도 Jackson 설정 거의 불필요).
- DataSource/MyBatis는 `application.yml`로 설정(`mybatis.mapper-locations` 등).

## application.yml 설정 요점

```yaml
spring:
  datasource:
    driver-class-name: org.h2.Driver        # 운영 DB 드라이버로 교체
    url: jdbc:h2:mem:testdb
    username: sa
    password:
mybatis:
  mapper-locations: classpath:mappers/**/*.xml
  type-aliases-package: egovframework.**.service
  configuration:
    map-underscore-to-camel-case: true
```

## 클래스 구현 요점

- 메인 클래스: `@SpringBootApplication`, 전자정부 부트 구성 포함.
- ServiceImpl: `org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl` 상속, `@Service("...")`.
- Mapper: 인터페이스에 `@Mapper` 선언(권장). 매퍼 XML namespace를 인터페이스 FQCN으로.
- Controller: `@RestController` + `@RequestMapping`. 서비스는 인터페이스 타입으로 주입
  (`@Resource(name="sampleService")` 또는 생성자 주입).

## 사용할 템플릿

`templates/v4/` 의 골격을 채워 사용한다:
- `EgovSampleController.java`
- `EgovSampleService.java`
- `EgovSampleServiceImpl.java`
- `EgovSampleMapper.java`
- `SampleVO.java`
- `EgovSample_SQL.xml`
- `application.yml`
- `build-snippet.gradle` (의존성/저장소 설정 참고)
