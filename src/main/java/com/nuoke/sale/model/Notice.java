package com.nuoke.sale.model;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@TableName("sys_notice")
public class Notice {

    private String id;
    private String notice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
