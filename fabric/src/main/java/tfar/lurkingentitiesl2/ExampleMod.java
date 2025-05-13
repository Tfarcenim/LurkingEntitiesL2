package tfar.lurkingentitiesl2;

import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        LurkingEntitiesL2.LOG.info("Hello Fabric world!");
        LurkingEntitiesL2.init();
    }
}
