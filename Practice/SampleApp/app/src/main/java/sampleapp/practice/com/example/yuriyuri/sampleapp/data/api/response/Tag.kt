package sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable


@JsonSerializable
data class Tag(
        @Json(name = "followers_count")
        var followersCount: Int,
        @Json(name = "icon_url")
        var tagIconImageUrl: String?,
        @Json(name = "id")
        var tagId: String,
        @Json(name = "items_count")
        var itemsCount: Int
)
