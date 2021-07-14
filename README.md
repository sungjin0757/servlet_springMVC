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
- 정리 : 자바코드로만 모든 것을 다 하다보니 복잡. '서블릿을 굳이 세개를 만들어야 하나'의 문제점! 발전할 필요가 있음!

### 2. JSP파일 생성 및 frontController 도입
frontController가 Client의 요청에 따라 Controller들을 호출.
- package : mvc.first
	- ControllerFirst.java
	request와 response를 매개변수로 처리하는 메소드를 정의한 인터페이스
		- 구현체
			- MemberFormControllerFirst.java
			form 양식을 보여주는 view로 forward
			- MemberSaveControllerFirst.java
			Member 저장 처리 및 저장 결과를 보여주는 view로 forward
			- MemberListControllserFirst.java
			Member의 List를 보여주는 view로 forward
	- frontcontollerFirst.java
	**실질적인 서블릿. ** was서버에서 이 서블릿 생성 시 url을 각자의 controller에 맞게 매핑 시켜놓음.
- 정리 : '서블릿을 굳이 세개를 만들어야 하나'의 문제점 해결. 
	- **BUT,** 
		- 여전히 많은 중복 코드. 
		**ex)**
		```java
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/save-success.jsp");
		dispatcher.forward(request,response);
		```
		이런 코드를 Controller마다 다 갖고있음.
		- Controller들 마다 잘 사용하지 않을 수 도있는 HttpServletRequest, HttpServletResponse 객체를 매개변수로서 꼭 갖고 있음.
	- 아직 발전해야할 것이 많음..! 
