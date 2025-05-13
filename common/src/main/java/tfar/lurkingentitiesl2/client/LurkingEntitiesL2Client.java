package tfar.lurkingentitiesl2.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import tfar.lurkingentitiesl2.client.render.LurkingChickenRenderer;
import tfar.lurkingentitiesl2.client.render.LurkingCowRenderer;
import tfar.lurkingentitiesl2.client.render.LurkingPigRenderer;
import tfar.lurkingentitiesl2.client.render.LurkingSheepRenderer;
import tfar.lurkingentitiesl2.init.ModEntityTypes;

public class LurkingEntitiesL2Client {

    public static void renderers() {
        EntityRenderers.register(ModEntityTypes.LURKING_CHICKEN, LurkingChickenRenderer::new);
        EntityRenderers.register(ModEntityTypes.LURKING_COW, LurkingCowRenderer::new);
        EntityRenderers.register(ModEntityTypes.LURKING_PIG, LurkingPigRenderer::new);
        EntityRenderers.register(ModEntityTypes.LURKING_SHEEP, LurkingSheepRenderer::new);
    }
}
