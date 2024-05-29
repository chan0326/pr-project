package site.toeicdoit.api.common.component.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component
@Builder
@Data
@AllArgsConstructor
public class PageRequestVo {
    private  int page;
    private  int size;
    private  String type;

    public PageRequestVo() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable (Sort sort){
        return PageRequest.of(page -1,size,sort);
    }
}
