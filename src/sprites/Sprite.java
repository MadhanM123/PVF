package sprites;

import java.awt.Graphics;
import java.awt.Image;

import music.MusicPlayer;

/**
 * Abstract base class for all sprites that holds coordinates, health, damage, attack rate, and counters
 * @author Madhan M.
 * @version 2023-05-28
 */
public abstract class Sprite{
    private int gridX;
    private int gridY;
    
    private int screenX;
    private int screenY;

    private int health;
    private int damage;
    private int attackRate;

    private int idleCounter;
    private int deathCounter;
    private int actionAniCounter;
    private int attackCounter;

    /**
     * All sprite states
     * {@link #IDLE}
     * {@link #ACTION}
     * {@link #DEATH}
     * {@link #REST}
     */
    public enum State{
        /**
         * Idle for plants, moving for zombies/projectiles
         */
        IDLE,

        /**
         * Attacking for plants and zombies
         */
        ACTION,

        /**
         * Death for plants and zombies, on collision for projectiles
         */
        DEATH,

        /**
         * Battling for walnuts and zombies
         */
        REST
    };

    private boolean doneDeath;

    private State prev;

    private Image currentImg;

    private MusicPlayer mp;

    /**
     * {@value #TILE_SIZE} - Length of Tile
     */
    public static final int TILE_SIZE = 110;

    /**
     * Initializes grid/screen coordinates, health, damage, and attack rate
     * @param gridX grid x-coordinate starting from left
     * @param gridY grid y-coordinate starting from top
     * @param screenX screen x-coordinate starting from left
     * @param screenY screen y-coordinate starting from top
     * @param health current health of sprite
     * @param damage attack damage
     * @param attackRate rate of attack
     */
    public Sprite(int gridX, int gridY, int screenX, int screenY, int health, int damage, int attackRate){
        this.gridX = gridX;
        this.gridY = gridY;
        this.screenX = screenX;
        this.screenY = screenY;
        this.health = health;
        this.damage = damage;
        this.attackRate = attackRate;
        this.doneDeath = false;
        this.actionAniCounter = 0;
        this.idleCounter = 0;
        this.deathCounter = 0;
        this.attackCounter = 0;
        this.mp = new MusicPlayer();
    }

    /**
     * Updates sprites based on state and ticks counters
     * @param state Current state
     */
    public abstract void update(State state);
    
    /**
     * Draws sprite onto given graphics
     * @param g Graphics context to draw
     */
    public abstract void draw(Graphics g);

    /**
     * Gets current screen x-coordinate
     * @return screen x-coordinate
     */
    public int getScreenX(){
        return screenX;
    }

    /**
     * Gets current screen y-coordinate
     * @return screen y-coordinate
     */
    public int getScreenY(){
        return screenY;
    }

    /**
     * Gets current grid x-coordiante
     * @return grid x-coordinate
     */
    public int getGridX(){
        return gridX;
    }

    /**
     * Gets current grid y-coordinate
     * @return grid y-coordinate
     */
    public int getGridY(){
        return gridY;
    }

    /**
     * Sets current screen x-coordinate
     * @param newX screen x-coordinate
     */
    public void setScreenX(int newX){
        screenX = newX;
    }

    /**
     * Sets current screen y-coordinate
     * @param newY nscreen y-coordinate
     */
    public void setScreenY(int newY){
        screenY = newY;
    }

    /**
     * Sets current grid x-coordinate
     * @param newX grid x-coordinate
     */
    public void setGridX(int newX){
        gridX = newX;
    }

    /**
     * Sets current grid y-coordinate
     * @param newY grid y-coordinate
     */
    public void setGridY(int newY){
        gridY = newY;
    }

    /**
     * Returns true if state changed and then updates previous state, returns false if state is same as previous state
     * @param s new state
     * @return True if state is different from previous state, false if state is same as previous state
     */
    public boolean comparePrevState(State s){
        if(s == prev){
            return false;
        }
        else{
            prev = s;
            return true;
        }
    }

    /**
     * Getter for current health
     * @return current health
     */
    public int getHealth(){
        return health;
    }

    /**
     * Modifier for current health
     * @param dec amount to reduce by
     */
    public void reduceHealth(int dec){
        this.health -= dec;
    }

    /**
     * Getter for damage
     * @return amount of damage
     */
    public int getDamage(){
        return damage;
    }

    /**
     * Getter for current image
     * @return current image
     */
    public Image getCurrentImg(){
        return currentImg;
    }

    /**
     * Setter for current image
     * @param i new image
     */
    public void setCurrentImg(Image i){
        this.currentImg = i;
    }

    /**
     * Getter for whether death animation is done
     * @return True if death is done, else false
     */
    public boolean getDoneDeath(){
        return doneDeath;
    }

    /**
     * Setter for whether death animation is done
     * @param d Sets whether death is done
     */
    public void setDoneDeath(boolean d){
        doneDeath = d;
    }

    /**
     * Ticks idle count by 1
     */
    public void tickIdleCounter(){
        idleCounter++;
    }

    /**
     * Getter for current idle count
     * @return idle count
     */
    public int getIdleCounter(){
        return idleCounter;
    }

    /**
     * Zeroes idle count
     */
    public void zeroIdleCounter(){
        idleCounter = 0;
    }

    /**
     * Ticks attack rate count by 1
     */
    public void tickAttackCounter(){
        attackCounter++;
    }

    /**
     * Getter for current attack rate count
     * @return attack rate count
     */
    public int getAttackCounter(){
        return attackCounter;
    }

    /**
     * Zeroes attack rate count
     */
    public void zeroAttackCounter(){
        attackCounter = 0;
    }

    /**
     * Ticks action animation count by 1
     */
    public void tickActionAniCounter(){
        actionAniCounter++;
    }

    /**
     * Getter for current action animation count
     * @return action animation count
     */
    public int getActionAniCounter(){
        return actionAniCounter;
    }

    /**
     * Zeroes action animation count
     */
    public void zeroActionAniCounter(){
        actionAniCounter = 0;
    }

    /**
     * Ticks death count by 1
     */
    public void tickDeathCounter(){
        deathCounter++;
    }

    /**
     * Getter for death count
     * @return death count
     */
    public int getDeathCounter(){
        return deathCounter;
    }

    /**
     * Zeroes death count
     */
    public void zeroDeathCounter(){
        deathCounter = 0;
    }

    /**
     * Checks if sprite should attack based on attack rate
     * @return True if sprite should attack, else false
     */
    public boolean attackReady(){
        attackCounter++;
        return attackCounter > attackRate;
    }

    /**
     * Sets a clip to play
     * @param ind Index of clip to select
     */
    public void setClip(int ind){
        mp.setFile(ind);
    }

    /**
     * Plays current clip
     */
    public void playClip(){
        mp.play();
    }
}