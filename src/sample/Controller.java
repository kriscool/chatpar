package sample;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import sample.messege.Message;
import sample.messege.MessageService;
import sample.thread.message.ReadMessage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private HessianProxyFactory factoryHessian = new HessianProxyFactory();
    private BurlapProxyFactory factoryBurlap = new BurlapProxyFactory();
    private ReadMessage readMessage;
    private String _urlHessian = "http://localhost:8080/hessian-service/hessian-service";
    private String _urlBurlap = "http://localhost:8080/hessian-service/burlap-service";
    private String _xmlRpc = "http://localhost:8080/hessian-service/xml";
    private XmlRpc xmlRpc = new XmlRpc(_xmlRpc);
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

    @FXML
    public void initialize() {
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
    public static Map<Integer,String> chatTextDisplay = new HashMap<>();
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
        try {
            _messege = xmlRpc.getXmlRpcApi();
            readMessage.setMessageService(_messege);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
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

    }

    public void End() throws IOException {
    }
}
