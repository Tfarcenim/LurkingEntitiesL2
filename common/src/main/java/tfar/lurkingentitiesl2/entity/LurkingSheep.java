package tfar.lurkingentitiesl2.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import tfar.lurkingentitiesl2.entity.goals.BeingLookedAtGoal;
import tfar.lurkingentitiesl2.entity.goals.DummyEatBlockGoal;
import tfar.lurkingentitiesl2.entity.goals.StareAtPlayerGoal;
import tfar.lurkingentitiesl2.entity.goals.LookforPlayerGoal;

public class LurkingSheep extends Sheep implements Enemy, CanLookAt {
    private boolean staredAt;

    public LurkingSheep(EntityType<? extends Sheep> entityType, Level level) {
        super(entityType, level);
    }

    //   private static final ResourceLocation SPRINTING_MODIFIER_ID = ResourceLocation.withDefaultNamespace("sprinting");
    //  private static final AttributeModifier SPEED_MODIFIER_SPRINTING = new AttributeModifier(
    //          SPRINTING_MODIFIER_ID, 0.3F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
    //  );

    @Override
    protected void registerGoals() {
        this.eatBlockGoal = new DummyEatBlockGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(3, new StareAtPlayerGoal(this, Player.class, 16));
        this.goalSelector.addGoal(3, this.eatBlockGoal);
        this.targetSelector.addGoal(1, new BeingLookedAtGoal<>(this, e -> true));
        this.targetSelector.addGoal(2, new LookforPlayerGoal<>(this, e -> staredAt));
    }

    public static AttributeSupplier.Builder createLurkingAttributes() {
        return Sheep.createAttributes().add(Attributes.ATTACK_DAMAGE, 5).add(Attributes.MOVEMENT_SPEED, .5).add(Attributes.FOLLOW_RANGE, 60);
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        if (living == null) {
            setStaredAt(false);
            setSprinting(false);
        }
        super.setTarget(living);
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

    void sound() {
        if (!level().isClientSide) {
            discard();
            playSound(SoundEvents.AMBIENT_CAVE.value(),5,1);
        }
    }

    static <T extends Animal & CanLookAt>void tick(T animal) {

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
