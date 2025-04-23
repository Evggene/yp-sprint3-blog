package org.bea;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();

        Context context = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        context.addApplicationListener(AppInitializer.class.getName());
        tomcat.setPort(8082);
        tomcat.getConnector().addLifecycleListener(event -> {
            if (event.getType().equals("start")) {
                System.out.println("Соединение установлено на порту: " +
                        ((Connector)event.getSource()).getPort());
            }
        });
        tomcat.start();
        tomcat.getServer().await();
    }
}