package net.god123.Firstmod.CultivationRealm;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

// 使用 NeoForge 的事件監聽器，自動幫我們註冊指令
@EventBusSubscriber(modid = "god123awsomemod", bus = EventBusSubscriber.Bus.GAME)
public class CultivationRealmCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // 註冊指令：/cultivation <玩家> set <境界名稱>
        dispatcher.register(
                Commands.literal("cultivation")
                        .requires(source -> source.hasPermission(2)) // 限制需要管理員(OP)權限
                        .then(Commands.argument("target", EntityArgument.player())

                                // 子指令 1：設定境界
                                .then(Commands.literal("set")
                                        .then(Commands.argument("realm", StringArgumentType.string())
                                                .executes(context -> {
                                                    ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                                    String realmName = StringArgumentType.getString(context, "realm");

                                                    // 💡 核心步驟：直接將境界以字串形式寫入玩家的 Persistent NBT 資料中
                                                    CompoundTag playerData = player.getPersistentData();
                                                    playerData.putString("CultivationRealm", realmName);

                                                    // 發送聊天欄提示
                                                    context.getSource().sendSuccess(() -> Component.literal("已將 " + player.getScoreboardName() + " 的境界設定為: " + realmName), true);
                                                    player.sendSystemMessage(Component.literal("§a你的修仙境界已晉升為: §6" + realmName));

                                                    return 1;
                                                })
                                        )
                                )

                                // 子指令 2：查詢境界
                                .then(Commands.literal("query")
                                        .executes(context -> {
                                            ServerPlayer player = EntityArgument.getPlayer(context, "target");

                                            // 讀取 NBT，如果不存在就預設為 "凡人"
                                            CompoundTag playerData = player.getPersistentData();
                                            String realm = playerData.contains("CultivationRealm") ? playerData.getString("CultivationRealm") : "凡人";

                                            context.getSource().sendSuccess(() -> Component.literal(player.getScoreboardName() + " 當前的境界是: " + realm), false);
                                            return 1;
                                        })
                                )
                        )
        );
    }
}