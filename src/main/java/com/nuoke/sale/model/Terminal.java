package com.nuoke.sale.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@TableName("sys_terminal")
public class Terminal {

    private String id;
    @TableField("terminal_name")
    private String terminalName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }
}
