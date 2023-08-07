package com.back.service;

import com.back.domain.Board;
import com.back.domain.BoardAuth;
import com.back.domain.dto.BoardAuthDto;
import com.back.domain.dto.BoardDto;
import com.back.domain.params.BoardParam;
import com.back.repository.AuthRepository;
import com.back.repository.BoardAuthCustomRepository;
import com.back.repository.BoardAuthRepository;
import com.back.repository.BoardCustomRepository;
import com.back.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCustomRepository boardCustomRepository;
    private final BoardAuthRepository boardAuthRepository;
    private final BoardAuthCustomRepository boardAuthCustomRepository;
    private final AuthRepository authRepository;

    /**
     * 게시판를 전체조회 합니다. 페이징처리
     */
    public Page<BoardDto> getBoards(BoardParam boardParam) {
        boardParam.setPaging(boardParam.page);
        return boardCustomRepository.findAllWithPaging(boardParam, PageRequest.of(boardParam.page, boardParam.size));
    }

    public Board createBoard(BoardParam boardParam) {

        Board board = new Board();
        board.setCreateParam(boardParam);

        Board saveResult = boardRepository.saveAndFlush(board);

        for (Long authId : boardParam.authIdArr) {
            BoardAuth boardAuth = new BoardAuth();
            boardAuth.auth = authRepository.findById(authId).orElseThrow(RuntimeException::new);
            boardAuth.board = saveResult;
            boardAuth.useYn = "Y";
            boardAuth.createdId = boardParam.createdId;

            boardAuthRepository.save(boardAuth);
        }

        return saveResult;
    }

    public BoardDto getBoard(Long id) {
        return boardCustomRepository.findBoardBy(id);
    }

    public Board updateBoard(Long id, BoardParam boardParam) {

        Board board = boardRepository.findById(id).orElseThrow(RuntimeException::new);
        board.setUpdateParam(boardParam);

        Board saveResult = boardRepository.saveAndFlush(board);

        boardAuthRepository.deleteAllByBoardId(saveResult.id, boardParam.updatedId);

        Long[] authIdArr = boardParam.authIdArr;

        for (Long authId : authIdArr) {

            BoardAuth boardAuth = boardAuthCustomRepository.findByBoardIdAndAuthId(board.id, authId);
            if(boardAuth == null){
                boardAuth = new BoardAuth();
                boardAuth.auth = authRepository.findById(authId).orElseThrow(RuntimeException::new);
                boardAuth.board = board;
                boardAuth.createdId = boardParam.updatedId;
            }else{
                boardAuth.updatedId = boardParam.updatedId;
            }

            boardAuth.useYn = "Y";

            boardAuthRepository.save(boardAuth);
        }

        return saveResult;
    }

    public List<BoardAuthDto> getBoardAuths(Long id) {
        return boardAuthCustomRepository.findAllByBoardId(id);
    }

}
