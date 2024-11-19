package com.ttcn.vnuaexam.utils;

import com.ttcn.vnuaexam.dto.search.SearchDto;
import jakarta.persistence.Query;
import org.springframework.data.domain.*;

import java.util.List;

public class PageUtils {
    public PageUtils() {
    }

    public static <T> List<T> getContent(SearchDto dto, Query q) {
        int pageIndex = dto.getPageIndex() - 1;
        int pageSize = dto.getPageSize();
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    public static <T> Page<T> getPage(List<T> entities, SearchDto dto, Query qCount) {
        long count = (Long)qCount.getSingleResult();
        Pageable pageable = getPageable(dto.getPageIndex(), dto.getPageSize());
        return new PageImpl(entities, pageable, count);
    }

    public static <T> Page<T> getPage(SearchDto dto, Query q, Query qCount) {
        List<T> entities = getContent(dto, q);
        long count = (Long)qCount.getSingleResult();
        Pageable pageable = getPageable(dto.getPageIndex(), dto.getPageSize());
        return new PageImpl(entities, pageable, count);
    }

    public static Pageable getPageable(Integer pageIndex, Integer pageSize, Sort sort) {
        pageIndex = getPageIndex(pageIndex);
        pageSize = getPageSize(pageSize);
        return PageRequest.of(pageIndex, pageSize, sort);
    }

    public static Pageable getPageable(Integer pageIndex, Integer pageSize) {
        pageIndex = getPageIndex(pageIndex);
        pageSize = getPageSize(pageSize);
        return PageRequest.of(pageIndex, pageSize);
    }

    public static int getPageIndex(Integer pageIndex) {
        return pageIndex != null && pageIndex > 0 ? pageIndex - 1 : 1;
    }

    public static int getPageSize(Integer pageSize) {
        return pageSize != null && pageSize > 0 ? pageSize : 10;
    }
}
