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

@EventBusSubscriber(modid = "god123awsomemod")
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
                                        .then(Commands.argument("level", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                                    int level = IntegerArgumentType.getInteger(context, "level");

                                                    // ✅ 使用 DataAttachment
                                                    CultivationRealm data = player.getData(CULTIVATION_REALM);

                                                    RealmLevel[] realmLevels = RealmLevel.values();
                                                    if (level < realmLevels.length && level >= 0) {
                                                        data.setRealm(realmLevels[level]);
                                                    } else {
                                                        context.getSource().sendFailure(
                                                                Component.literal("未知境界！可用境界: 凡人, 炼气期, 筑基期, 金丹期, 元婴期, 化神期 0 - 5")
                                                        );
                                                    }

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
                                //exp
                                .then(Commands.literal("exp")
                                    .then(Commands.literal("set")
                                        .then(Commands.argument("exp", IntegerArgumentType.integer())
                                            .executes(context -> {
                                                ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                                CultivationRealm data = player.getData(CULTIVATION_REALM);
                                                int exp = IntegerArgumentType.getInteger(context, "exp");
                                                data.setExp(exp);

                                                Component msg = Component.literal(
                                                        String.format("§e%s 当前的exp: §b%s",
                                                                player.getScoreboardName(),
                                                                data.getExp()
                                                        )
                                                );
                                                context.getSource().sendSuccess(() -> msg, false);

                                                return 1;
                                            })
                                        )
                                    )
                                    .then(Commands.literal("get")
                                        .executes(context -> {
                                            ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                            CultivationRealm data = player.getData(CULTIVATION_REALM);
                                            Component msg = Component.literal(
                                                    String.format("§e%s 当前的exp: §b%s",
                                                            player.getScoreboardName(),
                                                            data.getExp()
                                                    )
                                            );
                                            context.getSource().sendSuccess(() -> msg, false);

                                            return 1;
                                        })
                                    )
                                    .then(Commands.literal("add")
                                        .then(Commands.argument("exp", IntegerArgumentType.integer())
                                            .executes(context -> {
                                                ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                                CultivationRealm data = player.getData(CULTIVATION_REALM);
                                                int exp = IntegerArgumentType.getInteger(context, "exp");
                                                data.addExp(exp);

                                                Component msg = Component.literal(
                                                        String.format("§e%s 当前的exp: §b%s",
                                                                player.getScoreboardName(),
                                                                data.getExp()
                                                        )
                                                );
                                                context.getSource().sendSuccess(() -> msg, false);

                                                return 1;
                                            })
                                        )
                                    )
                                )
                                //up
                                .then(Commands.literal("up")
                                        .executes(context -> {
                                            ServerPlayer player = EntityArgument.getPlayer(context, "target");
                                            CultivationRealm data = player.getData(CULTIVATION_REALM);
                                            data.RealmUp();

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