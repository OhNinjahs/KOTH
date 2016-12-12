package subside.plugins.koth.adapter;

import java.util.Random;

import subside.plugins.koth.ConfigHandler;
import subside.plugins.koth.exceptions.KothNotExistException;

public class StartParams {

    private Koth koth;
    private String gamemode = "classic";
    private int captureTime = 15*60;
    private int maxRunTime = -1;
    private int lootAmount = ConfigHandler.getCfgHandler().getLoot().getLootAmount();
    private String lootChest = null;
    private boolean isScheduled = false;
    private String entityType = null;
        
        public String getLootChest(){
            if(lootChest != null) return null;
            return koth.getLoot();
        }
        
        public StartParams(String kth){
        	gamemode = KothHandler.getInstance().getGamemodeRegistry().getCurrentMode();
            if (kth.equalsIgnoreCase("random")) {
                if (KothHandler.getInstance().getAvailableKoths().size() > 0) {
                    kth = KothHandler.getInstance().getAvailableKoths().get(new Random().nextInt(KothHandler.getInstance().getAvailableKoths().size())).getName();
                }
            }

            for (Koth koth : KothHandler.getInstance().getAvailableKoths()) {
                if (koth.getName().equalsIgnoreCase(kth)) {
                    this.koth = koth;
                    return;
                }
            }
            throw new KothNotExistException(kth);
        }
    public Koth getKoth() {
        return koth;
    }

    public void setKoth(Koth koth) {
        this.koth = koth;
    }

    public String getGamemode() {
        return gamemode;
    }

    public void setGamemode(String gamemode) {
        this.gamemode = gamemode;
    }

    public int getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(int captureTime) {
        this.captureTime = captureTime;
    }

    public int getMaxRunTime() {
        return maxRunTime;
    }

    public void setMaxRunTime(int maxRunTime) {
        this.maxRunTime = maxRunTime;
    }

    public int getLootAmount() {
        return lootAmount;
    }

    public void setLootAmount(int lootAmount) {
        this.lootAmount = lootAmount;
    }
    public void setLootChest(String lootChest) {
        this.lootChest = lootChest;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
