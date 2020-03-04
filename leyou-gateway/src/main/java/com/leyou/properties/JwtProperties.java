package com.leyou.properties;

/**
 * Created by Zhang Ao on 2020/1/29 17:52
 * Email:17863572518@163.com
 */


import com.leyou.common.utils.RsaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * Created by Zhang Ao on 2020/1/29 15:28
 * Email:17863572518@163.com
 */
@ConfigurationProperties(prefix = "leyou.jwt")
public class JwtProperties {



    private String pubKeyPath;// 公钥



    private PublicKey publicKey; // 公钥


    private String cookieName;    //cookie Name

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    /**
     * @PostContruct：在构造方法执行之后执行该方法
     */
    @PostConstruct
    public void init(){
       try{

            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);

        } catch (Exception e) {
            logger.error("初始化公钥和私钥失败！", e);
            throw new RuntimeException();
        }
    }

    public String getPubKeyPath() {
        return pubKeyPath;
    }

    public void setPubKeyPath(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public static Logger getLogger() {
        return logger;
    }
}
