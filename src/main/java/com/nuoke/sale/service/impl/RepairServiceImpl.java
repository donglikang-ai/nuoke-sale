package com.nuoke.sale.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuoke.sale.dao.RepairManDao;
import com.nuoke.sale.model.RepairMan;
import com.nuoke.sale.service.IRepairService;
import org.springframework.stereotype.Service;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Service("repairManService")
public class RepairServiceImpl extends ServiceImpl<RepairManDao, RepairMan> implements IRepairService {
}


