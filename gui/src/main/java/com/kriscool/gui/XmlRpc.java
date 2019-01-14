package com.kriscool.gui;

import com.kriscool.api.MessageService;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.util.ClientFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class XmlRpc {

    public XmlRpc() {
    }

    public MessageService getXmlRpcApi() {
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL("http://localhost:8090/xmlrpc-service"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        config.setEnabledForExtensions(true);
        config.setEnabledForExceptions(true);

        xmlRpcClient.setConfig(config);
        xmlRpcClient.setTransportFactory(new XmlRpcCommonsTransportFactory(xmlRpcClient));
        return (MessageService) (new ClientFactory(xmlRpcClient).newInstance(MessageService.class));
    }


    @Bean(name = "xmlBean")
    public MessageService getXmlInvoker() {
        return getXmlRpcApi();
    }
}
