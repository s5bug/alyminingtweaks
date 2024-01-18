package tf.bug.miningtweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import tf.bug.miningtweaks.api.MiningLevel;
import tf.bug.miningtweaks.api.ToolMaterial;

import java.util.concurrent.atomic.AtomicReference;

public class MiningTweaks implements ModInitializer {
    public static final String MODID = "alyminingtweaks";

    public static final RegistryKey<Registry<MiningLevel>> MINING_LEVELS =
            RegistryKey.ofRegistry(Identifier.of(MODID, "mining_level"));

    public static final RegistryKey<Registry<ToolMaterial>> TOOL_MATERIALS =
            RegistryKey.ofRegistry(Identifier.of(MODID, "tool_material"));

    private static final AtomicReference<MiningTweaks> INSTANCE = new AtomicReference<>();

    @Override
    public void onInitialize() {
        if(!INSTANCE.compareAndSet(null, this))
            throw new IllegalStateException("Initialized twice");

        DynamicRegistrySetupCallback.EVENT.register(
                Identifier.of(MODID, "dynamic_registry_setup_handler"),
                registryView -> {
                    registryView.registerEntryAdded(MINING_LEVELS, (rawId, id, object) -> {
                        System.out.println("HELLO " + object);
                    });
                }
        );

        DynamicRegistries.registerSynced(MINING_LEVELS, MiningLevel.CODEC);
        DynamicRegistries.registerSynced(TOOL_MATERIALS, ToolMaterial.CODEC);
    }

    public static MiningTweaks getInstance() {
        return INSTANCE.getPlain();
    }

    public net.minecraft.item.ToolMaterial asVanilla(ToolMaterial data) {
        return new net.minecraft.item.ToolMaterial() {
            @Override
            public int getDurability() {
                return data.getItemDurability();
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return data.getMiningSpeed();
            }

            @Override
            public float getAttackDamage() {
                return data.getAttackDamage();
            }

            @Override
            public int getMiningLevel() {
                // TODO
                return 0;
            }

            @Override
            public int getEnchantability() {
                return data.getEnchantability();
            }

            @Override
            public Ingredient getRepairIngredient() {
                return data.getRepairIngredient();
            }
        };
    }

}
