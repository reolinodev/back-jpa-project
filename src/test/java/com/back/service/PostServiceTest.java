package com.back.service;

import com.back.domain.Post;
import com.back.domain.dto.PostDto;
import com.back.domain.params.PostParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void createPost() {
        //given
        PostParam postParam = new PostParam();
        postParam.postTitle ="첫째 공지사항입니다.";
        postParam.mainText ="본문입니다.";
        postParam.boardId = 1L;

        //when
        Post createPostResult  = postService.createPost(postParam);
        System.out.println("result = " + createPostResult);

        //then
        Assertions.assertEquals("첫째 공지사항입니다", createPostResult.postTitle);
    }

    @Test
    void getPosts() {
        //given
        PostParam postParam = new PostParam();
        postParam.useYn ="Y";
        postParam.postTitle = "공지";
        postParam.size = 10;
        postParam.page = 1;
        postParam.createdNm = "관리자";
        postParam.startDate = "2023-08-07";
        postParam.endDate = "2023-08-07";
        postParam.boardId = 1L;

        //when
        Page<PostDto> getPostsResult  = postService.getPosts(postParam);
        System.out.println("result = " + getPostsResult);

        //then
        Assertions.assertEquals(1, getPostsResult.getTotalElements());
    }

    @Test
    void getPost() {
        //given
        Long id = 1L;

        //when
        PostDto getPostResult  = postService.getPost(id);
        System.out.println("result = " + getPostResult);

        //then
        Assertions.assertEquals("첫째 공지사항입니다.", getPostResult.postTitle);
    }

    @Test
    void updatePost() {
        //given
        PostParam postParam = new PostParam();
        postParam.postTitle ="첫째 공지사항입니다.1";
        postParam.mainText ="본문입니다.1";
        postParam.updatedId = 1L;
        postParam.useYn = "N";

        //when
        Post updatePostResult  = postService.updatePost(1L, postParam);
        System.out.println("result = " + updatePostResult);

        //then
        Assertions.assertEquals("첫째 공지사항입니다.1", updatePostResult.postTitle);
    }

    @Test
    void checkPostAuth() {
        //given
        Long postId = 1L;
        Long authId = 1L;

        //when
        Boolean checkPostAuthResult  = postService.checkPostAuth(postId, authId);
        System.out.println("result = " + checkPostAuthResult);

        //then
        Assertions.assertEquals(true, checkPostAuthResult);
    }

}
