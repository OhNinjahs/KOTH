package subside.plugins.koth.hooks.plugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import subside.plugins.koth.ConfigHandler;
import subside.plugins.koth.ConfigHandler.Hooks.Featherboard;
import subside.plugins.koth.KothPlugin;
import subside.plugins.koth.adapter.Koth;
import subside.plugins.koth.events.KothEndEvent;
import subside.plugins.koth.events.KothStartEvent;
import subside.plugins.koth.hooks.AbstractHook;
import subside.plugins.koth.utils.Utils;

public class FeatherboardHook extends AbstractHook implements Listener {
    private boolean enabled = false;
    private Koth koth;
    
    
    private int range = 20;
    private int rangeMargin = 5;
    private String board;
    private List<OfflinePlayer> inRange;
    
    public FeatherboardHook(){
        inRange = new ArrayList<>();
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("FeatherBoard")){
            Featherboard fbHook = ConfigHandler.getCfgHandler().getHooks().getFeatherboard();
            if(fbHook.isEnabled()){
                enabled = true;
                range = fbHook.getRange();
                rangeMargin = fbHook.getRangeMargin();
                board = fbHook.getBoard();
            }
        }
        Utils.log("Featherboard hook: "+(enabled?"Enabled":"Disabled"));
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onKothStart(KothStartEvent event){
        koth = event.getKoth();
        if(range < 0){
            for(Player player : Bukkit.getOnlinePlayers()){
                inRange.add(player);
                setBoard(player, board);
            }
        }
    }

    @EventHandler
    public void onKothEnd(KothEndEvent event){
        if(!isEnabled() || koth == null) return;
        
        for(OfflinePlayer player : inRange){
            if(player.isOnline())
                resetBoard(player.getPlayer(), board);
        }
        inRange.clear();
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!isEnabled() || koth == null) return;
        if(range < 0){
            inRange.add(event.getPlayer());
            setBoard(event.getPlayer(), board);
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        inRange.remove(event.getPlayer());
    }

    
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event){
        inRange.remove(event.getPlayer());
    }
    
    
    @Override
    public void tick(){
        if(!isEnabled() || range < 0) return;
        
        if(koth == null || !koth.isRunning()){

            for(OfflinePlayer player : inRange){
                if(player.isOnline())
                    resetBoard(player.getPlayer(), board);
            }
            inRange.clear();
            koth = null;
            return;
        }
        
        for(Player player: Bukkit.getOnlinePlayers()){
            Location loc = koth.getMiddle();
            if(!inRange.contains(player)){
                if(loc.getWorld() == player.getLocation().getWorld() && loc.distance(player.getLocation()) <= range-rangeMargin){
                    inRange.add(player);
                    setBoard(player, board);
                }
            } else {
                if(loc.getWorld() != player.getLocation().getWorld() || loc.distance(player.getLocation()) >= range+rangeMargin){
                    inRange.remove(player);
                    resetBoard(player, board);
                }
            }
        }
    }

    
    public void setBoard(final Player player, final String board){
        Bukkit.getScheduler().runTaskLater(KothPlugin.getPlugin(), new Runnable(){
            @Override
            public void run() {
                be.maximvdw.featherboard.api.FeatherBoardAPI.showScoreboard(player, board);
                //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fb show "+player.getName()+" "+board);
            }
        }, 1);
    }
    
    public void resetBoard(final Player player, final String board){
        Bukkit.getScheduler().runTaskLater(KothPlugin.getPlugin(), new Runnable(){
            @Override
            public void run() {
                be.maximvdw.featherboard.api.FeatherBoardAPI.removeScoreboardOverride(player, board);
                be.maximvdw.featherboard.api.FeatherBoardAPI.resetDefaultScoreboard(player);
                //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fb hide "+player.getName()+" "+board);
            }
        }, 1);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public int getRange() {
        return range;
    }

    public int getRangeMargin() {
        return rangeMargin;
    }

    public String getBoard() {
        return board;
    }
}
