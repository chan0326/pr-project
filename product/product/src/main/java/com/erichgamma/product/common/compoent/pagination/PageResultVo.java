package com.erichgamma.product.common.compoent.pagination;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResultVo<Dto, EN> {
    private List<Dto> dtoList;
    private int totalPage,page,size,start,end;
    private boolean prev,next;
    private List<Integer> pageList;

    public PageResultVo(Page<EN> result, Function<EN,Dto> fn) {
        dtoList =result.stream().map(fn).collect(Collectors.toList());
        totalPage =result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber()+1;
        this.size = pageable.getPageSize();
        int tempEnd = (int) Math.ceil(page/10.0)*10;
        start = tempEnd -0;
        prev =start>1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

    }
}
