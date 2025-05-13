package tfar.lurkingentitiesl2.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;
import tfar.lurkingentitiesl2.LurkingEntitiesL2;
import tfar.lurkingentitiesl2.entity.LurkingChicken;
import tfar.lurkingentitiesl2.entity.LurkingCow;
import tfar.lurkingentitiesl2.entity.LurkingPig;
import tfar.lurkingentitiesl2.entity.LurkingSheep;

public class ModEntityTypes {

    public static final EntityType<LurkingChicken> LURKING_CHICKEN = register("lurking_chicken",EntityType.Builder.of(LurkingChicken::new, MobCategory.MONSTER)
            .sized(0.4F, 0.7F)
            .eyeHeight(0.644F)
            .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
            .clientTrackingRange(10).build(""));


    public static final EntityType<LurkingCow> LURKING_COW = register("lurking_cow",EntityType.Builder.of(LurkingCow::new, MobCategory.MONSTER)
            .sized(0.9F, 1.4F).eyeHeight(1.3F).passengerAttachments(1.36875F).clientTrackingRange(10).build(""));

    public static final EntityType<LurkingPig> LURKING_PIG = register("lurking_pig",EntityType.Builder.of(LurkingPig::new, MobCategory.MONSTER)
            .sized(0.9F, 0.9F).passengerAttachments(0.86875F).clientTrackingRange(10).build(""));

    public static final EntityType<LurkingSheep> LURKING_SHEEP = register("lurking_sheep",EntityType.Builder.of(LurkingSheep::new, MobCategory.MONSTER)
            .sized(0.9F, 1.3F).eyeHeight(1.235F).passengerAttachments(1.2375F).clientTrackingRange(10).build(""));

    private static <T extends Entity> EntityType<T> register(String key, EntityType<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, LurkingEntitiesL2.id(key), builder);
    }

    public static void init(){}
}
