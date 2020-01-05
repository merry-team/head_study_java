package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.neteye.xinzhizhu.utils.SpringContextUtils;

@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan({"com.*..*.dao"}) //,"com.*.*.*.dao"
@ComponentScan({"com.neteye","com.sharingcard"})
@SpringBootApplication
@EnableCaching
public class SharingCradApplication {
    
    public static void main(String[] args) {
    	//setApplicationContext
    	
    	//SpringContextUtils.setApplicationContext(springApplication);
        SpringApplication.run(SharingCradApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ   sharingcard启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
                "     _                _                               _ \n" + 
                " ___| |__   __ _ _ __(_)_ __   __ _  ___ __ _ _ __ __| |\n" + 
                "/ __| '_ \\ / _` | '__| | '_ \\ / _` |/ __/ _` | '__/ _` |\n" + 
                "\\__ \\ | | | (_| | |  | | | | | (_| | (_| (_| | | | (_| |\n" + 
                "|___/_| |_|\\__,_|_|  |_|_| |_|\\__, |\\___\\__,_|_|  \\__,_|\n" + 
                "                              |___/     ");
    }
}
