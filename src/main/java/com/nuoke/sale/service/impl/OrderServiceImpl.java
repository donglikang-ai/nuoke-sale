package com.nuoke.sale.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuoke.sale.dao.OrderDao;
import com.nuoke.sale.dao.TerminalDao;
import com.nuoke.sale.model.Order;
import com.nuoke.sale.model.Terminal;
import com.nuoke.sale.service.IOrderService;
import com.nuoke.sale.service.ITerminalService;
import org.springframework.stereotype.Service;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements IOrderService {
}


