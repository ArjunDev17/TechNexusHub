package com.codeneeti.technexushub.helper;


import com.codeneeti.technexushub.dtos.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public static<U,V> PageableResponse<V> getPageableResponse(Page<U> pages, Class<V>type){
        List<U> content = pages.getContent();

        List<V> userDTOList = content.stream().map(object -> new ModelMapper().map(object,type)).collect(Collectors.toList());

        PageableResponse<V> userDTOPageableResponse =new PageableResponse<>();
        userDTOPageableResponse.setContent(userDTOList);
        userDTOPageableResponse.setPageNumber(pages.getNumber());//if we want to start from page number 0 then +1
        userDTOPageableResponse.setPageSize(pages.getSize());
        userDTOPageableResponse.setTotalElement(pages.getTotalElements());
        userDTOPageableResponse.setTotalPages(pages.getTotalPages());
        userDTOPageableResponse.setLastPage(pages.isLast());
        return  userDTOPageableResponse;
    }
}
