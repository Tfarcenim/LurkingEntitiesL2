package tfar.lurkingentitiesl2.client.render;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SheepRenderer;
import tfar.lurkingentitiesl2.LurkingEntitiesL2;

public class LurkingChickenRenderer extends ChickenRenderer {


    public LurkingChickenRenderer(EntityRendererProvider.Context p_174366_) {
        super(p_174366_);
        addLayer(LurkingEyesLayer.create(this,LurkingEntitiesL2.id("textures/entity/chicken_eyes.png")));
    }
}
