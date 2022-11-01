# 쇼핑몰 프로젝트(진행중)

## 취지

 - 쇼핑몰과 게시판, 회원 관리 기능이 포함된 프로젝트를 진행한다.
 - 단순한 기능을 넘어서 실제로 유저가 사용하는 입장에서 Javascript나 JQuery, Ajax를 통한 디테일한 기능을 구현하는 것에 집중한다.

## 사용 기술

 - Java 11
 - Spring Boot 2.6
 - Spring Security
 - Mybatis
 - Mysql 8.0
 - Javascript
 - JSP(Bootstrap)
 - JQuery
 - Ajax
 

## DB 설계

![Shopping](https://user-images.githubusercontent.com/93370148/195993804-f13adb7c-3fe1-4517-8d83-e870cb35149f.png)

## 요구사항

### 회원관리

 - 로그인, 회원가입, 회원정보 변경(비밀번호 변경) 기능을 구현한다.
 - 로그인은 카카오톡 로그인이 가능하도록 연동한다.
 - 회원가입의 경우 아이디(이메일), 비밀번호, 이름, 전화번호, 주소(상세주소), 이메일 수신여부를 입력하도록 한다.
 - 로그인은 자동로그인을 체크할 경우 다음 로그인 시 자동 로그인이 되도록 한다.
 
 
### 게시판

 - 게시판은 게시글 성격을 설정하여 글을 작성한다.
 - 글 작성시 파일은 최대 3개까지 첨부가 가능하고, 개당 용량은 최대 1MB로 제한한다.
 - 게시글은 본인이 작성한 글만 수정 및 삭제가 가능하다.
 - 페이징 기능을 구현한다.
 - 글 분류, 검색 조건, 날짜, 검색어 별로 검색해서 원하는 게시글만 볼 수 있도록 검색 기능을 구현한다.
 - 각각의 게시글 당 댓글 기능을 구현한다.
 
 
### 쇼핑몰

 - 리스트는 그리드 형식으로 구현하고 상품명, 가격, 수량, 판매자, 작성일자를 볼 수 있다.
 - 글 작성시 판매 상품명, 수량, 가격, 상품정보를 입력할 수 있다.
 - 페이징을 구현하여 한 페이지당 12개의 상품을 보여준다.
 - 구매한 상품에 대하여 후기 및 평점을 작성할 수 있다.
 - 각 판매 상품에 대해 질문을 할 수 있고 판매자는 답변을 제공한다.



## 프로젝트 일지

2022-10-10(월)

 - 프로젝트 생성 및 게시글 리스트 조회 및 작성 기능을 구현
 


2022-10-11(화)

 - 게시글 상세보기 기능 및 페이징 기능을 구현



2022-10-12(수)

 - 페이징 기능 구현의 오류 발생 및 검색 데이터 유지가 되지 않아서 수정
 - 게시글 수정 기능을 본인만 가능하도록 수정



2022-10-13(목)

 - 페이징 기능 마무리
 
 
 
2022-10-14(금)

 - 회원가입시 아이디 중복체크, 비밀번호 확인 여부, 카카오 주소 API 적용 구현
 
 
 
2022-10-15(토)

 - Spring Security를 이용한 로그인 기능 구현
 - 비밀번호 변경시 비밀번호 확인 체크 
 
2022-10-16(일)

 - 카카오 SNS 로그인을 구현하였으나 로그아웃 시 서버에 정보가 그대로 남아있는 부분을 해결하지 못함
 - 카카오 오류로 인해서 제대로 된 테스트 실행이 어려움
 
 
2022-10-17(월)

 - 파일 업로드 구현
 - 최대 3개, 개당 1MB 제한을 기준으로 자바스크립와 스프링 사용하여 프론트와 백에서 이중으로 validation
 

2022-10-18(화)

 - 카카오 로그아웃 
 
2022-10-19(수)

 - 회원 로그아웃 시 자체 로그아웃과 카카오 로그아웃을 분기처리해야 함을 인식(아직 구현은 하지 못함)

2022-10-21(금)

 - 물건사기 코너 구현 시작
 - 리스트 한 페이지당 10개의 게시물이 나오도록 설정
 - 썸네일 이미지, 상품 이름, 내용 등이 나올 수 있도록 구현

2022-10-22(토)

 - summernote 에디터 추가
 - textarea에 이미지 업로드시 오류가 발생하여 DB저장시 ajax를 통해 이미지 url을 같이 저장할 수 있도록 설정

2022-10-23(일)

 - 상품 등록 기능 구현
 - 썸네일 이미지 등록시 버튼 커스텀 
 
2022-10-27(목)

 - 상품 후기 기능 추가
 - 사진 첨부랑 사진만 따로 볼 수 있도록 기능 추가 필요
 
2022-10-30(일)

 - 댓글 삭제시 바로 화면에서 사라지게 하게끔 Ajax를 통한 기능 추가함.
 - document.on 활용 및 document.ready()와의 차이점 이해
 
2022-10-31(월)

 - 물건사기 부분 가격에 콤마 표기 추가

