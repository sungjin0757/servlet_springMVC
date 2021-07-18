# 본래의 자바 서블릿 부터 스프링MVC 까지 Study

## 주된 목적은 servlet에서 부터 어떻게 스프링 MVC까지 구성되었나살펴 보는 것!</span>

- **SO!**
  - 간단히 JPA를 사용
  - domain : **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/domain/Member.java">CODE</a>**
    - Member Entity만 구성
      username, age로만 client의 요청을 다뤄볼 것!
  - repository : **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/repository/MemberRepository.java">CODE</a>**
    - 회원 저장
    - findById
    - findAll
  - service : **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/service/MemberService.java">Interface CODE</a>** **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/service/MemberServiceImpl.java">Impl CODE</a>**
    - 회원 저장
    - findById
    - findAll


### 1. **<a href="https://github.com/sungjin0757/servlet_springMVC/tree/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/onlyservlet/members">CODE</a>** 오직 Servlet으로만 request와 response 관리
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

### 2. **<a href="https://github.com/sungjin0757/servlet_springMVC/tree/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/mvc/first">CODE</a>** JSP파일 생성 및 나만의 DispatcherServlet 도입
DispathcherServlet가 Client의 요청에 따라 Controller들을 호출.
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
	- DispatcherServlett.java
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

### 3. **<a href="https://github.com/sungjin0757/servlet_springMVC/tree/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/mvc/second">CODE</a>** 2번에서의 문제인 dispatcher.forward(request,response)의 중복을 처리해주는 View 클래스를 만들어 해결
- MyView : **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/mvc/MyView.java">CODE</a>**
	- 공통으로 사용될 것이기 때문에
	- mvc package에 정의
	- 사용 방식
		1. DispatcherServlet에서 요청받은 url에 따라서 Controller를 호출.
		2. 호출된 Controller는 Controller자신이 해야할 일을 하고, render해야하는 viewPath를 인자로 MyView를 생성해서 반환.
		3. 생성된 MyView에서 생성될 때 인자로 넘어온 viewPath로 render.
- 정리
	```java
	 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/save-success.jsp");
	dispatcher.forward(request,response);
	 ```
	이와 같은 코드의 중복을 해결..
	**BUT,** 많은 발전을 이뤘지만 아직 여전히 많은 문제점
		1. viewPath를 넘겨줄 때 "/WEB-INF/views/save-success.jsp" 이렇게 귀찮게 다 써줘야하나의 문제
		2. 아직도 Controller 마다 잘 사용하지 않을 수도 있는 HttpServletRequest, HttpServletResponse 객체를 매개변수로서 꼭 갖고 있음.

### 4. **<a href="https://github.com/sungjin0757/servlet_springMVC/tree/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/mvc/third">CODE</a>** 
	- 3번에서의 문제점 
		1. viewPath를 넘겨줄 때 "/WEB-INF/views/save-success.jsp"의 귀찮음을 viewResolver로 해결
		2. Controller 마다 잘 사용하지 않을 수도 있는 HttpServletRequest, HttpServletResponse 객체를 매개변수로서 갖고있는 문제를 model을 따로 만들어서 해결
- MyViewResolver **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/mvc/MyViewResolver.java">Interface CODE</a>**  **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/mvc/MyViewResolverImpl.java">Impl CODE</a>**
	- 다형성을 위해 인터페이스 활용. (여기서는 MyViewResolverImpl을 구현체로서 빈으로 등록하여 좋은 객체지향 설계인 OCP 및 DIP의 규칙을 지킴)
	- controller들이 view의 name만 매개변수로 넘겨주게 되면 해당하는 JSP파일의 name을 갖도록 설정
- MyModel **<a href="https://github.com/sungjin0757/servlet_springMVC/blob/master/servlet-to-springmvc/src/main/java/study/servlettospringmvc/mvc/MyModel.java">CODE</a>**
	- view의 name 과 request.setattribute()에 모델을 담기 위한 Map저장소를 멤버로 가짐
	- 동작 과정
		1. request가 들어오면 DispatcherServlet이 Model의 파라미터에 맞게끔 request의 정보를 Mapping.
		2. request가 Mapping된 파라미터를 Controller에게 넘김.
		3. Controller들은 파라미터를 가지고 동작 실행.
		4. Controller들은 MyModel 객체에 ViewName과 Model을 지정해주어 반환.
	- **HttpServletRequest의 객체는 단지 DispatcherServlet에서 Mapping 시켜 Controller들에게 넘기므로 이제는 Controller들이 필요없는 인자를 가질 필요가 없게 됨!**
- 정리 : 이제는 어느정도 훌륭한 발전을 이룬 MVC FrameWork로서 동작! Spring의 HandlerAdapter및 HandlerMapping 기능만 추가된다면 거의 Spring의 MVC 와 유사할 정도..(유사? 아주 미미하지만..))