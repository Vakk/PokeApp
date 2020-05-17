package com.vakk.network.bean

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import junit.framework.TestCase
import org.junit.Test
import org.junit.internal.runners.JUnit38ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit38ClassRunner::class)
class GetPokemonInfoBeanTest : TestCase() {

    @Test
    fun testMapping_1() {
        val mapper = jacksonObjectMapper()
        val json = getServerJson()
        val data = mapper.readValue<GetPokemonInfoBean>(json)
    }


    private fun getServerJson(): String {
        return String(
            javaClass.classLoader!!.getResourceAsStream("GetPokomenInfoBeanSample.json").readBytes()
        )
    }
}