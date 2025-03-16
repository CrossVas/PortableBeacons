package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.init.PortableItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class PortableItemModelProvider extends ItemModelProvider {

    public PortableItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PortableBeacons.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> portable : PortableItems.ITEMS.getEntries()) {
            String name = portable.getId().getPath();
            String textureName = name.endsWith("_ii") ? name.substring(0, name.length() - 3) : name;
            withExistingParent(name, "item/handheld").texture("layer0", "item/" + textureName);
        }
    }
}
