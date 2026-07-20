package net.god123.Firstmod.item;

import net.god123.Firstmod.Firstmod;
import net.god123.Firstmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Firstmod.MODID);

    public static final Supplier<CreativeModeTab> CULTIVATION_ITEMS_TAB = CREATIVE_MODE_TAB.register("cultivation_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ItemIngredients.SPIRIT_STONE.get()))
            .title(Component.translatable("creativetab.god123awsomemod.cultivation_items"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ItemIngredients.SPIRIT_STONE);
                output.accept(ItemIngredients.RAW_MAGIC_STONE);
                output.accept(ItemIngredients.MYSTIC_IRON_INGOT);
                output.accept(ItemIngredients.RAW_MYSTIC_IRON);

                output.accept(ItemPill.HEALING_PILL);
                output.accept(ItemPill.QIRESTORATION_PILL);

                output.accept(ItemCultivationArt.BASIC_CULTIVATION_ART);
            }).build());

    public static final Supplier<CreativeModeTab> CULTIVATION_BLOCKS_TAB = CREATIVE_MODE_TAB.register("cultivation_blocks_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.SPIRIT_STONE_BLOCK))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Firstmod.MODID, "cultivation_items_tab"))
            .title(Component.translatable("creativetab.god123awsomemod.cultivation_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.SPIRIT_STONE_BLOCK);
                output.accept(ModBlocks.SPIRIT_STONE_ORE);
                output.accept(ModBlocks.MYSTIC_IRON_BLOCK);
                output.accept(ModBlocks.MYSTIC_IRON_ORE);
            }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
