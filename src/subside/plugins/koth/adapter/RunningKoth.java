package subside.plugins.koth.adapter;

import subside.plugins.koth.adapter.captypes.Capper;
import subside.plugins.koth.utils.MessageBuilder;

/**
 * @author Thomas "SubSide" van den Bulk
 *
 */
public interface RunningKoth {

    /** initializer, enforcing every implementation to have this makes it possible
     *  to dynamicly create games
     * 
     */
    @Deprecated
    public void init(StartParams params);
    
    /** Get the TimeObject for the running KoTH
     * 
     * @return          The TimeObject
     */
    public TimeObject getTimeObject();
    
    /** Get the KoTH that is currently running
     * 
     * @return          The KoTH object
     */
    public Koth getKoth();
    
    /** Get the current capper of the KoTH (this is for API reasons)
     * 
     * @return          The current capper
     */
    public Capper getCapper();
    
    /** Get the lootchest for the KoTH that is currently running
     * 
     * @return          The Loot object
     */
    public String getLootChest();
    
    /** Add data to the MessageBuilder
     * 
     * @return
     */
    public MessageBuilder fillMessageBuilder(MessageBuilder mB);

    @Deprecated
    public void update();
    
    /** End the KoTH with a reason
     * 
     * @return
     */
    public void endKoth(EndReason reason);


    public enum EndReason {
        FORCED, GRACEFUL, TIMEUP, WON, CUSTOM
    }
}
