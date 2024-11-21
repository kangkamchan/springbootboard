package net.fullstack7.springboot.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Data
public class ConditionResponseDTO<E> {
    private int pageNo;
    private int pageSize;
    private int pageBlockSize;
    private int totalCount;
    private int totalPage;
    private int blockStart;
    private int blockEnd;
    private boolean hasNext;
    private boolean hasPrev;
    private List<E> dtoList;
    private String linkURL;
    private String queryString;
    ConditionResponseDTO() {

    }
    @Builder(builderMethodName="withAll")
    ConditionResponseDTO(ConditionRequestDTO conditionRequestDTO,int totalCount,List<E> list) {
        this.pageNo = conditionRequestDTO.getPageNo();
        this.pageSize = conditionRequestDTO.getPageSize();
        this.pageBlockSize = conditionRequestDTO.getPageBlockSize();
        this.totalCount = totalCount;
        this.totalPage = (totalCount-1)/this.pageSize + 1;
        this.blockStart = ((this.pageNo-1)/this.pageSize)*this.pageBlockSize + 1;
        this.blockEnd = Math.min(this.blockStart + this.pageBlockSize -1,this.totalPage);
        this.hasPrev = blockStart > 1;
        this.hasNext = blockEnd < totalPage;
        this.dtoList = list;
        StringBuilder sb = new StringBuilder();
        sb.append("pageNo=").append(pageNo);
        sb.append("&pageSize=").append(pageSize);
        if(conditionRequestDTO.getSearchField()!=null && conditionRequestDTO.getSearchValue()!=null){
            sb.append("&searchField=").append(conditionRequestDTO.getSearchField());
            sb.append("&searchValue=").append(conditionRequestDTO.getSearchValue());
        }
        if(conditionRequestDTO.getSortField()!=null && conditionRequestDTO.getSortDirection()!=null){
            log.info("sortDirection : {}",conditionRequestDTO.getSortDirection());
            sb.append("&sortField=").append(conditionRequestDTO.getSortField());
            sb.append("&sortDirection=").append(conditionRequestDTO.getSortDirection());
        }
        this.queryString = sb.toString();
    }
}
