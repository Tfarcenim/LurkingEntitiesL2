package tfar.lurkingentitiesl2;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import tfar.lurkingentitiesl2.init.ModEntityTypes;

public class ModDatagen {

    static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookup = event.getLookupProvider();
        generator.addProvider(true,new ModDataPackProvider(output,lookup));
        generator.addProvider(event.includeClient(),new ModLang(output));
    }

    static class ModLang extends LanguageProvider {

        public ModLang(PackOutput output) {
            super(output,LurkingEntitiesL2.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(ModEntityTypes.LURKING_CHICKEN,"Lurking Chicken");
            add(ModEntityTypes.LURKING_COW,"Lurking Cow");
            add(ModEntityTypes.LURKING_PIG,"Lurking Pig");
            add(ModEntityTypes.LURKING_SHEEP,"Lurking Sheep");
        }
    }

}
