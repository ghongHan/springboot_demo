package com.hskj.common.util;

import lombok.Data;

/**
 * Created by hongHan_gao
 * Date: 2018/6/20
 */

@Data
public class Page {

    private Integer pageNo;

    private Integer pageSize;

    private Long count;

    public Page(Integer pageNo, Integer pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
