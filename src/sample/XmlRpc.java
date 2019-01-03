package sample;


import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.util.ClientFactory;
import sample.messege.MessageService;

import java.net.MalformedURLException;
import java.net.URL;

public class XmlRpc {

    String url;

    public XmlRpc(String url) {
        this.url = url;
    }


    public MessageService getXmlRpcApi() throws MalformedURLException {
        XmlRpcClient client = xmlRpcClient(xmlRpcClientConfig());
        return (MessageService) (new ClientFactory(client).newInstance(MessageService.class));
    }


    private XmlRpcClient xmlRpcClient(XmlRpcClientConfig config) {
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        xmlRpcClient.setConfig(config);
        xmlRpcClient.setTransportFactory(new XmlRpcCommonsTransportFactory(xmlRpcClient));
        return xmlRpcClient;
    }

    private XmlRpcClientConfigImpl xmlRpcClientConfig() throws MalformedURLException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(url));
        config.setEnabledForExtensions(true);
        config.setEnabledForExceptions(true);

        return config;
    }

}