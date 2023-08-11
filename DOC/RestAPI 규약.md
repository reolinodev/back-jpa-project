# RestAPI 규약

###1. URL Rules
####  * 마지막에 / 포함하지 않는다.
Bad
````
http://api.test.com/users/
````
Good
````
http://api.test.com/users
````
***

####  *  _(underbar) 대신 -(dash)를 사용한다. -(dash)의 사용도 최소한으로 설계한다. 정확한 의미나 표현을 위해 단어의 결합이 불가피한 경우 반드시 -(dash) 사용한다.
Bad
````
http://api.test.com/users/post_commnets
````
Good
````
http://api.test.com/users/post-commnets
````
***

####  *  소문자를 사용한다.
Bad
````
http://api.test.com/users/postCommnets
````
Good
````
http://api.test.com/users/post-commnets
````
***

####  * 행위(method)는 URL에 포함하지 않는다.
Bad
````
POST http://api.test.com/users/1/delete-post/1
````
Good
````
DELETE http://api.test.com/users/1/posts/1
````
***
####  * 컨트롤 자원을 의미하는 URL 예외적으로 동사를 허용한다. 함수처럼, 컨트롤 리소스를 나타내는 URL은 동작을 포함하는 이름을 짓는다.

Bad
````
http://api.test.com/posts/duplicating
````
Good
````
http://api.test.com/posts/duplicate
````
***


###2. RestApi status 및 result_code 규약

STATUS|result_code|description|
|------|---|---|
|200|ok|API 성공|
|200|newToken|access_token이 유효가 만료되고 refresh_token을 재발급|
|200|pwchange|비밀번호 초기화가 필요한 경우|
|201|ok|create 성공
|400|fail|API 실패
|400|invalid|파라미터 타입 유효하지 않음
|401|unauthorized|access_token 발급실패|
|403|tokenInvalid|access_token과 refresh_token 유효기간이 종료 되었을때
|500|error|서버에러|

