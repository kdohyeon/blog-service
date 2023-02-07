# 소개. Blog Service

- 블로그 검색과 관련된 서비스를 제공합니다.

# 빌드 결과물

- [결과물 다운로드](https://github.com/kdohyeon/blog-service/blob/main/apps/app-api/build/libs/kakaobank.jar)

# 환경 소개

- JAVA 17
- Spring Boot 2.7.3
- Gradle 기반 빌드
- H2 인메모리
- JUnit, MockMvc 기반 테스트 케이스 작성
- REST docs 기반 API 명세 작성

# 프로젝트 구성 소개

프로젝트 구성은 아래 디렉토리 구조를 참고합니다.

```
root
├── apps                  
│   └── app-api               - 블로그 API 스프링 어플리케이션
├── buildSrc                  - 의존성 버전 관리
├── commons                   - 공통 라이브러리 관리
│   └── protocol              - 서비스 공통 프로토콜
└── libs
    ├── adapter-http          - 외부 통신을 위한 HTTP 어댑터
    ├── adapter-persistence   - DB 통신을 위한 영속성 어댑터
    └── application           - 어플리케이션 서비스
```

# 모듈 설계

- 멀티 모듈로 구성되어 있습니다.
- 모듈 간 의존성은 각 모듈의 `build.gradle.kts` 에서 관리되고 있으며 효율적으로 관리하기 위해 Line 에서
  제공하는 [build-recipe-plugins](https://github.com/line/gradle-multi-project-support) 를 활용합니다.
- Clean Architecture 에 기반한 모듈 설계와 패키지 의존 구조를 가지고 있습니다.

![module structure](assets/module_structure.png)

## module - application
- 도메인 엔티티, 입력 포트, 출력 포트, 그리고 서비스 로직이 포함되어 있습니다.
  - `domain`
  - `service`
  - `port/input`
  - `port/output`

## module - adapter

- 출력 포트에 대한 구현체가 포함되어 있습니다. 
  - `adapter-persistence` (jpa)
  - `adapter-http` (http-client)

## module - app

앱을 동작하기 위한 부분이 포함되어 있습니다.
- `app/app-api`

# API 명세

`/apps/app-api/src/docs/index.adoc` 을 참고합니다.

## 1. 블로그 검색

- 키워드로 블로그를 검색할 수 있습니다.
- 정확도순, 최신순으로 정렬할 수 있습니다.
- 페이지네이션 기능을 제공합니다.
- 검색 소스는 [카카오 블로그 검색 OPEN API](https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide) 를 활용합니다.
- 카카오 API 가 제대로 작동이 되지 않으면 [네이버 블로그 검색 OPEN API](https://developers.naver.com/docs/serviceapi/search/blog/blog.md) 로
  대체합니다.
- RestTemplate 을 활용하여 HTTP 통신을 합니다.

### 사용 방법

```bash
$ curl 'http://localhost:8080/api/v1/blogs?keyword=SpringFramework&page=1&size=10' -i -X GET \
    -H 'Accept: application/json, application/javascript, text/javascript, text/json'

$ http GET 'http://localhost:8080/api/v1/blogs?keyword=SpringFramework&page=1&size=10' \
    'Accept:application/json, application/javascript, text/javascript, text/json'
```

### 요청

```bash
GET /api/v1/blogs?keyword=SpringFramework&page=1&size=10 HTTP/1.1
Accept: application/json, application/javascript, text/javascript, text/json
Host: localhost:8080
```

### 응답

```json
{
  "success": true,
  "code": "SUCCEED",
  "message": null,
  "data": {
    "documents": [
      {
        "title": "Spring 이란",
        "contents": "스프링 프레임워크 공부하기",
        "url": "www.google.com",
        "blogName": "기억보단 기록을",
        "thumbnail": "",
        "writtenAt": "2022-02-07 22:36:02"
      },
      {
        "title": "면접에 합격하는 방법",
        "contents": "코딩 테스트를 열심히 한다.",
        "url": "www.daum.net",
        "blogName": "허허",
        "thumbnail": "abc.jpg",
        "writtenAt": "2022-02-07 22:36:23"
      },
      ...
    ],
    "pagination": {
      "currentPage": 1,
      "totalPage": 32,
      "totalItemCount": 314,
      "countPerPage": 10,
      "hasNextPage": true,
      "nextPage": 2
    }
  }
}
```

|Path|Type|Description|
|---|---|---|
|`+success+`|`+Boolean+`|성공 여부|
|`+code+`|`+String+`|code|
|`+message+`|`+String+`|message|
|`+data+`|`+Object+`|데이터|
|`+data.documents[]+`|`+Array+`|블로그 문서 목록|
|`+data.documents[].title+`|`+String+`|블로그 제목|
|`+data.documents[].contents+`|`+String+`|블로그 내용|
|`+data.documents[].url+`|`+String+`|블로그 주소|
|`+data.documents[].blogName+`|`+String+`|블로그 이름|
|`+data.documents[].thumbnail+`|`+String+`|이미지 썸네일|
|`+data.documents[].writtenAt+`|`+String+`|블로그 글 작성 시간|
|`+data.pagination+`|`+Object+`|페이지네이션|

## 2. 인기 검색어 조회

- 사용자들이 많이 검색한 순서대로, 검색한 키워드를 최대 10개까지 제공합니다.
- 검색 키워드 별 검색된 횟수도 함께 표기됩니다.
- DB 는 H2 데이터베이스를 활용합니다.
- JPA, QueryDSL 을 활용하여 DB 컨트롤을 합니다.

### 사용하기

```bash
$ curl 'http://localhost:8080/api/v1/blogs/statistics/popular?top=10' -i -X GET

$ http GET 'http://localhost:8080/api/v1/blogs/statistics/popular?top=10'
```

### 요청

```
GET /api/v1/blogs/statistics/popular?top=10 HTTP/1.1
Host: localhost:8080
```

### 응답

```json
{
  "success": true,
  "code": "SUCCEED",
  "message": null,
  "data": [
    {
      "keyword": "qf\u001A\u001B\nm\t\u0002",
      "count": 7255837471620
    },
    {
      "keyword": "[\u0012Y\u0001\b$VA\u0005m\f4#t\u000B>\u0019V\u001Bm\tx1\u001C\u0000SH-:*@AJ\r#\\\u0013INN6\u0014Gl9\u0003\u0013Vw\u0007\u0000;\u00183w,!\u000B(=@\u001B9v?\u0010\u001D\r\u001DN1dGac\u0017Z\u00114)^%\u0013Xug\\\u0007\u0002@P1sxe\u0017%Z\u00182V\n$!\r[\r|\u000BDMg($_'n7\u001F\tOnKguy\u0004aEs\u0005 \u001DK\u0018i\u0003.wJCz4\u00169y\\\"cg*\u000Ev\"7W4[)AHza3U\u0010\fA",
      "count": 2748057499
    },
    {
      "keyword": "\u0007#{;\f#v&P| _\u000BYe\\x\u0016^h$Wvq\u0005\tE?9-B",
      "count": 1250062143
    },
    {
      "keyword": ".uHc Gn$\u0017f'u y]\u00141",
      "count": 593340420
    },
    {
      "keyword": "iw\u001D(S-\u0003e\u000Bm6\u000Ba\u0002WF\u0000s*\n!:\"\u001B",
      "count": 2751956
    },
    {
      "keyword": "\\D\u0005g\ra#2\u0012\t\u0014{bejniUe\u001DL2c4",
      "count": 1227437
    },
    {
      "keyword": "B$mf",
      "count": 24530
    },
    {
      "keyword": "e\u0010,\u0010",
      "count": 1046
    },
    {
      "keyword": "Bj~wy17",
      "count": 105
    },
    {
      "keyword": "|F.\u001F\u001B",
      "count": 4
    }
  ]
}
```
|Path|Type|Description|
|---|---|---|
|`+success+`|`+Boolean+`|성공 여부|
|`+code+`|`+String+`|code|
|`+message+`|`+String+`|message|
|`+data[]+`|`+Array+`|인기 블로그 검색어 목록|
|`+data[].keyword+`|`+String+`|인기 블로그 검색어|
|`+data[].count+`|`+Number+`|검색어 별 검색된 횟수|

# 실행하기

## 빌드 파일 만들기 (Executable Jar 만들기)

```
./gradlew clean :apps:app-api:build
```

## Executable Jar 실행하기
- `./apps/app-api/build/libs/` 디렉토리로 접근
- 아래 커맨드 실행
```
java -jar {jar_name}.jar
```

# 외부 라이브러리
## 개발 편의성
### [io.freefair.lombok](https://plugins.gradle.org/plugin/io.freefair.lombok)
- 어노테이션 및 코드 간결화를 위해 사용

### [com.google.guava](https://github.com/google/guava)
- Collection 과 관련된 유틸 메소드를 위해 사용

### [org.apache.commons](https://commons.apache.org/)
- Collection, String 등 유틸 메소드를 위해 사용

### [com.querydsl](http://querydsl.com/releases.html)
- JPA 를 좀 더 편하게 사용하기 위해 사용

### [org.mapstruct](https://mapstruct.org)
- 각 계층 간 객체 컨버팅을 편리하게 하기 위해 사용

## 빌드 편의성 
### [com.linecorp.build-recipe-plugin](https://plugins.gradle.org/plugin/com.linecorp.build-recipe-plugin)
- 멀티 모듈 환경에서 빌드 파일을 효율적으로 관리하기 위해 사용

## 테스트
### [com.coditory.integration-test](https://github.com/coditory/gradle-integration-test-plugin)
- 통합 테스트를 편리하게 하기 위해 사용

### [com.navercorp.fixturemonkey](https://naver.github.io/fixture-monkey/)
- 테스트 객체를 자동으로 생성하기 위해 사용 

### [org.junit.jupiter](https://junit.org/junit5/docs/current/user-guide/)
- JUnit 테스트를 위해 사용

### [org.assertj](https://mvnrepository.com/artifact/org.assertj/assertj-core)
- 테스트 코드의 가독성을 높이기 위해 사용

### [org.mockito](https://mvnrepository.com/artifact/org.mockito)
- 테스트 프레임워크 사용을 위해 사용

## API 명세 작성
### [com.epages.restdocs-api-spec](https://plugins.gradle.org/plugin/com.epages.restdocs-api-spec)
- REST docs 문서 작성을 위해 사용

### [org.asciidoctor.jvm.convert](https://asciidoctor.org/docs/asciidoctor-gradle-plugin/)
- API 명세 문서 snippet 을 생성하기 위해 사용

## 시스템 안정성
### [resilience4j](https://resilience4j.readme.io/docs/circuitbreaker)
- 카카오 API 에 장애가 발생한 경우, 네이버 API 를 조회하기 위해 사용 (서킷 브레이커)
