package com.falsepattern.endlessids.mixin.mixins.common;

import net.minecraft.server.management.ItemInWorldManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemInWorldManager.class)
public abstract class ItemInWorldManagerMixin {
    @ModifyConstant(method = "tryHarvestBlock",
                    constant = @Constant(intValue = 12, ordinal = 0),
                    require = 1)
    private int extend1(int constant) {
        return 16;
    }
}
