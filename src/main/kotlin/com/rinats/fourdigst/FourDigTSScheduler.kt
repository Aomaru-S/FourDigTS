package com.rinats.fourdigst

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList

@Component
class FourDigTSScheduler(
    @Autowired
    private val digTextRepository: DigTextRepository
) {
    @Scheduled(fixedRate = 60000)
    fun organizeDigText() {
        val digTextList = digTextRepository.findAll()
        val deleteIndexList = ArrayList<Int>()
        val now = Date()
        for ((i, e) in digTextList.withIndex()) {
            if (e.expire < now) {
                deleteIndexList.add(i)
            }
        }
        deleteIndexList.reverse()
        deleteIndexList.forEach {
            digTextList.removeAt(it)
        }
        digTextRepository.deleteAll()
        digTextRepository.saveAll(digTextList)
    }
}