/*
 * Copyright (c) 2024 Fzzyhmstrs
 *
 * This file is part of Modifier Core, a mod made for minecraft; as such it falls under the license of Modifier Core.
 *
 * Modifier Core is free software provided under the terms of the Timefall Development License - Modified (TDL-M).
 * You should have received a copy of the TDL-M with this software.
 * If you did not, see <https://github.com/fzzyhmstrs/Timefall-Development-Licence-Modified>.
 */

package me.fzzyhmstrs.symbols_n_stuff.mixins;

import me.fzzyhmstrs.symbols_n_stuff.text.SymbolTextContent;
import net.minecraft.text.TextCodecs;
import net.minecraft.text.TextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Arrays;

@Mixin(TextCodecs.class)
public class TextCodecsMixin {

    @ModifyVariable(method = "createCodec", index = 1, at = @At("STORE"))
    private static TextContent.Type<?>[] modifier_core_addSymbolTextType(TextContent.Type<?>[] types) {
        TextContent.Type<?>[] intermediate = Arrays.copyOf(types, types.length + 1);
        intermediate[intermediate.length - 1] = SymbolTextContent.Companion.getTYPE();
        return intermediate;
    }

}