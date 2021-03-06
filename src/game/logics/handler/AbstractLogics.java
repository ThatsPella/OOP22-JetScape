package game.logics.handler;

import java.awt.Graphics2D;
import java.util.Map;

import game.logics.interactions.SpeedHandler;
import game.utility.other.EntityType;

/**
 * The {@link AbstractLogics} class defines the basic parameters of the {@link LogicsHandler} class.
 */
public abstract class AbstractLogics implements Logics {

    private static int frameTime;
    private static int difficultyLevel = 1;
    private static final int INCREASE_DIFF_PER_SCORE = 250;

    private static final double SPAWN_INTERVAL = 3.3;
    private static final double CLEAN_INTERVAL = 5.0;

    private final SpeedHandler defaultEntitySpeed = new SpeedHandler(250.0, 15.0, 0);
    private final SpeedHandler backgroundSpeed = new SpeedHandler(150.0, 10.0, 0);
    private final Map<EntityType, SpeedHandler> entitiesSpeed = 
            Map.of(EntityType.MISSILE, new SpeedHandler(500.0, 10.0, 5000.0));

    static void setDifficultyLevel(final int newDifficultyLevel) {
        AbstractLogics.difficultyLevel = newDifficultyLevel;
    }

    static void updateTimer() {
        AbstractLogics.frameTime++;
    }
    /**
     * @return the current difficulty level
     */
    public static int getDifficultyLevel() {
        return AbstractLogics.difficultyLevel;
    }
    /**
     * @return how often difficulty should increase
     */
    public static int getIncreaseDiffPerScore() {
        return AbstractLogics.INCREASE_DIFF_PER_SCORE;
    }
    /**
     * @return the total number of frames passed since the game begin his execution
     */
    public static int getFrameTime() {
        return AbstractLogics.frameTime;
    }

    /**
     * @return how many seconds have to pass for the spawner to generate
     * another set of obstacles.
     */
    protected static double getSpawningInteval() {
        return AbstractLogics.SPAWN_INTERVAL;
    }
    /**
     * @return the interval of each check for entities to clean.
     */
    protected static double getCleanerActivityInterval() {
        return AbstractLogics.CLEAN_INTERVAL;
    }
    /**
     * @param type the {@link EntityType} of the required entity
     * @return the movement information for the type of entity specified
     */
    protected SpeedHandler getEntityMovementInfo(final EntityType type) {
        if (this.entitiesSpeed.containsKey(type)) {
            return this.entitiesSpeed.get(type);
        }
        return this.defaultEntitySpeed.copy();
    }
    /**
     * @return the movement information for the background
     */
    protected SpeedHandler getBackgroundMovementInfo() {
        return this.backgroundSpeed.copy();
    }

    /**
     * {@inheritDoc}
     */
    public abstract void updateAll();

    /**
     * {@inheritDoc}
     */
    public abstract void drawAll(Graphics2D g);

}
