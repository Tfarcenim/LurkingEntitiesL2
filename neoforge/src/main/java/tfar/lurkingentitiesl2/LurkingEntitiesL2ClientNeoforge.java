package tfar.lurkingentitiesl2;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import tfar.lurkingentitiesl2.client.LurkingEntitiesL2Client;

public class LurkingEntitiesL2ClientNeoforge {
    static void init(IEventBus bus) {
        bus.addListener(LurkingEntitiesL2ClientNeoforge::renderers);
    }

    static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        LurkingEntitiesL2Client.renderers();
    }
}
