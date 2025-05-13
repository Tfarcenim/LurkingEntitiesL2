package tfar.lurkingentitiesl2;


import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import tfar.lurkingentitiesl2.entity.LurkingChicken;
import tfar.lurkingentitiesl2.entity.LurkingCow;
import tfar.lurkingentitiesl2.entity.LurkingPig;
import tfar.lurkingentitiesl2.entity.LurkingSheep;
import tfar.lurkingentitiesl2.init.ModEntityTypes;

@Mod(LurkingEntitiesL2.MOD_ID)
public class LurkingEntitiesL2FNeoforge {

    public static final ResourceKey<BiomeModifier> OVERWORLD_SPAWN = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            LurkingEntitiesL2.id("overworld_spawn"));

    public LurkingEntitiesL2FNeoforge(IEventBus eventBus, Dist dist) {
        eventBus.addListener(this::register);
        eventBus.addListener(this::attributes);
        eventBus.addListener(ModDatagen::gather);
        eventBus.addListener(this::placements);
        if (dist.isClient()) {
            LurkingEntitiesL2ClientNeoforge.init(eventBus);
        }

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        LurkingEntitiesL2.init();
    }

    void placements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntityTypes.LURKING_COW, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                LurkingEntitiesL2FNeoforge::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntityTypes.LURKING_CHICKEN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                LurkingEntitiesL2FNeoforge::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntityTypes.LURKING_PIG, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                LurkingEntitiesL2FNeoforge::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntityTypes.LURKING_SHEEP, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                LurkingEntitiesL2FNeoforge::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    /**
     * Static predicate for determining whether a monster can spawn at the provided location, incorporating a check of the current light level at the location.
     */
    public static boolean checkMonsterSpawnRules(
            EntityType<? extends Mob> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random
    ) {
        return level.getDifficulty() != Difficulty.PEACEFUL
                && (MobSpawnType.ignoresLightRequirements(spawnType) || Monster.isDarkEnoughToSpawn(level, pos, random))
                && Monster.checkMobSpawnRules(type, level, spawnType, pos, random);
    }

    void register(RegisterEvent event) {
        if (event.getRegistry() == BuiltInRegistries.BLOCK) {
            ModEntityTypes.init();
        }
    }

    void attributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.LURKING_CHICKEN, LurkingChicken.createLurkingAttributes().build());
        event.put(ModEntityTypes.LURKING_COW, LurkingCow.createLurkingAttributes().build());
        event.put(ModEntityTypes.LURKING_PIG, LurkingPig.createLurkingAttributes().build());
        event.put(ModEntityTypes.LURKING_SHEEP, LurkingSheep.createLurkingAttributes().build());
    }
}