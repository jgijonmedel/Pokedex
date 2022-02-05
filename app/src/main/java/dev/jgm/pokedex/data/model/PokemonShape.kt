package dev.jgm.pokedex.data.model

import dev.jgm.pokedex.R


enum class PokemonShape(val text: Int) {
    BALL(text = R.string.shape_ball),
    SQUIGGLE(text = R.string.shape_squiggle),
    FISH(text = R.string.shape_fish),
    ARMS(text = R.string.shape_arms),
    BLOB(text = R.string.shape_blob),
    UPRIGHT(text = R.string.shape_upright),
    LEGS(text = R.string.shape_legs),
    QUADRUPED(text = R.string.shape_quadruped),
    WINGS(text = R.string.shape_wings),
    TENTACLES(text = R.string.shape_tentacles),
    HEADS(text = R.string.shape_heads),
    HUMANOID(text = R.string.shape_humanoid),
    BUG_WINGS(text = R.string.shape_bug_wings),
    ARMOR(text = R.string.shape_bug_armor)
}