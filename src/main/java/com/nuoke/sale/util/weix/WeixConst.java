package com.nuoke.sale.util.weix;

public class WeixConst {

    public static String AppID = "wxee3951989b8aed65";
    public static String AppSecret = "d147d43e350210f7e49cd00e8c2be4ae";//这两个都可以从微信公众平台中查找
    public static String TokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    public static String UserInfoUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    public static String TemplateID = "AnCv2dp1Jy3l2RcgE-0NTiw_IINFTVZuhwL0YDUumZ0";
    public static String TemplateUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
}
