package com.back.service;


import com.back.domain.Faq;
import com.back.domain.dto.FaqDto;
import com.back.domain.params.FaqParam;
import com.back.repository.BoardRepository;
import com.back.repository.FaqCustomRepository;
import com.back.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;
    private final FaqCustomRepository faqCustomRepository;
    private final BoardRepository boardRepository;

    /**
     * FAQ를 등록합니다.
     */
    public Faq createFaq(FaqParam faqParam) {
        Faq faq = new Faq();
        faq.setCreateParam(faqParam);
        faq.board = boardRepository.findById(faqParam.boardId).orElseThrow(RuntimeException::new);
        return faqRepository.save(faq);
    }

    /**
     * FAQ를 전체조회 합니다. 페이징처리
     */
    public Page<FaqDto> getFaqs(FaqParam faqParam) {
        faqParam.setPaging(faqParam.page);
        return faqCustomRepository.findAllWithPaging(faqParam, PageRequest.of(faqParam.page, faqParam.size));
    }

    /**
     * FAQ를 상세조회 합니다.
     */
    public FaqDto getFaq(Long id) {
        //상세 조회시 조회수를 1 올린다.
        Faq faq = faqRepository.findById(id).orElseThrow(RuntimeException::new);
        faq.viewCnt = faq.viewCnt + 1;
        faqRepository.save(faq);
        return faqCustomRepository.findFaqBy(id);
    }

    public Faq updateFaq(Long id, FaqParam faqParam) {
        Faq faq = faqRepository.findById(id).orElseThrow(RuntimeException::new);
        faq.setUpdateParam(faqParam);
        return faqRepository.save(faq);
    }


}
