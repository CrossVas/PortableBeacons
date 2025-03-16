package cross.mods.portablebeacons.utils;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigHandler {

    public final ModConfigSpec.BooleanValue toggleable;
    public final ModConfigSpec.IntValue effectRange;
    public final ModConfigSpec.IntValue cost;
    public final ModConfigSpec.IntValue costTimer;

    public ConfigHandler(ModConfigSpec.Builder builder) {
        builder.push("common");
        this.toggleable = builder.define("toggleable", true);
        this.effectRange = builder.defineInRange("range", 0, 0, 100);
        this.cost = builder.defineInRange("cost", 1, 0, 1000);
        this.costTimer = builder.defineInRange("x", 10, 1, 1000);
        builder.pop();
    }
}
