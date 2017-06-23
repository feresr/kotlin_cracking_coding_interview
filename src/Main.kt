import java.util.*

/**
 * Created by feresr on 5/6/17.
 */
fun main(args: Array<String>) {

    /* 1.1
    print(hasUniqueCharacters("abcd")) */

    /* 1.2
    print(permCheck("dog", "god")) */

    /* 1.3
    print(URLify("Mr John Smith      ", 13)) */

    /* 1.5
    println(oneAway("pale", "ple"))    //true
    println(oneAway("pales", "pale"))  //true
    println(oneAway("pale", "bale"))   //true
    println(oneAway("pale", "bake"))   //false */

    /* 1.6
    print(stringCompression("aaabbbbc")) */

    /* 1.7
    rotateMatrix(arrayOf(
            intArrayOf(1, 2, 3, 0),
            intArrayOf(4, 5, 6, 0),
            intArrayOf(7, 8, 9, 0),
            intArrayOf(0, 0, 0, 0))) */

    /*1.8
    zeroMatrix(arrayOf(
            intArrayOf(1, 2, 5),
            intArrayOf(1, 0, 3),
            intArrayOf(5, 1, 3),
            intArrayOf(9, 9, 9))) */

    /* 1.9
    print(stringRotation("bottlewater", "erbottlewat")) */

    /* 2.6
    print(isPalindrome(LinkedList().add(5).add(1).add(5))) */
}

fun isPalindrome(linkedList: LinkedList): Boolean {
    val head = linkedList.head ?: return false
    val stack: Stack<Int> = Stack()

    var p1: Node? = head
    var p2: Node? = head

    while (p2?.next != null) {
        p2 = p2.next
        if (p2 != null) {
            stack.add(p1?.value)
            p2 = p2.next
            if (p2 != null) {
                p1 = p1?.next
            }
        }
    }

    p1 = p1?.next

    while (!stack.isEmpty()) {
        if (p1!= null && stack.pop() == p1.value) {
            p1 = p1.next
        } else {
            return false
        }
    }
    return true
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

fun zeroMatrix(matrix: Array<IntArray>): Array<IntArray> {

    if (matrix.isEmpty()) return matrix
    if (matrix.first().isEmpty()) return matrix

    val columns = HashSet<Int>()
    val rows = HashSet<Int>()
    for ((rowIndex, row) in matrix.withIndex()) {
        for ((columnIndex, value) in row.withIndex()) {
            if (value == 0) {
                columns.add(columnIndex)
                rows.add(rowIndex)
            }
        }
    }

    for (rowIndex in rows) {
        for ((columnIndex, _) in matrix.first().withIndex()) {
            matrix[rowIndex][columnIndex] = 0
        }
    }
    for (columnIndex in columns) {
        for ((rowIndex, _) in matrix.withIndex()) {
            matrix[rowIndex][columnIndex] = 0
        }
    }

//print, not part of the algorithm
    for (row in matrix) {
        for (value in row) {
            print(" $value ")
        }
        println()
    }
    return matrix
}

fun stringRotation(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) return false
    return (s2 + s2).contains(s1)
}

fun rotateMatrix(matrix: Array<IntArray>): Array<IntArray> {

    if (matrix.isNotEmpty() && matrix.first().isNotEmpty() && matrix.size == matrix.first().size) {
        val size = matrix.size
        for (layer in 0 until size / 2) {
            val end = size - layer - 1
            for (j in layer until end) {
                //top -> right
                val right = matrix[j][end]
                matrix[j][end] = matrix[layer][j]

                //right -> bottom
                val bottom = matrix[end][end - j]
                matrix[end][end - j] = right

                //bottom -> left
                val left = matrix[end - j][layer]
                matrix[end - j][layer] = bottom

                //left -> top
                matrix[layer][j] = left
            }
        }

        for (i in 0 until size) {
            for (j in 0 until size) {
                print(" ${matrix[i][j]}")
            }
            println()
        }
    }

    return matrix
}