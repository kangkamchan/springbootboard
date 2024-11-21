package net.fullstack7.springboot.service;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.dto.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BoardServiceIf boardService;
//    @Test
//    public void registTest() {
//        log.info("registTest");
//        log.info("result : {}",boardService.regist(BoardDTO.builder()
//                        .title("regist test")
//                        .content("regist test content")
//                        .memberId("user2")
//                .build()));
//    }
//    @Test
//    public void viewTest(){
//        log.info("viewTest");
//        log.info("result : {}",boardService.view(13));
//    }
//
//    @Test
//    public void searchTest() {
//        log.info("searchTest");
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("idx").descending());
//        log.info("result : {}", boardService.search(pageRequest,"title","2"));
//    }

    @Test
    public void listTest(){
       log.info("listTest");
       ConditionRequestDTO reqDto = ConditionRequestDTO.builder()
               .pageNo(1)
               .pageSize(3)
               .pageBlockSize(5)
               .build();
       ConditionResponseDTO<BoardDTO> resDTO = boardService.conditionList(reqDto);
       log.info("result : {}",resDTO.getDtoList());
        log.info("result : {}",resDTO);
        log.info("result : {}",reqDto);
    }
}
