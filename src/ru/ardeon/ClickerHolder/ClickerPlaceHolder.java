package ru.ardeon.ClickerHolder;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import ru.ardeon.Clicker.Main;
import ru.ardeon.Clicker.PlayerData.CPlayer;
import ru.ardeon.Clicker.PlayerData.PlayerStat;

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
        if(identifier.equals("click_level")&&Main.plugin.players.containsKey(player)){
        	CPlayer cp = Main.plugin.players.get(player);
            return ""+(int) Math.pow(2, cp.stat.autoclickers);
        }
        if(identifier.equals("click_level_next")&&Main.plugin.players.containsKey(player)){
        	CPlayer cp = Main.plugin.players.get(player);
        	return ""+(int) Math.pow(2, cp.stat.autoclickers + 1);
        }
        if(identifier.equals("boost")&&Main.plugin.players.containsKey(player)){
        	CPlayer cp = Main.plugin.players.get(player);
            return ""+cp.stat.autoclickers;
        }
        if(identifier.equals("boostprice")&&Main.plugin.players.containsKey(player)){
        	CPlayer cp = Main.plugin.players.get(player);
            return ""+cp.stat.getBoostprice();
        }
        if(identifier.equals("timetoreward")){
            return Main.plugin.timeToReward.getTimeToReward();
        }

        // %someplugin_placeholder2%
        if(identifier.equals("coins")&&Main.plugin.players.containsKey(player)){
        	CPlayer cp = Main.plugin.players.get(player);
            return ""+cp.stat.coins;
        }
 
        if(identifier.startsWith("top")){
        	try {
        			int n = Integer.parseInt(identifier.substring(3));
        			if (Main.plugin.topchik.size()>=n && n>0) {
        				PlayerStat stat = Main.plugin.topchik.get(n-1).stat;
        				String name = Bukkit.getOfflinePlayer(Main.plugin.topchik.get(n-1).id).getName();
        				
        				return "&6"+name+" &f- &e"+stat.score;
        			}
        			else
        				return "<---->";
        			
        		}
        		catch (NumberFormatException e)
        		{
        			return null;
        		}
        }
        if(identifier.startsWith("player")){
        	try {
        			int n = Integer.parseInt(identifier.substring(6));
        			if (Main.plugin.topchik.size()>=n && n>0) {
        				String name = Bukkit.getOfflinePlayer(Main.plugin.topchik.get(n-1).id).getName();
        				return name;
        			}
        			else
        				return "<---->";
        			
        		}
        		catch (NumberFormatException e)
        		{
        			return null;
        		}
        }
        if(identifier.startsWith("previoustop")){
        	try {
        			int n = Integer.parseInt(identifier.substring(11));
        			if (Main.plugin.previousTopchik.size()>=n && n>0) {
        				PlayerStat stat = Main.plugin.previousTopchik.get(n-1).stat;
        				String name = Bukkit.getOfflinePlayer(Main.plugin.previousTopchik.get(n-1).id).getName();
        				
        				return "&6"+name+" &f- &e"+stat.score;
        			}
        			else
        				return "<---->";
        			
        		}
        		catch (NumberFormatException e)
        		{
        			return null;
        		}
        }
        if(identifier.startsWith("previousplayer")){
        	try {
        			int n = Integer.parseInt(identifier.substring(14));
        			if (Main.plugin.previousTopchik.size()>=n && n>0) {
        				String name = Bukkit.getOfflinePlayer(Main.plugin.previousTopchik.get(n-1).id).getName();
        				return name;
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
