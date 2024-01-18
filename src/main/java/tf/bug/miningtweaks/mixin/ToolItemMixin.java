package tf.bug.miningtweaks.mixin;

import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tf.bug.miningtweaks.MiningTweaks;
import tf.bug.miningtweaks.api.ToolMaterial;
import tf.bug.miningtweaks.imixin.IToolItemExtension;

@Mixin(ToolItem.class)
public class ToolItemMixin implements IToolItemExtension {

    @Override
    public ToolMaterial alyminingtweaks$getToolMaterial() {
        // TODO

        return null;
    }

    @Inject(
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true,
            method = "getMaterial"
    )
    public void replaceToolMaterial(CallbackInfoReturnable<net.minecraft.item.ToolMaterial> cir) {
        ToolItem thisx = (ToolItem) (Object) this;

        ToolMaterial material = ((IToolItemExtension) thisx).alyminingtweaks$getToolMaterial();
        cir.setReturnValue(MiningTweaks.getInstance().asVanilla(material));
    }

}
