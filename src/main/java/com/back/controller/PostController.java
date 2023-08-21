package com.back.controller;

import com.back.domain.Post;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.PostDto;
import com.back.domain.params.PostParam;
import com.back.service.BoardService;
import com.back.service.PostService;
import com.back.support.ResponseUtils;
import com.back.token.JwtUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final BoardService boardService;

    private final JwtUtils jwtUtils;

    //게시글을 전체조회한다
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getPosts(@RequestBody PostParam postParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        Page<PostDto> getPostsResult = postService.getPosts(postParam);
        Long totalCount = getPostsResult.getTotalElements();

        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getPostsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //게시글을 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createPost(
        @Validated(ValidationGroups.PostCreateGroup.class) @RequestBody PostParam postParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "게시글이 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        postParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        Post createPostResult = postService.createPost(postParam);

        if(!createPostResult.postTitle.equals(postParam.postTitle)){
            message ="게시판 생성에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }


    //게시글을 상세조회한다
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getPost(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        PostDto getPostResult = postService.getPost(id);

        int count = 0;
        if (getPostResult != null) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";
        boolean myPost = false;

        long userId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        //본인의 글이 맞는지 확인한다.
        if(userId == Objects.requireNonNull(getPostResult).createdId){
            myPost = true;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getPostResult);
        //수정권한이 있는지 체크해준다.
        responseMap.put("myPost", myPost);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    //게시글을 수정한다.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updatePost(@PathVariable Long id,
        @Validated(ValidationGroups.PostUpdateGroup.class)@RequestBody PostParam postParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        postParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        Post updatePostResult = postService.updatePost(id, postParam);

        String message = "게시글이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(!updatePostResult.updatedId.equals(postParam.updatedId)){
            message ="게시글 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));


        return new ResponseEntity<>(responseMap, status);
    }

    // 등록, 수정권한이 있는 게시판인지 확인한다.
    @PostMapping("/auth")
    public ResponseEntity<Map<String,Object>> checkBoardAuth(
        @RequestBody PostParam postParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        Long checkBoardAuthResult = boardService.checkBoardAuth(postParam.boardId, postParam.authId);

        String message = checkBoardAuthResult+"건이 조회되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", checkBoardAuthResult);

        return new ResponseEntity<>(responseMap, status);
    }

}
