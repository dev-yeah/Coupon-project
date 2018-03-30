
## 개요(Overview)
사용자로부터 이메일 주소를 입력으로 받아서 16자리의 알파벳과 숫자로 이루어진 **중복없는** 쿠폰 번호를 발급하고 발급된 쿠폰 정보를 같은 페이지에 리스팅하는 웹어플리케이션 프로젝트입니다.

## 문제해결 전략(Strategy)

- 중복된 이메일 입력에 따른 쿠폰 발행 불가를 위해 Client에서 Email 입력받은 후 Email을 Key값으로 데이터가 존재하는 지 확인하는 Validation Check를 구현
- 쿠폰번호 [0-9a-zA-Z] Available character Set을 구성하기 위해, character로 IntStream에서 range 설정 후 character를 String으로 변환해 배열에 저장
- 쿠폰번호 생성 시엔 java.util.Random 클래스를 사용해 index 값을 랜덤으로 pick한 후 Available character Set에서 문자를 가져와 StringBuffer를 사용해 16자리 쿠폰을 반환(실제 4자리마다 '-' 부호를 이어 붙여서 실질적으로 19자리)

- Coupon에 관한 Rest API는 공통적으로 "/coupon" url을 mapping.
- 조회와 추가 특성의 서비스가 존재하기 때문에 GET과 POST 방식 사용


## 프로젝트 구성 환경(Feature)
* Spring Boot (Back-end)
	- java 1.8.0
  - RestFul-API on SpringBoot
  - Handle CORS(Cross-origin resource sharing)
  - Database H2 with JPA
  - build with Gradle
	
* VueJS & webpack (Front-end)
	- node 8.10.0
	- npm 5.6.0
	- VueJs 2.9.3
	- axios, bootstrap-vue, sweetalert2 used
	
## 프로젝트 빌드 및 실행 방법 (Build & Run)

### Installation
* Build front-end environment

```
cd Coupon-project/frontend
npm install
```


	
### Usage
* run back-end server
```
cd Coupon-project/
gradle bootRun
```


* run front-end web page
```
cd Coupon-project/frontend
npm run dev
```

## 개선사항(After plan)
* Unit Test 코드개선
* email check할 때 bloomfilter사용해 성능 향상
