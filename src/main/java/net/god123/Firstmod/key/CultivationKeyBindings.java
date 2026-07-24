package net.god123.Firstmod.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.god123.Firstmod.gui.CultivationScreen;
import net.god123.Firstmod.network.request.CultivateRequestPacket;
import net.god123.Firstmod.network.request.RealmUpRequestPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = "god123awsomemod", value = Dist.CLIENT)
public class CultivationKeyBindings {

    public static final String KEY_CATEGORY = "key.category.god123awsomemod.cultivation";

    public static KeyMapping openGuiKey = new KeyMapping(
            "key.god123awsomemod.open_cultivation_gui",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY
    );

    public static KeyMapping realmUpKey = new KeyMapping(
            "key.god123awsomemod.realm_up",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            KEY_CATEGORY
    );

    public static KeyMapping cultivateKey = new KeyMapping(
            "key.god123awsomemod.cultivate",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_J,
            KEY_CATEGORY
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(openGuiKey);
        event.register(realmUpKey);
        event.register(cultivateKey);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (openGuiKey.consumeClick()) {
            handleOpenGuiKey();
        }

        if (realmUpKey.consumeClick()) {
            handleRealmUpKey();
        }

        if (cultivateKey.consumeClick()) {
            handleCultivate();
        }
    }

    private static void handleRealmUpKey() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        PacketDistributor.sendToServer(new RealmUpRequestPacket());
        player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("§e正在尝试突破境界...")
        );
    }

    private static void handleOpenGuiKey() {
        Minecraft.getInstance().setScreen(new CultivationScreen());
    }

    private static void handleCultivate() {
        PacketDistributor.sendToServer(new CultivateRequestPacket());
    }
}
