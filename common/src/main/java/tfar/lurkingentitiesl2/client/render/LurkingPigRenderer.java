
package tfar.lurkingentitiesl2.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PigRenderer;
import tfar.lurkingentitiesl2.LurkingEntitiesL2;

public class LurkingPigRenderer extends PigRenderer {


    public LurkingPigRenderer(EntityRendererProvider.Context p_174366_) {
        super(p_174366_);
        addLayer(LurkingEyesLayer.create(this,LurkingEntitiesL2.id("textures/entity/pig_eyes.png")));
    }
}
