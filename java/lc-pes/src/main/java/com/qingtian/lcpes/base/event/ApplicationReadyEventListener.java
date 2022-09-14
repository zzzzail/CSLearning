package com.qingtian.lcpes.base.event;

import com.qingtian.lcpes.base.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 坚挺应用程序已启动
 *
 * @author zhangxq
 * @since 2022/8/30
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        String appName = ApplicationContextHolder.getProperty("spring.application.name", "app");
        String ip = "127.0.0.1";
        String port = ApplicationContextHolder.getProperty("server.port", "8080");
        System.out.println(
                "     __                   ______                   \n" +
                "    /    )   ,               /      ,              \n" +
                "---/----/--------__----__---/-----------__----__-   :: 晴天平台应用启动成功       \n" +
                "  /  \\ /   /   /   ) /   ) /      /   /   ) /   )   :: " + appName +        "\n" +
                "_(____X___/___/___/_(___/_/______/___(___(_/___/_   :: " + ip + ":" + port + "\n" +
                "       \\               /                                                     \n" +
                "                   (_ /  "
        );
    }

}
