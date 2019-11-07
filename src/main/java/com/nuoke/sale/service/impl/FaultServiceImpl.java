package com.nuoke.sale.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuoke.sale.dao.FaultDao;
import com.nuoke.sale.dao.RepairManDao;
import com.nuoke.sale.model.Fault;
import com.nuoke.sale.model.RepairMan;
import com.nuoke.sale.service.IFaultService;
import com.nuoke.sale.service.IRepairService;
import org.springframework.stereotype.Service;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Service("faultService")
public class FaultServiceImpl extends ServiceImpl<FaultDao, Fault> implements IFaultService {
}


