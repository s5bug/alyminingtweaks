package tf.bug.miningtweaks.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.util.GlAllocationUtils;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

public class MiningTweaksClient implements ClientModInitializer {

    public static void dumpData(MatrixStack matrices, Matrix4f projectionMatrix) {

    }

    @Override
    public void onInitializeClient() {

    }
}
