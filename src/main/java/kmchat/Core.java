package kmchat;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

class Core
        extends JavaPlugin
        implements Listener {
    private Logger log = Logger.getLogger("Minecraft");
    private String logspath = "logs/";
    private List<KMPlayer> kmplayers = new ArrayList<>();

    //enabling bukkit plugin
    public void onEnable() {

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.logspath = this.getConfig().getString("logsdir");
        this.getServer().getPluginManager().registerEvents((Listener) this, (Plugin) this);
        this.log.info(String.format("%s is enabled!", this.getDescription().getFullName()));

    }

    //disabling bukkit plugin
    public void onDisable() {
        this.log.info(String.format("%s is disabled!", this.getDescription().getFullName()));
    }

    //handling player's join
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        String playerName = playerJoinEvent.getPlayer().getName();
        String playerIp = playerJoinEvent.getPlayer().getAddress().getHostName();
        //add new KMPlayer object to the list
        KMPlayer kmplayer = new KMPlayer(playerName);
        System.out.println(kmplayer.getDisplayNick());
        kmplayers.add(kmplayer);
        //send join message to game
        playerJoinEvent.setJoinMessage("§e" + playerName + "§f входит в игру");
        //save player's ip
        try (FileWriter writer = new FileWriter(logspath + "ipgame.log", true)) {
            writer.write(playerName + " " + playerIp + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //handling player's leave
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent playerQuitEvent) {
        String playerName = playerQuitEvent.getPlayer().getName();
        for (KMPlayer pl : kmplayers) {
            if (pl.getNick().equals(playerName)) {
                kmplayers.remove(pl);
                break;
            }
        }
        //send leave message to game
        playerQuitEvent.setQuitMessage("§e" + playerName + "§f выходит из игры");
    }

    //formatting chat messages
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent asyncPlayerChatEvent) {
        Player player = asyncPlayerChatEvent.getPlayer();
        //get KMPlayer object
        KMPlayer sender = new KMPlayer();
        for (KMPlayer pl : kmplayers) {
            if (pl.getNick().equals(player.getName())) {
                sender = pl;
            }
        }
        if (sender.getNick() == null) {
            System.out.println("Unexpected error: player not found in kmplayers");
            return;
        }
        //note what relevant permissions sender has
        if (player.hasPermission("KMChat.gm")) {
            sender.notePermission("gm");
        } else if (player.hasPermission("KMChat.builder")) {
            sender.notePermission("builder");
        }
        //create custom Message object
        Message message = new Message(asyncPlayerChatEvent.getMessage(), sender);
        //get Range object
        Range range = message.getRange();

        String format = message.getFormat();
        String messageStr = message.getMessage();
        System.out.println("format: " + format);
        System.out.println("messageStr: " + messageStr);
        asyncPlayerChatEvent.setFormat(String.format("%s: %s", format, messageStr));
        asyncPlayerChatEvent.setMessage(messageStr);

        //manage recipients
        asyncPlayerChatEvent.getRecipients().clear();
        double distance = this.getConfig().getDouble("range." + range.getDesc());
        List<Player> recipients;
/*		if (range.special()) {
			recipients = getPermissionRecipients(message.getMessage(), range.getSpecial());
		} else */
        if (range.global()) {
            recipients = Arrays.asList(Bukkit.getServer().getOnlinePlayers());
        } else {
            recipients = getLocalRecipients(player, message.getMessage(), distance);
        }

        asyncPlayerChatEvent.getRecipients().addAll(recipients);

    }

    //returns players who have certain permission (bukkit based)
    @SuppressWarnings("deprecation")
    protected List<Player> getPermissionRecipients(String mes, String permissionName) {
        LinkedList<Player> linkedList = new LinkedList<>();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission(permissionName)) {
                linkedList.add(player);
            }
        }
        return linkedList;
    }

    //returns players who are in the range and sends message to GM's that are not (bukkit based)
    @SuppressWarnings("deprecation")
    private List<Player> getLocalRecipients(Player player, String mes, double d) {
        Location location = player.getLocation();
        LinkedList<Player> linkedList = new LinkedList<>();
        double d2 = Math.pow(d, 2.0);
        //iterate through all players online
        for (Player player2 : Bukkit.getServer().getOnlinePlayers()) {
            //send faded message to GM if he is not in the range
            if (player2.hasPermission("KMChat.gm")) {
                if (!player2.getWorld().equals((Object) player.getWorld()) || location.distanceSquared(player2.getLocation()) > d2) {
                    //fade message
                    mes = mes.replaceAll("§e", "§7");
                    mes = mes.replaceAll("§f", "§7");
                    player2.sendMessage(mes);
                    continue;
                }
                linkedList.add(player2);
                continue;
            }
            //add all those who are in the range
            if (!player2.getWorld().equals((Object) player.getWorld()) || location.distanceSquared(player2.getLocation()) > d2)
                continue;
            linkedList.add(player2);
        }
        return linkedList;
    }
}


