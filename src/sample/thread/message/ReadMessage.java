package sample.thread.message;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import javafx.scene.control.TextArea;
import sample.Controller;
import sample.messege.Message;
import sample.messege.MessageService;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class ReadMessage  extends Thread {

    private MessageService _messege;
    private TextArea textOnChat;

      public ReadMessage(TextArea textArea){
           this.textOnChat = textArea;
      }
    public MessageService getService() {
        return _messege;
    }

    public void setMessageService(MessageService messageService){
        _messege = messageService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Controller.chatTextDisplay.size()<_messege.getMesseges().size()){
                int count=_messege.getMesseges().size()-Controller.chatTextDisplay.size();
                String text = textOnChat.getText();
                for(int i = _messege.getMesseges().size()-1;i>Controller.chatTextDisplay.size()-1;i--){
                    text+=_messege.getMesseges().get(i) + "\n";
                }
                textOnChat.setText(text);
                Controller.chatTextDisplay=_messege.getMesseges();
            }
        }
    }
}