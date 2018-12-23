package com.limp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/23 21:20
 * @website： www.shinians.com
 */
@ConfigurationProperties(prefix = "limp.hello")
public class HelloConfig {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
