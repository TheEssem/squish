package space.essem.squish;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.damage.DamageSource;
import moriyashiine.sizeentityattributes.SizeEntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeInstance;

public class Squish implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("*squish*");

		SquishEvent.EVENT.register((entity, source) -> {
			EntityAttributeInstance height = entity.getAttributeInstance(SizeEntityAttributes.HEIGHT_MULTIPLIER);

			if (height != null && source == DamageSource.ANVIL) {
				height.setBaseValue(0.05);
			}

			return ActionResult.PASS;
		});
	}
}
