package com.falsepattern.endlessids.mixin.mixins.common.extendedplanets;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.falsepattern.endlessids.EndlessIDs;
import com.falsepattern.endlessids.mixin.helpers.BiomePatchHelper;
import com.falsepattern.endlessids.mixin.helpers.GalaxySpaceHelper;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

@Pseudo
@Mixin(targets = {"ExtendedPlanets.planets.appleapachia.ChunkProviderAppleapachia"},
       remap = false)
public abstract class ChunkProviderAppleapachiaMixin {
	@Shadow
	private BiomeGenBase[] biomesForGeneration;
//	private static final BiomeGenBase appleapachiaBiome;
//	static {
//		Class<?> clazz;
//		try {
//			clazz = Class.forName("ExtendedPlanets.biomes.BiomeGenBaseEP");
//		} catch (ClassNotFoundException ignored) {
//			EndlessIDs.LOG.warn("Failed to find class ExtendedPlanets.biomes.BiomeGenBaseEP - throwing exception.");
//			throw new IllegalStateException("Failed to find class ExtendedPlanets.biomes.BiomeGenBaseEP!");
//		}
//		
//		try {
//			appleapachiaBiome = (BiomeGenBase) clazz.getDeclaredField("appleapachia_plains").get(null);
//		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ignored) {
//			EndlessIDs.LOG.warn("Failed to get field appleapachia_plains from ExtendedPlanets.biomes.BiomeGenBaseEP - throwing exception.");
//			throw new IllegalStateException("Failed to get field appleapachia_plains from ExtendedPlanets.biomes.BiomeGenBaseEP!");
//		}
//	}
	
    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(method = "func_73154_d",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/chunk/Chunk;func_76605_m()[B"),
              require = 0,
              expect = 0)
    private byte[] setBiomesTweaked(Chunk chunk) {
    	return BiomePatchHelper.getBiomeArrayTweaked(chunk, biomesForGeneration);
//        return BiomePatchHelper.getBiomeArrayTweaked(chunk, (i) -> appleapachiaBiome);
    }
}
