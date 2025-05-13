package tfar.lurkingentitiesl2.entity.goals;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class StareAtPlayerGoal extends Goal {
    protected final Mob mob;
    @Nullable
    protected Entity lookAt;
    protected final float lookDistance;
    private int lookTime;
    private final boolean onlyHorizontal;
    protected final Class<? extends LivingEntity> lookAtType;
    protected final TargetingConditions lookAtContext;


    public StareAtPlayerGoal(Mob mob, Class<? extends LivingEntity> lookAtType, float lookDistance) {
        this(mob, lookAtType, lookDistance,false);
    }

    public StareAtPlayerGoal(Mob mob, Class<? extends LivingEntity> lookAtType, float lookDistance, boolean onlyHorizontal) {
        this.mob = mob;
        this.lookAtType = lookAtType;
        this.lookDistance = lookDistance;
        this.onlyHorizontal = onlyHorizontal;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        if (lookAtType == Player.class) {
            this.lookAtContext = TargetingConditions.forNonCombat()
                    .range(lookDistance)
                    .selector(p_25531_ -> EntitySelector.notRiding(mob).test(p_25531_));
        } else {
            this.lookAtContext = TargetingConditions.forNonCombat().range(lookDistance);
        }
    }

    @Override
    public boolean canUse() {
        if (this.mob.getTarget() != null) {
            this.lookAt = this.mob.getTarget();
        }

        if (this.lookAtType == Player.class) {
            this.lookAt = this.mob.level().getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.lookAt = this.mob
                    .level()
                    .getNearestEntity(
                            this.mob
                                    .level()
                                    .getEntitiesOfClass(
                                            this.lookAtType,
                                            this.mob.getBoundingBox().inflate(this.lookDistance, 3.0, this.lookDistance),
                                            p_148124_ -> true
                                    ),
                            this.lookAtContext,
                            this.mob,
                            this.mob.getX(),
                            this.mob.getEyeY(),
                            this.mob.getZ()
                    );
        }

        return this.lookAt != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (!this.lookAt.isAlive()) {
            return false;
        } else {
            return !(this.mob.distanceToSqr(this.lookAt) > (double) (this.lookDistance * this.lookDistance)) && this.lookTime > 0;
        }
    }

    @Override
    public void start() {
        this.lookTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
    }

    @Override
    public void stop() {
        this.lookAt = null;
    }

    @Override
    public void tick() {
        if (this.lookAt.isAlive()) {
            double d0 = this.onlyHorizontal ? this.mob.getEyeY() : this.lookAt.getEyeY();
            this.mob.getLookControl().setLookAt(this.lookAt.getX(), d0, this.lookAt.getZ());
            this.lookTime--;
        }
    }
   }