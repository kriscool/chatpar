package com.kriscool.gui;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import com.kriscool.api.MessageService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.util.ClientFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Component
public class Controller implements Initializable {

    private HessianProxyFactory factoryHessian = new HessianProxyFactory();
    private BurlapProxyFactory factoryBurlap = new BurlapProxyFactory();
    private ReadMessage readMessage;
    private String _urlHessian = "http://localhost:8090/hessian-service";
    private String _urlBurlap = "http://localhost:8090/burlap-service";
    //private XmlRpc xmlRpc = new XmlRpc();
    private MessageService _messege;
    private String login;
    private MessageService getService()
    {
        return _messege;
    }
    private String textOnChat="Witaj \n";
    @FXML
    Button sendButton;
    @FXML
    TextArea chatText;
    @FXML
    TextField textField;
    @FXML
    MenuItem xmlRpcButton;
    @FXML
    MenuItem burlapButton;
    @FXML
    MenuItem hessianButton;
    @FXML
    Button logInButton;
    @FXML
    TextField loginText;
    @FXML
    Text TypeLogin;
    @FXML
    MenuButton menuButtons;
    @FXML
    Text loginShow;

    public static HashMap<Integer,String> chatTextDisplay = new HashMap<>();
    @FXML
    private void changeToHessian(){
        try {
            _messege = (MessageService) factoryHessian.create(MessageService.class, _urlHessian);
            readMessage.setMessageService(_messege);
            System.out.println("Hessian");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeToBurlap(){
        try {
            _messege = (MessageService) factoryBurlap.create(MessageService.class, _urlBurlap);
            readMessage.setMessageService(_messege);
            System.out.println("Burlap");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeToXmlRpc(){
        _messege = getXmlRpcApi();
        readMessage.setMessageService(_messege);
        System.out.println("XmlRpc");
    }
    @FXML
    private void sendMessage(){
        if(!textField.getText().isEmpty()) {
            getService().placeMEssage(login, textField.getText());
            chatTextDisplay.put(chatTextDisplay.size(),login+";"+textField.getText());
            textOnChat=chatText.getText() + login + " : " + textField.getText() + "\n";
            chatText.setText(textOnChat);
            textField.clear();
        }

    }
    @FXML
    private void logIn(){
        sendButton.setVisible(true);
        textField.setVisible(true);
        chatText.setVisible(true);
        burlapButton.setVisible(true);
        hessianButton.setVisible(true);
        xmlRpcButton.setVisible(true);
        menuButtons.setVisible(true);
        loginShow.setVisible(true);
        logInButton.setVisible(false);
        loginText.setVisible(false);
        TypeLogin.setVisible(false);
        login = loginText.getText();
        readMessage = new ReadMessage(chatText);
        readMessage.start();
        try {
            _messege = (MessageService) factoryHessian.create(MessageService.class, _urlHessian);
        }
        catch (MalformedURLException e) {
        }
        readMessage.setMessageService(_messege);
        chatTextDisplay = getService().getMesseges();

    }

    public void End() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
