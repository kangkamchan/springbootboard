package net.fullstack7.springboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.domain.Board;
import net.fullstack7.springboot.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard boardQ = QBoard.board;
        JPQLQuery<Board> query = from(boardQ);
        //query.where(boardQ.title.like("3"));//WHERE title like '%3%'
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(boardQ.title.like("1"));
        booleanBuilder.or(boardQ.content.like("2"));
        query.where(booleanBuilder);
        //페이징
        Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);
        List<Board> boards = query.fetch();
        int total = (int) query.fetchCount();
        return null;
    }

    @Override
    public Page<Board> search2(Pageable pageable, String searchCategory, String searchWord) {
        QBoard boardQ = QBoard.board;
        JPQLQuery<Board> query = from(boardQ);
        //검색
        if ((searchCategory != null && !searchCategory.isEmpty()) && (searchWord != null && !searchWord.isEmpty())) {
            switch (searchCategory) {
                case "title":
                    query.where(boardQ.title.contains(searchWord));
                    break;
                case "content":
                    query.where(boardQ.content.contains(searchWord));
                    break;
                case "memberId":
                    query.where(boardQ.memberId.contains(searchWord));
                    break;
                default:
                    break;
            }
            query.where(boardQ.idx.gt(0));
        }
        //페이징
        Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);
        List<Board> boards = query.fetch();
        int total = (int) query.fetchCount();
        return new PageImpl<Board>(boards, pageable, total);
    }

    @Override
    public Page<Board> search3(Pageable pageable, String[] searchCategory, String searchWord) {
        QBoard boardQ = QBoard.board;
        JPQLQuery<Board> query = from(boardQ);
        //검색
        BooleanBuilder booleanBuilder = setWhereQuery(boardQ,searchCategory,searchWord);
        if(booleanBuilder!=null) {
            query.where(booleanBuilder);
        }
        //페이징
        Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);
        List<Board> boards = query.fetch();
        int total = (int) query.fetchCount();
        return new PageImpl<Board>(boards, pageable, total);
    }

    @Override
    public Page<Board> searchAndSort(Pageable pageable, String[] searchCategory, String searchWord, String sortField, String sortDir) {
        QBoard boardQ = QBoard.board;
        JPQLQuery<Board> query = from(boardQ);
        //검색
        BooleanBuilder booleanBuilder = setWhereQuery(boardQ,searchCategory,searchWord);
        if(booleanBuilder!=null) {
            query.where(booleanBuilder);
        }
        //정렬
        if(sortField!=null && sortDir!=null) {
            switch (sortField) {
                case "t":
                    query.orderBy(sortDir.equals("d")?boardQ.title.desc():boardQ.title.asc());
                    break;
                case "c":
                    query.orderBy(sortDir.equals("d")?boardQ.content.desc():boardQ.content.asc());
                    break;
                case "m":
                    query.orderBy(sortDir.equals("d")?boardQ.memberId.desc():boardQ.memberId.asc());
                    break;
                default:
                    break;
            }
        }
        //페이징
        Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);
        List<Board> boards = query.fetch();
        int total = (int) query.fetchCount();
        return new PageImpl<Board>(boards, pageable, total);
    }
    private BooleanBuilder setWhereQuery(QBoard boardQ, String[] searchCategory, String searchWord){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(searchCategory!=null && searchCategory.length>0) {
            for(String category : searchCategory) {
                if ((category != null && !category.isEmpty()) && (searchWord != null && !searchWord.isEmpty())) {
                    switch (category) {
                        case "t":
                            booleanBuilder.or(boardQ.title.contains(searchWord));
                            break;
                        case "c":
                            booleanBuilder.or(boardQ.content.contains(searchWord));
                            break;
                        case "m":
                            booleanBuilder.or(boardQ.memberId.contains(searchWord));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return booleanBuilder;
    }
}




