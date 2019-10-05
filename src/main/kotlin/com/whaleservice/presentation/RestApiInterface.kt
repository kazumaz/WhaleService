package com.whaleservice.presentation


import com.whaleservice.domain.Email
import com.whaleservice.domain.Password
import com.whaleservice.domain.UserService
import com.whaleservice.infrastructure.RedisUserRepositoryImpl
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("score")
class ScoreController(val log: Logger) {

    @PostMapping("create")
    fun createScore(@RequestBody scoreData: ScoreData) {
        println(scoreData.beat)
        println(scoreData.partScores[0].abcdata)
    }

}

data class ScoreData(
        val title: String,
        val musicKey: String,
        val length: String,
        val beat: String,
        val composer: String,
        val bpm: String,
        val partScores: List<PartScoreData>
)

data class PartScoreData (
        val partName: String,
        val key: String,
        val midiprogram: String,
        val abcdata: String
)

data class Loginform(
        val email: String,
        val password: String
)
