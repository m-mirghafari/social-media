package com.sky.social.data

import com.sky.social.utils.Utils
import javax.inject.Inject


class ApiMockImpl @Inject constructor() : ApiMock {


    override fun getVideos(): List<VideoData>? {
        return listOf(
            VideoData(
                id = "vide 01",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/d7a540c3387702295959804547/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/d7a540c3387702295959804547/QwUFWAi4sy0A.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            ),
            VideoData(
                id = "vide 02",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/d7a540bc387702295959804540/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/d7a540bc387702295959804540/ySHClak1o80A.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            ),
            VideoData(
                id = "vide 03",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/889f56c7387702295958746592/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/889f56c7387702295958746592/35vG0gXfrVwA.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            ),
            VideoData(
                id = "vide 04",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/889f568c387702295958746579/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/889f568c387702295958746579/JT3Wy2euaWYA.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            ),
            VideoData(
                id = "vide 05",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/889f5684387702295958746571/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/889f5684387702295958746571/xHlafL7zMYcA.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            ),
            VideoData(
                id = "vide 06",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/889f5664387702295958746562/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/889f5664387702295958746562/Y13Ia5P90I0A.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            ),
            VideoData(
                id = "vide 07",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/889f5642387702295958746551/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/889f5642387702295958746551/3leiuUGMwG4A.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            ),
            VideoData(
                id = "vide 01",
                thumbnail = "http://1307889028.vod2.myqcloud.com/3c791431vodtransuse1307889028/889f5608387702295958746539/coverBySnapshot/coverBySnapshot_10_0.jpg",
                videoUrl = "http://1307889028.vod2.myqcloud.com/88517189voduse1307889028/889f5608387702295958746539/cDXm3I08XpoA.mp4",
                likes = Utils.generateRandomNumber(),
                views = Utils.generateRandomNumber()
            )
        )
    }
}