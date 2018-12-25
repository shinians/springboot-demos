package com.limp.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//使用JPA注解配置映射关系
@Entity//告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "lp_users") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class UserInfo  {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Column(name = "ID")
    private String id;
    @Column(name = "ACCOUNT",length = 60) //这是和数据表对应的一个列
    private String account;

    @Column(name = "NAME")//省略默认列名就是属性名
    private String name;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IP")
    private String ip;

    @Column(name = "MAC")
    private String mac;

    private Short isautoexpire;

    @Column(name = "ISBINDIP")
    private Short isbindip;

    @Column(name = "ISBINDMAC")
    private Short isbindmac;

    /*private Date lasttime;

    private String lastip;

    private String lastmac;

    private Short logincount;

    private Date createdate;

    private Date updatetime;

    private Short state;

    private String icn;

    private String unitcode;

    private String phone;

    private Short utype;

    private Short uversion;

    private String keypath;

    private Date expiredate;

    private String email;

    private String conaccount;

    private String apptype;

    private String rc1;

    private String rc2;

    private Short rc3;

    private String optaccount;

    private String rc4;

    private String rc5;

    private Date rc6;*/

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Short getIsautoexpire() {
        return isautoexpire;
    }

    public void setIsautoexpire(Short isautoexpire) {
        this.isautoexpire = isautoexpire;
    }

    public Short getIsbindip() {
        return isbindip;
    }

    public void setIsbindip(Short isbindip) {
        this.isbindip = isbindip;
    }

    public Short getIsbindmac() {
        return isbindmac;
    }

    public void setIsbindmac(Short isbindmac) {
        this.isbindmac = isbindmac;
    }


}