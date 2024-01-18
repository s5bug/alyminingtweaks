package tf.bug.miningtweaks.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.bug.miningtweaks.api.ToolMaterial;
import tf.bug.miningtweaks.imixin.IToolItemExtension;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(
            at = @At(
                    value = "HEAD"
            ),
            method = "appendTooltip"
    )
    public void appendMiningLevelToTools(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        Item thisx = (Item) (Object) this;

        if(thisx instanceof ToolItem tool) {
            ToolMaterial material = ((IToolItemExtension) tool).alyminingtweaks$getToolMaterial();
            tooltip.add(Text.literal("Mining level: " + material.getMiningLevel()));
        }
    }

}
