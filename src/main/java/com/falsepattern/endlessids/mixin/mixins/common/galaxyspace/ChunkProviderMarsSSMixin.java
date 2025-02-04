package com.falsepattern.endlessids.mixin.mixins.common.galaxyspace;

import com.falsepattern.endlessids.mixin.helpers.BiomePatchHelper;
import com.falsepattern.endlessids.mixin.helpers.GalaxySpaceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.chunk.Chunk;

@Pseudo
@Mixin(targets = {"galaxyspace.systems.SolarSystem.satellites.mars.dimension.ChunkProviderMarsSS",
                  "galaxyspace.SolarSystem.satellites.mars.dimension.ChunkProviderMarsSS"},
       remap = false)
public abstract class ChunkProviderMarsSSMixin {

    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(method = "func_73154_d",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/chunk/Chunk;func_76605_m()[B"),
              require = 0,
              expect = 0)
    private byte[] setBiomesTweaked(Chunk chunk) {
        return BiomePatchHelper.getBiomeArrayTweaked(chunk, (i) -> GalaxySpaceHelper.GSSpace());
    }
}
