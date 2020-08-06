package space.essem.squish.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import space.essem.squish.SquishEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerSquishMixin {
    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    private void applyDamage(final DamageSource source, final float amount, final CallbackInfo info) {
        ActionResult result = SquishEvent.EVENT.invoker().interact((LivingEntity)(Object)this, source);
        if (result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
