package com.tua.boorugalleryzero.data.source

import com.tua.boorugalleryzero.domain.FileType
import com.tua.boorugalleryzero.domain.Post
import com.tua.boorugalleryzero.domain.Rating
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DanbooruJson(
    private val httpClient: HttpClient,
) : Client {

    override val name = "Danbooru.Json"
    override val clientId = Clients.DanbooruJson
    override val baseUrl = "https://testbooru.donmai.us/posts.json"
    override val referer = "https://danbooru.donmai.us"
    override val initPage = 1

    override suspend fun fetchPosts(page:Int,fetchQuantity:Int): Result<List<Post>> {

        return runCatching {
            httpClient.get(baseUrl) {
                url {
                    encodedParameters.apply {
                        append("limit",fetchQuantity.toString())
                        append("tags", "")
                        append("page",page.toString())
                    }
                }
            }.body<List<RemotePost>>().map { it.toDomainPost() }
        }

    }

    @Serializable
    private data class RemotePost(
        val id: Int,
        val md5: String = "",
        @SerialName("tag_string")
        val tagString: String,
        @SerialName("file_ext")
        val fileExt: String,
        @SerialName("file_url")
        val fileUrl: String = "",
        @SerialName("preview_file_url")
        val previewFileUrl: String = "",
        @SerialName("large_file_url")
        val largeFileUrl: String = "",
        @SerialName("created_at")
        val createdAt: String,
        @SerialName("image_width")
        val imageWidth: Int,
        @SerialName("image_height")
        val imageHeight: Int,
        @SerialName("parent_id")
        val parentId: Int?,
        val rating: RemoteRating
    ) {
        fun toDomainPost(): Post {
            return Post(
                id = id,
                md5 = md5,
                tags = tagString,
                fileExt = fileExt,
                fileUrl = fileUrl,
                previewUrl = previewFileUrl,
                sampleUrl = largeFileUrl,
                createdAt = createdAt,
                width = imageWidth,
                height = imageHeight,
                parentId = parentId,
                rating = when(rating) {
                    RemoteRating.General -> Rating.General
                    RemoteRating.Sensitive -> Rating.Sensitive
                    RemoteRating.Questionable -> Rating.Questionable
                    RemoteRating.Explicit -> Rating.Explicit
                },
                fileType = when(fileExt) {
                    "jpg", "png", "webp" -> FileType.Image
                    "gif" -> FileType.Gif
                    "webm", "mp4" -> FileType.Video
                    else -> FileType.Unsupported
                }
            )
        }
    }

    @Serializable
    private enum class RemoteRating {
        @SerialName("g") General,
        @SerialName("s") Sensitive,
        @SerialName("q") Questionable,
        @SerialName("e") Explicit;
    }

}