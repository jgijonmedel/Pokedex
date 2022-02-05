package dev.jgm.pokedex.data.model

import dev.jgm.pokedex.R

enum class PokemonEggGroup (val text: Int){
    MONSTER(text = R.string.egg_monster),
    WATER1(text = R.string.egg_water1),
    WATER2(text = R.string.egg_water2),
    WATER3(text = R.string.egg_water3),
    BUG(text = R.string.type_bug),
    FLYING(text = R.string.type_flying),
    GROUND(text = R.string.type_ground),
    FAIRY(text = R.string.type_fairy),
    PLANT(text = R.string.egg_plant),
    HUMANSHAPE(text = R.string.egg_humanshape),
    MINERAL(text = R.string.egg_mineral),
    INDETERMINATE(text = R.string.egg_indeterminate),
    DITTO(text = R.string.egg_ditto),
    DRAGON(text = R.string.type_dragon),
    NO_EGGS(text = R.string.egg_no_eggs)
}