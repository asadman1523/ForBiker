package com.jack.forbiker.utils

import kotlin.math.*

object Utils {
    fun twd97tolatlon(x: Double, y: Double) : Pair<Double, Double>{
        val a = 6378137.0
        val b = 6356752.314245
        val long_0 = 121 * PI / 180.0
        val k0 = 0.9999
        val dx = 250000
        val dy = 0
        var xx = x
        var yy = y

        val e = (1-b.pow(2)/ a.pow(2)).pow(0.5)

        xx -= dx
        yy -= dy

        val M = yy / k0

        val mu = M / ( a*(1-e.pow(2)/4 - 3*e.pow(4)/64 - 5 * e.pow(6)/256))
        val e1 = (1.0 - (1 - e.pow(2)).pow(0.5)) / (1.0 +(1.0 -e.pow(2)).pow(0.5))

        val j1 = 3*e1/2-27*e1.pow(3)/32
        val j2 = 21 * e1.pow(2)/16 - 55 * e1.pow(4)/32
        val j3 = 151 * e1.pow(3)/96
        val j4 = 1097 * e1.pow(4)/512

        val fp = mu + j1 * sin(2*mu) + j2 * sin(4* mu) + j3 * sin(6*mu) + j4 * sin(8* mu)

        val e2 = (e*a/b).pow(2)
        val c1 = (e2*cos(fp)).pow(2)
        val t1 = tan(fp).pow(2)
        val r1 = a * (1-e.pow(2)) / (1-e.pow(2)* sin(fp).pow(2)).pow(3.0/2.0)
        val n1 = a / (1-e.pow(2)*sin(fp).pow(2)).pow(0.5)
        val d = xx / (n1*k0)

        val q1 = n1* tan(fp) / r1
        val q2 = d.pow(2)/2
        val q3 = ( 5 + 3 * t1 + 10 * c1 - 4 * c1.pow(2) - 9 * e2 ) * d.pow(4)/24
        val q4 = (61 + 90 * t1 + 298 * c1 + 45 * t1.pow(2) - 3 * c1.pow(2) - 252 * e2) * d.pow(6)/720
        var lat = fp - q1 * (q2 - q3 + q4)

        val q5 = d
        val q6 = (1+2*t1+c1) * d.pow(3) / 6
        val q7 = (5 - 2 * c1 + 28 * t1 - 3 * c1.pow(2) + 8 * e2 + 24 * t1.pow(2)) * d.pow(5) / 120
        var lng = long_0 + (q5 - q6 + q7) / cos(fp)

        lat = (lat*180) / PI
        lng = (lng*180) / PI

        return Pair(lng, lat)
    }
}