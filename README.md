## 설명
  - 해당 코드는 스프링 시큐리티 예제코드입니다. 이 예제코드에서는 다음과 같은 기능을 다룹니다.
    - 로그인 & 자동로그인 처리
    - 로그인 성공했을때의 처리
    - 로그인 실패했을때의 처리
    - 사용자 인증을 하지않은 상태로 페이지에 접근하였을 때 차단처리
    - 권한이 없는 페이지에 접근하였을 때 차단처리

<br>
    
## 프로젝트 환경
  - 프로젝트 환경구성은 다음과 같습니다.
    - JAVA 11
    - Spring Boot 2.6.1
    - MySQL 8.0.27
    - Docker
    
<br>
  
## 테스트 하는법
  - 사용자의 정보를 데이터베이스에 저장하기 위해 Docker로 MySQL Container를 띄워줍시다.
    - docker pull mysql
    - docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 Image아이디
  - 프로젝트를 clone 받은 후 어플리케이션을 구동하시면 ADMIN 권한을 가진 사용자와 USER 권한을 가진 사용자가 생성됩니다.
    - ADMIN 권한사용자 정보
      - ID : admin
      - PW : 1234
    - USER 권한사용자 정보
      - ID : user
      - PW : 1234
