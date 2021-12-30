# reading

## getReadingBookByUser
해당 유저가 읽고 있는 책 목록을 리턴
reading DB에서 해당 UserKey와 매칭되는 BookKey를 모두 가져옴 (SQL)

[ GET, POST ]  
* parameters 
  * **userKey** (string) 
    선택한 유저
* result (JSON)
    ```
    {
        "error": {
            "code": 0,
            "message": ""
        },
        "data": [
	]
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
  * **data** (string)  
    결과값
    
## startReading
유저가 책을 읽기 시작함
reading DB에 해당 유저와 책 데이터가 들어간 row를 삽입 (

[ GET, POST ]  
* parameters
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
  * **contents** (string)  
    입력한 독후감 내용

* result (JSON) 
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보

## saveBookReport
독후감 내용을 저장함
DB에 입력한 독후감 내용을 해당되는 id row에 저장하고, isWrittenBookReport를 True로 만들어줌 (SQL문)

[ GET, POST ]  
* parameters
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
  * **contents** (string)  
    입력한 독후감 내용

* result (JSON) 
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보

## recordBookReport
독후감 내용 입력을 위한  녹음을 시작함
소리 -> 텍스트 변환하는 API를 사용해서, 음성을 인식해서 텍스트로 바꿔줌 (Speech to text API 사용)

[ GET, POST ]  
* parameters 
  * **contents** (string) (음성메시지) 
    입력받은 음성파일
* result (JSON)
    ```
    {
        "error": {
            "code": 0,
            "message": ""
        },
        "data": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
  * **data** (string)  
    결과값

## giveScore
별점을 입력함
별점을 입력받고, DB에 저장함 (SQL)

[ GET, POST ]  
* parameters
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
  * **score** (int)  
    별점 (최소 1 ~ 최대 5)
* result (JSON)
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보

## plusCurrentPage
다음페이지로 넘어감
CurrentPage를 +1 해줌 (SQL)

[ GET, POST ]  
* parameters 
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
* result (JSON) 
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보

## minusCurrentPage
이전페이지로 넘어감
CurrentPage를 -1 해줌 (SQL)

[ GET, POST ]  
* parameters 
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
* result (JSON)
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보

## playAudio
해당 부분의 오디오를 재생함
TotalPage와 CurrentPage를 비교해서, 해당 부분 텍스트를 음성으로 변환하는 API에 적용한다. (Text to Speecth API)

[ GET, POST ]  
* parameters
  * **userKey** (Long)  -> 현재페이지를 가져오기 위한 값
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
* result (JSON)
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보

## stopAudio
오디오를 멈춘다.
오디오 재생 API를 일시정지한다. (API)

[ GET, POST ]  
* parameters
X
* result (JSON)
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
    
## getScore
해당 도서의 별점을 받아온다.
DB에서 해당도서의 score를 받아온다. (SQL)

[ GET, POST ]  
* parameters
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
* result (JSON) 
    ```
    {
        "error": {
            "code": 0,
            "message": ""
        },
        "data": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
  * **data** (int)  
    결과값
    
## getCurrentPage
해당 도서의 현재 읽고 있는 페이지를 가져온다.
DB에서 해당 도서의 현재 페이지를 가져온다. (SQL)

[ GET, POST ]  
* parameters
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
* result (JSON)
    ```
    {
        "error": {
            "code": 0,
            "message": ""
        },
        "data": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
  * **data** (int)  
    결과값
    
## getIsWrittenBookReport
독후감이 적혀져 있는지 확인
DB에서 isWrittenBookReport의 값을 받아온다 (SQL)

[ GET, POST ]  
* parameters 
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id
  * **contents** (string)  
    입력한 독후감 내용

* result (JSON)
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보    
    
## getBookReport
현재 읽는 중인책(reading) 클래스에 속하며 사용자가 작성한 독후감(bookReport)값을 가져올때 사용한다.
getIsWrittenBookReport를 사용하여 사용자가 bookReport를 작성한 경우에만(isWrittenBookReport = true)인 경우에만 값(contents)을 리턴 / false일때는 그냥 비어있는 string을 return


[ GET, POST ]  
* parameters ***일단 생각나는대로 정리해보기 / 쿠키나 세션을 쓸 수 도 있지만, 일단 연관되는 인자 정리하기***
  * **userKey** (Long)  
    현재 선택된 유저 id
  * **bookKey** (Long)  
    현재 선택된 책 id

* result (JSON) ***결과 형태은 바뀔 수 있음. 일단 정리해두는 것은, 어떤 결과값 요소가 나와야되는지 정리***
    ```
    {
        "error": {
            "code": 0,
            "message": ""
        },
        "data": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
    ex. 잘못된 회원정보를 사용하였습니다.
    ex. 회원이 현재 읽고 있는 중인 책이 아닙니다.
  * **data** (string)
    독후감(bookReport) 내용
    독후감을 입력한 적이 없다면 ""을 리턴



# book

## loadRecentBook(int page) 
	홈페이지에 등록된 시간을 기준으로 최신순으로 책 목록을 가져옴.  (SQL)
	page당 4-5권의 책을 load할 것. 
[ GET, POST ]  
* parameters
  * **page** (int)  
* data 결과값
  * **bookKey** (Long)  

##  searchBook
	사용자가 검색어를 입력하면 책 제목이나 저자 이름에 해당 검색어가 포함되어 있으면 해당하는 책이나 저자의 책을 찾아줌.  (SQL)
[ GET, POST ]  
* parameters 
	  * **search** (String)  
* data 결과값
  * **bookKey** (Long)  

##  getTitle
	해당하는 도서의 제목을 받아옴. 
	DB에서 해당 도서의 Title을 받아온다. (SQL)
[ GET, POST ]  
* parameters
  * **bookKey** (Long)  
* data 결과값
  * **title** (String )  
	

## getAuthor
	해당하는 도서의 저자를 받아옴. 
	DB에서 해당 도서의 Author를 받아온다. (SQL)
[ GET, POST ]  
* parameters 
  * **bookKey** (Long)  
* data 결과값
  * **author** (String )  

## getPublisher
	해당하는 도서의 출판사를 받아옴.	
	DB에서 해당 도서의 Publisher를 받아온다. (SQL)
[ GET, POST ]  
* parameters 
  * **bookKey** (Long)  
* data 결과값
  * **publisher** (String )  


## getbookIntro
	해당하는 도서의 책 소개를 받아옴.	
	DB에서 해당 도서의 bookIntro를 받아온다. (SQL)
[ GET, POST ]  
* parameters
  * **bookKey** (Long)  
* data 결과값
  * **bookIntro** (String )  

## getbookCover
	해당하는 도서의 책 표지를 받아옴.	
	DB에서 해당 도서의 bookCover를 받아온다. (SQL)
[ GET, POST ]  
* parameters
  * **bookKey** (Long)  
* data 결과값
  * **bookCover** (String )  

# user

## deleteAccount
회원가입한 사용자가 계정 삭제를 원할때 사용
userDB에서 해당 row삭제 필요
readingDB에서도 해당 user와 관련된 reading모두 삭제 필요


[ GET, POST ]  
* parameters ***일단 생각나는대로 정리해보기 / 쿠키나 세션을 쓸 수 도 있지만, 일단 연관되는 인자 정리하기***
  * **userKey**(Long)

* result (JSON) ***결과 형태은 바뀔 수 있음. 일단 정리해두는 것은, 어떤 결과값 요소가 나와야되는지 정리***
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
    ex. 가입된 회원정보가 아닙니다.
    ex. 해당 회원과 관련된 정보(reading)를 삭제하던 중 오류가 발생했습니다.


## deleteReading
현재 읽고 있는 책 목록중 일부를 삭제하고 싶을때 사용
reading DB에 해당되는 row가 삭제한다.


[ GET, POST ]  
* parameters ***일단 생각나는대로 정리해보기 / 쿠키나 세션을 쓸 수 도 있지만, 일단 연관되는 인자 정리하기***
  * **userKey**(Long)
  * **bookKey**(Long)
  
* result (JSON) ***결과 형태은 바뀔 수 있음. 일단 정리해두는 것은, 어떤 결과값 요소가 나와야되는지 정리***
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
    ex. 잘못된 회원정보를 사용하였습니다.
    ex. 회원이 현재 읽고 있는 중인 책이 아닙니다.

## logout
현재 로그인 중인 사용자가 로그아웃을 원할때 사용

[ GET, POST ]  
* parameters ***일단 생각나는대로 정리해보기 / 쿠키나 세션을 쓸 수 도 있지만, 일단 연관되는 인자 정리하기***
  * **userKey**(Long)

* result (JSON) ***결과 형태은 바뀔 수 있음. 일단 정리해두는 것은, 어떤 결과값 요소가 나와야되는지 정리***
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
    ex. 잘못된 회원정보를 사용하였습니다.

# admin

## createBook
관리자 계정에서 새로운 책을 등록할때 사용한다.
unique한 bookKey를 생성하며 book DB에 row가 추가 되어야 한다.
기존에 저장되어 있는 책과 같은 경우에는 새로 DB에 추가하지 않는다 
(isbn을 이용하여 기존에 저장되어 있었던 책인지 확인한다)

[ GET, POST ]  
* parameters ***일단 생각나는대로 정리해보기 / 쿠키나 세션을 쓸 수 도 있지만, 일단 연관되는 인자 정리하기***
  관리자에게 책등록에 필요한 정보를 모두 입력받아야 한다.
  
  * **title**(string)
  * **author**(string)
  * **isbn**(string)
  * **publisher**(string)
  * **country**(string)
      Nullable = true 로 생각중인 상황
  * **bookIntro**(string)
      책 소개 페이지에서 사용
  * **bookCover**(string)
       이미지 링크만 가지고 와야함
  * **bookContent**(string)
  * **publishedDate**(string)

* result (JSON) ***결과 형태은 바뀔 수 있음. 일단 정리해두는 것은, 어떤 결과값 요소가 나와야되는지 정리***
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
    ex.  이미 저장된 책입니다
    ex. 필요한 정보를 모두 입력하지 않았습니다.

## deleteBook
관리자 계정에서 책을 삭제할때 사용한다.
bookKey를 이용하여 book DB에서 해당되는 row가 삭제되어야 한다.
삭제하는 책이 reading에 속하는 책일수 있으므로 reading DB에서도 해당 책에 관련된 정보가 모두 삭제되어야 함에 주의

[ GET, POST ]  
* parameters ***일단 생각나는대로 정리해보기 / 쿠키나 세션을 쓸 수 도 있지만, 일단 연관되는 인자 정리하기***
  * **bookKey**(Long)

* result (JSON) ***결과 형태은 바뀔 수 있음. 일단 정리해두는 것은, 어떤 결과값 요소가 나와야되는지 정리***
    ```
    {
        "code": 0,
        "message": ""
    }
    ```
  * **code** (integer)  
    처리 결과 코드. 오류가 없을 시 0
  * **message** (string)  
    오류 상세 정보
    ex. 해당책과 관련된 읽는중인 책(reading)을 삭제하던 도중 오류가 발생했습니다.
    
