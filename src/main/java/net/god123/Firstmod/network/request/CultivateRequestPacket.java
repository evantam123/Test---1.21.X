package net.god123.Firstmod.network.request;

import net.god123.Firstmod.cultivationrealm.CultivateHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CultivateRequestPacket() implements CustomPacketPayload {

    public static final Type<CultivateRequestPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("god123awsomemod", "cultivate_request"));

    public static final StreamCodec<FriendlyByteBuf, CultivateRequestPacket> STREAM_CODEC = StreamCodec.of(
            (buf, payload) -> {

            },
            buf -> new CultivateRequestPacket()
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) iPayloadContext.player();
            CultivateHandler.toggleCultivate(player);
        });
    }
}
