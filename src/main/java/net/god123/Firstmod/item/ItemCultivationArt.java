package net.god123.Firstmod.item;

import net.god123.Firstmod.item.cultivationart.BasicCultivationArt;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.god123.Firstmod.item.ModItems.ITEMS;

public class ItemCultivationArt {
    public static final DeferredItem<Item> BASIC_CULTIVATION_ART = ITEMS.register("basic_cultivation_art", BasicCultivationArt::new);

    public static void init() {}
}
