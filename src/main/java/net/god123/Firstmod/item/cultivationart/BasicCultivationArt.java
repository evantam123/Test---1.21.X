package net.god123.Firstmod.item.cultivationart;

import net.god123.Firstmod.cultivationrealm.CultivationRealmData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import static net.god123.Firstmod.cultivationrealm.CultivationRealmData.CULTIVATION_REALM;

public class BasicCultivationArt extends Item implements ICurioItem {

    public BasicCultivationArt() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
        );
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        // 可以在这里添加装备条件，比如需要特定等级
        return true;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        // 装备时触发的逻辑
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        // 卸下时触发的逻辑
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        // 每个 tick 触发的逻辑（比如每秒恢复生命）
    }

    boolean hotFix = true;

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        LivingEntity wearer = slotContext.entity();

        // 检查是否是玩家
        if (wearer instanceof Player player) {
            CultivationRealmData.CultivationRealm data = wearer.getData(CULTIVATION_REALM);
            if (data.getExp() == 0 && data.getRealm() == CultivationRealmData.RealmLevel.MORTAL) {
                return true;
            } else {
                // 🚫 阻止卸下，并发送提示信息
                player.displayClientMessage(
                        Component.literal("§6§l⚠ 你正在修炼 " + "基本功法" + "，无法中途放弃！").withStyle(ChatFormatting.RED),
                        false  // false 表示显示在聊天栏，true 表示显示在动作栏
                );
            }

            // 你也可以播放音效
            // player.playSound(SoundEvents.ITEM_BREAK, 1.0F, 1.0F);
        }

        // 返回 false 阻止卸下
        return false;
    }
}