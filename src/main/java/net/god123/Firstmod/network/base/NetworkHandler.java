package net.god123.Firstmod.network.base;

import net.god123.Firstmod.Firstmod;
import net.god123.Firstmod.network.request.CultivateRequestPacket;
import net.god123.Firstmod.network.request.RealmUpRequestPacket;
import net.god123.Firstmod.network.response.RealmUpResponsePacket;
import net.god123.Firstmod.network.sync.CultivationDataSyncPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Firstmod.MODID)
public class NetworkHandler {
    
    @SubscribeEvent
    public static void registerPackets(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Firstmod.MODID);
        
        registrar.playToServer(
                RealmUpRequestPacket.TYPE,
                RealmUpRequestPacket.STREAM_CODEC,
                RealmUpRequestPacket::handle
        );

        registrar.playToServer(
                CultivateRequestPacket.TYPE,
                CultivateRequestPacket.STREAM_CODEC,
                CultivateRequestPacket::handle
        );

        registrar.playToClient(
                RealmUpResponsePacket.TYPE,
                RealmUpResponsePacket.STREAM_CODEC,
                RealmUpResponsePacket::handle
        );

        registrar.playToClient(
                CultivationDataSyncPacket.TYPE,
                CultivationDataSyncPacket.STREAM_CODEC,
                CultivationDataSyncPacket::handle
        );
    }
}
