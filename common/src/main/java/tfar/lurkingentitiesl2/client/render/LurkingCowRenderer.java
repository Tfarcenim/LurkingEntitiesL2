package tfar.lurkingentitiesl2.client.render;

import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SheepRenderer;
import tfar.lurkingentitiesl2.LurkingEntitiesL2;

public class LurkingCowRenderer extends CowRenderer {


    public LurkingCowRenderer(EntityRendererProvider.Context p_174366_) {
        super(p_174366_);
        addLayer(LurkingEyesLayer.create(this,LurkingEntitiesL2.id("textures/entity/cow_eyes.png")));
    }
}
