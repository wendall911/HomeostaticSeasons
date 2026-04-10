package homeostaticseasons.api;

import java.util.Locale;

import org.jspecify.annotations.NonNull;

import net.minecraft.util.StringRepresentable;

public enum Hemisphere implements StringRepresentable {

    NORTHERN,
    SOUTHERN;

    @Override
    public @NonNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

}
