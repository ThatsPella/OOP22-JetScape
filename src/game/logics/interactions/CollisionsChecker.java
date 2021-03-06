package game.logics.interactions;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import game.logics.entities.generic.Entity;
import game.logics.entities.player.PlayerInstance;
import game.logics.hitbox.Hitbox;
import game.utility.other.EntityType;

/**
 * The {@link CollisionsChecker} class helps {@link CollisionsHandler} to detect
 * collisions between player and other entities.
 */ 
public class CollisionsChecker {
    private final Map<EntityType, Set<Entity>> entities;
    private final Hitbox player;
    /**
     * collisions queue of hits to handle.
     */
    private final Queue<Entity> collisions;

    /**
     * initializes the queue that stores collisions between the 
     * {@link PlayerInstance}  p and {@link Entity}.
     * @param entities
     * @param p
     */
    public CollisionsChecker(final Map<EntityType, Set<Entity>> entities, final PlayerInstance p) {
        super();
        this.entities = entities;
        this.collisions = new  LinkedBlockingQueue<>();
        this.player = p.getHitbox();
    }

    /**
     * @return Optional of the fist entity hit else Optional empty 
     */
    public Optional<Entity> getNextToHandle() {
        return Optional.ofNullable(this.collisions.poll());
    }

    /**
     * if there are some contacts, adds in the collisions queue all 
     * the entities that are touching the player .
     */
    public void updateCollisions() {
        this.entities.forEach((type, entities) -> {
            if (!type.equals(EntityType.PLAYER)) {
                entities.forEach(entity -> {
                    if (this.collides(entity.getHitbox())) {
                        this.collisions.add(entity);
                    }
                });
            }
        });
    }


    /**
     * @param entity {@link Hitbox}
     * @return true if the entity's {@link Hitbox} collides with the player one
     */
    private boolean collides(final Hitbox entity) {
        return this.player.getRectangles().stream().filter(player -> entity.getRectangles()
                .stream().filter(target -> player.intersects(target))
                .findFirst().isPresent())
                .findFirst().isPresent();
    }
}
