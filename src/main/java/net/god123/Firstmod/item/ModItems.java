package net.god123.Firstmod.item;

import net.god123.Firstmod.Firstmod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Firstmod.MODID);

    public static final DeferredItem<Item> SPIRITSTONE = ITEMS.register("spirit_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAWMAGICSTONE = ITEMS.register("raw_magic_stone", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
