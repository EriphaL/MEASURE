package com.nbst.comnutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @ClassName: serverConf
 * @Description:读取配置信息类
 * @author 兵
 * @date 2017-6-9
 *
 */
@Configuration
public class serverConf {
    @Value("${server.name}")
    private String servername;
    @Value("${server.port}")
    private String port;
    @Value("${server.ip}")
    private String ip;

    public String getPort() {
        return port;
    }
    public String getServername() {
        return servername;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
}
