package space.essem.squish;

import net.fabricmc.fabric.api.event.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;

public interface SquishEvent {

    Event<SquishEvent> EVENT = EventFactory.createArrayBacked(SquishEvent.class,
        (listeners) -> (entity, source) -> {
            for (SquishEvent listener : listeners) {
                ActionResult result = listener.interact(entity, source);
                if (result != ActionResult.PASS) {
                    return result;
                }
            }
            return ActionResult.PASS;
    });

    ActionResult interact(LivingEntity entity, DamageSource source);
}