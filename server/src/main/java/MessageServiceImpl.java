
import java.util.HashMap;
import java.util.Map;

public class MessageServiceImpl implements MessageService {
	 private Map<Integer,String> messages = new HashMap<Integer,String>();
	 private Integer countMessages = 0;

	@Override
	public Map<Integer,String> getMesseges() {
		return new HashMap<>(messages);
	}

	@Override
	public boolean placeMEssage(String from, String contents) {
		messages.put(countMessages++, from+" : "+contents);
		return true;
	}

}
