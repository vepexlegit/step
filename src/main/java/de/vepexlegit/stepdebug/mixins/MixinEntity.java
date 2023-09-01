package de.vepexlegit.stepdebug.mixins;

import de.vepexlegit.stepdebug.Step;
import de.vepexlegit.stepdebug.command.StepCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraftforge.client.ClientCommandHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {
    @Inject(method = "onUpdate", at = @At("RETURN"))
    private void startGame(final CallbackInfo ci) {
        ClientCommandHandler.instance.registerCommand(new StepCommand());
    }

    private static Minecraft mc = Minecraft.getMinecraft();

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onUpdate(final CallbackInfo ci) {
        if (Step.INSTANCE.isEnabled()) {
            if ((mc.thePlayer.isCollidedHorizontally) && (mc.thePlayer.onGround)) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.42, mc.thePlayer.posZ, mc.thePlayer.onGround));
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.72, mc.thePlayer.posZ, mc.thePlayer.onGround));
                mc.thePlayer.stepHeight = 1.0F;
            } else {
                mc.thePlayer.stepHeight = 0.5F;
            }
        }
    }
}
