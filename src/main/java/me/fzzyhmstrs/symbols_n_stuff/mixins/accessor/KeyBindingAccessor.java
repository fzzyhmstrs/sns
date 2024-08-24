/*
 * Copyright (c) 2024 Fzzyhmstrs
 *
 * This file is part of Modifier Core, a mod made for minecraft; as such it falls under the license of Modifier Core.
 *
 * Modifier Core is free software provided under the terms of the Timefall Development License - Modified (TDL-M).
 * You should have received a copy of the TDL-M with this software.
 * If you did not, see <https://github.com/fzzyhmstrs/Timefall-Development-Licence-Modified>.
 */

package me.fzzyhmstrs.symbols_n_stuff.mixins.accessor;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(KeyBinding.class)
public interface KeyBindingAccessor {
    @Accessor
    static Map<String, KeyBinding> getKEYS_BY_ID() {
        throw new UnsupportedOperationException();
    }

    @Accessor
    InputUtil.Key getBoundKey();
}