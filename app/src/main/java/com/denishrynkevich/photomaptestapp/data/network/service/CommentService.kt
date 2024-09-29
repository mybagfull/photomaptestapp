package com.denishrynkevich.photomaptestapp.data.network.service

import com.denishrynkevich.photomaptestapp.data.models.comment.AddCommentRequest
import com.denishrynkevich.photomaptestapp.data.models.comment.CommentListResponse
import com.denishrynkevich.photomaptestapp.data.models.comment.CommentOutResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentService {

    @GET("image/{imageId}/comment")
    suspend fun getComments(
        @Path("imageId") imageId: Int,
        @Query("page") page: Int
    ): Response<CommentListResponse>

    @POST("image/{imageId}/comment")
    suspend fun addComment(
        @Body addCommentRequest: AddCommentRequest,
        @Path("imageId") imageId: Int
    ): Response<CommentOutResponse>

    @DELETE("image/{imageId}/comment/{commentId}")
    suspend fun deleteComment(
        @Path("imageId") imageId: Int,
        @Path("commentId") commentId: Int
    ): Response<CommentOutResponse>

}