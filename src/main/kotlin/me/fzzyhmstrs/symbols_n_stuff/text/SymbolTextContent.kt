/*
 * Copyright (c) 2024 Fzzyhmstrs
 *
 * This file is part of Modifier Core, a mod made for minecraft; as such it falls under the license of Modifier Core.
 *
 * Modifier Core is free software provided under the terms of the Timefall Development License - Modified (TDL-M).
 * You should have received a copy of the TDL-M with this software.
 * If you did not, see <https://github.com/fzzyhmstrs/Timefall-Development-Licence-Modified>.
 */

package me.fzzyhmstrs.symbols_n_stuff.text

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.fzzyhmstrs.symbols_n_stuff.mixins.accessor.KeyBindingAccessor
import net.minecraft.text.MutableText
import net.minecraft.text.StringVisitable
import net.minecraft.text.Style
import net.minecraft.text.TextContent
import net.minecraft.util.Identifier
import java.util.*

/**
 * Text Content that displays a series of glyph-texture symbols based inputs
 * @author fzzyhmstrs
 * @since 0.1.0
 */
class SymbolTextContent private constructor(internal val ids: List<String>): TextContent {

    private val str: String
        get() {
            return (ids.joinToString("") {
                (KeyBindingAccessor.getKEYS_BY_ID()[it] as? KeyBindingAccessor)?.boundKey?.code?.plus(0xe000)?.toChar()?.toString() ?: if(it.length == 1) it else null ?: SymbolMap.get(it)
            })
        }

    override fun getType(): TextContent.Type<*> {
        return TYPE
    }

    override fun <T : Any?> visit(visitor: StringVisitable.Visitor<T>): Optional<T> {
        return visitor.accept(str)
    }

    override fun <T : Any?> visit(visitor: StringVisitable.StyledVisitor<T>, style: Style): Optional<T> {
        return visitor.accept(STYLE, str)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SymbolTextContent
        return ids == other.ids
    }

    override fun hashCode(): Int {
        return ids.hashCode()
    }

    override fun toString(): String {
        return "symbol{ids=$ids}"
    }


    companion object {

        /**
         * Builds text with [SymbolTextContent] that will display the given symbols from the [SymbolMap] in the order provided
         * @return [MutableText]
         * @author fzzyhmstrs
         * @since 0.1.0
         */
        @JvmStatic
        fun of(vararg ids: SymbolMap): MutableText {
            return MutableText.of(SymbolTextContent(ids.map { it.id() }))
        }

        /**
         * Builds text with [SymbolTextContent] that will display the given symbols based on string representation in the order provided.
         * @return [MutableText]
         * @author fzzyhmstrs
         * @since 0.1.0
         */
        @JvmStatic
        fun of(vararg ids: String): MutableText {
            return MutableText.of(SymbolTextContent(ids.toList()))
        }

        private val FONT_ID: Identifier = Identifier.of("symbols_n_stuff", "symbols")!!
        private val STYLE: Style = Style.EMPTY.withFont(FONT_ID)

        private val CODEC = RecordCodecBuilder.mapCodec { instance: RecordCodecBuilder.Instance<SymbolTextContent> ->
            instance.group(
                Codec.STRING.listOf().fieldOf("ids").forGetter { s -> s.ids }
            ).apply(instance) { id -> SymbolTextContent(id) }
        }

        val TYPE = TextContent.Type(CODEC, "symbol")
    }
}