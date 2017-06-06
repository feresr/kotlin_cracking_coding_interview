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
