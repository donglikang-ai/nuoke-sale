package com.nuoke.sale.util.weix;

import com.nuoke.sale.model.Order;
import com.nuoke.sale.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TemplateUtils {

    public static Logger log = LoggerFactory.getLogger(TemplateUtils.class);

    public static String templateSend(Order order) {

        String token = TokenGet.get();

        Map<String, String> param = new HashMap<>();
        param.put("touser", order.getOpenid());
        param.put("template_id", WeixConst.TemplateID);
        param.put("page", "pages/faultinfo/info?id=" + order.getId());
        param.put("data", getData(order));
        String result = HttpUtils.doPostJson(WeixConst.TemplateUrl + token, getJson(param));

        log.info("templateSendReturn------->{}", result);

        return "";
    }


    static String getJson(Map<String, String> param) {

        String params = "{";
        for (Map.Entry<String, String> entity : param.entrySet()) {
            if (entity.getKey().equals("data")) {
                params += "\"" + entity.getKey() + "\":" + entity.getValue() + ",";
            } else {
                params += "\"" + entity.getKey() + "\":" + "\"" + entity.getValue() + "\",";
            }
        }
        params = params.substring(0, params.length() - 1);
        params += "}";

        log.info("参数参数参数----->{}", params);
        return params;
    }

    public static String getData(Order order) {

        String orderStatus = "订单提交成功";
        switch (order.getStatus()) {
            case 1:
                orderStatus = "订单已受理";
                break;
            case 2:
                orderStatus = "订单已完成";
                break;
            default:
                break;

        }

        return new StringBuffer().append("{")
                .append("\"").append("date2").append("\":")
                .append("{\"value\":").append("\"").append(order.getCreateDate()).append("\"").append("},")
                .append("\"").append("name3").append("\":")
                .append("{\"value\":").append("\"").append(order.getName()).append("\"").append("},")
                .append("\"").append("thing7").append("\":")
                .append("{\"value\":").append("\"").append(order.getFaultName()).append("\"").append("},")
                .append("\"").append("thing8").append("\":")
                .append("{\"value\":").append("\"").append(orderStatus).append("\"").append("},")
                .append("\"").append("thing5").append("\":")
                .append("{\"value\":").append("\"").append("订单状态已更新，感谢您的使用").append("\"").append("}")
                .append("}").toString();

    }


}
