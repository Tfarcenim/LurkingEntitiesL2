package tfar.lurkingentitiesl2.client.render;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LurkingEyesLayer<T extends Entity, M extends EntityModel<T>> extends EyesLayer<T, M> {


    private final RenderType texture;

    public static<T extends Entity, M extends EntityModel<T>> EyesLayer<T, M> create(RenderLayerParent<T, M> renderer, ResourceLocation texture) {
        return new LurkingEyesLayer<>(renderer,texture);
    }

    public LurkingEyesLayer(RenderLayerParent<T, M> renderer, ResourceLocation texture) {
        super(renderer);
        this.texture = RenderType.eyes(texture);
    }

    @Override
    public RenderType renderType() {
        return texture;
    }
}
