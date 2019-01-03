

import java.util.Map;

public interface MessageService {

    boolean placeMEssage(String from,String messeage);

    Map<Integer,String> getMesseges();

}