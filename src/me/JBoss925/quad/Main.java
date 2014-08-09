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
            Quadratic q = new Quadratic(Double.parseDouble(args[0]), 0.0, 0.0);
            Player p = (Player) sender;
            for(Location loc : q.getLocationsInCoordinateAlignedQuadratic(-100.0, 100.0, 0.1, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getWorld(), false)){
                Block b = p.getWorld().getBlockAt(loc);
                b.setType(Material.OBSIDIAN);
            }
            return true;
        }
        if(command.getName().equalsIgnoreCase("fun2")){
            Quadratic q = new Quadratic(Double.parseDouble(args[0]), 0.0, 4.0);
            Player p = (Player) sender;
            for(Location loc : q.getLocationsInPlayerAlignedQuadratic(p, -100.0, 100.0, 0.1)){
                Block b = p.getWorld().getBlockAt(loc);
                b.setType(Material.OBSIDIAN);
            }
            return true;
        }
        if(command.getName().equalsIgnoreCase("yaw")){
            Player p = (Player) sender;
        }
        return false;
    }

}
