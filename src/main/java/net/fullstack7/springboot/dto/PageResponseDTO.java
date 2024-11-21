package net.fullstack7.springboot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Data
public class PageResponseDTO<E> {
    private int pageNo;
    private int pageSize;
    private int offset;
    private int pageBlockSize;
    private int blockStart;
    private int blockEnd;
    private int totalCount;
    private int totalPage;
    private boolean hasPrev;
    private boolean hasNext;
    private List<E> dtoList;
    private String searchField;
    private String searchValue;
    private String searchDateBegin;
    private String searchDateEnd;
    private String[] sortField;
    private String[] sortDirection;

    public PageResponseDTO() {}
    public PageResponseDTO(PageRequestDTO dto,List<E> list) {
        this.pageSize = dto.getPageSize();
        this.offset = dto.getOffset();
        this.pageBlockSize = dto.getPageBlockSize();
        this.totalCount = dto.getTotalCount();
        this.searchField = dto.getSearchField();
        this.searchValue = dto.getSearchValue();
        this.searchDateBegin = dto.getSearchDateBegin();
        this.searchDateEnd = dto.getSearchDateEnd();
        this.sortField = dto.getSortField();
        this.sortDirection = dto.getSortDirection();
        this.dtoList = list;
        this.totalPage = (totalCount-1)/pageSize;
        this.pageNo = Math.min(dto.getPageNo(),totalPage);
        this.blockStart = ((pageNo-1)/pageBlockSize)*pageBlockSize + 1;
        this.blockEnd = Math.min(blockStart + pageBlockSize - 1,totalPage);
        this.hasPrev = this.blockStart > 1;
        this.hasNext = this.blockEnd < this.totalPage;
    }

}
