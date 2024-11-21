package net.fullstack7.springboot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.domain.Board;
import net.fullstack7.springboot.dto.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;
import net.fullstack7.springboot.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardServiceIf{
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public int regist(BoardDTO boardDTO) {
        return boardRepository.save(modelMapper.map(boardDTO, Board.class)).getIdx();
    }

    @Override
    public BoardDTO view(int idx) {
        return boardRepository.findById(idx).map(board -> modelMapper.map(board, BoardDTO.class)).orElse(null);
    }

    @Override
    public Page<BoardDTO> search(Pageable pageable, String searchCategory, String searchWord) {
        return boardRepository.search2(pageable,searchCategory,searchWord).map(board -> modelMapper.map(board, BoardDTO.class));
    }

    @Override
    public int modify(BoardDTO boardDTO) {

        Board board = boardRepository.findById(boardDTO.getIdx()).orElse(null);
        if(board == null){
            return -1;
        }
        board.modify(boardDTO.getMemberId(),boardDTO.getTitle(),boardDTO.getContent(),boardDTO.getDisplayDate());
        return boardRepository.save(board).getIdx();
    }

    @Override
    public void delete(int idx) {
        boardRepository.deleteById(idx);
    }

    @Override
    public ConditionResponseDTO<BoardDTO> conditionList(ConditionRequestDTO conditionRequestDTO) {
        String[] searchFields = conditionRequestDTO.getSearchFields();
        String searchValue = conditionRequestDTO.getSearchValue();
        Pageable pageable = conditionRequestDTO.getPageable("idx");
        Page<Board> result = boardRepository.search3(pageable,searchFields,searchValue);
        List<BoardDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board, BoardDTO.class)).toList();
        return ConditionResponseDTO.<BoardDTO>withAll()
                .conditionRequestDTO(conditionRequestDTO)
                .totalCount((int)result.getTotalElements())
                .list(dtoList)
                .build();
    }

    @Override
    public ConditionResponseDTO<BoardDTO> searchAndSort(ConditionRequestDTO conditionRequestDTO) {
        String[] searchFields = conditionRequestDTO.getSearchFields();
        String searchValue = conditionRequestDTO.getSearchValue();
        String sortFild = conditionRequestDTO.getSortField();
        String sortDirection = conditionRequestDTO.getSortDirection();
        Pageable pageable = conditionRequestDTO.getPageable();
        Page<Board> result = boardRepository.searchAndSort(pageable,searchFields,searchValue,sortFild,sortDirection);
        List<BoardDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board, BoardDTO.class)).toList();
        return ConditionResponseDTO.<BoardDTO>withAll()
                .conditionRequestDTO(conditionRequestDTO)
                .totalCount((int)result.getTotalElements())
                .list(dtoList)
                .build();
    }


}
