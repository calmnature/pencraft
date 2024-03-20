<div align='center'>
  <img src="https://capsule-render.vercel.app/api?type=waving&fontColor=48648a&height=150&section=header&text=PEN%20CRAFT&fontSize=40&desc=Smart%20Factory&descAlignY=70&descAlign=50"/>
  <h3>🖍보드마카 생산 공장🖍</h3>
  <h4>☑ 제품별 품질 관리</h4>
  <h4>☑ 라인 내 에러 정보 통합 모니터링</h4>
  <h4>☑ 분석 시스템 구축</h4>
</div>

<span id="table"></span>
## 📞0. 목차
1. [프로젝트 소개](#1)
2. [개발 일정](#2)
3. [개발 환경 / Dependencies / 기술 스택](#3)
4. [담당 업무](#4)
5. [프로젝트 폴더 구조](#5)
6. [주요 기능 소개](#6)
7. [핵심 코드](#7)
8. [트러블 슈팅](#8)
9. [리팩토링 완료](#9)
10. [보완 사항](#10)
11. [보완 완료](#11)
12. [느낀 점 및 아쉬운 점](#12)
<br><br><br><br>
<span id="1"></span>
## 🏭1. 프로젝트 소개
![메인페이지](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/home.png)
해당 프로젝트는 5명의 인원으로 제품별 품질 관리와 라인 내 에러 정보 통합 모니터링 및 분석 시스템 구축이라는 스마트 팩토리의 주제를 선정하였을 때 실제 공장에서 일을 해본 적 경험이 없는 저희로서는 생각했을 때 직관적이고 단순하게 생각할 수 있는 보드마카를 생산하는 공장을 선정하였습니다.
저희의 프로젝트에서는 제품을 생산하는 4가지의 공정이 있고, 생산 지시가 내려졌을 때 1공정부터 4공정까지 진행이 되고 각 공정마다 일정 시간이 지난 뒤 그래프를 통해 양품과 불량품의 개수가 보일 수 있도록 모니터링을 할 수 있게 하고 생산된 제품들은 품질 관리 페이지에서 제품별 품질 관리를 할 수 있는 기능을 가지고 있습니다. 생산되는 현황을 확인하고 생산된 제품의 품질을 관리할 수 있는 PEN CRAFT 프로젝트의 완성 과정을 소개해드리겠습니다.<br>
[목차](#table)
<br><br><br><br>

<span id="2"></span>
## 📆2. 개발 일정
#### [2024.02.19 ~ 2024.03.15]
![개발일정](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/%EA%B0%9C%EB%B0%9C%EC%9D%BC%EC%A0%95.png)
<br>
[목차](#table)
<br><br><br><br>

<span id="3"></span>
## ⚙3. 개발 환경 / Dependencies / 기술 스택
### 🛠개발환경
  - **OS : Windows 10**
  - **통합개발환경(IDE) : IntelliJ**
  - **JDK Version : JDK 17**
  - **Database : MySQL**
  - **Build Tool : IntelliJ IDEA**

### 🖥Dependencies
  - **Spring Web**
  - **Spring Data JPA**
  - **Spring Boot DevTools**
  - **WebSocket**
  - **Lombok**
  - **Mysql Driver**
  - **Thymeleaf**

### ✏기술 스택
  - **Front End**<br>
![HTML](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![Javascript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white)
![jQuery](https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-239120?style=for-the-badge)
  - **Back End**<br>
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-red?style=for-the-badge)
![JPA](https://img.shields.io/badge/JPA-DB7093?style=for-the-badge)
  - **Database**<br>
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Woekbench](https://img.shields.io/badge/MySQL%20Workbench-00000F?style=for-the-badge&logo=mysql&logoColor=white)
  - **Communication**<br>
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)
<br>

[목차](#table)
<br><br><br><br>

<span id="4"></span>
## ✋4. 담당 업무
#### [담당 업무]
- 웹소켓과 쓰레드를 이용한 생산 공정 로직 구현
- 데이터 시각화를 위한 Javascript와 jQuery 지식 습득 및 공유
- 전체적인 에러 해결 및 코드 리팩토링
- 각각의 기능을 구현한 코드 통합
<br>

[목차](#table)
<br><br><br><br>

<span id="5"></span>
## 🗂5. 프로젝트 폴더 구조
```
📁src
┣ 📂main
┃ ┣ 📂java
┃ ┃ ┣ 📂com.example.pencraft
┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┣ 📄WebConfig.java                  // 필터 순서 설정 파일
┃ ┃ ┃ ┃ ┗ 📄WebSocketConfig.java            // 웹 소켓 설정 파일
┃ ┃ ┃ ┣ 📂constant
┃ ┃ ┃ ┃ ┗ 📄SessionConst.java               // 섹션에 저장할 로그인 이름 상수 선언
┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┣ 📄EmployeesController.java        // 회원 관련 컨트롤러
┃ ┃ ┃ ┃ ┣ 📄ErrorController.java            // 에러 페이지 발생 컨트롤러
┃ ┃ ┃ ┃ ┣ 📄HomeController.java             // 로그인 성공 후 메인 페이지 컨트롤러
┃ ┃ ┃ ┃ ┣ 📄LoginController.java            // 로그인 컨트롤러
┃ ┃ ┃ ┃ ┣ 📄LotController.java              // Lot 관리 컨트롤러
┃ ┃ ┃ ┃ ┣ 📄ProcessController.java          // 생산 지시 컨트롤러
┃ ┃ ┃ ┃ ┣ 📄ProductController.java          // 제품 조회 컨트롤러
┃ ┃ ┃ ┃ ┗ 📄WebSocketController.java        // 웹 소켓 컨트롤러
┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┣ 📄BaseTimeEntity.java             // Lot 생성 시 생성, 수정 시각 저장할 클래스 파일(상속 받아 사용)
┃ ┃ ┃ ┃ ┣ 📄Employees.java                  // 회원 Entity
┃ ┃ ┃ ┃ ┣ 📄Error.java                      // 생산 제품 에러 Entity
┃ ┃ ┃ ┃ ┣ 📄Lot.java                        // Lot Entity
┃ ┃ ┃ ┃ ┣ 📄OccurrenceError.java            // Lot 발생 에러 Entity
┃ ┃ ┃ ┃ ┣ 📄Process.java                    // 공정 Entity
┃ ┃ ┃ ┃ ┣ 📄Product.java                    // 제품 Entity
┃ ┃ ┃ ┃ ┗ 📄Standard.java                   // 제품 규격 Entity
┃ ┃ ┃ ┣ 📂filter
┃ ┃ ┃ ┃ ┣ 📄LogFilter.java                  // 모든 페이지 필터
┃ ┃ ┃ ┃ ┣ 📄LoginCheckFilter.java           // 비로그인 시 접근 가능 페이지 필터
┃ ┃ ┃ ┃ ┗ 📄RoleCheckFilter.java            // 로그인 후 관리자 및 일반 사용자 필터
┃ ┃ ┃ ┣ 📂form
┃ ┃ ┃ ┃ ┣ 📄CountForm.java                  // 생산 지시 DTO
┃ ┃ ┃ ┃ ┣ 📄EmployeesForm.java              // 회원 DTO
┃ ┃ ┃ ┃ ┣ 📄LastDataForm.java               // 모니터링 접속 시 가장 마지막에 생산된 양품, 불량품을 보낼 DTO
┃ ┃ ┃ ┃ ┣ 📄LoginForm.java                  // 로그인 시 사용할 Validation의 Form
┃ ┃ ┃ ┃ ┣ 📄LotCountForm.java               // 로그인 성공 후 메인 페이지에서 일/월/년별 그래프를 그리기 위한 DTO
┃ ┃ ┃ ┃ ┣ 📄LotForm.java                    // Lot DTO
┃ ┃ ┃ ┃ ┣ 📄PassFailProductForm.java        // Lot 관리 상세보기 시 각 공정의 양,불 개수를 보여주기 위한 DTO
┃ ┃ ┃ ┃ ┗ 📄ProductForm.java                // 제품 DTO
┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┣ 📄EmployeesRepository.java        // 회원 Repository
┃ ┃ ┃ ┃ ┣ 📄ErrorRepository.java            // 에러 Repository
┃ ┃ ┃ ┃ ┣ 📄LotRepository.java              // Lot Repository
┃ ┃ ┃ ┃ ┣ 📄ProcessRepository.java          // 공정 Repository(미사용)
┃ ┃ ┃ ┃ ┣ 📄ProductRepository.java          // 제품 Repository
┃ ┃ ┃ ┃ ┗ 📄StandardRepository.java         // 제품 규격 Repository
┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┣ 📄EmployeesService.java           // 회원 Service
┃ ┃ ┃ ┃ ┣ 📄ErrorService.java               // 에러 Service
┃ ┃ ┃ ┃ ┣ 📄LoginService.java               // 로그인 Service
┃ ┃ ┃ ┃ ┣ 📄LotService.java                 // Lot Service
┃ ┃ ┃ ┃ ┣ 📄ProcessService.java             // 생산 지시 Service
┃ ┃ ┃ ┃ ┣ 📄ProductService.java             // 제품 Service
┃ ┃ ┃ ┃ ┣ 📄StandardService.java            // 규격 Service
┃ ┃ ┃ ┃ ┗ 📄WebSocketSenderService.java     // 서버 -> 클라이언트에게 메세지를 보내기 위한 Service
┃ ┃ ┃ ┣ 📄DummyDataLoader.java              // 서버 최초 실행 시 DB에 더미데이터를 넣기 위한 클래스 파일
┃ ┃ ┃ ┗ 📄PencraftApplication.java
┃ ┗ 📂resource
┃ ┃ ┣ 📂static
┃ ┃ ┃ ┣ 📂css
┃ ┃ ┃ ┃ ┣ 📄bootstrap.css                   // 부트스트랩 CSS
┃ ┃ ┃ ┃ ┣ 📄jumbotron-narrow.css
┃ ┃ ┃ ┃ ┣ 📄loginhome.css                   // 로그인 성공 후 관리자, 일반 사용자의 메인 페이지의 CSS
┃ ┃ ┃ ┃ ┣ 📄navbar.css                      // navbar.html의 CSS
┃ ┃ ┃ ┃ ┗ 📄style.css
┃ ┃ ┃ ┣ 📂img
┃ ┃ ┃ ┗ 📂js
┃ ┃ ┃ ┃ ┣ 📄home.js                         // SockJS를 연결하여 메인 페이지의 일/월/년별 데이터를 ChartJS로 그릴 JS 파일
┃ ┃ ┃ ┃ ┣ 📄monitoring.js                   // SockJS를 연결하여 모니터링 페이지에서 실시간 공정 생산 현황의 데이터를 ChartJS로 그릴 JS 파일
┃ ┃ ┃ ┃ ┗ 📄productShow.js                  // 제품 조회 페이지 '수정' 버튼 누를 경우 모달에서 데이터를 보여주기 위한 JS 파일
┃ ┃ ┣ 📂templates
┃ ┃ ┃ ┣ 📂employees
┃ ┃ ┃ ┃ ┣ 📄change_password.html            // 비밀번호 변경 시 현재 비밀번호를 입력하여 사용자를 확인하기 위한 뷰 페이지
┃ ┃ ┃ ┃ ┣ 📄create_employee.html            // 회원 생성 시 회원을 만들기 위한 뷰 페이지
┃ ┃ ┃ ┃ ┣ 📄input_password.html             // 현재 비밀번호 확인 후 새 비밀번호를 입력받기 위한 뷰 페이지
┃ ┃ ┃ ┃ ┗ 📄show_employee_list.html         // 회원 목록을 보여주기 위한 뷰 페이지
┃ ┃ ┃ ┣ 📂error
┃ ┃ ┃ ┃ ┣ 📄4xx.html                        // 400번대 에러 시 보여줄 뷰 페이지
┃ ┃ ┃ ┃ ┣ 📄5xx.html                        // 500번대 에러 시 보여줄 뷰 페이지
┃ ┃ ┃ ┃ ┗ 📄goBack.html                     // 일반 사용자가 관리자 페이지에 접속 시 보여 줄 뷰 페이지
┃ ┃ ┃ ┣ 📂fragment                          // 템플릿화를 하기 위한 폴더
┃ ┃ ┃ ┃ ┣ 📄bodyheader.html
┃ ┃ ┃ ┃ ┣ 📄header.html
┃ ┃ ┃ ┃ ┣ 📄navbar.html
┃ ┃ ┃ ┃ ┗ 📄pageup.html
┃ ┃ ┃ ┣ 📂lot
┃ ┃ ┃ ┃ ┣ 📄show_lot.html                    // Lot 관리를 보여줄 뷰 페이지
┃ ┃ ┃ ┃ ┗ 📄show_lot_detail.html             // Lot 관리 상세보기 버튼을 클릭 시 보여줄 뷰 페이지
┃ ┃ ┃ ┣ 📂products
┃ ┃ ┃ ┃ ┣ 📄error_products.html              // 불량품의 제품을 보여줄 뷰 페이지
┃ ┃ ┃ ┃ ┣ 📄monitoring.html                  // 생산 지시 후 실시간 생산 현황을 보여줄 뷰 페이지
┃ ┃ ┃ ┃ ┣ 📄show_products.html               // 생산된 제품을 보여줄 뷰 페이지
┃ ┃ ┃ ┃ ┗ 📄start_process.html               // 생산 지시의 뷰 페이지(규격, 수량 입력)
┃ ┃ ┃ ┣ 📂teams
┃ ┃ ┃ ┃ ┗ 📄teamlist.html                    // 팀원 리스트의 뷰 페이지
┃ ┃ ┃ ┣ 📄home.html                          // 최초 로그인 전 보여줄 뷰 페이지
┃ ┃ ┃ ┣ 📄loginAdminHome.html                // 로그인 성공 후 관리자가 볼 메인 페이지
┣ ╋ ╋ ┷ 📄loginStaffHome.html                // 로그인 성공 후 사용자가 볼 메인 페이지
┗ ┷ ┷ 📄application.properties
```
<br>

[목차](#table)
<br><br><br><br>

<span id="6"></span>
## 🔍6. 주요 기능 소개
|                                                로그인                                                 |                                                회원 생성 및 조회                                                 |
| :-----------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: |
| ![로그인](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/1.%EB%A1%9C%EA%B7%B8%EC%9D%B8.gif) | ![회원생성](https://github.com/calmnature/pencraft/blob/main/GIF/2.%ED%9A%8C%EC%9B%90%20%EC%83%9D%EC%84%B1%20%EB%B0%8F%20%EC%A1%B0%ED%9A%8C.gif?raw=true) |

|                                                비밀번호 변경                                                 |                                                비밀번호 초기화                                                 |
| :-----------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: |
| ![비밀번호 변경](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/3.%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8%20%EB%B3%80%EA%B2%BD.gif) | ![비밀번호 초기화](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/4.%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8%20%EC%B4%88%EA%B8%B0%ED%99%94.gif) |

|                                                회원 삭제                                                 |                                                일/월/년 그래프                                                 |
| :-----------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: |
| ![회원 삭제](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/5.%ED%9A%8C%EC%9B%90%20%EC%82%AD%EC%A0%9C.gif) | ![일/월/년 그래프](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/6.%EC%9D%BC%EC%9B%94%EB%85%84%EA%B7%B8%EB%9E%98%ED%94%84.gif) |

|                                                생산 지시 및 중단                                                 |                                                제품 조회 및 수정                                                 |
| :-----------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: |
| ![생산 지시 및 중단](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/7.%EC%83%9D%EC%82%B0%20%EC%A7%80%EC%8B%9C%20%EB%B0%8F%20%EC%A4%91%EB%8B%A8.gif) | ![제품 조회 및 수정](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/8.%EC%A0%9C%ED%92%88%20%EC%A1%B0%ED%9A%8C%20%EB%B0%8F%20%EC%88%98%EC%A0%95.gif) |

|                                                에러 조회                                                 |                                                Lot 조회                                                 |
| :-----------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: |
| ![에러 조회](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/9.%EC%97%90%EB%9F%AC%20%EC%A1%B0%ED%9A%8C.gif) | ![Lot 조회](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/10.Lot%20%EC%A1%B0%ED%9A%8C.gif) |
<br>

[목차](#table)
<br><br><br><br>

<span id="7"></span>
## ⌨7. 핵심 코드
<details>
  <summary><b>1공정 ~ 4공정 생산 로직</b></summary>
  <b>반복문을 이용하여 제품을 생산</b>하고 SEND_MESSAGE_DEALY와 PRODUCTION_DELAY를 이용하여 SEND_INDEX의 값을 만들어 msgSendCount와 같아지면 <b>메세지를 전송</b>하도록 설정 <br><br>
  코드 리팩토링을 하면서 <b>각 공정에 관련된 메서드</b>인 processOne, processTwo, processThree, processFour로 나누고 <b>생산 로직 또한 따로 메서드</b>로 만들어 processOneLogic, processTwoLogic, processThreeLogic, processFourLogic으로 나누어 <b>추후 기능의 확장 같은 것을 고려하여 해당 부분만 변경할 수 있도록 코딩</b>
  <br><br>
  1공정 : 하나의 Lot에서 N개의 제품을 생성하기 때문에 Lot의 생성과 N개의 제품을 리스트에 담아 랜덤한 잉크를 주입하고 제품 규격과 비교하여 양품과 불량품을 리스트에 저장<br><br>
  2공정 : 1공정에서 생산된 제품 중 양품인 제품만 닙(보드마카의 촉)을 삽입을 하는데 랜덤하게 닙 깊이를 삽입하여 제품 규격과 비교하여 불량품일 경우 양품&불량품 여부의 값을 불량으로 변경<br><br>
  3공정 : 2공정에서 생산된 제품 중 양품인 제품만 몸체를 결합하는데 랜덤하게 결합 여부를 Y, N으로 하여 불량일 경우 양품&불량품 여부의 값을 불량으로 변경<br><br>
  4공정 : 3공정에서 생단된 제품 중 양품인 제품만 뚜껑을 결합하는데 3공정과 동일하게 랜덤으로하여 불량품일 경우 양품&불량품 여부의 값을 불량으로 변경<br><br>
  저장 : 현재까지 생산된 모든 제품과 해당 Lot을 Database에 저장<br>
  
```java
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProcessService {

    private final StandardRepository standardRepository;
    private final LotRepository lotRepository;
    private final ProductRepository productRepository;
    private final WebSocketSenderService webSocketSenderService;

    private Standard standard; // DB에서 가져온 규격을 저장
    private Lot lot; // Lot Entity
    private List<Product> productList; // 생산된 제품을 저장할 리스트

    private ExecutorService executorService = Executors.newSingleThreadExecutor(); // 생산을 돌릴 쓰레드
    private Future<?> productionFuture; // 생산을 담당할 Future

    // 사용자에게 양품, 불량품을 보내주기 위한 변수
    // 각 공정마다 최초 시작에 두 변수를 0으로 초기화해서 사용
    private int goodCount; private int badCount;

    // 현재 공정 상태 0 : 생산 종료, 1 ~ 4 : N공정 생산 중
    private int nowProcess;

    // 몇초 주기로 클라이언트에게 메세지를 보낼지 시간 설정
    private static final int SEND_MESSAGE_DELAY = 1000;
    // 제품 1개 생산하는데 몇 초마다 생산할지 시간 설정
    private static final int PRODUCTION_DELAY = 100;

    // 생산 for문에서 인덱스(i)가 sendIndex마다 메세지 전송
    private static final int SEND_INDEX = SEND_MESSAGE_DELAY / PRODUCTION_DELAY;

    private int msgSendCount;

    // 클라이언트가 최초로 monitoring 페이지에 접속할 경우 마지막 생산 데이터를 전송
    private Map<String, LastDataForm> lastDataMap = new HashMap<>();

    // 생산 지시 시 실행되는 메서드
    public void processStart(CountForm countForm) throws Exception {
        // standard = standardRepository.findById(1L).orElse(null);
        standard = standardRepository.findById(countForm.getStandard_id()).orElse(null);
        log.info("제품 규격 = {}", standard);
        productionFuture = executorService.submit(processTask(countForm.getCount()));
    }


    // 생산 중단 시 실행되는 메서드
    public void stopProduction() {
        log.warn("제품 생산 중단");
        productionFuture.cancel(true);
        if(nowProcess != 0){
            lastDataMap = new HashMap<>();
        }
    }

    // 사용자에게 메세지를 보내는 메서드
    private void sendMessage(String destination) {
        Map<String, Integer> data = new HashMap<>();
        data.put("goodCount", goodCount);
        data.put("badCount", badCount);
        webSocketSenderService.sendMessageToClient(destination,data);
    }

    // 사용자에게 보낼 LastData를 만들어 반환하는 메서드
    private LastDataForm lastDataCreate() {
        return new LastDataForm(nowProcess, goodCount, badCount);
    }

    // monitoring 페이지 최초 접속 클라이언트에게 보낼
    // LastData에 현재 몇 번째 공정인지 업데이트 하는 메서드
    private void nowProcessUpdate() {
        nowProcess++;
        for(String key : lastDataMap.keySet()){
            lastDataMap.get(key).setNowProcess(nowProcess);
        }
    }

    // 사용자가 최초 접속 시 1~4공정의 LastData를 보내주는 메서드
    public void sendLastDataMap() {
        log.info("최초 접속 사용자에게 보낼 Map 데이터 : " + lastDataMap);
        webSocketSenderService.sendMessageToClient("/process/lastdata", lastDataMap);
    }

    // executorService에 할당할 쓰레드 반환 메서드
    private Runnable processTask(int count) {
        return () -> {
            try {
                processOne(count); // 1공정 메서드
                processTwo(); // 2공정 메서드
                processThree(); // 3공정 메서드
                processFour(); // 4공정 메서드
                saveAll(); // 4공정까지 진행된 Lot, List<Product> 저장 메서드
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    // 1공정 메서드
    private void processOne(int count) throws Exception{
        log.info("1공정 시작");
        nowProcess = 0; // 현재 공정 값 0으로 초기화
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot = new Lot(); // Lot Entity 생성
        lot.setOutput(count); // 입력받은 생산량 저장
        lot.setStart_time(LocalDateTime.now()); // 생산 시작 시간
        lot.setFirst_start(LocalDateTime.now());  // 1공정 시작 시간
        lot.setStatus(1); // 1 : 진행 중
        productList = new ArrayList<>(); // 제품 리스트 초기화
        processOneLogic(count); // 1공정 생산 로직
        lot.setFirst_end(LocalDateTime.now()); // 1공정 종료 시간
        lastDataMap.put("processOne", lastDataCreate()); // Map에 1공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/first"); // 1공정 구독 메세지 전송
        log.info("1공정 종료");
    }

    private void processTwo() throws Exception{
        log.info("2공정 시작");
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot.setSecond_start(LocalDateTime.now()); // 2공정 시작 시간
        processTwoLogic(); // 2공정 생산 로직
        lot.setSecond_end(LocalDateTime.now()); // 2공정 종료 시간
        lastDataMap.put("processTwo", lastDataCreate()); // Map에 2공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/second"); // 2공정 구독 메세지 전송
        log.info("2공정 종료");
    }

    private void processThree() throws Exception{
        log.info("3공정 시작");
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot.setThird_start(LocalDateTime.now()); // 3공정 시작 시간
        processThreeLogic(); // 3공정 생산 로직
        lot.setThird_end(LocalDateTime.now()); // 3공정 종료 시간
        lastDataMap.put("processThree", lastDataCreate()); // Map에 3공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/third"); // 3공정 구독 메세지 전송
        log.info("3공정 종료");
    }

    private void processFour() throws Exception{
        log.info("4공정 시작");
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot.setFourth_start(LocalDateTime.now()); // 4공정 시작 시간
        processFourLogic(); // 4공정 생산 로직
        lot.setFourth_end(LocalDateTime.now()); // 4공정 종료 시간
        lot.setEnd_time(LocalDateTime.now()); // 생산 종료 시간
        lot.setS_count(goodCount); // 양품 개수
        lot.setF_count(lot.getOutput() - lot.getS_count()); // 불량품 개수
        lot.setStatus(2); // 상태 변경 2 : 종료
        lastDataMap.put("processFour", lastDataCreate()); // Map에 4공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/fourth"); // 4공정 구독 메세지 전송
        log.info("4공정 종료");
    }

    private void saveAll() throws Exception {
        nowProcess = -1; // 현재 진행 중인 프로세스 -1로 셋팅
        nowProcessUpdate(); // 업데이트
        Lot saveLot = lotRepository.save(lot); // lot Entity 저장
        log.info("저장된 Lot = {}", saveLot);

        for (Product product : productList) {
            product.setLot(lot); // 생산된 제품에 Lot 등록
            product.setStandard(standard); // 생산된 제품에 규격 등록
        }

        List<Product> saveProductList = productRepository.saveAll(productList);
        log.info("저장된 제품 리스트 = {}", saveProductList);
    }

    private void processOneLogic(int count) throws Exception{
        goodCount = 0;
        badCount = 0;
        msgSendCount = 0;
        for (int i = 0; i < count; i++) {
            msgSendCount++; // msgSendCount 1 증가
            Thread.sleep(PRODUCTION_DELAY);
            Random random = new Random();
            Double start = 0.0;
            Double end = 0.0;
            Double volume = 0.0;
            int ran = random.nextInt(1, 101);
            if(ran > 5) {
                if(standard.getStandard_id() == 1L){
                    start = 98.0;
                    end = 102.0;
                }
                else if(standard.getStandard_id() == 2L){
                    start = 196.0;
                    end = 204.0;
                }
            } else {
                boolean bool = random.nextBoolean();
                if(bool){
                    if(standard.getStandard_id() == 1L){
                        start = 90.0;
                        end = 98.0;
                    }
                    else if(standard.getStandard_id() == 2L){
                        start = 190.0;
                        end = 195.5;
                    }
                } else{
                    if(standard.getStandard_id() == 1L){
                        start = 103.0;
                        end = 110.0;
                    }
                    else if(standard.getStandard_id() == 2L){
                        start = 205.0;
                        end = 212.0;
                    }
                }
            }
            volume = Math.round(random.nextDouble(start,end) * 100) / 100.0;

            Product p = new Product();
            p.setVolume(volume);

            // 최소 잉크와 최대 잉크 규격이내라면
            if(standard.getMin_volume() <= volume && standard.getMax_volume() >= volume){
                p.setAcceptance("P"); // 제품 양&불 여부 등록 -> P : Pass(양품)
                goodCount++;
            }
            else {
                p.setAcceptance("F"); // 제품 양&불 여부 등록 -> F : Fail(불양품)
                p.setError_code(1L); // Error 코드 100번 등록
                badCount++;
            }
            productList.add(p);
            if(msgSendCount == SEND_INDEX){
                sendMessage("/process/first"); // 1공정 구독자에게 메세지 전송
                msgSendCount = 0;
            }
        }
    }

    // processOneLogic ~ processFourLogic까지는 거의 비슷한 로직이기에 중략
}

```

</details>

<details>
  <summary><b>웹소켓을 이용한 실시간 데이터 시각화</b></summary>
  생산 로직에서 <b>서버는 일정 시간을 주기로 데이터를 전송</b>하여 클라이언트가 해당 데이터를 수신<br>
  클라이언트측에서는 <b>Javascript의 SockJS를 이용하여 웹소켓과 연결하여 양방향 통신 구축</b><br>
  <b>StompJS를 통하여 주소를 구독</b>하고 구독한 주소로부터 데이터를 수신받게 되면 수신한 데이터를 JSON.parse()를 이용하여 <b>자바스크립트 객체로 변환</b>하고 <b>해당 데이터를 ChartJS를 이용하여 그래프 시각화</b><br>
  
```javascript
var stompClient = null;
// new Chart() 객체를 저장할 변수
var chartOne = null;
var chartTwo = null;
var chartThree = null;
var chartFour = null;

// 1~4공정의 각각의 캔버스를 담을 변수
var canvasOne;
var canvasTwo;
var canvasThree;
var canvasFour;

$(function () {
    canvasOne = $("#processOne");
    canvasTwo = $("#processTwo");
    canvasThree = $("#processThree");
    canvasFour = $("#processFour");
    connect();
});

function connect() {
    var socket = new SockJS('/sockjs');
    console.log("SockJS 생성");

    stompClient = Stomp.over(socket);
    console.log("Stomp 위에 SockJS 올림");


    stompClient.connect({}, function () {
        // alert("WebSocket(SockJS)이 연결되었습니다.");
        var lastData1 = null;
        var lastData2 = null;
        var lastData3 = null;
        var lastData4 = null;
        stompClient.send("/client/request",{},{})
        var subscription = stompClient.subscribe('/process/lastdata', function (data) {
            console.log(data);
            console.log(data.body);
            console.log(JSON.parse(data.body));
            var jsondata = JSON.parse(data.body);
            if(jsondata.processOne != null){
                lastDataDraw(jsondata);
            }
            subscription.unsubscribe();
        });

        stompClient.subscribe('/process/first', function (data) {
            if(chartTwo !== null){
                chartTwo.destroy();
                chartThree.destroy();
                chartFour.destroy();
                $("#secondText").html("");
                $("#thirdText").html("");
                $("#fourthText").html("");
            }
            var newData1 = JSON.parse(data.body);
            console.log(newData1);
            console.log(lastData1);
            if(!compareData(newData1, lastData1)){
                chartOne = drawChart(canvasOne,JSON.parse(data.body),"1공정");
                resultData($("#firstText"), JSON.parse(data.body));
                lastData1 = newData1;
            }
        });


        stompClient.subscribe('/process/second', function (data) {
            console.log(data);
            console.log(chartTwo);
            var newData2 = JSON.parse(data.body);
            if(!compareData(newData2, lastData2)){
                chartTwo = drawChart(canvasTwo,JSON.parse(data.body),"2공정");
                resultData($("#secondText"), JSON.parse(data.body));
                lastData2 = newData2;
            }
        });

        stompClient.subscribe('/process/third', function (data) {
            console.log(data);
            var newData3 = JSON.parse(data.body);
            if(!compareData(newData3, lastData3)){
                chartThree = drawChart(canvasThree,JSON.parse(data.body),"3공정");
                resultData($("#thirdText"), JSON.parse(data.body));
                lastData3 = newData3;
            }
        });

        stompClient.subscribe('/process/fourth', function (data) {
            console.log(data);
            var newData4 = JSON.parse(data.body);
            if(!compareData(newData4, lastData4)){
                chartFour = drawChart(canvasFour,JSON.parse(data.body),"4공정");
                resultData($("#fourthText"), JSON.parse(data.body));
                lastData4 = newData4;
            }
        });
    });
}


function drawChart(canvas, data, text) {
    var canvasId = canvas[0].id;
    var chartStatus = Chart.getChart(canvasId);
    if(chartStatus != undefined)
        chartStatus.destroy();
    return new Chart(canvas,{
        type: 'pie',
        data: {
            labels: ['양품', '불량품'],
            datasets: [{
                label: '수량',
                data: [data.goodCount, data.badCount],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    display:false
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: text,   //제목 "1공정"
                    position: 'top',
                    align: 'center',
                    font: {
                        size: 30,
                        weight: 'bold',
                    },
                    color: 'white'
                }
            }
        }
    });
}

function lastDataDraw(jsondata) {
    var select = jsondata.processOne.nowProcess;
    switch (select) {
        case 1:
            break;
        case 2:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            resultData($("#firstText"), jsondata.processOne);
            break;
        case 3:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            chartTwo = drawChart(canvasTwo, jsondata.processTwo, "2공정");
            resultData($("#firstText"), jsondata.processOne);
            resultData($("#secondText"), jsondata.processTwo);
            break;
        case 4:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            chartTwo = drawChart(canvasTwo, jsondata.processTwo, "2공정");
            chartThree = drawChart(canvasThree, jsondata.processThree, "3공정");
            resultData($("#firstText"), jsondata.processOne);
            resultData($("#secondText"), jsondata.processTwo);
            resultData($("#thirdText"), jsondata.processThree);
            break;
        case 0:
            chartOne = drawChart(canvasOne, jsondata.processOne, "1공정");
            chartTwo = drawChart(canvasTwo, jsondata.processTwo, "2공정");
            chartThree = drawChart(canvasThree, jsondata.processThree, "3공정");
            chartFour = drawChart(canvasFour, jsondata.processFour, "4공정");
            resultData($("#firstText"), jsondata.processOne);
            resultData($("#secondText"), jsondata.processTwo);
            resultData($("#thirdText"), jsondata.processThree);
            resultData($("#fourthText"), jsondata.processFour);
            break;
    }
}

function resultData(pTag, jsondata) {
    pTag.html("<br><b>" + "양품 : " + jsondata.goodCount + "개</b><br><br>" + "<b>불량품 : " + jsondata.badCount + "개</b>");
}

function compareData(newData, lastData){
    if(lastData !== null && JSON.stringify(lastData) === JSON.stringify(newData)) {
        return true;
    }
    return false;
}
```

</details>
<br>

[목차](#table)
<br><br><br><br>

<span id="8"></span>
## 🚀8. 트러블 슈팅
<details>
  <summary><b>생산 로직의 쓰레드 충돌</b></summary>
  <b>&gt; 현상</b><br>
  기존의 코드는 Thread Pool을 2개를 생성하여 각각 <b>생산과 N초마다 메세지를 보내는 형식</b>으로 구현<br>
  하지만 1공정의 생산 쓰레드인 productionFuture가 종료됨과 동시에 메세지를 보내는 쓰레드인 sendFuture를 종료시키기 때문에 1공정이 모두 종료된 후 2공정 메서드가 시작될 것이라고 생각하였지만, 1공정 시작 메서드에서 productionFuture와 sendFuture는 독립적으로 돌아가고 바로 2공정 메서드(processSecond)를 실행시키고 2공정 메서드 안에서 productionFuture와 sendFuture에 또 다시 새로운 future가 들어가서 쓰레드의 충돌이 나는 것이라고 생각 됨<br><br>
  또한 productionFuture가 종료가 된 다음 processSecond()를 호출하기 위해 productionFuture.get()을 사용하게 되면, productionFuture의 작업이 끝날 때까지 Block 되기 때문에 sendFuture가 실행되지 않아 클라이언트가 메세지를 받을 수 없었음<br><br>
  <b>&gt; 해결 방안</b><br>
  <img src="https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/thread.gif" alt="쓰레드 이미지"><br>
  쓰레드의 작업은 순차적으로 진행되는 것이 아니라 컴퓨터가 내부적으로 돌리기 때문에 생각했던 순서대로 진행이 되지 않았음<br>
  따라서 1개의 쓰레드로 1공정 실행 -> 1공정 종료 -> 2공정 실행 -> 2공정 종료의 형식이 아니라 1공정 ~ 4공정까지 하나의 작업으로 묶어서 아래의 코드처럼 processTask를 실행하면 그 안에서 1~4공정 로직과 저장까지 하는 것을 1개의 Task로 묶음
  
  ```java
    private Runnable processTask(int count) {
        return () -> {
            try {
                processOne(count); // 1공정 메서드
                processTwo(); // 2공정 메서드
                processThree(); // 3공정 메서드
                processFour(); // 4공정 메서드
                saveAll(); // 4공정까지 진행된 Lot, List<Product> 저장 메서드
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
  ```

</details>

<details>
  <summary><b>ChartJS를 이용한 데이터 시각화</b></summary>
  <b>&gt; 현상</b><br>
  최초 그래프를 그린 후 그래프가 새로 그려지지 않으며 F12에서 이미 사용중인 캔버스이므로 destroy를 해야한다는 에러 문구 발생<br>
  <b>&gt; 해결 방안</b><br>
  <b>한 개의 canvas 태그에는 한 개의 차트만</b> 그려질 수 있기에 다음과 같이 <b>차트를 그리기 전 차트가 있다면 해당 차트를 destroy()메서드로 차트를 없앤 뒤</b> 새로 그림
  
  ```javascript
function drawChart(canvas, data, text) {
    var canvasId = canvas[0].id;
    var chartStatus = Chart.getChart(canvasId);
    if(chartStatus != undefined)
        chartStatus.destroy();
    return new Chart(canvas,{
    // (중략)
  ```
</details>
<br>

[목차](#table)
<br><br><br><br>

<span id="9"></span>
## ⭕9. 리팩토링 완료
- **생산 공정 로직**
  - processStart() 메서드 실행 시 processTask() 메서드 호출
  - 각 공정 메서드인 processOne() ~ processFour() 실행
  - 각 공정 메서드는 각 공정마다 해야할 일들을 처리하고 각각 processOneLogic() ~ processFourLogic() 실행

- **일/월/년 데이터 로직**
  - 각각의 데이터가 '일일' '월별' '연별'로 달라지기 때문에 로그인 성공 후 메인 페이지 접속 시 LotService의 allDateSend() 호출
  - allDateSend()는 dayDateSend(), monthDateSend(), yearDateSend() 호출
  - 각각의 메서드는 findLot()를 호출하여 기준에 따라 DB에서 데이터를 가져와 저장
  - 클라이언트에게 보낼 데이터를 정제하기 위해 createMap() 메서드 호출
  - 기준에 따라 DateTimeFormatter.ofPattern()이 달라지고 Map에 데이터 저장
  - 각각의 데이터를 구독자 주소에 맞게 Send

- **JPA Repository Paging 기능**
  - 페이징은 0번부터 시작하므로 사용자가 요청한 페이지의 -1을 하여 조회 필요

- **Javascript 및 jQuery**
  - 메인페이지와 모니터링 페이지의 각 공정마다 그리는 그래프를 그리는 코드를 drawChart()라는 함수로 따로 관리
<br>

[목차](#table)
<br><br><br><br>

<span id="10"></span>
## ❗10. 보완 사항
- **비 로그인 시 권한이 없는 URL 접속할 경우 Alert창 추가**
  - **로그인이 되지 않은 상태**로 로그인 시 이용 가능한 주소창을 직접 입력할 경우 로그인 페이지로 필터가 되지만 특별한 알림창이 출력되지 않음
  - alert 창을 이용하여 '로그인이 필요합니다' 같은 알림창 추가
- **보안**
  - 현재 **로그인 성공 시 로그인 정보를 세션에 담기** 때문에 해당 세션을 가로챌 줄 아는 사람이라면 세션의 사용자의 아이디 비밀번호 등 개인 정보가 포함되어 있어 해당 부분 수정 필요
  - **회원 생성 시 사번과 비밀번호가 동일하게 설정**이 되어있는데, 사용자가 비밀번호를 변경하지 않을 시 누군가가 10001번부터 계속하여 입력하여 해당 아이디가 해킹되는 등의 보안을 어떻게 처리해야할 지 수정 필요
- **생산 중단 기능 확장**
  - **생산량 대비 불량률이 일정 수치가 넘어갈 경우** (2~4%) 기기 또는 어떠한 오류가 있을 것이라고 판단하여 **자동으로 생산을 중단**할 수 있는 기능 추가
  - 여러 개의 생산 지시를 내린 후 생산 중단 기능을 누를 경우 생산 지시가 모두 삭제 되어 **생산 중단 버튼 클릭 시 N개의 생산 지시 중 1개만 선택하여 해당 지시를 취소**할 수 있도록 로직 변경 필요
  - **N번 공정에서 생산 도중 생산 중단 버튼 클릭 시 모두 삭제,** 실무로 보면 3공정에서 중단 시 1~3공정까지 만들어진 모든 제품을 모두 버리고 1공정부터 새로 만들게 되는 식의 로직이기 때문에 생산 중단 시점에서 다시 시작할 수 있도록 로직 변경 필요
  - 생산 중단을 하였을 경우 어떤 사용자가 어떤 이유로 생산을 중단했는지 등의 로그나 확인 페이지 같은 것이 필요
- **페이징된 뷰 페이지(제품 조회, 에러 조회, Lot 조회) 검색 기능 추가**
  - 검색 기능 추가 및 세분화 및 정렬 기준 추가
<br>

[목차](#table)
<br><br><br><br>

<span id="11"></span>
## ☑11. 보완 완료
- **페이지 필터**
  - **로그인이 되지 않은 상태(세션에 로그인 정보가 없는 상태)에서 로그인 이외의 페이지를 요청을 할 경우 접근이 불가능**하고 로그인을 유도하기 위해 로그인 페이지로 이동
- **예외 처리**
  - Controller에 맵핑되지 않은 주소 요청이 올 경우 **Whitelabel Error Page가 아닌 만들어둔 400번, 500번 에러 페이지로 이동 가능**
- **생산 지시의 규격 추가**
  - 기존 로직으로는 1개의 규격으로만 생산을 하게 되어있었지만 **다른 규격이 추가되어 여러 개의 규격 중 선택**하여 생산 가능
- **모니터링 리팩토링**
  - 생산 지시 후 생산이 되고 있는 중에 다른 사용자가 모니터링 페이지에 접속하면 데이터를 정상적으로 수신할 수 있지만, 생산 중에만 데이터를 보내는 것이기 때문에 **생산 공정이 돌아가지 않을 때 모니터링 페이지 접속할 경우 가장 마지막의 데이터를 클라이언트에게 데이터를 전송**
- **비밀번호 변경**
  - 사용자가 본인이 사용하는 비밀번호대로 변경을 하기 위해 비밀번호 변경 기능 추가
- **로그인 시 빈 공백으로 로그인할 경우 에러페이지**
  - @Valid 어노테이션 바로 뒤에 BindingResult가 오도록 순서 변경하여 해결
- **생산 지시 후 동일한 데이터가 올 경우 중복 차트 그리지 않도록 수정**
  - 서버로부터 받은 데이터와 기존 차트의 데이터가 동일할 경우 그리지 않음
- **반응형 웹 페이지**
  - 모바일 환경에서 그래프가 제대로 출력되지 않아 부트스트랩 및 CSS를 활용하여 반응형 웹페이지 구성
- **Alert 변경**
  - 기본 alert이 너무 심플하여 CDN 추가 -> 좀 더 감각적인 디자인으로 Alert 수정
<br>

[목차](#table)
<br><br><br><br>

<span id="12"></span>
## 🙆‍♂️12. 느낀 점 및 아쉬운 점
> 느낀점
- 스프링의 Controller, Service, Repository의 역할을 명확히 구분
- 웹소켓과 쓰레드를 이용한 실시간 통신을 구현할 수 있었으며, 몰랐던 새로운 기술을 응용하기 위한 방법 습득
- Javascript와 jQuery의 기본적인 문법과 var, let, const 자료형에 대해 알게 되었으며 ajax를 이용한 서버와 클라이언트 간의 비동기 통신을 사용
- Validation을 이용하여 컨트롤러 단에서 검증을 하는 것을 습득
- JpaRepository의 페이징의 기능의 사용법 습득
- 타임리프를 이용한 뷰페이지 표현과 로직 코드 사용법 습득
- 에러가 발생했을 때의 트러블 슈팅 능력 향상
> 아쉬운 점
- 검색 기능을 만들지 못한 점
- 빅데이터라고 할만큼 방대한 양의 데이터를 다루지 못한 점
- 파이썬과 연결하여 머신러닝&딥러닝의 요소를 넣지 못한 점
- 파이썬과 연결하여 서버에서 데이터를 파이썬으로 보내고 파이썬에서는 해당 데이터로 ggplot을 이용하여 그래프를 만들어 png 파일을 서버에 보내주고, 서버는 클라이언트에게 해당 사진 자료를 보내주어 데이터 시각화를 좀 더 깔끔하고 이쁜 디자인으로 보여주지 못한 점
- 생산 지시에서 1000개의 생산을 하였을 때 클라이언트측에 시각화가 정상적으로 이루어지긴 하지만 데이터 베이스에 저장할 때는 약 5분이상의 시간이 소요되는 부분을 빠르게 해결하기 위한 방법을 찾지 못한 점
<br>

[목차](#table)
