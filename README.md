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
5. [주요 기능 소개](#5)
6. [기술적 의사 결정](#6)
7. [트러블 슈팅](#7)
8. [리팩토링 완료](#8)
9. [보완 사항](#9)
10. [보완 완료](#10)
11. [느낀 점 및 아쉬운 점](#11)
<br><br><br><br>
<span id="1"></span>
## 🏭1. 프로젝트 소개
![메인페이지](https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/home.png)
<b>5명의 인원으로 1명의 Front-End, 3명의 Back-End, 1명의 DB 설계</b>로 구성된 프로젝트입니다.<br>
저희의 주제는 <b>'제품별 품질 관리와 라인 내 에러 정보 통합 모니터링 및 분석 시스템 구축'</b>으로 보드마카를 생산하는 공장을 선정하였습니다.
해당 프로젝트는 <b>제품 생산, 모니터링, 품짐 관리를 하는 기능을 주요 기능</b>으로 생각하고 구현하였습니다.<br>
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
  - **Back End**<br>
![SpringBoot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-239120?style=for-the-badge)
![WebSocket](https://img.shields.io/badge/WebSocket-red?style=for-the-badge)
  - **Database**<br>
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
  - **Communication**<br>
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)
<br>

[목차](#table)
<br><br><br><br>

<span id="4"></span>
## ✋4. 담당 업무
#### [담당 업무]
- 웹소켓과 스레드를 이용한 생산 공정 로직 구현 (Process, Websocket)
- ChartJS를 통한 데이터 시각화 (.js 파일 코딩, LotService, ProcessService)
- 팀원의 Backend 에러 발생 시 트러블 슈팅 및 중복 코드 리팩토링
- 형상 관리에 대해 몰랐기 때문에 팀원이 개발한 코드들을 수동으로 합치는 작업
- Bulk insert를 통해 대량의 데이터 추가 시 성능 대폭 향상
<br>

[목차](#table)
<br><br><br><br>

<span id="5"></span>
## 🔍5. 주요 기능 소개
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

<span id="6"></span>
## ⌨6. 기술적 의사 결정
<details>
  <summary><b>서버 ↔ 클라이언트 간의 통신</b></summary>
  - 문제 : 제품을 생산하고 생산한 데이터를 <b>일정 시간을 주기</b>로 클라이언트측에 전송하고, 클라이언트 측에서는 데이터를 수신할 때마다 그래프로 시각화가 목적<br>
  - 해결 : 양방향 실시간 통신이 필요했기 때문에 Polling, Server Sent Envent, WebSocket 중 WebSocket을 선택<br>
  - 성과 : 서버의 WebSocket, 클라이언트의 SockJS를 이용하여 <b>양방향 통신을 구축</b>하고 구독을 통해 구독한 주소로 데이터가 수신될 때마다 <b>ChartJS를 이용하여 그래프 시각화</b>
</details>

<details>
  <summary><b>생산 지시 후 모니터링 페이지 즉시 전환</b></summary>
  - 문제 : 생산 지시를 하였을 때, 단순히 생산 반복문을 진행을 하게 될 경우 <b>동기적으로 작동</b>을 하기 때문에 반복문이 10초 소요되면 클라이언트는 10초 지연, 100초 소요 시 100초 지연이 되어버림<br>
  → 즉, <b>반복문에 소요되는 시간 = 클라이언트 지연 시간</b><br>
  - 해결 : 생산 지시를 하게 될 경우 메인 작업의 흐름을 방해하지 않고, <b>비동기적으로 백그라운드에서 실행</b>될 수 있도록 Thread의 사용이 필요<br>
  → <b>Thread의 라이프 사이클 등의 관리</b>를 편리하게 해주는 <b>ExecutorService</b>를 적용
  - 성과 : 비동기적으로 작동하기 때문에 그 어떤 <b>대기 없이 생산 지시 → 모니터링 페이지로 즉시 전환</b>
  → <b>지연시간 100% 성능 개선</b>
</details>

<details>
  <summary><b>벌크 INSERT을 이용한 대용량 데이터 추가 속도 성능 개선 → 3412%(34.12배) 증가</b></summary>
  - 문제 : 기존 JpaRepository의 saveAll() 방식으로는 <b>999개의 데이터</b> 추가 시 <b>약 2.79초 소요</b><br>
  - 해결 : Bulk Insert를 사용하여 MySQL에서 한 번에 insert를 하는 방법을 적용<br>
  - 성과 : Bulk INSERT 사용 시 <b>1000개의 데이터</b> 추가 시 <b>약 0.082초 소요</b><br>
  <img src="https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/11.Bulk.png" alt="벌크insert 이미지"><br><br>
  - 결론 : Bulk Insert 사용 전 후 <b>2.79초 → 0.082초</b>로 성능 개선
</details>
<br>

[목차](#table)
<br><br><br><br>

<span id="7"></span>
## 🚀7. 트러블 슈팅
<details>
  <summary><b>제품 생산이 2번 이상 요청되었을 경우 무한 반복 발생</b></summary>
  <b>&gt; 현상</b><br>
  - 기존의 코드는 Thread Pool 2개를 생성하여 각각 제품 생산과 N초마다 메세지를 보내는 무한 반복문으로 구성<br>
  - 1공정 생산 + 메세지 전송 -> 1공정 생산 종료 + 생산 메서드가 메세지 전송을 강제 종료 -> 2공정 생산 + 메세지 전송의 방식으로 진행될 것이라 예상하였으나<br>
  - 스레드는 운영체제에 의해 제어되어 순서 지정불가 <br><br>
  <img src="https://raw.githubusercontent.com/calmnature/pencraft/main/GIF/thread.gif" alt="스레드 이미지"><br>
  <b>&gt; 해결 방안</b><br>
  - 싱글 스레드로 1~4번의 제품 생산을 하나의 그룹(Task)으로 지정 → 메세지 전송 : 반복문에서 일정 주기마다 데이터 전송<br>
  - 2회 이상의 제품 생산 요청 시 요청 순서대로 생산 가능
</details>

<details>
  <summary><b>ChartJS를 이용한 데이터 시각화</b></summary>
  <b>&gt; 현상</b><br>
  최초 그래프를 그린 후 그래프가 새로 그려지지 않으며 F12에서 이미 사용중인 캔버스이므로 destroy를 해야한다는 에러 문구 발생<br>
  <b>&gt; 해결 방안</b><br>
  <b>한 개의 canvas 태그에는 한 개의 차트만</b> 그려질 수 있기에 다음과 같이 <b>차트를 그리기 전 차트가 있다면 해당 차트를 destroy()메서드로 차트를 없앤 뒤</b> 새로 그림
</details>

<br>

[목차](#table)
<br><br><br><br>

<span id="8"></span>
## ⭕8. 리팩토링 완료
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

<span id="9"></span>
## ❗9. 보완 사항
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

<span id="10"></span>
## ☑10. 보완 완료
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

<span id="11"></span>
## 🙆‍♂️11. 느낀 점 및 아쉬운 점
> 느낀점
- 스프링의 Controller, Service, Repository의 역할을 명확히 구분
- 웹소켓과 스레드를 이용한 실시간 통신을 구현할 수 있었으며, 몰랐던 새로운 기술을 응용하기 위한 방법 습득
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
