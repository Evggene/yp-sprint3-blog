package org.bea;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.bea.configuration.AppConfig;
import org.bea.configuration.DataConfig;
import org.h2.server.web.JakartaWebServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class Main {
    public static void main(String[] args) throws Exception {
        var tomcat = new Tomcat();
        tomcat.setPort(8080);

        var tomcatContext = tomcat.addContext("", null);
        var appContext = createWebApplicationContext();

        Tomcat.addServlet(tomcatContext, "dispatcher", new DispatcherServlet(appContext))
                .addMapping("/");
        tomcatContext.addServletMappingDecoded("/*", "dispatcher");

        addServletH2(tomcatContext);

        tomcat.getConnector();
        tomcat.start();
        System.out.println("Server started on port " + tomcat.getConnector().getPort());
        tomcat.getServer().await();
    }

    private static void addServletH2(Context tomcatContext) {
        Tomcat.addServlet(tomcatContext, "h2Console", new JakartaWebServlet())
                .addMapping("/h2Console/*");
        tomcatContext.addServletMappingDecoded("/h2Console/*", "h2Console");
    }

    private static WebApplicationContext createWebApplicationContext() {
        var context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.register(DataConfig.class);
        context.refresh();
        return context;
    }
}