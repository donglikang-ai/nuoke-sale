package com.nuoke.sale.websocket.service.impl;

import com.nuoke.sale.websocket.handle.MonitorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MonitorServiceImpl
 * @Author dlkang
 * @Description kds注册gateway类
 * @DATE 2019/3/6 10:04
 **/
@Component
public class MonitorServiceImpl implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    private Session session;


    @Override
    public void run(ApplicationArguments args) {
        if (connectToMerchant()) {
            new Thread(() -> {
                while (true) {
                    keepAlive();
                    try {
                        Thread.sleep(1000 * 5 * 1);//30"一次心跳
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        } else {
            logger.info("与总部握手失败");
        }
    }


    boolean connectToMerchant() {

        while (true) {

            try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();

                connectMonitorServer(container);
                return true;
            } catch (Exception e) {
                logger.info("监控服务注册失败，两分钟后重新尝试[{}]", e.getMessage());
            }

            try {
                TimeUnit.MILLISECONDS.sleep(60 * 1000 * 2);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

    }


    public void keepAlive() {

        this.sendTextMsg("{message: \"ping\", socketId: \"135\", userId: \"135\", userType: \"1\"}", true);

    }


    public synchronized boolean sendTextMsg(String textMsg, boolean logMsg) {
        if (logMsg) {
            logger.info("发送消息：" + textMsg);
        }
        try {
            session.getBasicRemote().sendText(textMsg);
        } catch (Exception e) {
            logger.info("与总部握手心跳停止..." + textMsg);
            this.reconnectToServer();
            return false;
        }
        return true;
    }


    public synchronized void reconnectToServer() {
        logger.info("重连websocket-与总部重新握手");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {

            connectMonitorServer(container);

        } catch (Exception e) {
            logger.info("重连websocket-与总部重新握手--失败");
        }
    }

    private void connectMonitorServer(WebSocketContainer container) throws DeploymentException, IOException, URISyntaxException {

        String url = "wss://www.jw787.com/socket/135/1";
        logger.info("监控服务注册url---->[{}]", url);

        session = container.connectToServer(MonitorHandler.class, new URI(url));
    }
}
