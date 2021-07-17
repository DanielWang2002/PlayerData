package mc.danielwang.playerdata;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerJoinLog implements Listener {
    private String NAME;
    private String IP;
    private String UUID;
    private String MONEY;
    private Economy econ = PDCommand.econ;

    @EventHandler
    public void ExportTXT(PlayerJoinEvent e) throws IOException {
        Player player = e.getPlayer();
        NAME = player.getDisplayName();
        IP = player.getAddress().toString();
        UUID = player.getUniqueId().toString();
        MONEY = econ.format(econ.getBalance(player));

        File pd = new File("./PlayerData/" + NAME + ".txt");
        pd.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(pd));
        out.write("Name: " + NAME + "\r\n");
        out.write("IP:   " + IP + "\r\n");
        out.write("UUID: " + UUID + "\r\n");
        out.write("Money: " + MONEY);
        out.flush();
        out.close();
        return;
    }
}
