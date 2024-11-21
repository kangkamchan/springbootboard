package net.fullstack7.springboot.repository;

import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.domain.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RepositoryTests {
    @Autowired
    private BoardRepository repository;

    @Test
    public void insertTest(){
        log.info("insert test");
        for (int i = 0; i < 198; i++) {
            Board savedBoard = repository.save(Board.builder()
                    .memberId("user1")
                    .title("testTitle " + i)
                    .content("testContent " + i)
                    .displayDate(
                            "2024-11-21"
                    )
                    .build());
            log.info("savedBoard : {}", savedBoard);
        }
    }

//    @Test
//    public void findTest(){
//        log.info("find test");
//        int idx = 1 ;
//        Optional<Board> result = repository.findById(idx);
//        Board board = result.orElse(null);
//        Assertions.assertNotNull(board);
//        log.info("board : {}",board.toString());
//    }

//    @Test
//    public void modifyTest(){
//        log.info("modify test");
//        Board board = repository.save(Board.builder()
//                        .idx(11)
//                        .content("modified content")
//                        .title("modified title")
//                        .memberId("user2")
//                        .displayDate("2024-12-01")
//                .build());
//        log.info("board : {}", board.toString());
//    }

//    @Test
//    public void findAllTest(){
//        log.info("findAll test");
//        Iterable<Board> boards = repository.findAll();
//        boards.forEach(log::info);
//    }
//
//    @Test
//    public void deleteTest(){
//        log.info("delete test");
//        repository.deleteById(1);
//        Assertions.assertNull(repository.findById(1).orElse(null));
//    }

//    @Test
//    public void pageTest(){
//        Pageable pageable = PageRequest.of(1,5, Sort.by("idx").descending());
//        Page<Board> page = repository.findAll(pageable);
//
//        page.forEach(log::info);
//        log.info(page.toString());
//        log.info("totalElements : {}",page.getTotalElements());
//        log.info("totalPages : {}",page.getTotalPages());
//        log.info("number : {}",page.getNumber());
//        log.info("size : {}",page.getSize());
//        log.info("content : {}",page.getContent());
//        log.info("numberOfElements : {}",page.getNumberOfElements());
//        log.info("sort : {}",page.getSort());
//        log.info("pageable : {}", page.getPageable());
//
//
//    }

//    @Test
//    public void testSearch1(){
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("idx").descending());
//        repository.search1(pageRequest);
//    }
//    @Test
//    public void testSearch2(){
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("idx").descending());
//        Page<Board> boardPage = repository.search2(pageRequest,new String[]{"title","content","memberId"},"2");
//        boardPage.forEach(log::info);
//        log.info(boardPage.toString());
//        log.info("totalElements : {}",boardPage.getTotalElements());
//        log.info("totalPages : {}",boardPage.getTotalPages());
//        log.info("number : {}",boardPage.getNumber());
//        log.info("size : {}",boardPage.getSize());
//        log.info("content : {}",boardPage.getContent());
//        log.info("numberOfElements : {}",boardPage.getNumberOfElements());
//        log.info("sort : {}",boardPage.getSort());
//        log.info("pageable : {}", boardPage.getPageable());
//        log.info("prev : {}", boardPage.hasPrevious());
//        log.info("next : {}", boardPage.hasNext());
//        log.info("nextOrLastPageable : {}",boardPage.nextOrLastPageable());
//        log.info("nextPageable : {}",boardPage.nextPageable());
//        log.info("isLast : {}", boardPage.isLast());
//    }
}
