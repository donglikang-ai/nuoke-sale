package com.nuoke.sale.model;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@TableName("sys_order")
public class Order {

    private String id;
    private String terminalType;
    private String faultType;
    private String name;
    private String mobile;
    private String address;
    private String terminalName;
    private String faultName;
    private String faultInfo;
    private int status;
    private String repairmanId;
    private String repairmanName;
    private String repairmanTel;
    private String createDate;//创建时间
    private String doDate;//处理时间
    private String closeDate;//处理完成时间

    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDoDate() {
        return doDate;
    }

    public void setDoDate(String doDate) {
        this.doDate = doDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getRepairmanTel() {
        return repairmanTel;
    }

    public void setRepairmanTel(String repairmanTel) {
        this.repairmanTel = repairmanTel;
    }

    public String getRepairmanId() {
        return repairmanId;
    }

    public void setRepairmanId(String repairmanId) {
        this.repairmanId = repairmanId;
    }

    public String getRepairmanName() {
        return repairmanName;
    }

    public void setRepairmanName(String repairmanName) {
        this.repairmanName = repairmanName;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
