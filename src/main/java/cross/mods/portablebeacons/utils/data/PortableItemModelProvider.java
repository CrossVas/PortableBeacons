package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.init.PortableItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class PortableItemModelProvider extends ItemModelProvider {

    public PortableItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PortableBeacons.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> portable : PortableItems.ITEMS.getEntries()) {
            String name = portable.getId().getPath();
            String textureName = name.endsWith("_ii") ? name.substring(0, name.length() - 3) : name;
            withExistingParent(name, "item/handheld").texture("layer0", "item/" + textureName);
        }
    }
}
