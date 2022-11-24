package com.example.fashionboomer.dto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface RetrofitInterface {

    // 로그인 요청 시 post
    @POST("/v11/members")
    Call<DataModel.ResponseMember> getResponseMember(@Body DataModel.Member member);

    // 옷 목록 이미지 보여주기 get
    @GET("/v11/clothes/images/{category}/{details}/{id}")
    @Streaming
    Call<ResponseBody> getClothImage(
            @Path("category") String category,
            @Path("details") String details,
            @Path("id") int clothId);

    // 옷 상세 정보 보여주기 get
    @GET("/v11/clothes/list/{category}/{details}")
    @Streaming
    Call<ClothBean.ResponseCloth> getClothDetails(
            @Path("category") String category,
            @Path("details") String details,
            @Query("page") int page,
            @Query("size") int size);

    // 옷장
    @GET("/v11/closets/images/{closet_id}")
    @Streaming
    Call<ResponseBody> downloadImage(
            @Path("closet_id") int closetId);

    @GET("/v11/closets/images/nukki/{closet_id}")
    @Streaming
    Call<ResponseBody> downloadNukkiImage(
            @Path("closet_id") int closetId);

    @GET("/v11/closets/{closet_id}")
    @Streaming
    Call<DataModel.Data> getCloset(
            @Path("closet_id") int closetId);

    @GET("/v11/closets/members/{member_id}")
    @Streaming
    Call<DataModel.PageData> getMemberClosets(
            @Path("member_id") int memberId,
            @Query("page") int page,
            @Query("size") int size);
}