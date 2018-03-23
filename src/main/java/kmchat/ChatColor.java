package kmchat;


enum ChatColor {
	PREFIX("§8"),
	NICK("§a"),
	GMCHAT("§6"),
	BDCHAT("§3"),
	OOC("§d"),
	DEFAULT("§f");
	
	private String color;
	ChatColor(String color) {
		this.color = color;
	}
	public String get() {
		return color;
	}
}
