# 본래의 자바 서블릿 부터 스프링MVC 까지 Study

## 주된 목적은 servlet에서 부터 어떻게 스프링 MVC까지 구성되었나살펴 보는 것!</span>

- **SO!**
  - 간단히 JPA를 사용
  - domain
    - Member Entity만 구성
      username, age로만 client의 요청을 다뤄볼 것!
  - repository
    - 회원 저장
    - findById
    - findAll
  - service 
    - 회원 저장
    - findById
    - findAll

### 1. 오직 Servlet으로만 request와 response 관리
HttpServletRequest로 파라미터를 받아온 뒤 HttpServletResponse로 곧바로 Html을 응답
즉, 별도의 view가 없는 자바코드로만 구성
- package : onlyservlet
	- MemberFormServlet.java
		- MemberForm을 곧 바로 response
	- MemberListServlet.java
		- MemberService로 부터 Member를 저장한 후 저장 결과를 response
	- MemberSaveServlet.java		
		- MemberService로부터 List<Member>를 불러온 후 이를 response
- 정리 : 자바코드로만 모든 것을 다 하다보니 복잡. 발전할 필요가 있음!

