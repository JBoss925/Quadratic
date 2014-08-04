package me.JBoss925.quad;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by jagger1 on 8/1/14.
 */
public class Main extends JavaPlugin {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("fun")){
            Quadratic q = new Quadratic(1.0, 1.0, 4.0);
            Player p = (Player) sender;
            for(Location loc : q.getLocationsInPlayerAlignedParabola(p, -10.0, 10.0, .5)){
                Block b = p.getWorld().getBlockAt(loc);
                b.setType(Material.OBSIDIAN);
                System.out.println(loc.getX() + "/" + loc.getY() + "/" + loc.getZ() + "/" + p.getLocation().getYaw());
            }
            p.sendMessage("Done");
            return true;
        }
        return false;
    }

}
