package com.wgb.utils;

import com.wgb.utils.util.string.StringUtil;
import com.wgb.utils.util.xml.entity.LoadWorld;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * 启动类
 * @author INNERPEACE
 */

@SpringBootApplication
@Slf4j
public class UtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

    @PostConstruct
    public void init() {
        String worldPath = "file/xml/world.xml";
        try {
            String xml = StringUtil.inputStreamToString(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(worldPath)));
            LoadWorld.getInstance().load(xml);
            log.info("成功加载:{}配置文件", worldPath);
        } catch (Exception e) {
            log.error("加载配置文件:{}异常", worldPath, e);
        }
    }
}
