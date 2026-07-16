package net.god123.Firstmod.CultivationRealm;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public abstract  class CultivationRealm {
    private ResourceLocation id;
    private String name;
    private int commission;

    protected CultivationRealm(ResourceLocation id, String name, int commission) {
        this.id = Objects.requireNonNull(id, "Realm ID can't be null!");
        this.name = Objects.requireNonNull(name, "Realm Name can't be null!");
        this.commission = Integer.max(0, commission);
    }

    public ResourceLocation getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCommission() {
        return commission;
    }

    public boolean isPlayerOverRealm(ResourceLocation otherId) {
        return this.id.equals(otherId);
    }
    public boolean isPlayerInRealm(ResourceLocation otherId) {
        return this.id.equals(otherId);
    }
}
