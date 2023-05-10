package com.peramods.cogicoin.loot; //

import com.google.common.base.Suppliers; //
import com.mojang.serialization.Codec; //
import com.mojang.serialization.codecs.RecordCodecBuilder; //
import it.unimi.dsi.fastutil.objects.ObjectArrayList; //
import net.minecraft.world.item.Item; //
import net.minecraft.world.item.ItemStack; //
import net.minecraft.world.level.storage.loot.LootContext; //
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition; //
import net.minecraftforge.common.loot.IGlobalLootModifier; //
import net.minecraftforge.common.loot.LootModifier; //
import net.minecraftforge.registries.ForgeRegistries; //
import org.jetbrains.annotations.NotNull; //

import java.util.function.Supplier;

public class ZombieLootModifier extends LootModifier {
    public static final Supplier<Codec<ZombieLootModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("copper").forGetter(m -> m.item))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("silver").forGetter(m -> m.secItem))
            .apply(inst, ZombieLootModifier::new)));

    private final Item item;
    private final Item secItem;

    protected ZombieLootModifier(LootItemCondition[] conditionsIn, Item item, Item secItem) {
        super(conditionsIn);
        this.item = item;
        this.secItem = secItem;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        int numDropsItem = context.getRandom().nextInt(9) + 1; // Genera un número aleatorio entre 1 y 10
        int numDropsSecItem = 1; // Siempre se dropeará solo un secItem

        for (int i = 0; i < numDropsItem; i++) {
            generatedLoot.add(new ItemStack(item));
        }

        if(context.getRandom().nextFloat() >= 0.9f) {
            for (int i = 0; i < numDropsSecItem; i++) {
                generatedLoot.add(new ItemStack(secItem));
            }
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
