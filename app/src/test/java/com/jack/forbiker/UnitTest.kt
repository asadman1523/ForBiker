package com.jack.forbiker

import com.google.common.truth.Truth.assertThat
import com.jack.forbiker.utils.Utils
import org.junit.Test


class UnitTest {
    @Test
    fun addition_isCorrect() {
    }
    @Test
    fun testTWD97ToLatLon() {
        val pair = Utils.twd97tolatlon(302902.85270, 2768892.46050)
        assertThat(pair.first).isEqualTo(121.524212754977)
        assertThat(pair.second).isEqualTo(25.02719806741144)
    }
}