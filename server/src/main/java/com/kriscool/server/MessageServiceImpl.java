package com.kriscool.server;

import com.kriscool.api.MessageService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

public class MessageServiceImpl implements MessageService, Serializable {

    private HashMap<Integer,String> messages = new HashMap<Integer,String>();
    private Integer countMessages = 0;

    @Override
    public HashMap<Integer,String> getMesseges() {
        return new HashMap<>(messages);
    }

    @Override
    public boolean placeMEssage(String from, String contents) {
        messages.put(countMessages++, from+" : "+contents);
        return true;
    }
}
