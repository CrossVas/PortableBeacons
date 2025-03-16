package cross.mods.portablebeacons.utils.data;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public record IngredientPredicate(Ingredient ingredient, ItemPredicate predicate) {

    public static ItemPredicate fromItem(ItemLike in) {
        return new IngredientPredicate(Ingredient.of(in), ItemPredicate.Builder.item().of(in).build()).predicate();
    }

    public static ItemPredicate fromTag(TagKey<Item> in) {
        return new IngredientPredicate(Ingredient.of(in), ItemPredicate.Builder.item().of(in).build()).predicate();
    }
}
