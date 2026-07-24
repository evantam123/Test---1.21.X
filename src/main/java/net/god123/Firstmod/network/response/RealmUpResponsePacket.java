package net.god123.Firstmod.network.response;

import net.god123.Firstmod.cultivationrealm.CultivationRealmData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record RealmUpResponsePacket(boolean success, String realmName, String realmId) implements CustomPacketPayload {

    public static final Type<RealmUpResponsePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("god123awsomemod", "realm_up_response"));

    public static final StreamCodec<FriendlyByteBuf, RealmUpResponsePacket> STREAM_CODEC = StreamCodec.of(
            (buf, payload) -> {
                buf.writeBoolean(payload.success());
                buf.writeUtf(payload.realmName());
                buf.writeUtf(payload.realmId());
            },
            buf -> new RealmUpResponsePacket(
                    buf.readBoolean(),
                    buf.readUtf(),
                    buf.readUtf()
            )
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;
            CultivationRealmData.RealmLevel realm = CultivationRealmData.RealmLevel.valueOf(realmId);
            if (player != null) {
                CultivationRealmData.CultivationRealm data = player.getData(CultivationRealmData.CULTIVATION_REALM);
                data.setRealm(realm);
                if (success) {
                    data.setExp(0);
                    String msg = String.format("§a突破成功！ 当前境界: §6%s", realmName);
                    player.sendSystemMessage(net.minecraft.network.chat.Component.literal(msg));
                } else {
                    String msg = String.format("§c突破失败! 当前境界: §6%s", realmName);
                    player.sendSystemMessage(net.minecraft.network.chat.Component.literal(msg));
                }
            }
        });
    }
}
