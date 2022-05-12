package game.logics.hitbox;

import game.utility.other.Pair;
import game.utility.screen.Screen;

/**
 * The <code>ZapperRayHorizontalHitbox</code> class represents a ZapperRayHorizontal's hitbox in
 * the game environment.
 * 
 * @author Giacomo Amadio
 */
public class ZapperRayHorizontalHitbox extends HitboxInstance {
    static final double rectangleX = 0;
    static final double rectangleY = 6;
    static final double rectangleW = 32;
    static final double rectangleH = 20;

    public ZapperRayHorizontalHitbox(Pair<Double, Double> startingPos, Screen gScreen) {
        super(startingPos, gScreen);
        super.addRectangle(rectangleX, rectangleY, rectangleW, rectangleH);
    }
}
