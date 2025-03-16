package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.init.PortableItems;
import cross.mods.portablebeacons.items.PortableBeaconItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class PortableRecipeProvider extends RecipeProvider {


    public PortableRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> output) {
        ShapedRecipeBuilder.shaped(PortableItems.BEACON_SHELL.get())
                .define('G', Tags.Items.GLASS)
                .define('O', Tags.Items.OBSIDIAN)
                .pattern("GGG")
                .pattern("G G")
                .pattern("OOO")
                .unlockedBy("has_item",
                        inventoryTrigger(
                                IngredientPredicate.fromTag(Tags.Items.GLASS),
                                IngredientPredicate.fromTag(Tags.Items.OBSIDIAN)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(PortableItems.PORTABLE_BEACON_IRON.get())
                .define('S', PortableItems.BEACON_SHELL.get())
                .define('B', Items.BEACON)
                .define('I', Tags.Items.STORAGE_BLOCKS_IRON)
                .pattern(" S ")
                .pattern(" B ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.BEACON_SHELL.get()),
                                IngredientPredicate.fromItem(Items.BEACON),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_IRON)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(PortableItems.PORTABLE_BEACON_GOLD.get())
                .define('S', PortableItems.PORTABLE_BEACON_IRON.get())
                .define('I', Tags.Items.STORAGE_BLOCKS_GOLD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_IRON.get()),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_GOLD)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(PortableItems.PORTABLE_BEACON_GOLD.get())
                .define('S', PortableItems.PORTABLE_BEACON_IRON_II.get())
                .define('I', Tags.Items.STORAGE_BLOCKS_GOLD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_IRON_II.get()),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_GOLD)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(PortableItems.PORTABLE_BEACON_GOLD.get()) + "_1");

        ShapedRecipeBuilder.shaped(PortableItems.PORTABLE_BEACON_EMERALD.get())
                .define('S', PortableItems.PORTABLE_BEACON_GOLD.get())
                .define('I', Tags.Items.STORAGE_BLOCKS_EMERALD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_GOLD.get()),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_EMERALD)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(PortableItems.PORTABLE_BEACON_EMERALD.get())
                .define('S', PortableItems.PORTABLE_BEACON_GOLD_II.get())
                .define('I', Tags.Items.STORAGE_BLOCKS_EMERALD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_GOLD_II.get()),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_EMERALD)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(PortableItems.PORTABLE_BEACON_EMERALD.get()) + "_1");

        ShapedRecipeBuilder.shaped(PortableItems.PORTABLE_BEACON_DIAMOND.get())
                .define('S', PortableItems.PORTABLE_BEACON_EMERALD.get())
                .define('I', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_EMERALD.get()),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_DIAMOND)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(PortableItems.PORTABLE_BEACON_DIAMOND.get())
                .define('S', PortableItems.PORTABLE_BEACON_EMERALD_II.get())
                .define('I', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_EMERALD_II.get()),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_DIAMOND)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(PortableItems.PORTABLE_BEACON_DIAMOND.get()) + "_1");

        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_IRON, PortableItems.PORTABLE_BEACON_IRON_II);
        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_GOLD, PortableItems.PORTABLE_BEACON_GOLD_II);
        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_EMERALD, PortableItems.PORTABLE_BEACON_EMERALD_II);
        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_DIAMOND, PortableItems.PORTABLE_BEACON_DIAMOND_II);

        UpgradeRecipeBuilder.smithing(
                Ingredient.of(PortableItems.PORTABLE_BEACON_DIAMOND.get()),
                Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE),
                PortableItems.PORTABLE_BEACON_NETHERITE.get()
        ).unlocks("has_item",
                inventoryTrigger(
                        IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_DIAMOND.get()),
                        IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_NETHERITE)
                )
        ).save(output, PortableItems.PORTABLE_BEACON_NETHERITE.getId());

        UpgradeRecipeBuilder.smithing(
                Ingredient.of(PortableItems.PORTABLE_BEACON_DIAMOND_II.get()),
                Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE),
                PortableItems.PORTABLE_BEACON_NETHERITE_II.get()
        ).unlocks("has_item",
                inventoryTrigger(
                        IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_DIAMOND_II.get()),
                        IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_NETHERITE)
                )
        ).save(output, PortableItems.PORTABLE_BEACON_NETHERITE_II.getId());
    }

    public void addSmithingRecipe(Consumer<FinishedRecipe> output, RegistryObject<PortableBeaconItem> input, RegistryObject<PortableBeaconItem> item) {
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(input.get()),
                        Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS),
                        item.get())
                .unlocks("has_item",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(input.get()),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_LAPIS)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(item.get()));
    }
}
