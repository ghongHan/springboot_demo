package com.hskj.common.util;

import com.google.common.base.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页
 * Created by Administrator on 2017/12/15.
 */
public class PageUtil {

    /**
     * 根据条件排序
     * @param pageNo
     * @param pageSize
     * @param sortType
     * @param sortParams
     * @return
     */
    public static Pageable buildPageRequest(int pageNo, int pageSize, String sortType, String[] sortParams){
        Sort sort = null;
        if(!StringUtil.stringArrIsEmpty(sortParams)){
            if(Strings.isNullOrEmpty(sortType)){
                sort = new Sort(Sort.Direction.DESC, sortParams);
            }else{
                sort = new Sort(Sort.Direction.fromString(sortType), sortParams);
            }
        }
        return new PageRequest(pageNo - 1, pageSize, sort);
    }
}
