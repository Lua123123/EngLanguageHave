package com.example.englanguage.network;

import com.example.englanguage.model.login.Login;
import com.example.englanguage.model.login.UserLogin;
import com.example.englanguage.model.signup.SignUp;
import com.example.englanguage.model.topic.Topic;
import com.example.englanguage.model.vocabulary.DeleteVocabulary;
import com.example.englanguage.model.vocabulary.SuccessInsertVocabulary;
import com.example.englanguage.model.vocabulary.Vocabulary;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    public static final String DOMAIN = "http://21.64.13.104/quanlytuvung/public/api/";
    API api = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API.class);

    //USER
    @FormUrlEncoded
    @POST("auth/signup")
    Call<SignUp> postUsers(@Field("email") String email,
                           @Field("password") String password,
                           @Field("name") String name,
                           @Field("password_confirmation") String password_confirmation);

    @POST("auth/login")
    Call<Login> getUsers(@Body UserLogin userLogin);

    //TOPIC
    @FormUrlEncoded
    @POST("topic/getAllTopic")
    Call<Topic> getTopics(@Field("user_create") int user_create);

    //VOCABULARY
    @FormUrlEncoded
    @POST("vocabulary/getVocabulary")
    Call<Vocabulary> getVocabulary(@Field("user_create") int user_create,
                                   @Field("search") String search,
                                   @Field("page") String page);

    @FormUrlEncoded
    @POST("vocabulary/getAllVocabularyByUserId")
    Call<Vocabulary> getVocabularyOfTopic(@Field("user_create") int user_create,
                                          @Field("topic_id") int topic_id);

    @FormUrlEncoded
    @POST("vocabulary/insertVocabulary")
    Call<SuccessInsertVocabulary> insertVocabulary(@Header("Authorization") String Authorization,
                                                   @Field("word") String word,
                                                   @Field("mean") String mean,
                                                   @Field("image_path") String image_path,
                                                   @Field("example") String example,
                                                   @Field("id_wordtype") int id_wordtype,
                                                   @Field("user_create") int user_create);

    @FormUrlEncoded
    @POST("vocabulary/deleteVocabulary")
    Call<DeleteVocabulary> deleteVocabulary(@Header("Authorization") String Authorization,
                                            @Field("user_create") int user_create,
                                            @Field("word") String word);
}
