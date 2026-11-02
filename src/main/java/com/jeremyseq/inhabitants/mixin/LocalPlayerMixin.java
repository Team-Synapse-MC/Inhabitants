package com.jeremyseq.inhabitants.mixin;

import com.jeremyseq.inhabitants.items.SpikeDrillItem;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.LocalPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    @WrapOperation(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"
            )
    )
    private boolean inhabitants$noInputSlowdownForDrill(LocalPlayer player, Operation<Boolean> original) {
        if (player.getUseItem().getItem() instanceof SpikeDrillItem) {
            return false;
        }
        return original.call(player);
    }
}
