package net.god123.Firstmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.god123.Firstmod.item.ModItems.ITEMS;

public class ItemTechnique {
    public static final DeferredItem<Item> BASICTECHNIQUE = ITEMS.register("basic_technique", () -> new Item(new Item.Properties()));

    public static void init() {}
}
