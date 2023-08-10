package com.back.service;

import com.back.domain.Board;
import com.back.domain.dto.BoardAuthDto;
import com.back.domain.dto.BoardDto;
import com.back.domain.params.BoardParam;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;


    @Test
    void createBoard() {
        //given
        BoardParam boardParam = new BoardParam();
        boardParam.boardTitle = "FAQ";
        boardParam.boardType = "FAQ";
        boardParam.useYn = "Y";
        boardParam.memo = "FAQ게시판";
        boardParam.attachYn = "Y";
        boardParam.commentYn = "Y";
        boardParam.createdId = 2L;
        boardParam.authIdArr = new Long[]{1L};

        //when
        Board result  = boardService.createBoard(boardParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("FAQ", result.boardTitle);
    }

    @Test
    void getBoards() {
        //given
        BoardParam boardParam = new BoardParam();
        boardParam.useYn = "Y";


        //when
        Page<BoardDto>  boards  = boardService.getBoards(boardParam);
        System.out.println("result = " + boards);

        //then
        Assertions.assertEquals(1, boards.getTotalPages());
    }


    @Test
    void getBoard() {
        //given
        Long id = 1L;

        //when
        BoardDto  board  = boardService.getBoard(id);
        System.out.println("result = " + board);

        //then
        Assertions.assertEquals("공지사항", board.boardTitle);
    }


    @Test
    void updateBoard() {
        //given
        BoardParam boardParam = new BoardParam();
        boardParam.boardTitle = "FAQ1";
        boardParam.useYn = "N";
        boardParam.memo = "FAQ게시판1";
        boardParam.attachYn = "N";
        boardParam.commentYn = "N";
        boardParam.updatedId = 2L;
        boardParam.authIdArr = new Long[]{1L, 2L};

        //when
        Board result  = boardService.updateBoard(2L, boardParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("FAQ1", result.boardTitle);
    }

    @Test
    void getBoardAuths() {
        //given
        Long id = 2L;

        //when
        List<BoardAuthDto> boardAuths  = boardService.getBoardAuths(id);
        System.out.println("result = " + boardAuths);

        //then
        Assertions.assertEquals(1, boardAuths.size());
    }


    @Test
    void getItemUsedBoards() {
        //given
        String boardType = "POST";

        //when
        List<BoardDto>  getUsedBoardsResult  = boardService.getItemUsedBoards(boardType);
        System.out.println("result = " + getUsedBoardsResult);

        //then
        Assertions.assertEquals(1, getUsedBoardsResult.size());
    }
}
