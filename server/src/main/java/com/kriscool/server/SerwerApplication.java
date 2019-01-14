package com.kriscool.server;

import com.kriscool.api.MessageService;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SerwerApplication {

    private MessageServiceImpl messageServiceImpl = new MessageServiceImpl();

    @Bean(name = "/xmlrpc-service")
    public XmlServiceExporter xmlRpc() throws XmlRpcException {
        return new XmlServiceExporter(messageServiceImpl);
    }

    @Bean(name = "/hessian-service")
    RemoteExporter hessian() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(messageServiceImpl);
        exporter.setServiceInterface(MessageService.class);
        return exporter;
    }

    @Bean(name = "/burlap-service")
    RemoteExporter burlap() {
        BurlapServiceExporter exporter = new BurlapServiceExporter();
        exporter.setService(messageServiceImpl);
        exporter.setServiceInterface(MessageService.class);
        return exporter;
    }

    public MessageServiceImpl getChatImpl() {
        return messageServiceImpl;
    }

    public static void main(String[] args) {
        SpringApplication.run(SerwerApplication.class, args);
    }
}
