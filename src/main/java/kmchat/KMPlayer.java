package kmchat;


import java.util.ArrayList;
import java.util.List;

public class KMPlayer {
	private String prefix = "";
	private String nick;
	private List<String> permissions = new ArrayList<>();
	
	public KMPlayer() {
		this.nick = null;
	}
	public KMPlayer(String nick) {
		this.nick = nick;
	}
	
	//NOTE this does not affect ingame's permissions
	public void notePermission(String permissionName) {
		if (!permissions.contains(permissionName)) {
			permissions.add(permissionName);
		}
	}	
	
	
	//get prefix based mostly on permissions
 	public String getPrefix() {
		if (permissions.contains("gm")) {
			return ChatColor.PREFIX.get() + "[GM]" + ChatColor.DEFAULT.get();
		} else if (permissions.contains("builder")) {
			return ChatColor.PREFIX.get() + "[BD]" + ChatColor.DEFAULT.get();
		} else {
			return prefix;
		}
 	}
 	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getDisplayNick() {
		return ChatColor.NICK.get() + nick + ChatColor.DEFAULT.get();
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
}
