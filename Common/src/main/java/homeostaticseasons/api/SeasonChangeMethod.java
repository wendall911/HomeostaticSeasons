package homeostaticseasons.api;

import java.util.Locale;

import org.jspecify.annotations.NonNull;

import net.minecraft.util.StringRepresentable;

public enum SeasonChangeMethod implements StringRepresentable {

    REALTIME,
    FIXED,
    CONFIGURED;

    @Override
    public @NonNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

}
