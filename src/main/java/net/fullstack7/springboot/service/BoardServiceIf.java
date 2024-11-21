package net.fullstack7.springboot.service;

import net.fullstack7.springboot.dto.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardServiceIf {
    public int regist(BoardDTO boardDTO);
    public BoardDTO view(int idx);
    public Page<BoardDTO> search(Pageable pageable, String searchCategory, String searchWord);
    public int modify(BoardDTO boardDTO);
    public void delete(int idx);
    public ConditionResponseDTO<BoardDTO> conditionList(ConditionRequestDTO conditionRequestDTO);
    public ConditionResponseDTO<BoardDTO> searchAndSort(ConditionRequestDTO conditionRequestDTO);
}
