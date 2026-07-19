package net.god123.Firstmod.cultivationrealm;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import static net.god123.Firstmod.cultivationrealm.CultivationRealmData.*;

@EventBusSubscriber(modid = "god123awsomemod", bus = EventBusSubscriber.Bus.GAME)
public class CultivationRealmCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
                Commands.literal("cultivation")
                        .requires(source -> source.hasPermission(Commands.LEVEL_ADMINS))
                        .then(Commands.argument("target", EntityArgument.player())
                                // SET 指令
                                .then(Commands.literal("set")
                                        .then(Commands.argument("level", IntegerArgumentType.integer(0, 5))
                                                .executes(context -> {
                                                    ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                                    int level = IntegerArgumentType.getInteger(context, "level");

                                                    // ✅ 使用 DataAttachment
                                                    CultivationRealm data = player.getData(CULTIVATION_REALM);

                                                    // 从枚举查找匹配的境界
                                                    RealmLevel newRealm = null;
                                                    for (RealmLevel realm : RealmLevel.values()) {
                                                        if (realm.getLevel() == level) {
                                                            newRealm = realm;
                                                            break;
                                                        }
                                                    }

                                                    if (newRealm == null) {
                                                        context.getSource().sendFailure(
                                                                Component.literal("未知境界！可用境界: 凡人, 炼气期, 筑基期, 金丹期, 元婴期, 化神期")
                                                        );
                                                        return 0;
                                                    }

                                                    data.setRealm(newRealm);

                                                    context.getSource().sendSuccess(
                                                            () -> Component.literal(
                                                                    String.format("§a已更新 %s 的境界为: §6%s",
                                                                            player.getScoreboardName(),
                                                                            data.getRealmName())
                                                            ), true
                                                    );

                                                    return 1;
                                                })
                                        )
                                )
                                // QUERY 指令
                                .then(Commands.literal("query")
                                        .executes(context -> {
                                            ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                            CultivationRealm data = player.getData(CULTIVATION_REALM);

                                            Component msg = Component.literal(
                                                    String.format("§e%s 当前的境界: §b%s",
                                                            player.getScoreboardName(),
                                                            data.getRealmName()
                                                    )
                                            );
                                            context.getSource().sendSuccess(() -> msg, false);

                                            return 1;
                                        })
                                )
                        )
        );
    }
}