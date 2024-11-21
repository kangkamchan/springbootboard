package net.fullstack7.springboot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {
    @Builder.Default
    @Positive
    @Min(value=1)
    private int pageNo = 1;
    @Builder.Default
    @Positive
    @Min(value=1)
    private int pageSize=10;
    @Builder.Default
    @PositiveOrZero
    @Min(value=0)
    private int offset=0;
    @Builder.Default
    @Positive
    @Min(value=1)
    @Max(value=30)
    private int pageBlockSize=5;
    private int totalCount;
    private String searchField;
    private String searchValue;
    private String searchDateBegin;
    private String searchDateEnd;
    private String[] sortField;
    private String[] sortDirection;
    public int getOffset(){
        return (pageNo-1)*pageSize;
    }
}
