package tfar.lurkingentitiesl2;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Pigs, Sheep, Cows, Chickens with white eyes which spawn in random locations, e.g when your mining a chicken with
// white eyes may spawn in your cave, or in a larger cave. (Also spawn in normal places like overworld).
public class LurkingEntitiesL2 {

    public static final String MOD_ID = "lurkingentitiesl2";
    public static final String MOD_NAME = "LurkingEntitiesL2";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {


        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.

    }

    public static ResourceLocation id(String key){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID,key);
    }
}