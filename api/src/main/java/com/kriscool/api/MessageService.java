package com.kriscool.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;

public interface MessageService extends Remote {
    boolean placeMEssage(String from,String messeage);

    HashMap<Integer,String> getMesseges();
}
