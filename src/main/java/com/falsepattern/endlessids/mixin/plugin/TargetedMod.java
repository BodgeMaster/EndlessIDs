package com.falsepattern.endlessids.mixin.plugin;

import com.google.common.io.Files;
import lombok.val;

import java.nio.file.Path;

public enum TargetedMod {

    VANILLA("Minecraft", "", "", true),
    UBC("Underground Biomes Constructs", "undergroundbiomesconstructs-", "", false),
    COFHLIB("CoFH Lib", "cofhlib-", "", false),
    DRAGONAPI("DragonAPI", "dragonapi", "", false),
    MFQM("More Fun Quicksand Mod", "morefunquicksandmod-", "", false),
    WORLDEDIT("WorldEdit", "worldedit-", "", false),
    ;

    public final String modName;
    public final String jarNamePrefixLowercase;
    public final String jarNameContainsLowercase;

    public final boolean loadInDevelopment;

    TargetedMod(String modName, String jarNamePrefix, String jarNameContains, boolean loadInDevelopment) {
        this.modName = modName;
        this.jarNamePrefixLowercase = jarNamePrefix.toLowerCase();
        this.jarNameContainsLowercase = jarNameContains.toLowerCase();
        this.loadInDevelopment = loadInDevelopment;
    }

    @SuppressWarnings("UnstableApiUsage")
    public boolean isMatchingJar(Path path) {
        val pathString = path.toString();
        val nameLowerCase = Files.getNameWithoutExtension(pathString).toLowerCase();
        val fileExtension = Files.getFileExtension(pathString);

        return "jar".equals(fileExtension) && nameLowerCase.startsWith(jarNamePrefixLowercase) && nameLowerCase.contains(jarNameContainsLowercase);
    }

    @Override
    public String toString() {
        return "TargetedMod{" +
                "modName='" + modName + '\'' +
                ", jarNamePrefixLowercase='" + jarNamePrefixLowercase + '\'' +
                '}';
    }
}
