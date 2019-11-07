package com.nuoke.sale.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuoke.sale.dao.FaultDao;
import com.nuoke.sale.dao.NoticeDao;
import com.nuoke.sale.model.Fault;
import com.nuoke.sale.model.Notice;
import com.nuoke.sale.service.IFaultService;
import com.nuoke.sale.service.INoticeService;
import org.springframework.stereotype.Service;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, Notice> implements INoticeService {
}


