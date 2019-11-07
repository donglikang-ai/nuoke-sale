package com.nuoke.sale.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuoke.sale.dao.RepairManDao;
import com.nuoke.sale.dao.TerminalDao;
import com.nuoke.sale.model.RepairMan;
import com.nuoke.sale.model.Terminal;
import com.nuoke.sale.service.IRepairService;
import com.nuoke.sale.service.ITerminalService;
import org.springframework.stereotype.Service;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Service("terminalService")
public class TerminalServiceImpl extends ServiceImpl<TerminalDao, Terminal> implements ITerminalService {
}


