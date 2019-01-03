package sample.messege;
import java.io.Serializable;
import java.util.HashMap;

public class Message implements Serializable {
	public HashMap<String, String> getMessage() {
		return message;
	}

	public void setMessage(HashMap<String, String> message) {
		this.message = message;
	}

	private static final long serialVersionUID = 5982103721460501595L;
	HashMap<String,String> message;
}
