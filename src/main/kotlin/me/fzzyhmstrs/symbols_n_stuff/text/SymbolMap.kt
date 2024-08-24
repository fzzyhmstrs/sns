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
import com.mojang.serialization.DataResult
import me.fzzyhmstrs.symbols_n_stuff.text.SymbolTextContent
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.StringIdentifiable
import java.util.*

enum class SymbolMap(private val id: String, private val chr: List<String>, private val isSocket: Boolean = true): StringIdentifiable {

    //general reserved \uE800, and \uE801 to \uE8FF
    //Currently in use \uE801-23 to \uE829-45

    //Emi Loot: \uE700 to \uE7FF

    // general symbols \uE901 to \uE9FF
    INFO("symbol.info", "\uE901", false),
    F3T("input.f3.t", "\uE902", false),
    UNKNOWN("symbol.unknown", "\uE903", false),
    PROJECTILE_HIT_BLOCK("symbol.projectile_hit_block", "\uE904", false),
    PROJECTILE_HIT_ENTITY("symbol.projectile_hit_entity", "\uE905", false),
    ATTRIBUTE("symbol.attribute", "\uE906", false),
    ENCHANTMENT("symbol.enchantment", "\uE907", false),
    TICK("symbol.tick", "\uE908", false),
    ON_ADD("symbol.on_add", "\uE909", false),
    ON_REMOVE("symbol.on_remove", "\uE90A", false),
    STATUS("symbol.status", "\uE90B", false),
    ON_HEAL("symbol.on_heal", "\uE90C", false),
    ON_IGNITE("symbol.on_ignite", "\uE90D", false),
    ON_FROZEN("symbol.on_frozen", "\uE90E", false),
    DAMAGE_SOURCE("symbol.damage_source", "\uE90F", false),
    PET_DAMAGE_SOURCE("symbol.pet_damage_source", "\uE910", false),
    ATTACK("symbol.attack", "\uE911", false),
    DAMAGED("symbol.damaged", "\uE912", false),
    KILL("symbol.kill", "\uE913", false),
    DEATH("symbol.death", "\uE914", false),
    PET_ATTACK("symbol.pet_attack", "\uE915", false),
    PET_DAMAGED("symbol.pet_damaged", "\uE916", false),
    PET_DEATH("symbol.pet_death", "\uE917", false),
    PET_KILL("symbol.pet_kill", "\uE918", false),
    ADD_DROPS("symbol.add_drops", "\uE919", false),
    MODIFY_DROP("symbol.modify_drop", "\uE91A", false),

    // Currently keybinds.png and large_keybinds.png
    // keybinds reserved \uE000 to \uE3FF
    SHIFT("input.shift", "\uE154", false),
    CONTROL("input.control", "\uE155", false),
    ALT("input.alt", "\uE156", false),

    // animated sockets 1, \uEA00 to \uEAFF
    RUNEFUSED_GEM("item.simplyswords.runefused_gem", listOf("\uEA00", "\uEA01", "\uEA02", "\uEA03", "\uEA04", "\uEA05", "\uEA06", "\uEA07", "\uEA00", "\uEA00", "\uEA00", "\uEA00", "\uEA00", "\uEA00", "\uEA00", "\uEA00")),
    NETHERFUSED_GEM("item.simplyswords.netherfused_gem", listOf("\uEA08", "\uEA09", "\uEA0A", "\uEA0B", "\uEA0C", "\uEA0D", "\uEA0E", "\uEA0F")),

    // minecraft basic gems, basic sockets \uEB00 to \uEB01
    EMPTY_SOCKET("socket.empty", "\uEB00", false),
    FALLBACK_SOCKETABLE("socketable.fallback", "\uEB01", false),

    // minecraft basic gems, \uEB02 to \uEB09
    NETHER_QUARTZ("item.minecraft.nether_quartz", "\uEB02"),
    DIAMOND("item.minecraft.diamond", "\uEB03"),
    EMERALD("item.minecraft.emerald", "\uEB04"),
    LAPIS_LAZULI("item.minecraft.lapis.lazuli", "\uEB05"),
    PRISMARINE("item.minecraft.prismarine", "\uEB06"),
    REDSTONE("item.minecraft.redstone", "\uEB07"),
    AMETHYST_SHARD("item.minecraft.amethyst_shard", "\uEB08"),
    GLOWSTONE("item.minecraft.glowstone", "\uEB09"),

    // minecraft items \uEB0A to \uEB4F
    BLAZE_POWDER("item.minecraft.blaze_powder", "\uEB0A"),
    CLOCK("item.minecraft.clock", "\uEB0B"),
    COAL("item.minecraft.coal", "\uEB0C"),
    COMPASS("item.minecraft.compass", "\uEB0D"),
    COOKIE("item.minecraft.cookie", "\uEB0E"),
    CRYING_OBSIDIAN("item.minecraft.crying_obsidian", "\uEB0F"),
    DRAGON_EGG("item.minecraft.dragon_egg", "\uEB10"),
    DRAGON_BREATH("item.minecraft.dragon_breath", "\uEB11"),
    EGG("item.minecraft.egg", "\uEB12"),
    END_CRYSTAL("item.minecraft.end_crystal", "\uEB13"),
    ENDER_EYE("item.minecraft.ender_eye", "\uEB14"),
    ENDER_PEARL("item.minecraft.ender_pearl", "\uEB15"),
    EXPERIENCE_BOTTLE("item.minecraft.experience_bottle", "\uEB16"),
    FERMENTED_SPIDER_EYE("item.minecraft.fermented_spider_eye", "\uEB17"),
    FIRE_CHARGE("item.minecraft.fire_charge", "\uEB18"),
    FIREWORK_STAR("item.minecraft.firework_star", "\uEB19"),
    GHAST_TEAR("item.minecraft.ghast_tear", "\uEB1A"),
    OCHRE_FROGLIGHT("item.minecraft.ochre_froglight", "\uEB1B"),
    PEARLESCENT_FROGLIGHT("item.minecraft.pearlescent_froglight", "\uEB1C"),
    VERDANT_FROGLIGHT("item.minecraft.verdant_froglight", "\uEB1D"),
    HEART_OF_THE_SEA("item.minecraft.heart_of_the_sea", "\uEB1E"),
    HEAVY_CORE("item.minecraft.heavy_core", "\uEB1F"),
    HONEY_BOTTLE("item.minecraft.honey_bottle", "\uEB20"),
    LODESTONE("item.minecraft.lodestone", "\uEB21"),
    MAGMA_CREAM("item.minecraft.magma_cream", "\uEB22"),
    NAUTILUS_SHELL("item.minecraft.nautilus_shell", "\uEB23"),
    NETHER_STAR("item.minecraft.nether_star", "\uEB24"),
    OMINOUS_BOTTLE("item.minecraft.ominous_bottle", "\uEB25"),
    OMINOUS_TRIAL_KEY("item.minecraft.ominous_trial_key", "\uEB26"),
    POPPED_CHORUS_FRUIT("item.minecraft.popped_chorus_fruit", "\uEB27"),
    PUFFER_FISH("item.minecraft.puffer_fish", "\uEB28"),
    RECOVERY_COMPASS("item.minecraft.recovery_compass", "\uEB29"),
    SCULK("item.minecraft.sculk", "\uEB2A"),
    SCUTE("item.minecraft.scute", "\uEB2B"),
    SLIME_BALL("item.minecraft.slime_ball", "\uEB2C"),
    SNIFFER_EGG("item.minecraft.sniffer_egg", "\uEB2D"),
    SNOWBALL("item.minecraft.snowball", "\uEB2E"),
    TNT("item.minecraft.tnt", "\uEB2F"),
    TRIAL_KEY("item.minecraft.trial_key", "\uEB30"),
    WIND_CHARGE("item.minecraft.wind_charge", "\uEB31"),


    // music discs \uEB50 to \uEB8F
    MUSIC_DISC_5("item.minecraft.music_disc_5", "\uEB50"),
    MUSIC_DISC_11("item.minecraft.music_disc_11", "\uEB51"),
    MUSIC_DISC_13("item.minecraft.music_disc_13", "\uEB52"),
    MUSIC_DISC_BLOCKS("item.minecraft.music_disc_blocks", "\uEB53"),
    MUSIC_DISC_CATS("item.minecraft.music_disc_cats", "\uEB54"),
    MUSIC_DISC_CHIRP("item.minecraft.music_disc_chirp", "\uEB55"),
    MUSIC_DISC_CREATOR("item.minecraft.music_disc_creator", "\uEB56"),
    MUSIC_DISC_CREATOR_MUSIC_BOX("item.minecraft.music_disc_creator_music_box", "\uEB57"),
    MUSIC_DISC_FAR("item.minecraft.music_disc_far", "\uEB58"),
    MUSIC_DISC_MALL("item.minecraft.music_disc_mall", "\uEB59"),
    MUSIC_DISC_MELLOHI("item.minecraft.music_disc_mellohi", "\uEB5A"),
    MUSIC_DISC_OTHERSIDE("item.minecraft.music_disc_otherside", "\uEB5B"),
    MUSIC_DISC_PIGSTEP("item.minecraft.music_disc_pigstep", "\uEB5C"),
    MUSIC_DISC_PRECIPICE("item.minecraft.music_disc_precipice", "\uEB5D"),
    MUSIC_DISC_RELIC("item.minecraft.music_disc_relic", "\uEB5E"),
    MUSIC_DISC_STAL("item.minecraft.music_disc_stal", "\uEB5F"),
    MUSIC_DISC_STRAD("item.minecraft.music_disc_strad", "\uEB60"),
    MUSIC_DISC_WAIT("item.minecraft.music_disc_wait", "\uEB61"),
    MUSIC_DISC_WARD("item.minecraft.music_disc_ward", "\uEB62"),

    //effect-based icons \uEB90 to \uEBAF
    CONDUIT_POWER("buff.minecraft.conduit_power", "\uEB90"),
    INVISIBILITY("buff.minecraft.invisibility", "\uEB91"),
    POISON("buff.minecraft.poison", "\uEB92"),
    SOUL("buff.minecraft.soul", "\uEB93"),
    SCULK_SOUL("buff.minecraft.sculk_soul", "\uEB94"),

    //mob heads \uEBB0 to \uEBCF
    SKELETON("item.minecraft.skeleton_skull", "\uEBB0"),
    CREEPER("item.minecraft.creeper_head", "\uEBB1"),
    PIGLIN("item.minecraft.piglin_head", "\uEBB2"),
    WITHER_SKELETON("item.minecraft.wither_skeleton_skull", "\uEBB3"),
    ZOMBIE("item.minecraft.zombie_head", "\uEBB4"),
    ENDER_DRAGON("item.minecraft.dragon_head", "\uEBB5"),
    HAMPTER("hampter.hampter", "\uEBB6"),
    PLAYER("item.minecraft.player_head", "\uEBB7"),
    RAINE("doggo.raine", "\uEBB8"),

    //amethyst imbuement \uEBD0 to \uEBEF
    CELESTINE("item.amethyst_imbuement.celestine", "\uEBD0"),
    MOONSTONE("item.amethyst_imbuement.moonstone", "\uEBD1"),
    DANBURITE("item.amethyst_imbuement.danburite", "\uEBD2"),
    IRIDESCENT_ORB("item.amethyst_imbuement.iridescent_orb", "\uEBD3"),
    LUSTROUS_SPHERE("item.amethyst_imbuement.lustrous_sphere", "\uEBD4"),
    MALACHITE_FIGURINE("item.amethyst_imbuement.malachite_figurine", "\uEBD5"),
    ACCURSED_FIGURINE("item.amethyst_imbuement.accursed_figurine", "\uEBD6"),
    BRILLIANT_DIAMOND("item.amethyst_imbuement.brilliant_diamond", "\uEBD7"),
    HEARTSTONE("item.amethyst_imbuement.heartstone", "\uEBD8"),
    MANA_POTION("item.amethyst_imbuement.mana_potion", "\uEBD9"),
    GOLDEN_HEART("item.amethyst_imbuement.golden_heart", "\uEBDA"),
    CRYSTALLINE_HEART("item.amethyst_imbuement.crystalline_heart", "\uEBDB"),
    SMOKY_QUARTZ("item.amethyst_imbuement.smoky_quartz", "\uEBDC"),
    SARDONYX("item.amethyst_imbuement.sardonyx", "\uEBDD"),
    PYRITE("item.amethyst_imbuement.pyrite", "\uEBDE"),
    OPAL("item.amethyst_imbuement.opal", "\uEBDF"),
    CHORSE_CHIT("item.amethyst_imbuement.chorse_chit", "\uEBE0"),

    // dwagon and metals \uEBF0 to \uEC3F
    DRAGON_SCALE("mats.dragon_scale", "\uEBF0"),
    ADAMANTINE_DRAGON_SCALE("mats.adamantine_coated_dragon_scale", "\uEBF1"),
    SHADOW_ADAMANTINE_DRAGON_SCALE("mats.shadow_adamantine_coated_dragon_scale", "\uEBF2"),
    PLATINUM("mats.platinum", "\uEBF3"),
    ADAMANTINE("mats.adamantine", "\uEBF4"),
    SHADOW_ADAMANTINE("mats.shadow_adamantine", "\uEBF5"),
    MYTHRIL("mats.mythril", "\uEBF6"),
    TUNGSTEN("mats.tungsten", "\uEBF7"),
    PALLADIUM("mats.palladium", "\uEBF8"),
    DARK_STEEL("mats.dark_steel", "\uEBF9"),
    ELVEN_STEEL("mats.elven_steel", "\uEBFA"),
    STEEL("mats.steel", "\uEBFB"),
    TIN("mats.tin", "\uEBFC"),
    COLD_IRON("mats.cold_iron", "\uEBFD"),
    INFERNAL_IRON("mats.infernal_iron", "\uEBFE"),
    BRONZE("mats.bronze", "\uEBFF"),
    SILVER("mats.silver", "\uEC00"),
    LEAD("mats.lead", "\uEC01"),
    ZINC("mats.zinc", "\uEC02"),
    STAR_METAL("mats.star_metal", "\uEC03"),
    ARANDUR("mats.arandur", "\uEC04"),
    ELECTRUM("mats.electrum", "\uEC05"),
    GLACIAL("mats.glacial", "\uEC06"),
    BONE("mats.bone", "\uEC07"),
    COPPER("mats.copper", "\uEC08"),
    GOLD("mats.gold", "\uEC09"),
    IRON("mats.iron", "\uEC0A"),
    NETHERITE("mats.netherite", "\uEC0B"),
    AQUARIUM("mats.aquarium", "\uEC0C"),
    KYBER("mats.kyber", "\uEC0D"),
    MORKITE("mats.morkite", "\uEC0E"),
    PROMETHEUM("mats.prometheum", "\uEC0F"),
    STAR_PLATINUM("mats.star_platinum", "\uEC10"),
    STARRITE("mats.starrite", "\uEC11"),
    STORMYX("mats.stormyx", "\uEC12"),
    BANGLUM("mats.banglum", "\uEC13"),
    CARMOT("mats.carmot", "\uEC14"),
    DURASTEEL("mats.durasteel", "\uEC15"),
    HALLOWED("mats.hallowed", "\uEC16"),
    MANGANESE("mats.manganese", "\uEC17"),
    MIDAS_GOLD("mats.midas_gold", "\uEC18"),
    ORICHALCUM("mats.orichalcum", "\uEC19"),
    OSMIUM("mats.osmium", "\uEC1A"),
    QUADRILLUM("mats.quadrillum", "\uEC1B"),
    RUNITE("mats.runite", "\uEC1C"),
    VOIDSTEEL("mats.voidsteel", "\uEC1D"),
    PURESTEEL("mats.puresteel", "\uEC1E"),
    //conjuring
    //mariums soulslike
    //better end - metals, amber gem, eternal crystal, enchanted materials, etc.
    //wired redstone
    //tns
    //galosphere
    //mythic mounts feathers
    //MCDX
    //timefall coin
    //spelunkery
    //everlasting
    //totem of undying
    //aquamirae
    //bewitchment?
    //companion bats - spirit crystal
    //enderman overhaul - the custom eyesss
    //fabric waystones
    //hexed
    //illager invasion
    //spectrum
    //

    //elements \uEC40 to \uEC4F
    MAGMA("vibe.magma", "\uEC40"),
    ICE("vibe.ice", "\uEC41"),
    WATER("vibe.water", "\uEC42"),
    DEMONIC("vibe.demonic", "\uEC43"),
    NATURE("vibe.nature", "\uEC44"),
    HOLY("vibe.holy", "\uEC45"),
    DARK("vibe.dark", "\uEC46"),

    // generic gems \uEC50 to \uEC7F
    PEARL("gems.pearl", "\uEC50"),
    CITRINE("gems.citrine", "\uEC51"),
    AMETRINE("gems.ametrine", "\uEC52"),
    SAPPHIRE("gems.sapphire", "\uEC53"),
    FIRE_OPAL("gems.fire_opal", "\uEC54"),
    ALEXANDRITE("gems.alexandrite", "\uEC55"),
    GARNET("gems.garnet", "\uEC56"),
    YELLOW_GARNET("gems.yellow_garnet", "\uEC57"),
    BLACK_DIAMOND("gems.black_diamond", "\uEC58"),
    LONSDALEITE("gems.lonsdaleite", "\uEC59"),
    CHRYSOBERYL("gems.chrysoberyl", "\uEC5A"),
    JADE("gems.jade", "\uEC5B"),
    RUBY("gems.ruby", "\uEC5C"),
    AQUAMARINE("gems.aquamarine", "\uEC5D"),
    BLUE_TOPAZ("gems.blue_topaz", "\uEC5E"),
    TOPAZ("gems.topaz", "\uEC5F"),
    TANZANITE("gems.tanzanite", "\uEC60"),

    // runes \uEC80 to \uEC8F
    ARCANE_STONE("item.runes.arcane_stone", "\uEC80"),
    FIRE_STONE("item.runes.fire_stone", "\uEC81"),
    FROST_STONE("item.runes.frost_stone", "\uEC82"),
    HEALING_STONE("item.runes.healing_stone", "\uEC83"),
    LIGHTNING_STONE("item.runes.lightning_stone", "\uEC84"),
    SOUL_STONE("item.runes.soul_stone", "\uEC85"),

    // colors \uEC90 to \uECAF
    RED("hues.red", "\uEC90"),
    ORANGE("hues.orange", "\uEC91"),
    YELLOW("hues.yellow", "\uEC92"),
    LIME("hues.lime", "\uEC93"),
    GREEN("hues.green", "\uEC94"),
    BLUE_GREEN("hues.blue_green", "\uEC95"),
    CYAN("hues.cyan", "\uEC96"),
    LIGHT_BLUE("hues.light_blue", "\uEC97"),
    BLUE("hues.blue", "\uEC98"),
    PURPLE("hues.purple", "\uEC99"),
    MAGENTA("hues.magenta", "\uEC9A"),
    PINK("hues.pink", "\uEC9B"),
    RAINBOW("hues.rainbow", "\uEC9C"),
    WHITE("hues.white", "\uEC9D"),
    LIGHT_GRAY("hues.light_gray", "\uEC9E"),
    GRAY("hues.gray", "\uEC9F"),
    BLACK("hues.black", "\uECA0"),
    BROWN("hues.brown", "\uECA1");

    constructor(id: String, chr: String, isSocket: Boolean = true): this(id, listOf(chr), isSocket)

    init {
        if (id.length == 1) throw IllegalArgumentException("SymbolMap ID can't be one or fewer characters long")
    }

    fun id(): String {
        return id
    }

    fun getChar(counter: Long): String {
        if (chr.size == 1) return chr[0]
        val index = counter % chr.size
        return chr[index.toInt()]
    }

    override fun asString(): String {
        return id
    }

    fun asText(): MutableText {
        return SymbolTextContent.of(id)
    }

    companion object {

        private val symbolEnums: Map<String, SymbolMap> by lazy {
            SymbolMap.entries.associateBy { it.id }
        }

        private var counter = 0L

        fun increment() {
            counter++
        }

        fun get(id: String): String {
            return getEnum(id)?.getChar(counter) ?: "\uE902"
        }

        fun getEnum(id: String): SymbolMap? {
            return symbolEnums[id]
        }

        private val itemStackMap: MutableMap<String, SymbolMap> = mutableMapOf()

        fun getSymbolFromItem(itemStack: ItemStack?): SymbolMap {

            if (itemStack == null) return FALLBACK_SOCKETABLE
            if (itemStack.isEmpty) return FALLBACK_SOCKETABLE

            val translationKey = itemStack.translationKey

            // try to pull from the symbol map exactly, or try to pull from the map cache
            val symbolTry = getEnum(translationKey) ?: itemStackMap[translationKey]
            if (symbolTry != null) return symbolTry //exact item match

            // scores potential matches by the character length matching, with full section matching getting a bigger score.
            val scores: SortedSet<Pair<Float, SymbolMap>> = TreeSet { (first), (first1) -> first.compareTo(first1) }

            // using only the item identifier to avoid automatic biasing to the "item" symbols.
            // using on the path of the identifier to avoid biasing by mod_ids that happen to have matches, without actually informing what the item is overall.
            // For example "amethyst_imbuement" would strongly bias to "item.minecraft.amethyst_shard" because of an exact word match of "amethyst"
            // splits on common separators... includes /, which is not used in the map ids but could be in item ids.
            val keyPieces = Registries.ITEM.getId(itemStack.item).path.split(".", "_", "/")

            // iterate over the symbol map to score the item against potential matches
            for (symbol in SymbolMap.entries.filter { it.isSocket }) {

                //split on common separators to compare individual id "phrases"
                val idPieces = symbol.id().split(".", "_")
                var score = 0

                //iterate over both string lists to check individual comparisons
                for (idPiece in idPieces) {
                    for (keyPiece in keyPieces) {
                        if (keyPiece == idPiece) {
                            //big bonus for exact equality, like if there's a "tin_ingot", the "tin" matching exactly should matter
                            //probably will need to mess with the multiplier to not over-bias incidental matching
                            score += (idPiece.length * 3)
                            continue
                        }
                        if (keyPiece.contains(idPiece) || idPiece.contains(keyPiece)) {
                            score += idPiece.length
                        }
                    }
                }

                if (score > 0) {
                    scores.add(Pair(score / symbol.id().length.toFloat(), symbol))
                }
            }

            if (scores.isNotEmpty()) {
                // store the best-matching find in the cache
                itemStackMap[translationKey] = scores.last().second
                //return that result
                return scores.last().second
            }

            return FALLBACK_SOCKETABLE
        }

        /*val CODEC: Codec<SymbolMap> = StringIdentifiable.createCodec { SymbolMap.entries.toTypedArray() }

        val SYMBOL_CODEC: Codec<Text> = Codec.withAlternative(SymbolMap.CODEC.flatComapMap(
            { s -> SymbolTextContent.of(s.id()) },
            { t -> try { DataResult.success(SymbolMap.getEnum((t.content as SymbolTextContent).ids[0])) ?: throw IllegalStateException("failed to resolve symbol") } catch (e: Throwable) { DataResult.error { "Invalid text content" } }  }
        ),
            TextCodecs.CODEC)*/
    }
}