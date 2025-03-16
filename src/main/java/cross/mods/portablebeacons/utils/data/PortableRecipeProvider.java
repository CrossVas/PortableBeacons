package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.init.PortableItems;
import cross.mods.portablebeacons.items.PortableBeaconItem;
import cross.mods.portablebeacons.utils.PortableBeaconsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.concurrent.CompletableFuture;

public class PortableRecipeProvider extends RecipeProvider {

    public PortableRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider provider) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.BEACON_SHELL)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('O', Tags.Items.OBSIDIANS)
                .pattern("GGG")
                .pattern("G G")
                .pattern("OOO")
                .unlockedBy("has_item",
                        inventoryTrigger(
                                IngredientPredicate.fromTag(Tags.Items.GLASS_BLOCKS),
                                IngredientPredicate.fromTag(Tags.Items.OBSIDIANS)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.PORTABLE_BEACON_IRON)
                .define('S', PortableItems.BEACON_SHELL)
                .define('B', Items.BEACON)
                .define('I', Tags.Items.STORAGE_BLOCKS_IRON)
                .pattern(" S ")
                .pattern(" B ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.BEACON_SHELL),
                                IngredientPredicate.fromItem(Items.BEACON),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_IRON)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.PORTABLE_BEACON_GOLD)
                .define('S', PortableItems.PORTABLE_BEACON_IRON)
                .define('I', Tags.Items.STORAGE_BLOCKS_GOLD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_IRON),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_GOLD)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.PORTABLE_BEACON_GOLD)
                .define('S', PortableItems.PORTABLE_BEACON_IRON_II)
                .define('I', Tags.Items.STORAGE_BLOCKS_GOLD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_IRON_II),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_GOLD)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(PortableItems.PORTABLE_BEACON_GOLD) + "_1");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.PORTABLE_BEACON_EMERALD)
                .define('S', PortableItems.PORTABLE_BEACON_GOLD)
                .define('I', Tags.Items.STORAGE_BLOCKS_EMERALD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_GOLD),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_EMERALD)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.PORTABLE_BEACON_EMERALD)
                .define('S', PortableItems.PORTABLE_BEACON_GOLD_II)
                .define('I', Tags.Items.STORAGE_BLOCKS_EMERALD)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_GOLD_II),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_EMERALD)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(PortableItems.PORTABLE_BEACON_EMERALD) + "_1");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.PORTABLE_BEACON_DIAMOND)
                .define('S', PortableItems.PORTABLE_BEACON_EMERALD)
                .define('I', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_EMERALD),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_DIAMOND)
                        )
                ).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PortableItems.PORTABLE_BEACON_DIAMOND)
                .define('S', PortableItems.PORTABLE_BEACON_EMERALD_II)
                .define('I', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .pattern(" S ")
                .pattern("III")
                .unlockedBy("has_items",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_EMERALD_II),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_DIAMOND)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(PortableItems.PORTABLE_BEACON_DIAMOND) + "_1");

        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_IRON, PortableItems.PORTABLE_BEACON_IRON_II);
        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_GOLD, PortableItems.PORTABLE_BEACON_GOLD_II);
        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_EMERALD, PortableItems.PORTABLE_BEACON_EMERALD_II);
        addSmithingRecipe(output, PortableItems.PORTABLE_BEACON_DIAMOND, PortableItems.PORTABLE_BEACON_DIAMOND_II);

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(PortableItems.PORTABLE_BEACON_DIAMOND.asItem()),
                Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE),
                RecipeCategory.TOOLS,
                PortableItems.PORTABLE_BEACON_NETHERITE.asItem()
        ).unlocks("has_item",
                inventoryTrigger(
                        IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_DIAMOND.asItem()),
                        IngredientPredicate.fromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_NETHERITE)
                )
        ).save(output, PortableItems.PORTABLE_BEACON_NETHERITE.getId());

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(PortableItems.PORTABLE_BEACON_DIAMOND_II.asItem()),
                Ingredient.of(Tags.Items.STORAGE_BLOCKS_NETHERITE),
                RecipeCategory.TOOLS,
                PortableItems.PORTABLE_BEACON_NETHERITE_II.asItem()
        ).unlocks("has_item",
                inventoryTrigger(
                        IngredientPredicate.fromItem(PortableItems.PORTABLE_BEACON_DIAMOND_II.asItem()),
                        IngredientPredicate.fromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_NETHERITE)
                )
        ).save(output, PortableItems.PORTABLE_BEACON_NETHERITE_II.getId());
    }

    public void addSmithingRecipe(RecipeOutput output, DeferredItem<PortableBeaconItem> input, DeferredItem<PortableBeaconItem> item) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(PortableBeaconsTags.ARMOR_TRIMS),
                        Ingredient.of(input),
                        Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS),
                        RecipeCategory.TOOLS,
                        item.get())
                .unlocks("has_item",
                        inventoryTrigger(
                                IngredientPredicate.fromItem(input),
                                IngredientPredicate.fromTag(PortableBeaconsTags.ARMOR_TRIMS),
                                IngredientPredicate.fromTag(Tags.Items.STORAGE_BLOCKS_LAPIS)
                        )
                ).save(output, RecipeBuilder.getDefaultRecipeId(item));
    }
}
