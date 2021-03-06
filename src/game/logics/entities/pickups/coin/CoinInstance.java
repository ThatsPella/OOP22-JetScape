package game.logics.entities.pickups.coin;

import java.awt.Color;

import game.logics.entities.pickups.generic.PickupInstance;
import game.logics.entities.player.Player;
import game.logics.handler.Logics;
import game.logics.hitbox.CoinHitbox;
import game.logics.interactions.SpeedHandler;
import game.utility.other.EntityType;
import game.utility.other.Pair;

/**
 * The class {@code CoinInstance} is used for defining a coin pickup in the environment.
 * 
 * A coin is a bonus item that can be picked up by the player granting the
 * possibility to buy items in your favor at the store.
 */
public class CoinInstance extends PickupInstance implements Coin {

    /**
     * Specifies the path within the sprite folder [specified in {@link game.utility.sprites.Sprite Sprite} class]
     * where {@link CoinInstance} sprites can be found.
     */
    private static final String SPRITE_PATH = "coin" + System.getProperty("file.separator");
    /**
     * If sprites are missing, they will be replace by a rectangle of the color specified in
     * <code>{@link CoinInstance}.PLACE_HOLDER</code>.
     */
    private static final Color PLACE_HOLDER = Color.yellow;

    /**
     * @param l the logics handler which the entity is linked to
     * @param position the starting position of the pickup in the environment
     * @param player a reference to the {@link Player} entity
     * @param speed the {@link SpeedHandler} to use for the pickup
     */
    public CoinInstance(final Logics l, final Pair<Double, Double> position, final Player player, final SpeedHandler speed) {
        super(l, position, EntityType.COIN, player, speed);

        final var spritesMgr = this.getSpriteManager();
        spritesMgr.setPlaceH(PLACE_HOLDER);
        spritesMgr.addSprite("coin", SPRITE_PATH + "coin.png");
        spritesMgr.setAnimator(() -> "coin");
        super.setHitbox(new CoinHitbox(position));
    }
}
