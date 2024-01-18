package tf.bug.miningtweaks.api;

import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import tf.bug.miningtweaks.MiningTweaks;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public final class MiningLevel {

    private final TagKey<MiningLevel> betterThan;
    @Nullable
    private final Integer vanillaMiningLevel;

    public MiningLevel(TagKey<MiningLevel> betterThan, @Nullable Integer vanillaMiningLevel) {
        this.betterThan = betterThan;
        this.vanillaMiningLevel = vanillaMiningLevel;
    }

    public TagKey<MiningLevel> getBetterThan() {
        return betterThan;
    }

    public @Nullable Integer getVanillaMiningLevel() {
        return vanillaMiningLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiningLevel that = (MiningLevel) o;
        return Objects.equals(betterThan, that.betterThan) && Objects.equals(vanillaMiningLevel, that.vanillaMiningLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(betterThan, vanillaMiningLevel);
    }

    @Override
    public String toString() {
        return "MiningLevel[" +
                "betterThan=" + betterThan +
                ", vanillaMiningLevel=" + vanillaMiningLevel +
                ']';
    }

    public static final Codec<MiningLevel> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    TagKey.codec(MiningTweaks.MINING_LEVELS)
                            .fieldOf("better_than")
                            .forGetter(MiningLevel::getBetterThan),
                    Codec.INT
                            .optionalFieldOf("vanilla_mining_level", null)
                            .forGetter(MiningLevel::getVanillaMiningLevel)
            ).apply(instance, MiningLevel::new)
    );
}
