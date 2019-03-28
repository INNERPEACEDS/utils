package com.wgb.utils.util.path;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : innerpeace
 * @date : 2018/9/19 15:52
 * 文件路径工具
 */
@Data
@Component
@ConfigurationProperties(prefix = "path")
@PropertySource("classpath:/properties/filepath.properties")
public class PathUtil {
    private String pubKey;
    private String priKey;
    private String realPath;
}
