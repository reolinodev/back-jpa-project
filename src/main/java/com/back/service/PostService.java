package com.back.service;

import com.back.domain.Post;
import com.back.domain.dto.PostDto;
import com.back.domain.params.PostParam;
import com.back.repository.BoardAuthCustomRepository;
import com.back.repository.BoardRepository;
import com.back.repository.PostCustomRepository;
import com.back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostCustomRepository postCustomRepository;

    private final BoardRepository boardRepository;
    private final BoardAuthCustomRepository boardAuthCustomRepository;

    /**
     * 게시글을 등록합니다.
     */
    public Post createPost(PostParam postParam) {
        Post post = new Post();
        post.setCreateParam(postParam);
        post.board = boardRepository.findById(postParam.boardId).orElseThrow(RuntimeException::new);
        return postRepository.save(post);
    }

    /**
     * 게시글을 전체조회 합니다. 페이징처리
     */
    public Page<PostDto> getPosts(PostParam postParam) {
        postParam.setPaging(postParam.page);
        return postCustomRepository.findAllWithPaging(postParam, PageRequest.of(postParam.page, postParam.size));
    }

    /**
     * 게시글을 상세조회 합니다.
     */
    public PostDto getPost(Long id) {
        //상세 조회시 조회수를 1 올린다.
        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new);
        post.viewCnt = post.viewCnt + 1;
        postRepository.save(post);
        return postCustomRepository.findPostBy(id);
    }


    /**
     * 게시글을 수정합니다.
     */
    public Post updatePost(Long id, PostParam postParam) {
        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new);
        post.setUpdateParam(postParam);
        return postRepository.save(post);
    }

    public Boolean checkPostAuth(Long postId, Long authId) {
        PostDto postDto = postCustomRepository.findPostBy(postId);
        Long result = boardAuthCustomRepository.countByBoardIdAndAuthId(postDto.boardId, authId);
        return result != 0;
    }
}
