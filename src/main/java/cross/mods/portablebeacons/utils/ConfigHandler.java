package cross.mods.portablebeacons.utils;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {

    public final ForgeConfigSpec.BooleanValue toggleable;
    public final ForgeConfigSpec.IntValue effectRange;
    public final ForgeConfigSpec.IntValue cost;
    public final ForgeConfigSpec.IntValue costTimer;

    public ConfigHandler(ForgeConfigSpec.Builder builder) {
        builder.push("common");
        this.toggleable = builder.define("toggleable", true);
        this.effectRange = builder.defineInRange("range", 0, 0, 100);
        this.cost = builder.defineInRange("cost", 1, 0, 1000);
        this.costTimer = builder.defineInRange("x", 10, 1, 1000);
        builder.pop();
    }
}
