package chucknorris

import java.lang.Exception

fun main() {
    var i = 0
    while (i == 0) {
        println("Please input operation (encode/decode/exit):")
        val str = readln()
        when (str) {
            "encode" -> {
                println("Input string:")
                val input = readln().trim()
                println("Encoded string:")
                println(encode(input))
                println()
            }

            "decode" -> {
                println("Input encoded string:")
                val input = readln().trim()
                println(decoder(input))
                println()
            }

            "exit" -> {
                println("Bye!")
                break
            }
            else -> {
                println("There is no '$str' operation")
                println()
            }
        }
    }
}

fun encode(input: String): String {
    var n = 0
    var zero = "0"
    var output = ""
    var b = ""
    var c = ""
    while (n in 0..input.length - 1) {
        val a = Integer.toBinaryString(input[n].toInt())
        c = String.format("%07d", a.toInt())
        b += c
        n++
    }
    val _input = b + "22"
    n = 0
    while (n in 0.._input.length - 2) {
        if (_input[n] == _input[n + 1]) {
            zero += "0"
            n++
        } else {
            if (_input[n] == '1') {
                output += "0 $zero "
                n++
                zero = "0"
            } else {
                output += "00 $zero "
                n++
                zero = "0"
            }
        }
    }
    return output
}

fun decoder(input: String): String {
    try {
        val _input = input.split(" ")
        var n = 0
        var output = ""
        while (n in 0.._input.size - 1) {
            if (_input[n] == "0") {
                val a = _input[n + 1]
                var i = 0
                while (i in 0..a.length - 1) {
                    output += "1"
                    i++
                }
            } else if (_input[n] == "00") {
                output += _input[n + 1]
            } else {
                return "Encoded string is not valid."
            }
            n += 2
        }
        if (output.length < 7 || output.length % 7 != 0) {
            return "Encoded string is not valid."
        }
        var c = 0
        var k = ""
        val a = output.chunked(7)
        var i = 0
        while (i in 0..a.size - 1) {
            c = Integer.parseInt(a[i], 2)
            k += c.toChar().toString()
            i++
        }
        return "Decoded string:\n$k"
    } catch (e: Exception) {
        return "Encoded string is not valid."
    }
}
