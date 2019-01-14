package com.example.klient;

import com.kriscool.api.MessageService;
import javafx.scene.control.TextArea;

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

            if(MainController.chatTextDisplay.size()<_messege.getMesseges().size()){
                int count=_messege.getMesseges().size()-MainController.chatTextDisplay.size();
                String text = textOnChat.getText();
                for(int i = _messege.getMesseges().size()-1;i>MainController.chatTextDisplay.size()-1;i--){
                    text+=_messege.getMesseges().get(i) + "\n";
                }
                textOnChat.setText(text);
                MainController.chatTextDisplay=_messege.getMesseges();
            }
        }
    }
}