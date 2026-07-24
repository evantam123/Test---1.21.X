package net.god123.Firstmod.network.request;

import net.god123.Firstmod.cultivationrealm.CultivationRealmData;
import net.god123.Firstmod.network.response.RealmUpResponsePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record RealmUpRequestPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RealmUpRequestPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("god123awsomemod", "realm_up_request"));

    public static final StreamCodec<FriendlyByteBuf, RealmUpRequestPacket> STREAM_CODEC = StreamCodec.of(
            (buf, payload) -> {

            },
            buf -> new RealmUpRequestPacket()
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) iPayloadContext.player();
            CultivationRealmData.CultivationRealm data = player.getData(CultivationRealmData.CULTIVATION_REALM);
            boolean success = data.RealmUp();
            String realmName = data.getRealmName();
            String realmId = data.getRealm().name();
            iPayloadContext.reply(new RealmUpResponsePacket(success, realmName, realmId));
        });
    }
}
