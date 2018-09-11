package com.gemini.starwar.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StarWarApi {

    @GET("/api/people/")
    Call<CharacterListResponseSchema> allCharacterList();

    @GET("/api/people/?")
    Call<SingleCharacterResponseSchema> characterDetails(@Query("search") String name);


    @GET("/api/films/{id}")
    Call<FilmsSchema> filmDetails(@Path("id") Integer date);

}
