package tfar.lurkingentitiesl2.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import tfar.lurkingentitiesl2.entity.goals.BeingLookedAtGoal;
import tfar.lurkingentitiesl2.entity.goals.LookforPlayerGoal;
import tfar.lurkingentitiesl2.entity.goals.StareAtPlayerGoal;

public class LurkingChicken extends Chicken implements Enemy, CanLookAt{
    private boolean staredAt;


    public LurkingChicken(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createLurkingAttributes() {
        return Chicken.createAttributes().add(Attributes.ATTACK_DAMAGE, 5).add(Attributes.MOVEMENT_SPEED, .5).add(Attributes.FOLLOW_RANGE, 60);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new StareAtPlayerGoal(this, Player.class, 16));
        this.targetSelector.addGoal(1, new BeingLookedAtGoal<>(this, e -> true));
        this.targetSelector.addGoal(2, new LookforPlayerGoal<>(this, e -> staredAt));
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        if (living == null) {
            setStaredAt(false);
            setSprinting(false);
        }
        super.setTarget(living);
    }

    void sound() {
        if (!level().isClientSide) {
            discard();
            playSound(SoundEvents.AMBIENT_CAVE.value(),5,1);
        }
    }

    boolean chase;

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (!chase && staredAt) {
                chase = random.nextBoolean();
                if (!chase) {
                    discard();
                } else {
                    setSprinting(true);
                }
                sound();
            }
        }
    }

    @Override
    public void setStaredAt(boolean staredAt) {
        this.staredAt = staredAt;
    }

    @Override
    public boolean isStaredAt() {
        return staredAt;
    }
}
