package net.god123.Firstmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.god123.Firstmod.item.ModItems.ITEMS;

public class ItemPill {
    public static final DeferredItem<Item> HEALINGPILL = ITEMS.register("healing_pill", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> QIRESTORATIONPILL = ITEMS.register("qi_restoration_pill", () -> new Item(new Item.Properties()));

    public static void init() {}
}
