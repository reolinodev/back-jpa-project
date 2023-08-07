package com.back.controller;


import com.back.domain.Auth;
import com.back.domain.Board;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.AuthDto;
import com.back.domain.dto.BoardAuthDto;
import com.back.domain.dto.BoardDto;
import com.back.domain.params.BoardParam;
import com.back.service.BoardService;
import com.back.support.ResponseUtils;
import com.back.token.JwtUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final JwtUtils jwtUtils;

    //게시판을 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createBoard(

        @Validated(ValidationGroups.BoardCreateGroup.class) @RequestBody BoardParam boardParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        String message = "게시판이 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        boardParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        Board createBoardResult = boardService.createBoard(boardParam);

        if(!createBoardResult.boardTitle.equals(boardParam.boardTitle)){
            message ="게시판 생성에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

    //게시판을 전체 조회한다
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getBoards(@RequestBody BoardParam boardParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        Page<BoardDto> getBoardsResult = boardService.getBoards(boardParam);
        Long totalCount = getBoardsResult.getTotalElements();

        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getBoardsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //게시판을 상세조회한다
    @GetMapping("/{id}")
    public ResponseEntity <Map<String,Object>> getBoard(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        BoardDto getBoardResult = boardService.getBoard(id);

        int count = 0;
        if (getBoardResult != null) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";

        List<BoardAuthDto> getBoardAuthsResult = boardService.getBoardAuths(id);

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getBoardResult);
        responseMap.put("boardAuths", getBoardAuthsResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //게시판을 수정한다.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateBoard(@PathVariable Long id,
        @Validated(ValidationGroups.BoardUpdateGroup.class) @RequestBody BoardParam boardParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        boardParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        Board updateBoardResult = boardService.updateBoard(id, boardParam);

        String message = "게시판이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(!updateBoardResult.updatedId.equals(boardParam.updatedId)){
            message ="게시판 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));


        return new ResponseEntity<>(responseMap, status);
    }
}
