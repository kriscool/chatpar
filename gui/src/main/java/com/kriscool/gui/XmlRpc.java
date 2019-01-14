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

    private String url;
    public XmlRpc(String url) {
        this.url=url;
    }

    public MessageService getXmlRpcApi() {
        XmlRpcClient client = xmlRpcClient(xmlRpcClientConfig());
        return (MessageService) (new ClientFactory(client).newInstance(MessageService.class));
    }

    private XmlRpcClient xmlRpcClient(XmlRpcClientConfig config) {
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        xmlRpcClient.setConfig(config);
        xmlRpcClient.setTransportFactory(new XmlRpcCommonsTransportFactory(xmlRpcClient));
        return xmlRpcClient;
    }

    private XmlRpcClientConfigImpl xmlRpcClientConfig() {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        config.setEnabledForExtensions(true);
        config.setEnabledForExceptions(true);

        return config;
    }


    @Bean(name = "xmlBean")
    public MessageService getXmlInvoker() {
        return getXmlRpcApi();
    }
}
