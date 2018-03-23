package kmchat;


public class Message {
	private Range range;
	private KMPlayer sender;
	private String message; //what is said
	private String format; //what is printed before message (e.g. [GM]Player (OOC):)
	private String result; //equals message + format
	
	//create Message object out of String
	public Message(String mes, KMPlayer player) {
		sender = player;
		//define range of the message
		range = new Range(mes);
		//format message based on range
		message = range.getRawMessage();
		format = String.format("%s%s", sender.getPrefix()+sender.getDisplayNick(), range.getDesc());
		//format special messages
		if (range.ooc()) {
			message = ChatColor.OOC.get() + "(( " + message + " ))" + ChatColor.DEFAULT.get();
		}

		result = String.format("%s: %s", format, message);
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}
	
	
}
