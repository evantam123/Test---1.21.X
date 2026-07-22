package net.god123.Firstmod.datagen;

import net.god123.Firstmod.Firstmod;
import net.god123.Firstmod.item.ItemCultivationArt;
import net.god123.Firstmod.item.ItemIngredients;
import net.god123.Firstmod.item.ItemPill;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Firstmod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemIngredients.LOW_SPIRIT_STONE.get());
        basicItem(ItemIngredients.MID_SPIRIT_STONE.get());
        basicItem(ItemIngredients.HIGH_SPIRIT_STONE.get());
        basicItem(ItemIngredients.MYSTIC_IRON_INGOT.get());
        basicItem(ItemIngredients.RAW_MYSTIC_IRON.get());

        basicItem(ItemPill.HEALING_PILL.get());
        basicItem(ItemPill.QIRESTORATION_PILL.get());

        basicItem(ItemCultivationArt.BASIC_CULTIVATION_ART.get());
    }
}
