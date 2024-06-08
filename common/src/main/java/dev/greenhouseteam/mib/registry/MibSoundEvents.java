package dev.greenhouseteam.mib.registry;

import dev.greenhouseteam.mib.Mib;
import dev.greenhouseteam.mib.registry.internal.RegistrationCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class MibSoundEvents {
    public static final SoundEvent FANTASY_TRUMPET_START = SoundEvent.createVariableRangeEvent(Mib.asResource("mib.instrument.fantasy_trumpet_start"));
    public static final SoundEvent FANTASY_TRUMPET_LOOP = SoundEvent.createVariableRangeEvent(Mib.asResource("mib.instrument.fantasy_trumpet_loop"));
    public static final SoundEvent FLUTE_START = SoundEvent.createVariableRangeEvent(Mib.asResource("mib.instrument.flute_start"));
    public static final SoundEvent FLUTE_LOOP = SoundEvent.createVariableRangeEvent(Mib.asResource("mib.instrument.flute_loop"));
    public static final SoundEvent KEYBOARD = SoundEvent.createVariableRangeEvent(Mib.asResource("mib.instrument.keyboard"));

    public static void registerAll(RegistrationCallback<SoundEvent> callback) {
        callback.register(BuiltInRegistries.SOUND_EVENT, Mib.asResource("mib.instrument.fantasy_trumpet_loop"), FANTASY_TRUMPET_LOOP);
        callback.register(BuiltInRegistries.SOUND_EVENT, Mib.asResource("mib.instrument.fantasy_trumpet_start"), FANTASY_TRUMPET_START);
        callback.register(BuiltInRegistries.SOUND_EVENT, Mib.asResource("mib.instrument.flute_loop"), FLUTE_LOOP);
        callback.register(BuiltInRegistries.SOUND_EVENT, Mib.asResource("mib.instrument.flute_start"), FLUTE_START);
        callback.register(BuiltInRegistries.SOUND_EVENT, Mib.asResource("mib.instrument.keyboard"), KEYBOARD);
    }
}
