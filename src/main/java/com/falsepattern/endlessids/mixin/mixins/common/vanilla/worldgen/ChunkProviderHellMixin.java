package com.falsepattern.endlessids.mixin.mixins.common.vanilla.worldgen;

import com.falsepattern.endlessids.mixin.helpers.BiomePatchHelper;
import com.falsepattern.endlessids.mixin.helpers.IChunkMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.MapGenNetherBridge;

import java.util.Random;

@Mixin(ChunkProviderHell.class)
public abstract class ChunkProviderHellMixin implements IChunkProvider {
    private BiomeGenBase[] bgb;

    @Inject(method = "provideChunk",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/world/chunk/Chunk;getBiomeArray()[B",
                     shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILHARD,
            require = 1)
    private void setBiomesTweaked1(int p_73154_1_, int p_73154_2_, CallbackInfoReturnable<Chunk> cir, Block[] ablock, byte[] meta, BiomeGenBase[] abiomegenbase, Chunk chunk) {
        bgb = abiomegenbase;

    }

    @Redirect(method = "provideChunk",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/chunk/Chunk;getBiomeArray()[B"),
              require = 1)
    private byte[] setBiomesTweaked2(Chunk chunk) {
        try {
            return BiomePatchHelper.getBiomeArrayTweaked(chunk, bgb);
        } finally {
            bgb = null;
        }
    }
}
