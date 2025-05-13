package tfar.lurkingentitiesl2;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import tfar.lurkingentitiesl2.init.ModEntityTypes;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModDataPackProvider::biomeModifiers);


    public ModDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(LurkingEntitiesL2.MOD_ID));
    }

    public static void biomeModifiers(BootstrapContext<BiomeModifier> context) {

        var holders = context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);

        List<? extends EntityType<? extends Animal>> lurkingChicken = List.of(ModEntityTypes.LURKING_CHICKEN,
                ModEntityTypes.LURKING_COW, ModEntityTypes.LURKING_PIG, ModEntityTypes.LURKING_SHEEP);
        context.register(LurkingEntitiesL2FNeoforge.OVERWORLD_SPAWN,new BiomeModifiers.AddSpawnsBiomeModifier(holders,
                            lurkingChicken.stream().map(entityType -> new MobSpawnSettings.SpawnerData(entityType,250,1,1)).toList()));


    }
}
