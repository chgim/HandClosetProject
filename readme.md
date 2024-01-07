# Hand Closet

## 프로젝트 개요
- 프로젝트 주제: 개인 옷장 시스템을 활용한 옷차림 추천 웹앱
- 프로젝트 이름: Hand Closet
- 개발 인원: 2명
- 개발 기간: 2023.05.14~
- Programing Languages: Java 11, Html5, Css3, Javascript
- Framework/ Library: react, Spring Boot, Spring Security, JWT
- Server: H2, Apache Tomcat, Redis
- Tooling/ DevOps: GitHub, IntelliJ IDEA, Visual Studio Code, Postman 
- Api: OpenWeather, kakaomap

## 프로젝트 기능
### 사용자
1.  회원: Spring Security, JWT를 이용하여 Token 방식의 회원 로직 구현. Redis와 DB에서 RefreshToken 관리
2.  디지털 옷장: 아이템 이미지, 카테고리, 계절, 색상, 설명 CRUD. 이미지는 200 X 200 사이즈 리사이징 후 파일 시스템에 저장
3.  다이어리: 날짜 선택 후 Thumbnail, 계절, 디지털 옷장에 등록된 아이템 선택하여 저장, 상세보기, 삭제. 다이어리 저장 시 선택 된 아이템의 등록일 선택한 날짜에 맞게 업데이트, 입은횟수 +1. 삭제 시 이전 등록일로 업데이트 ,입은 횟수 -1.
4.  통계: 사용자가 등록한 아이템을 기반으로 요즘 입지 않은 옷, 가장 자주 입은 옷, 의류 카테고리 순위, 계절 별 상세 통계 제공
5.  코디 추천: kakaomap, OpenWeather API를 활용하여 사용자의 현재 위치의 기온을 가져오고, 해당 기온에 맞는 옷차림 추천. (많이 입은 옷, 적게 입은 옷, 상황 별<캐주얼/포멀>, 랜덤)  
6.  마이페이지: 로그인 한 사용자 정보 조회, 로그아웃, 회원 정보 수정, 탈퇴   
### 관리자
1. 회원 관리


## 프로젝트 ppt
https://github.com/chgim/HandClosetProject/blob/main/%EA%B0%9C%EC%9D%B8%20%EC%98%B7%EC%9E%A5%20%EC%8B%9C%EC%8A%A4%ED%85%9C%EC%9D%84%20%ED%99%9C%EC%9A%A9%ED%95%9C%20%EC%98%B7%EC%B0%A8%EB%A6%BC%20%EC%B6%94%EC%B2%9C%20%EC%9B%B9%EC%95%B1.pptx


