package space.essem.squish.mixin;

import space.essem.squish.SquishEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import moriyashiine.sizeentityattributes.SizeEntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeInstance;

@Mixin(LivingEntity.class)
public class SquishMixin {
	@Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
	private void applyDamage(final DamageSource source, final float amount, final CallbackInfo info) {
		ActionResult result = SquishEvent.EVENT.invoker().interact((LivingEntity)(Object)this, source);
		if (result == ActionResult.FAIL) {
			info.cancel();
		}
	}

	@Inject(method = "jump", at = @At("HEAD"))
	private void jump(final CallbackInfo info) {
		LivingEntity entity = (LivingEntity)(Object)this;
		EntityAttributeInstance height = entity.getAttributeInstance(SizeEntityAttributes.HEIGHT_MULTIPLIER);
		if (height != null && height.getBaseValue() == 0.05) {
			height.setBaseValue(1);
		}
	}
}
