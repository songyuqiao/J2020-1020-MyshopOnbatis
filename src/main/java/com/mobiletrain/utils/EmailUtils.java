package com.mobiletrain.utils;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtils {


    public static void sendRegisterSuccess(String to, String activeCode) {
        try {
            HtmlEmail email = new HtmlEmail();
            //设计编码记
            email.setCharset("utf-8");
            //发件人信息
            email.setFrom("3622807903@qq.com", "有网站没，来个");
            //设计发件人的授权信息
            email.setAuthentication("3622807903" , "lsihefypzksrdbia");
            //设计邮箱服务器
            email.setHostName("smtp.qq.com");
            //设计收件人
            email.addTo(to , "你大哥");
            //设计消息体
            email.setHtmlMsg("<html><head><meta charset=\"UTF-8\"></head><body><a href='http://localhost:8080/J2020_1020_MyshopOnbatis_war_exploded/user?action=activation&code="+activeCode+"'>点击链接激活您的帐号</a></body></html>");
            //设计邮件主题
            email.setSubject("有内鬼，终止交易");

            //发送邮件
            email.send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
