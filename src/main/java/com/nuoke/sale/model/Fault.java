package com.nuoke.sale.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@TableName("sys_fault")
public class Fault {

    private String id;
    @TableField("fault_name")
    private String faultName;
    @TableField("fault_info")
    private String faultInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }
}
