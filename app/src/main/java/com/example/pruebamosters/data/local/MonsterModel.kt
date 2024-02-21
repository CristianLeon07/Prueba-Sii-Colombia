package com.example.pruebamosters.data.local


data class Monster(
    var id: Int? = 0,
    var name: String? = "",
    var type: String? = "",
    var frameType: String? = "",
    var desc: String? = "",
    var atk: Int? = 0,
    var def: Int? = 0,
    var level: Int? = 0,
    var race: String? = "",
    var attribute: String? = "",
    var archetype: String? = "",
    var ygoprodeck_url: String? = "",
    var card_set: List<CardSet>? = emptyList(),
    var banlist_info: Banlist?,
    var card_images: List<CardImage>? = emptyList(),
    var card_prices: List<CardPrices>? = emptyList(),
)

data class CardSet(
    var set_name: String? = "",
    var set_code: String? = "",
    var set_rarity: String? = "",
    var set_rarity_code: String? = "",
    var set_price: String? = "",
)

data class Banlist(
    var ban_tcg: String? = "",
    var ban_ocg: String? = "",
)

data class CardImage(
    var id: Int? = 0,
    var image_url: String? = "",
    var image_url_small: String? = "",
    var image_url_cropped: String? = "",

    )

data class CardPrices(
    var cardmarket_price: String? = "",
    var tcgplayer_price: String? = "",
    var ebay_price: String? = "",
    var amazon_price: String? = "",
    var coolstuffinc: String? = "",

    )
