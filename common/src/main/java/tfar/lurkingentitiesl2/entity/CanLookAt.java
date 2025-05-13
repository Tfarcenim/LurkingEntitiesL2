package tfar.lurkingentitiesl2.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public interface CanLookAt {
    default boolean isLookingAtMe(Player player) {
        return beingLookedAt((Entity) this,player);
    }
    void setStaredAt(boolean staredAt);
    boolean isStaredAt();

    static boolean beingLookedAt(Entity thisEntity, Player player) {
            Vec3 vec3 = player.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(thisEntity.getX() - player.getX(), thisEntity.getEyeY() - player.getEyeY(), thisEntity.getZ() - player.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);
            return d1 > 1.0D - 0.025D / d0 && player.hasLineOfSight(thisEntity);
    }
}
