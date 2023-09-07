package com.deniz.term_project.service

import com.deniz.term_project.model.*
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Single<user>

    @GET("userList")
    //fun getUsers(): Single<List<user>>
    fun getUserList(): Single<List<user>>

    @GET("/user/{id}")
    fun getUser(@Path("id") id: String): Single<user>

    @GET("buildingList")
    fun getBuildingList(): Single<List<building>>

    @GET("teamList")
    fun getTeamList(): Single<List<team>>

    @GET("roadList")
    fun getRoadList(): Single<List<road>>

    @GET("/groupList")
    fun getGroupList () : Single<List<group>>

    @GET("/groupedList")
    fun getGroupedList (
        @Query("id") id :String
    ) : Single<List<building>>

    @GET("/algorithm")
    fun getMatchingBuilding(
        @Query("lat") lat: String,
        @Query("long") lng: String,
        @Query("id") id: String? = null
    ): Single<building>

    @GET("/getBuilding")
    fun getBuilding(
        @Query("lat") lat: String,
        @Query("long") lng: String,
        @Query("team_id") team_id: String,
        @Query("group_id") group_id: String
    ): Single<building>

    @GET("/algorithm")
    fun test(
        @Query("id") id: String
    ): Single<building>


    @GET("/adminBuildingList")
    fun createBuildingList(
        @Query("merkezlat") merkezlat: String,
        @Query("merkezlng") merkezlng: String,
        @Query("distance") distance: String,
        @Query("count") count: String
    ): Single<ResponseMessage>

    @GET("/createUserList")
    fun createUserList() : Single<ResponseMessage>

    @GET("/adminTeamList")
    fun createTeamList(
        @Query("merkezlat") merkezlat: String,
        @Query("merkezlng") merkezlng: String,
        @Query("distance") distance: String,
        @Query("count") count: String
    ) : Single<ResponseMessage>

    @GET("/createGroupList")
    fun createGroupList() : Single<ResponseMessage>

    @GET("/createDestroyedList")
    fun createRoadList (
        @Query("id") id :String
    ) : Single<ResponseMessage>

    @GET("/change")
    fun change(
        @Query("id") id :String,
        @Query("name") name :String,
        @Query("password") password :String
    ): Single<ResponseMessage>

    @GET("/deleteDataset")
    fun delete () : Single<ResponseMessage>

    @GET("/distance")
    fun distance(
        @Query("lat") lat: String,
        @Query("lng") lng: String,
        @Query("merkezlat") merkezlat: String,
        @Query("merkezlng") merkezlng: String,
        @Query("distance") distance: String
    ) : Single<ResponseMessage>

    @GET("/setCompleted")
    fun complete(
        @Query("building_id") building_id :String,
        @Query("team_id") team_id :String
    ): Single<ResponseMessage>

    @GET("/getEarthquake")
    fun getEarthquake() : Single<Algorithm>

    @GET("/updateUser")
    fun updateUser(
        @Query("lat") lat: String,
        @Query("lng") lng: String,
        @Query("id") id : String
    ) : Single<ResponseMessage>

}



