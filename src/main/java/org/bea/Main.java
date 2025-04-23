package org.bea;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.bea.configuration.AppConfig;
import org.bea.configuration.DataConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Создаем Tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // 2. Создаем Spring Web контекст (важно именно WebApplicationContext)
        WebApplicationContext appContext = createWebApplicationContext();

        // 3. Настраиваем веб-приложение
        Context tomcatContext = tomcat.addContext("", null);

        // 4. Регистрируем DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
        Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet)
                .addMapping("/");
        tomcatContext.addServletMappingDecoded("/*", "dispatcher");

        // 5. Запускаем сервер
        tomcat.getConnector();
        tomcat.start();
        System.out.println("Server started on port " + tomcat.getConnector().getPort());
        tomcat.getServer().await();
    }

    private static WebApplicationContext createWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // Регистрируем конфигурационные классы в правильном порядке:
        context.register(DataConfig.class); // Сначала Flyway и DataSource
        context.register(AppConfig.class);     // Затем Web MVC конфигурацию
        context.refresh();
        return context;
    }
}