# 3.x (전통 Spring MVC + XML) REST 상세

전자정부 표준프레임워크 3.x 기반 REST API 백엔드 개발 시 참조. 버전이 3.x일 때만 읽는다.

## 기술 스택 기준선

- 실행환경 패키지 접두사: `org.egovframework.rte.*`
- Spring Framework 5.x (실행환경 버전에 따름), JDK 8 / 11
- 빌드: Maven (`pom.xml`). 의존성은 전자정부 전용 저장소에서 수신.
- 설정: **XML 기반** (web.xml + dispatcher-servlet + context-*.xml)
- 영속성: MyBatis (SqlSessionTemplate)
- 배포: WAR → WAS(Tomcat 등)

## 핵심 차이점 (4.x 대비)

- `@SpringBootApplication` 없음. `web.xml`이 진입점.
- DataSource / SqlSession / Transaction / IdGen을 **XML로 명시 설정**한다.
- REST 응답은 `@RestController` 또는 `@Controller` + `@ResponseBody`로 처리하고,
  Jackson(`MappingJackson2HttpMessageConverter`)을 `<mvc:annotation-driven>`에 구성한다.
- 의존성 주입은 `@Resource(name="...")` 관례를 자주 사용한다.

## XML 설정 골격

### web.xml 핵심
- `ContextLoaderListener` + `contextConfigLocation` (context-*.xml)
- `DispatcherServlet` 매핑 (`*.do` 또는 `/api/*`)
- 인코딩 필터(`CharacterEncodingFilter`, UTF-8)

### dispatcher-servlet.xml 핵심
```xml
<mvc:annotation-driven />
<context:component-scan base-package="egovframework">
    <context:include-filter type="annotation"
        expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```
REST JSON을 위해 `<mvc:annotation-driven>`에 Jackson 메시지 컨버터가 포함되도록 한다.

### context-datasource.xml / context-sqlMap.xml / context-transaction.xml / context-idgen.xml
- DataSource(혹은 JNDI), `SqlSessionFactoryBean`(mapperLocations 지정),
  `DataSourceTransactionManager`, `EgovTableIdGnrService`를 각각 빈으로 등록한다.

## 클래스 구현 요점

- ServiceImpl: `org.egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl` 상속.
- DAO: `org.egovframework.rte.psl.dataaccess.EgovAbstractDAO` 상속,
  `@Repository`로 등록, `insert/update/delete/selectList/selectByPk` 사용.
- Controller: `@Controller` + `@ResponseBody` 또는 `@RestController`.
  서비스는 `@Resource(name="sampleService")`로 인터페이스 타입 주입.

## 사용할 템플릿

`templates/v3/` 의 골격을 채워 사용한다:
- `EgovSampleController.java`
- `EgovSampleService.java`
- `EgovSampleServiceImpl.java`
- `EgovSampleDAO.java`
- `SampleVO.java`
- `EgovSample_SQL.xml`
- `pom-snippet.xml` (의존성/저장소 설정 참고)
