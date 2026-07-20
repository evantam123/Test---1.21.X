package net.god123.Firstmod.item;

import net.god123.Firstmod.Firstmod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Firstmod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        ItemIngredients.init();
        ItemPill.init();
        ItemCultivationArt.init();
    }
}
