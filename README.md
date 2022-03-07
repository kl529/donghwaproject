# 1. donghwa_project 간단 설명
  동화팀의 첫번째 프로젝트 입니다.   
  google의 speech to text, text to speech를 이용하여 동화를 읽어주는 웹페이지를 만들어 보고자 합니다.   
  저희 프로젝트의 이름은 **Liverary**입니다.

# 2. 프론트 엔드
## 2.1 프론트엔드 예시
https://www.figma.com/file/1mX0J3iX3y7ePxbevfGsm0/Untitled

## 2.2 페이지 종류 + 구현 기능
  ### 1. **랜딩페이지**
  ```
      - 소셜로그인 ( 구글 / 네이버 )
      - 프로젝트 소개
      - 공유하기
      - Today 방문자수
   ```

  ### 2. **홈페이지**
  ```
      - 책 검색 ( 제목, 저자 )
      - 로그아웃
      - 읽고 있는책 리스트 표시 
      - 새로 들어온 책 리스트 최신순으로 표시 
   ```

  ### 3. **내정보 페이지**
  ```
      - 지금까지 읽은 책 리스트 표시 ( 책제목 / 표지 )
      - 평점 남기기 / 평점 확인하기
      - 독후감 쓴 책 리스트 표시
      - 작성한 독후감 보러 가기
      - 읽는 중인 책 리스트 표시
   ```


  ### 4. **책 소개 페이지**
  ```
      - 책 표지 이미지 표시
      - 책 제목, 저자, 출판사, 책소개 등 소개 정보 표시
      - 책 읽기 ( 이어 읽기 / 처음부터 읽기 )
      - 독후감 쓰기
      - 독후감 확인하기
  ```

  ### 5. **책 읽기 페이지**
  ```
      - 책 내용 표시
      - 진행상황 확인 (게이지)
      - 이전 페이지
      - 다음 페이지
      - 책 내용을 음성으로 듣기 (TTS)
  ```

  ### 6. **독후감 작성 페이지**
  ```
      - 책 정보 확인
      - 평점 남기기
      - 독후감 직접 작성하기
  ```

  ### 7. **관리자용 페이지**
  ```
      - 책 등록 ( 책표지 : 이미지 파일 업로드 가능 )
      - 책 삭제
  ```

# 3. 백엔드
## 3.1 DB Schema
### 1. Book Table
| Type | Column | Extra |
|:---:|:---:|:---:|
Long | book_key 책키 | Nullable = False / Auto_Increment
String | author 저자 | Nullable = False
String | book_content 책 내용 | Nullable = False
String | book_intro 책 소개 | Nullable = False
String | country 나라 | Nullable = True
Datetime | created_date 등록날짜 | Nullable = True
Long | fileId 책표지 id | Nullable = True
Datetime | modified_date 수정날짜 | Nullable = True
Date | published_date 출간날짜 | Nullable = False
String | publisher 출판사 | Nullable = False
String | title 책제목 | Nullable = False
Int | total_page 페이지수 | Nullable = False

  
### 2. User Table
| Type | Column | Extra |
|:---:|:---:|:---:|
Long | user_key 유저키 | Nullable = False / Auto_Increment
Datetime | created_date 등록 날짜 | Nullable = True
String | email 이메일 | Nullable = False
Datetime | created_date 등록 날짜 | Nullable = True
String | role 역할 | Nullable = True
  
### 3. Reading Table
| Type | Column | Extra |
|:---:|:---:|:---:|
Long | book_key 북키 | Nullable = True
String | book_report 독후감 내용 | Nullable = True
Int | current_page 현재 페이지 | Nullable = False
Boolean | is_written_book_report 독후감 여부 | Nullable = False
Long | reading_key 독서키 | Nullable = False / Auto_Increment
Int | score 평점 | Nullable = False
Long | user_key 유저키 | Nullable = True

### 4. File Table
| Type | Column | Extra |
|:---:|:---:|:---:|
String | file_path 파일주소 | Nullable = False
String | filename 파일명 | Nullable = False
Long | id 파일id | Nullable = False / Auto_Increment
String | orig_filename 기존 파일명 | Nullable = False

## 3.2 Method
  - Book
    - saveBook -> 책 등록
    - updateBook -> 책 내용 수정
    - deleteBook -> 책 삭제
    - getBookList -> 모든 책 리스트 받기
    - findBookById -> Id에 따른 책 찾기
    - findeAllSearch -> 책 키워드 검색
 
  - Reading
    - startReading -> 책 읽기 시작
    - saveBookReport -> 독후감 등록하기
    - giveScore -> 평점 주기
    - calcPage -> 페이지 관리하기
    - updateReading -> 읽은 책 업데이트
    - deleteReading -> 읽은 책 삭제
    - findById -> Id에 따른 읽은 책 찾기
    - findAllDesc -> book_key나 user_key로 id찾기
    - findReadingDesc -> user_key, book_key 둘다 해당하는 id 찾기
          
  - User
    - getUserKey -> 이메일을 통해 userKey 찾기
    - findByEmailUser -> 이메일을 통해 유저 찾기
    - deleteUser -> 유저 삭제

  - File
    - saveFile -> 파일 등록하기
    - getFile -> 파일 받아오기
