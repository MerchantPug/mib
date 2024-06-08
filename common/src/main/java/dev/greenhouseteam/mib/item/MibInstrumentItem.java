package dev.greenhouseteam.mib.item;

import dev.greenhouseteam.mib.component.ItemInstrument;
import dev.greenhouseteam.mib.registry.MibComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class MibInstrumentItem extends Item {
    public MibInstrumentItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        return ItemInstrument.playInstrument(player, stack, hand);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int useTicksRemaining) {
        if (!stack.has(MibComponents.INSTRUMENT))
            return;
        if (useTicksRemaining % 10 == 0) {
            entity.gameEvent(GameEvent.INSTRUMENT_PLAY, entity);
            if (stack.has(MibComponents.INSTRUMENT) && stack.get(MibComponents.INSTRUMENT).animation().isPresent() && !stack.get(MibComponents.INSTRUMENT).animation().get().handsToSwing().isEmpty())
                stack.get(MibComponents.INSTRUMENT).animation().get().handsToSwing().forEach(hand -> entity.swing(hand, true));
        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        if (!stack.has(MibComponents.INSTRUMENT))
            return 40;
        return stack.get(MibComponents.INSTRUMENT).maxUseDuration();
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int useTicksRemaining) {
        super.releaseUsing(stack, level, entity, useTicksRemaining);
        if (entity instanceof Player player)
            player.getCooldowns().addCooldown(stack.getItem(), 40);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player)
            player.getCooldowns().addCooldown(stack.getItem(), 40);
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (stack.has(MibComponents.INSTRUMENT) && stack.get(MibComponents.INSTRUMENT).animation().isPresent() && stack.get(MibComponents.INSTRUMENT).animation().get().useAnim() != null)
            return stack.get(MibComponents.INSTRUMENT).animation().get().useAnim();
        return super.getUseAnimation(stack);
    }
}