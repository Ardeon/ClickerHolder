package ru.FIRST_114.ClickerHolder;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import ru.FIRST_114.Clicker.Main;
import ru.FIRST_114.Clicker.PlayerData.CPlayer;
import ru.FIRST_114.Clicker.PlayerData.PlayerStat;

public class ClickerPlaceHolder extends PlaceholderExpansion {


    // We get an instance of the plugin later.
    @SuppressWarnings("unused")
	private Main plugin;

    @Override
    public boolean canRegister(){
        return (plugin = (Main) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String getAuthor(){
        return "FIRST_114";
    }
 
    @Override
    public String getIdentifier(){
        return "clicker";
    }
  
    @Override
    public String getRequiredPlugin(){
        return "Clicker";
    }

    @Override
    public String getVersion(){
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier){

        if(player == null){
            return "";
        }

        // %someplugin_placeholder1%
        if(identifier.equals("score")&&Main.plugin.players.containsKey(player)){
        	CPlayer cp = Main.plugin.players.get(player);
            return ""+cp.stat.score;
        }

        // %someplugin_placeholder2%
        if(identifier.equals("coins")&&Main.plugin.players.containsKey(player)){
        	CPlayer cp = Main.plugin.players.get(player);
            return ""+cp.stat.coins;
        }
 
        if(identifier.startsWith("top")){
        	try {
        			int n = Integer.parseInt(identifier.substring(3));
        			if (Main.plugin.topchik.size()>=n) {
        				PlayerStat stat = Main.plugin.topchik.get(n).stat;
        				String name = Bukkit.getOfflinePlayer(Main.plugin.topchik.get(n).id).getName();
        				
        				return name+" "+stat.score;
        			}
        			else
        				return "<---->";
        			
        		}
        		catch (NumberFormatException e)
        		{
        			return null;
        		}
        }
        // We return null if an invalid placeholder (f.e. %someplugin_placeholder3%) 
        // was provided
        return null;
    }
}
