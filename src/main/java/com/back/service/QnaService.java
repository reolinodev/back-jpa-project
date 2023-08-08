package com.back.service;

import com.back.domain.Qna;
import com.back.domain.dto.QnaDto;
import com.back.domain.params.QnaParam;
import com.back.repository.BoardRepository;
import com.back.repository.QnaCustomRepository;
import com.back.repository.QnaRepository;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;
    private final QnaCustomRepository qnaCustomRepository;
    private final BoardRepository boardRepository;

    /**
     * QNA를 등록합니다.
     */
    public Qna createQna(QnaParam qnaParam) throws NoSuchAlgorithmException {
        Qna qna = new Qna();
        qna.setCreateParam(qnaParam);
        qna.board = boardRepository.findById(qnaParam.boardId).orElseThrow(RuntimeException::new);
        return qnaRepository.save(qna);
    }

    /**
     * Qna를 전체조회 합니다. 페이징처리
     */
    public Page<QnaDto> getQnas(QnaParam qnaParam) {
        qnaParam.setPaging(qnaParam.page);
        return qnaCustomRepository.findAllWithPaging(qnaParam, PageRequest.of(qnaParam.page, qnaParam.size));
    }

    /**
     * Qna를 상세조회 합니다.
     */
    public QnaDto getQna(Long id) {
        Qna qna = qnaRepository.findById(id).orElseThrow(RuntimeException::new);
        qna.viewCnt = qna.viewCnt + 1;
        qnaRepository.save(qna);
        return qnaCustomRepository.findQnaBy(id);
    }

    /**
     *  Qna을 수정합니다. 관리자의 시점과 사용자의 시점을 나눠서 생각해야 합니다.
     */
    public Qna updateQna(Long id, QnaParam qnaParam) throws NoSuchAlgorithmException {
        Qna qna = qnaRepository.findById(id).orElseThrow(RuntimeException::new);
        qna.setUpdateParam(qnaParam);
        return qnaRepository.save(qna);
    }

    /**
     * 작성자의 아이디와 패스워드로 체크합니다.
     */
    public Boolean checkQnaPw(Long id, QnaParam qnaParam) throws Exception {
        Qna qna = qnaRepository.findById(id).orElseThrow(RuntimeException::new);
        return Objects.equals(qna.createdId, qnaParam.createdId) && qna.qnaPw.equals(
            CryptUtils.encryptSha256(qnaParam.qnaPw));
    }
}
