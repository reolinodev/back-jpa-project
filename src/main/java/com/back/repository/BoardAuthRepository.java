package com.back.repository;

import com.back.domain.BoardAuth;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardAuthRepository extends JpaRepository <BoardAuth, Long> {
    String updateUseYnNSql =
        "      update ws.tb_board_auth \n"
        + "        set use_yn = 'N'\n"
        + "        , updated_id = :updatedId \n"
        + "        where board_id = :boardId";

    @Transactional
    @Modifying
    @Query(value = updateUseYnNSql, nativeQuery = true)
    void deleteAllByBoardId(@Param("boardId") Long boardId, @Param("updatedId") Long updatedId);


}

