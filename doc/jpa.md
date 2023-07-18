#JPA 사용예시

###method	 

| method | 기능 |
|---|:---|
|save()|레코드 저장 (insert, update)|
|findAll()|전체 레코드 불러오기. 정렬(sort), 페이징(pageable) 가능|
|findOne()|primary key로 레코드 한건 찾기|
|count()| 레코드 갯수|
|delete()|레코드 삭제|


### keyword
| method | sample | descript |
|---|:---|:---|
|And                |findByEmailAndUserId(String email, String userId)      |여러필드를 and 로 검색|
|Or                 |findByEmailOrUserId(String email, String userId)       |여러필드를 or 로 검색|
|Between            |findByCreatedAtBetween(Date fromDate, Date toDate)     |필드의 두 값 사이에 있는 항목 검색|
|LessThan           |findByAgeGraterThanEqual(int age)                      |작은 항목 검색|
|GreaterThanEqual   |findByAgeGraterThanEqual(int age)                      |크거나 같은 항목 검색|
|Like               |findByNameLike(String name)                            |like 검색|
|IsNull             |findByJobIsNull()                                      |null 인 항목 검색|
|In                 |findByJob(String … jobs)                               |여러 값중에 하나인 항목 검색|
|OrderBy            |findByEmailOrderByNameAsc(String email)                |검색 결과를 정렬하여 전달|





#querysdl

### 조건절 적용하기
```java
     User findUser = queryFactory
        .selectFrom(user)
        .where(
            user.loginId.isNotNull().and(user.id.in(1L, 3L)) //id in (1L, 3L)
        )
        .fetchOne();
```

### 검색조건
```java
     user.loginId.eq("test1@gmail.com") //login_id = 'test1@gmail.com'
    ,user.loginId.ne("test1@gmail.com") //login_id != 'test1@gmail.com'
    ,user.loginId.eq("test1@gmail.com").not() //login_id != 'test1@gmail.com'
    ,user.loginId.isNotNull() //login_id is not null
    ,user.id.in(1L, 3L) //id in (1L, 3L)
    ,user.id.between(1L, 3L) //id between 1L, 3L
    ,user.id.goe(3L) // id >= 3L
    ,user.id.gt(3L) // id > 3L
    ,user.id.loe(3L) // id <= 3L
    ,user.id.lt(3L) // id < 3L
    ,user.loginId.like("test1%") // login_id like 'test1%'
    ,user.loginId.contains("test1") // login_id like '%test1%'
    ,user.loginId.startsWith("test1") // login_id like 'test1%'	 
```

### 기본적인 쿼리 메서드
| method | 기능 |
|---|:---|
|select()||
|from()||
|selectFrom()|select하는 엔티티와 from의 엔티티가 일치한 경우 합칠 수 있다.|
|where()||
|update()||
|set()||
|delete()||

### 결과 조회 메서드
| method | 기능 |
|---|:---|
|fetch()|리스트 조회, 데이터가 없으면 빈 리스트 반환|
|fetchOne()|단 건 조회, 결과 없으면 null, 결과가 2개 이상이면 NonUniquerResultException 반환|
|fetchFirst()|첫번째 결과 조회|
|fetchFirst()|페이징 정보 포함 및 totalCount 쿼리를 추가 실행|
|fetchCount()|count 쿼리로 변경해서 count 수를 조회할 수 있다.|
 
	 	 
	 	 
	 	 
