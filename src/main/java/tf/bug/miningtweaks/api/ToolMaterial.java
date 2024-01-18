package tf.bug.miningtweaks.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import tf.bug.miningtweaks.MiningTweaks;

import java.util.Objects;

public final class ToolMaterial {

    private final RegistryKey<MiningLevel> miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Ingredient repairIngredient;
    private final TagKey<Item> tools;

    public ToolMaterial(
            RegistryKey<MiningLevel> miningLevel,
            int itemDurability,
            float miningSpeed,
            float attackDamage,
            int enchantability,
            Ingredient repairIngredient,
            TagKey<Item> tools
    ) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
        this.tools = tools;
    }

    public RegistryKey<MiningLevel> getMiningLevel() {
        return miningLevel;
    }

    public int getItemDurability() {
        return itemDurability;
    }

    public float getMiningSpeed() {
        return miningSpeed;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public int getEnchantability() {
        return enchantability;
    }

    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    public TagKey<Item> getTools() {
        return tools;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToolMaterial that = (ToolMaterial) o;
        return itemDurability == that.itemDurability && Float.compare(miningSpeed, that.miningSpeed) == 0 && Float.compare(attackDamage, that.attackDamage) == 0 && enchantability == that.enchantability && Objects.equals(miningLevel, that.miningLevel) && Objects.equals(repairIngredient, that.repairIngredient) && Objects.equals(tools, that.tools);
    }

    @Override
    public int hashCode() {
        return Objects.hash(miningLevel, itemDurability, miningSpeed, attackDamage, enchantability, repairIngredient, tools);
    }

    @Override
    public String toString() {
        return "ToolMaterial{" +
                "miningLevel=" + miningLevel +
                ", itemDurability=" + itemDurability +
                ", miningSpeed=" + miningSpeed +
                ", attackDamage=" + attackDamage +
                ", enchantability=" + enchantability +
                ", repairIngredient=" + repairIngredient +
                ", tools=" + tools +
                '}';
    }

    public static final Codec<ToolMaterial> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    RegistryKey.createCodec(MiningTweaks.MINING_LEVELS)
                            .fieldOf("mining_level")
                            .forGetter(ToolMaterial::getMiningLevel),
                    Codec.INT
                            .fieldOf("item_durability")
                            .forGetter(ToolMaterial::getItemDurability),
                    Codec.FLOAT
                            .fieldOf("mining_speed")
                            .forGetter(ToolMaterial::getMiningSpeed),
                    Codec.FLOAT
                            .fieldOf("attack_damage")
                            .forGetter(ToolMaterial::getAttackDamage),
                    Codec.INT
                            .fieldOf("enchantability")
                            .forGetter(ToolMaterial::getEnchantability),
                    Ingredient.ALLOW_EMPTY_CODEC
                            .fieldOf("repair_ingredient")
                            .forGetter(ToolMaterial::getRepairIngredient),
                    TagKey.codec(RegistryKeys.ITEM)
                            .fieldOf("tools")
                            .forGetter(ToolMaterial::getTools)
            ).apply(instance, ToolMaterial::new)
    );
}
