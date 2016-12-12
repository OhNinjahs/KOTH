package subside.plugins.koth;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
    private static ConfigHandler cfgHandler;
    
	private Global global;
	private Loot loot;
	private Koth koth;
	private Scoreboard scoreboard;
	private Hooks hooks;
	
	public ConfigHandler(FileConfiguration cfg){
		cfgHandler = this;
        
		global = new Global(cfg.getConfigurationSection("global"));
		loot = new Loot(cfg.getConfigurationSection("loot"));
		koth = new Koth(cfg.getConfigurationSection("koth"));
		scoreboard = new Scoreboard(cfg.getConfigurationSection("scoreboard"));
		hooks = new Hooks(cfg.getConfigurationSection("hooks"));
	}

	public class Global {
	    private String timeZone = "Europe/Amsterdam";
        private int minuteOffset = 0;
        private int startWeekMinuteOffset = 0;
	    private boolean usePlayerMoveEvent = false;
	    private int preBroadcast = 0;
	    private int noCapBroadcastInterval = 30;
	    private List<String> helpCommand = null;
	    private boolean useFancyPlayerName = false;
	    private boolean debug = false;
	    
	    public Global(ConfigurationSection section){
	        timeZone = section.getString("schedule-timezone");
            minuteOffset = section.getInt("minuteoffset");
            startWeekMinuteOffset = section.getInt("startweekminuteoffset");
	        usePlayerMoveEvent = section.getBoolean("use-playermoveevent");
	        preBroadcast = section.getInt("pre-broadcast");
	        noCapBroadcastInterval = section.getInt("nocap-broadcast-interval");
	        helpCommand = section.getStringList("helpcommand");
	        useFancyPlayerName = section.getBoolean("fancyplayername");
	        debug = section.getBoolean("debug");
	    }

		public String getTimeZone() {
			return timeZone;
		}

		public int getMinuteOffset() {
			return minuteOffset;
		}

		public int getStartWeekMinuteOffset() {
			return startWeekMinuteOffset;
		}

		public boolean isUsePlayerMoveEvent() {
			return usePlayerMoveEvent;
		}

		public int getPreBroadcast() {
			return preBroadcast;
		}

		public int getNoCapBroadcastInterval() {
			return noCapBroadcastInterval;
		}

		public List<String> getHelpCommand() {
			return helpCommand;
		}

		public boolean isUseFancyPlayerName() {
			return useFancyPlayerName;
		}

		public boolean isDebug() {
			return debug;
		}
	}
	
	public class Hooks {
	    private boolean vanishNoPacket = true;
	    private boolean factions = true;
	    private boolean kingdoms = true;
	    private Featherboard featherboard;
	    
	    public Hooks(ConfigurationSection section){
            vanishNoPacket = section.getBoolean("vanishnopacket");
            factions = section.getBoolean("factions");
            kingdoms = section.getBoolean("kingdoms");
            featherboard = new Featherboard(section.getConfigurationSection("featherboard"));
	    }

		public boolean isKingdoms() {
			return kingdoms;
		}

		public Featherboard getFeatherboard() {
			return featherboard;
		}

		public boolean isFactions() {
			return factions;
		}

		public boolean isVanishNoPacket() {
			return vanishNoPacket;
		}

		public class Featherboard {
	        private boolean enabled = false;
	        private int range = 100;
	        private int rangeMargin = 5;
	        private String board = "KoTH";
	        
	        public Featherboard(ConfigurationSection section){
	            enabled = section.getBoolean("enabled");
	            range = section.getInt("range");
	            rangeMargin = section.getInt("rangemargin");
	            board = section.getString("board");
	        }

			public int getRangeMargin() {
				return rangeMargin;
			}

			public String getBoard() {
				return board;
			}

			public int getRange() {
				return range;
			}

			public boolean isEnabled() {
				return enabled;
			}
		}
	}
	
	public class Loot {

	    private String defaultLoot = "";
	    private boolean randomizeLoot = true;
	    private int lootAmount = 5;
	    private boolean randomizeStackSize = false;
	    private boolean useItemsMultipleTimes = true;
	    private long removeLootAfterSeconds = 0;
	    private boolean dropLootOnRemoval = false;
	    private boolean instantLoot = false;

        private boolean cmdEnabled = false;
        private boolean cmdIngame = false;
        private boolean cmdNeedOp = true;
	    
	    
	    public Loot(ConfigurationSection section){
	        defaultLoot = section.getString("default");
	        randomizeLoot = section.getBoolean("randomize");
	        lootAmount = section.getInt("default-amount");
	        randomizeStackSize = section.getBoolean("randomize-stacksize");
	        useItemsMultipleTimes = section.getBoolean("can-use-same-items");
	        removeLootAfterSeconds = section.getInt("remove-after");
	        dropLootOnRemoval = section.getBoolean("drop-on-removal");
	        instantLoot = section.getBoolean("give-instantly");

            cmdEnabled = section.getBoolean("commands.enabled");
            cmdNeedOp = section.getBoolean("commands.needop");
            cmdIngame = section.getBoolean("commands.changeingame");
	    }

		public boolean isCmdNeedOp() {
			return cmdNeedOp;
		}

		public boolean isCmdIngame() {
			return cmdIngame;
		}

		public boolean isCmdEnabled() {
			return cmdEnabled;
		}

		public boolean isInstantLoot() {
			return instantLoot;
		}

		public boolean isDropLootOnRemoval() {
			return dropLootOnRemoval;
		}

		public long getRemoveLootAfterSeconds() {
			return removeLootAfterSeconds;
		}

		public boolean isUseItemsMultipleTimes() {
			return useItemsMultipleTimes;
		}

		public boolean isRandomizeStackSize() {
			return randomizeStackSize;
		}

		public int getLootAmount() {
			return lootAmount;
		}

		public boolean isRandomizeLoot() {
			return randomizeLoot;
		}

		public String getDefaultLoot() {
			return defaultLoot;
		}
	}
	
	public class Koth {
	    private int knockTime = 0;
	    private int minimumPlayersNeeded = 0;
	    
	    public Koth(ConfigurationSection section){
	        knockTime = section.getInt("knockTime");
	        minimumPlayersNeeded = section.getInt("minimum-players");
	    }

		public int getKnockTime() {
			return knockTime;
		}

		public int getMinimumPlayersNeeded() {
			return minimumPlayersNeeded;
		}
	}
	
	public class Scoreboard {
	    private boolean useScoreboard = true;
	    private boolean useOldScoreboard = false;
	    private ConfigurationSection section;
	    
	    public Scoreboard(ConfigurationSection section){
	        useScoreboard = section.getBoolean("use-scoreboard");
	        useOldScoreboard = section.getBoolean("use-old-scoreboard");
	        this.section = section;
	    }
		public boolean isUseScoreboard() {
			return useScoreboard;
		}

		public boolean isUseOldScoreboard() {
			return useOldScoreboard;
		}
	    public ConfigurationSection getSection(){
	        return section;
	    }

	}

	public static ConfigHandler getCfgHandler() {
		return cfgHandler;
	}

	public Global getGlobal() {
		return global;
	}

	public Loot getLoot() {
		return loot;
	}

	public Koth getKoth() {
		return koth;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public Hooks getHooks() {
		return hooks;
	}
}
