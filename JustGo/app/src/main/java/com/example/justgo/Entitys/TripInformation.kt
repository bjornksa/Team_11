package com.example.justgo.Entitys

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDateTime

abstract class TripInformation(name:String, value:Any) : Serializable{
    var name:String = name
    var value = value
}

class TripDates(name: String="Dates") : TripInformation(name, value=""){
    @RequiresApi(Build.VERSION_CODES.O)
    public var dates : MutableMap<LocalDateTime, String> = mutableMapOf()
}

class CoTravellersList() : TripInformation("Co-Travellers", ""){
    var coTravellersList : ArrayList<CoTraveller> = ArrayList()

    fun addCoTraveller(coTraveller: CoTraveller){
        coTravellersList.add(coTraveller)
    }
    fun deleteCoTraveller(coTraveller: CoTraveller){
        coTravellersList.remove(coTraveller)
    }
}

class PictureVideoList() : TripInformation("Pictures and Videos", ""){
    var picturesAndVideos:ArrayList<Picture> = ArrayList()


    fun addPictureVideo(pictureOrVideo: Uri, type: PictureVideoType){
        picturesAndVideos.add(Picture(pictureOrVideo,type))
    }

    fun getPicturesVideosList(beforeOrFromType: PictureVideoType): ArrayList<Uri>{
        val returnList: ArrayList<Uri> = ArrayList()
        picturesAndVideos.forEach {
            if(it.type_==beforeOrFromType){
                returnList.add(it.uri)
            }
        }

        return returnList
    }

    fun deletePictureOrVideo(toDelete : Uri?, type : PictureVideoType? = null)
    {
        picturesAndVideos.forEach {
            if(it.uri_.equals(toDelete)){
                picturesAndVideos.remove(it)
                return
            }
        }
    }
}

class TemplateTripinfo(name: String) : TripInformation(name , "")


class TripDestination(name : String="Locations") : TripInformation(name, value=""){
    public var destinations : MutableList<Destination> = mutableListOf()
}

class TripFood(name : String="Foods") : TripInformation(name, value=""){
    public var foods : MutableList<Food> = mutableListOf()

    fun getFood(foodType: FoodType): ArrayList<Food>{
        var returnList: ArrayList<Food> = ArrayList()
        println("testtesttest")
        foods.forEach {
            println(it.toString())
            if(it._foodType == foodType){
                returnList.add(it)
            }
        }
        return returnList
    }

    fun deleteFood(id:Int){
        var found : Food? = null
        for (food in foods) {
            if(food.foodID == id)
            {
                found = food
            }
        }
        if(found != null){
            foods.remove(found)
        }
    }
}