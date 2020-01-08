package com.nbst.comnutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogOut {

    @Autowired
    serverConf conf;

    public void info(String msg){
        log.info(conf.getServername() + " - " + msg);
    }

    public void error(Throwable e){
        log.error(conf.getServername(),e);
    }

    public void debug(String msg){
        log.debug(conf.getServername() + " - " + msg);
    }
}
