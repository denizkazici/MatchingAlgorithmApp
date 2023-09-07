package com.deniz.term_project.service

import com.deniz.term_project.model.*
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class services {
    private val BASE_URL = "http://10.0.2.2:5000"
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()
    private val api = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    val Service = api.create(ApiService::class.java)

    fun getTeamList(): Single<List<team>> {
        return Service.getTeamList()
    }

    fun getRoadList(): Single<List<road>> {
        return Service.getRoadList()
    }
    fun getBuildingList(): Single<List<building>> {
        return Service.getBuildingList()
    }
    fun getUserList(): Single<List<user>> {
        return Service.getUserList()
    }
    fun getGroupList(): Single<List<group>>{
        return Service.getGroupList()
    }
    fun getUser(name: String, password: String): Single<user> {
        return Service.login(LoginRequest(name, password))
    }
    fun getUser(id: String): Single<user> {
        return Service.getUser(id)
    }
    fun  getMatchingBuilding(lat: String, lng: String, id: String? = null): Single<building> {
        if(id != null ){
            return Service.getMatchingBuilding(lat, lng, id)
        }else{
            return Service.getMatchingBuilding(lat, lng)
        }

    }
    fun getBuilding(lat: String, lng: String, team_id: String, group_id : String): Single<building>{
        return Service.getBuilding(lat, lng, team_id, group_id)
    }
    fun test(id : String) : Single<building>{
        return Service.test(id)
    }
    fun createBuildingList (merkezlat: String, merkezlng: String, distance: String, count: String) : Single<ResponseMessage> {
        return Service.createBuildingList(merkezlat, merkezlng, distance, count)
    }
    fun createUserList() : Single<ResponseMessage> {
        return Service.createUserList()
    }
    fun createTeamList (merkezlat: String, merkezlng: String, distance: String, count: String) : Single<ResponseMessage> {
        return Service.createTeamList(merkezlat, merkezlng, distance, count)
    }
    fun createGroupList() :Single<ResponseMessage> {
        return Service.createGroupList()
    }
    fun createRoadList(id : String) :Single<ResponseMessage> {
        return Service.createRoadList(id)
    }
    fun getGrupedList ( id : String) : Single<List<building>> {
        return Service.getGroupedList(id)
    }
    fun change(id : String, name : String, password: String) :Single<ResponseMessage>{
        return Service.change(id, name, password)
    }
    fun delete () : Single<ResponseMessage> {
        return Service.delete()
    }
    fun distance(lat: String, lng: String, merkezlat: String, merkezlng: String, distance: String) :Single<ResponseMessage> {
        return Service.distance(lat, lng, merkezlat, merkezlng, distance)
    }
    fun complete(building_id :String, team_id: String) : Single<ResponseMessage>{
        return Service.complete(building_id, team_id)
    }
    fun getEarthquake() : Single<Algorithm> {
        return Service.getEarthquake()
    }
    fun updateUser (lat: String, lng: String, id : String): Single<ResponseMessage> {
        return Service.updateUser(lat, lng, id)
    }
}