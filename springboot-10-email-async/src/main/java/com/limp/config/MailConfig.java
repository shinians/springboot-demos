package com.limp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/31 1:33
 * @website： www.shinians.com
 */
//@Component
//@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {
    public String host;

    public Integer port;
    public String username;

    public String password;

    public String emailform;
    @Value("${spring.mail.properties.timeout}")
    public String timeout;
    @Value("${spring.mail.properties.company}")
    public String personal;

    public Map<String, String> properties;

    public Map getProperties() {
        return properties;
    }

    public void setProperties(Map properties) {
        this.properties = properties;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailform() {
        return emailform;
    }

    public void setEmailform(String emailform) {
        this.emailform = emailform;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
