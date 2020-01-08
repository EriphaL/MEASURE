package com.nbst.model;
/**
 * 数据库
 * @author zm
 *
 */
public class Db {
	
    private Integer sjkId;//Id
    
    private Integer sjkGsId;//公司Id
    
    private String sjkUrl;//数据库URL
    
    private String sjkUsername;//用户名

    private String sjkPassword;//密码

    private String sjkExtend1;//扩展字段1

    private String sjkExtend2;//扩展字段2

    private String sjkExtend3;//扩展字段3

    private String sjkExtend4;//扩展字段4

    private String sjkExtend5;//扩展字段5

    private Integer sjkState;//状态

    public Integer getSjkId() {
        return sjkId;
    }

    public void setSjkId(Integer sjkId) {
        this.sjkId = sjkId;
    }

    public Integer getSjkGsId() {
        return sjkGsId;
    }

    public void setSjkGsId(Integer sjkGsId) {
        this.sjkGsId = sjkGsId;
    }

    public String getSjkUrl() {
        return sjkUrl;
    }

    public void setSjkUrl(String sjkUrl) {
        this.sjkUrl = sjkUrl == null ? null : sjkUrl.trim();
    }

    public String getSjkUsername() {
        return sjkUsername;
    }

    public void setSjkUsername(String sjkUsername) {
        this.sjkUsername = sjkUsername == null ? null : sjkUsername.trim();
    }

    public String getSjkPassword() {
        return sjkPassword;
    }

    public void setSjkPassword(String sjkPassword) {
        this.sjkPassword = sjkPassword == null ? null : sjkPassword.trim();
    }

    public String getSjkExtend1() {
        return sjkExtend1;
    }

    public void setSjkExtend1(String sjkExtend1) {
        this.sjkExtend1 = sjkExtend1 == null ? null : sjkExtend1.trim();
    }

    public String getSjkExtend2() {
        return sjkExtend2;
    }

    public void setSjkExtend2(String sjkExtend2) {
        this.sjkExtend2 = sjkExtend2 == null ? null : sjkExtend2.trim();
    }

    public String getSjkExtend3() {
        return sjkExtend3;
    }

    public void setSjkExtend3(String sjkExtend3) {
        this.sjkExtend3 = sjkExtend3 == null ? null : sjkExtend3.trim();
    }

    public String getSjkExtend4() {
        return sjkExtend4;
    }

    public void setSjkExtend4(String sjkExtend4) {
        this.sjkExtend4 = sjkExtend4 == null ? null : sjkExtend4.trim();
    }

    public String getSjkExtend5() {
        return sjkExtend5;
    }

    public void setSjkExtend5(String sjkExtend5) {
        this.sjkExtend5 = sjkExtend5 == null ? null : sjkExtend5.trim();
    }

    public Integer getSjkState() {
        return sjkState;
    }

    public void setSjkState(Integer sjkState) {
        this.sjkState = sjkState;
    }
}