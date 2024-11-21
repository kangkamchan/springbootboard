package net.fullstack7.springboot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionRequestDTO {
    @Builder.Default
    @Positive
    @Min(1)
    private int pageNo=1;
    @Builder.Default
    @Min(5)
    @Max(20)
    @Positive
    private int pageSize=10;
    @Builder.Default
    @Positive
    @Min(5)
    @Max(30)
    private int pageBlockSize=10;

    private String searchField;
    private String searchValue;
    private String sortField;
    private String sortDirection;
    private String returnLink;

    public String[] getSearchFields(){
        return (searchField!=null&&!searchField.isEmpty())?searchField.split(""):null;
    }

    public Pageable getPageable(String...params){
        return PageRequest.of(this.pageNo-1,this.pageSize, Sort.by(params).descending());
    }
    public Pageable getPageable(){
        return PageRequest.of(this.pageNo-1,this.pageSize);
    }

    public String getReturnLink(){
        if(returnLink==null||returnLink.isEmpty()){
            StringBuilder builder = new StringBuilder();
            builder.append("pageNo=").append(pageNo);
            builder.append("&pageSize=").append(pageSize);
            builder.append(searchField!=null&&!searchField.isEmpty()&&searchValue!=null&&!searchValue.isEmpty()?
                    "&searchField="+searchField+"&searchValue="+searchValue:"");
            builder.append(sortField!=null&&!sortField.isEmpty()&&sortDirection!=null&&!sortDirection.isEmpty()?
                    "&sortField="+sortField+"&sortDirection="+sortDirection:"");
        }
        try {
            return URLEncoder.encode(builder().toString(), "UTF-8");
        }catch(UnsupportedEncodingException e) {
            log.error(e);
            return "pageNo=1&pageSize=10";
        }
    }
}
