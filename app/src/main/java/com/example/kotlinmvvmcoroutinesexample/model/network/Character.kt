package com.example.kotlinmvvmcoroutinesexample.model.network

import com.squareup.moshi.Json

/**
 * @author Lior Hazael
 */
data class Character(
 @Json(name="name") // This annotation allows to define from which json key this field should take its value from - in this case they are the same.
 val name : String,
 @Json(name="image") // This annotation allows to define from which json key this field should take its value from - in this case they are the same.
 val image: String
)

data class CharacterResponse(
 @Json(name = "results")
val result: List<Character>
)