package tfar.lurkingentitiesl2.entity.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;

public class DummyEatBlockGoal extends EatBlockGoal {
    public DummyEatBlockGoal(Mob mob) {
        super(mob);
    }

    @Override
    public boolean canUse() {
        return false;
    }
}
