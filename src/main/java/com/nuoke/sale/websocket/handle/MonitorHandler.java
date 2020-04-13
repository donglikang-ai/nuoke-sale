package com.nuoke.sale.websocket.handle;

import com.nuoke.sale.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MonitorHandler
 * @Author dlkang
 * @Description TODO
 * @DATE 2019/3/6 10:18
 **/
@Service
@ClientEndpoint
public class MonitorHandler {

    private static final Logger _log = LoggerFactory.getLogger(MonitorHandler.class);


    @OnOpen
    public void onOpen(Session session) {

        _log.info("服务监控注册成功");


    }


    @OnMessage(maxMessageSize = 3000000)
    @Async("KdsThreadPoolTaskExecutor")
    public void onMessage(String message, Session session) {


        _log.info("来自服务器消息[{}]", message);


        if (!StringUtils.isEmpty(message) && !message.equals("pong") && message.contains("询价单")) {

            for (int i = 0; i < 10; i++) {
                try {

                    TimeUnit.MILLISECONDS.sleep(3000);

                } catch (Exception e) {

                }
                HttpUtils.doPostJson("https://oapi.dingtalk.com/robot/send?access_token=eabd465f22ea17e91db9fcaa566bfc0f081ffa86806bde4d6866b8f31311fcd7", "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + message + "\"}}");

            }
        }


    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        _log.info(String.format("与总部握手连接中断--Session %s close because of %s", session.getId(), closeReason));

    }

    public static void main(String[] args) {
        HttpUtils.doPostJson("https://oapi.dingtalk.com/robot/send?access_token=eabd465f22ea17e91db9fcaa566bfc0f081ffa86806bde4d6866b8f31311fcd7", "{ \"msgtype\": \"text\", \"text\": {\"content\": \"询价单11111test\"}}");

    }
}
