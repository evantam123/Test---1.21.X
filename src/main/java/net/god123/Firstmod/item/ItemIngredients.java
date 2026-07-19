package net.god123.Firstmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.god123.Firstmod.item.ModItems.ITEMS;

public class ItemIngredients {
    public static final DeferredItem<Item> SPIRIT_STONE = ITEMS.register("spirit_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_MAGIC_STONE = ITEMS.register("raw_magic_stone", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MYSTIC_IRON_INGOT = ITEMS.register("mystic_iron_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_MYSTIC_IRON = ITEMS.register("raw_mystic_iron", () -> new Item(new Item.Properties()));

    public static void init() {}
}
