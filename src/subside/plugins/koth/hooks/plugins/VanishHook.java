package subside.plugins.koth.hooks.plugins;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kitteh.vanish.VanishManager;
import org.kitteh.vanish.VanishPlugin;

import subside.plugins.koth.ConfigHandler;
import subside.plugins.koth.hooks.AbstractHook;
import subside.plugins.koth.utils.Utils;

public class VanishHook extends AbstractHook {
    private boolean enabled = false;
    private VanishManager vanishManager;
    
    public VanishHook(){
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("VanishNoPacket")){
            if(ConfigHandler.getCfgHandler().getHooks().isVanishNoPacket()){
                enabled = true;
            }
            vanishManager = ((VanishPlugin)Bukkit.getServer().getPluginManager().getPlugin("VanishNoPacket")).getManager();
        }
        Utils.log("Vanish hook: "+(enabled?"Enabled":"Disabled"));
    }
    
    @Override
    public boolean canCap(Player player) {
        return !vanishManager.isVanished(player);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
