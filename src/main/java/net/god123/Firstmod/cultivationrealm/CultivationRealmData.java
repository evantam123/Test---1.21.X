package net.god123.Firstmod.cultivationrealm;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.StringRepresentable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class CultivationRealmData {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, "god123awsomemod");

    public enum RealmLevel implements StringRepresentable {
        MORTAL("凡人", 0),
        QI_REFINING("炼气期", 1),
        FOUNDATION("筑基期", 2),
        GOLDEN_CORE("金丹期", 3),
        NASCENT_SOUL("元婴期", 4),
        SPIRIT_SHEDDING("化神期", 5);

        private final String name;
        private final int level;

        RealmLevel(String name, int level) {
            this.name =name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public static final Codec<RealmLevel> CODEC = StringRepresentable.fromValues(RealmLevel::values);

    }

    public static class CultivationRealm {
        private RealmLevel realm;

        public CultivationRealm() {
            this.realm = RealmLevel.MORTAL;
        }

        public CultivationRealm(RealmLevel realm) {
            this.realm = realm;
        }

        public RealmLevel getRealm() {
            return realm;
        }

        public void setRealm(RealmLevel realm) {
            this.realm = realm;
        }

         public String getRealmName() {
            return realm.getName();
         }

         public static final Codec<CultivationRealm> CODEC = RecordCodecBuilder.create(instance ->
                 instance.group(
                         RealmLevel.CODEC.fieldOf("realm").forGetter(CultivationRealm::getRealm)
                 ).apply(instance, CultivationRealm::new)
         );
    }

    public static final Supplier<AttachmentType<CultivationRealm>> CULTIVATION_REALM = ATTACHMENT_TYPES.register(
            "cultivation_realm",
            () -> AttachmentType.builder(() -> new CultivationRealm())
                    .serialize(CultivationRealm.CODEC)
                    .copyOnDeath()
                    .build()
    );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
