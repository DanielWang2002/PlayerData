package mc.danielwang.playerdata;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import java.util.Collection;

public class PDCommand implements CommandExecutor {
    public static String NAME = "";
    public static String IP = "";
    public static String UUID = "";
    public static String MONEY = "";
    public static Economy econ = Playerdata.getEconomy();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0){
            commandSender.sendMessage(ChatColor.YELLOW + "----------" + ChatColor.AQUA + "玩家資料 指令小幫手" + ChatColor.YELLOW + "----------");
            commandSender.sendMessage(ChatColor.RED + "/pd <玩家ID>" + ChatColor.GOLD + " 顯示該玩家的相關資料(限在線玩家)");
        }else if (strings.length == 1){
            if (strings[0].equalsIgnoreCase("help")){ //pd help
                commandSender.sendMessage(ChatColor.YELLOW + "----------" + ChatColor.RED + "玩家資料 指令小幫手" + ChatColor.YELLOW + "----------");
                commandSender.sendMessage(ChatColor.RED + "/pd <玩家ID>" + ChatColor.GOLD + " 顯示該玩家的相關資料(限在線玩家)");
            }else{ //pd PlayerID
                String playername = strings[0]; //取得輸入ID
                Boolean Player_isOnline = Boolean.FALSE; //檢測玩家是否在線上，預設為離線
                final Collection<? extends Player> players = Bukkit.getOnlinePlayers(); //取得所有在線玩家為Collection

                for(Player p: players){
                    if (p.getName().equals(playername)){ //如果線上名單有該玩家
                        Player_isOnline = Boolean.TRUE;
                        NAME = p.getName();
                        IP = p.getAddress().toString();
                        UUID = p.getUniqueId().toString();
                        MONEY = econ.format(econ.getBalance(p));
                        break;
                    }
                }

                if (Player_isOnline){ //如果玩家在線上
                    commandSender.sendMessage(ChatColor.YELLOW + "Name: " + NAME);
                    commandSender.sendMessage(ChatColor.YELLOW + "IP: " + IP);
                    commandSender.sendMessage(ChatColor.YELLOW + "UUID: " + UUID);
                    commandSender.sendMessage(ChatColor.YELLOW + "Money: " + MONEY);

                }else{
                    commandSender.sendMessage(ChatColor.RED + "The Player isn't Online, please try another player name");
                }
            }
        }
        return false;
    }
}