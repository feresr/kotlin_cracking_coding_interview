/**
 * Created by feresr on 5/6/17.
 */

fun main(args: Array<String>) {

    // 1.1
    //print(hasUniqueCharacters("abcd"))

    // 1.2
    //print(permCheck("dog", "god"))

    // 1.3
    //print(URLify("Mr John Smith      ", 13))

    // 1.5
    //println(oneAway("pale", "ple"))     //true
    //println(oneAway("pales", "pale"))   //true
    //println(oneAway("pale", "bale"))    //true
    //println(oneAway("pale", "bake"))    //false

    //1.6
    //print(stringCompression("aaabbbbc"))
}

fun hasUniqueCharacters(s1: String): Boolean {
    if (s1.length > 128) return false //ASCII (assumption)

    val charset = BooleanArray(128)
    s1.forEach { c ->
        if (charset[c.toInt()]) return false
        charset[c.toInt()] = true
    }

    return true
}

fun permCheck(s1: String, s2: String): Boolean {

    if (s1.length != s2.length) return false

    val m = IntArray(128) //ASCII (assumption)

    for (c in s1) {
        m[c.toInt()] += 1
    }

    for (c in s2) {
        if (m[c.toInt()] == 0) return false
        m[c.toInt()] -= 1
    }

    return true
}

fun URLify(s: String, length: Int): String {

    val ca: CharArray = s.toCharArray()
    var space_count = ca.copyOfRange(0, length).count { it == ' ' }

    for (index in (length - 1) downTo 0) {
        val newIndex = index + space_count * 2 // "%20".length - " ".length = 2
        if (ca[index] == ' ') {
            ca[newIndex] = '0'
            ca[newIndex - 1] = '2'
            ca[newIndex - 2] = '%'
            space_count--
        } else {
            ca[newIndex] = ca[index]
        }
    }

    return ca.toString()
}

fun oneAway(s1: String, s2: String): Boolean {
    if (Math.abs(s1.length - s2.length) > 1) return false

    var allowedChanges = 1
    if (s1.length == s2.length) {
        for ((index, value) in s1.withIndex()) {
            if (s2[index] != value) {
                if (allowedChanges > 0) {
                    allowedChanges--
                } else {
                    return false
                }
            }
        }
        return true
    } else {
        val longer: String
        val shorter: String

        if (s1.length > s2.length) {
            longer = s1
            shorter = s2
        } else {
            shorter = s1
            longer = s2
        }
        var diff = 0
        for ((i, v) in shorter.withIndex()) {
            if (v != longer[i + diff]) {
                if (diff < allowedChanges) {
                    diff++
                    if (v != longer[i + diff]) {
                        return false
                    }
                } else {
                    return false
                }
            }
        }

        return true
    }
}

fun stringCompression(s1: String): String {
    //check if it's worth it
    var compressedLength = 0
    var repeatCount = 0
    for (i in 0 until s1.length) {
        repeatCount++
        if (i + 1 >= s1.length || s1[i] != s1[i + 1]) {
            compressedLength += repeatCount.toString().length + 1
            repeatCount = 0
        }
    }

    if (compressedLength < s1.length) {
        //worth it
        val sb = StringBuilder(compressedLength)
        repeatCount = 0
        for (i in 0 until s1.length) {
            repeatCount++
            if (i + 1 >= s1.length || s1[i] != s1[i + 1]) {
                sb.append(s1[i]).append(repeatCount)
                repeatCount = 0
            }
        }

        return sb.toString()
    } else {
        return s1
    }
}