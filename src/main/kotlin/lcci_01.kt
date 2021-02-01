import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set
import kotlin.math.abs


// https://leetcode-cn.com/problems/is-unique-lcci/
fun isUnique(astr: String): Boolean {
    // All character, maybe not in English...?
    // if (astr.length > 24) return false
    astr.forEachIndexed { index, c -> if (astr.indexOf(c) != index) return false }
    return true
}

// https://leetcode-cn.com/problems/check-permutation-lcci/
fun CheckPermutation(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) return false
    if (s1.toCharArray().sorted() == s2.toCharArray().sorted()) return true
    return false
}

// https://leetcode-cn.com/problems/string-to-url-lcci/
fun replaceSpaces(S: String, length: Int): String {
    // substring: [start, end)
    val tempString = S.substring(0, length)
    return tempString.replace(" ", "%20")
}

// https://leetcode-cn.com/problems/palindrome-permutation-lcci/
fun canPermutePalindrome(s: String): Boolean {
    val tempMap = HashMap<Char, Int>()
    s.forEach { tempMap[it] = (tempMap[it] ?: 0) + 1 }
    val groupCount = tempMap.values.count { it % 2 != 0 }
    return groupCount == s.length % 2
}

// https://leetcode-cn.com/problems/one-away-lcci/
fun oneEditAway(first: String, second: String): Boolean {
    if (first == second) return true
    if (first.length == second.length) {
        var diffCount = 0
        for (pair in first zip second)
            if (pair.first != pair.second)
                diffCount++

        // Exclude cases 0.
        return diffCount == 1
    }
    if (abs(first.length - second.length) == 1) {
        val longest = if (first.length > second.length) first else second
        val another = if (longest == first) second else first
        if (another.isEmpty()) return true

        var indexLongest = 0
        var indexAnother = 0
        while (indexAnother != another.length) {
            if (longest[indexLongest] == another[indexAnother]) {
                indexLongest++
                indexAnother++
                continue
            } else {
                // Error more than one times.
                if (indexLongest != indexAnother) return false

                indexLongest++
                continue
            }
        }

        return true
    }

    return false
}

// https://leetcode-cn.com/problems/compress-string-lcci/
fun compressString(S: String): String {
    if (S.isEmpty()) return ""

    var currentChar = S[0]
    var currentCount = 0

    var result = ""
    S.forEach {
        if (it != currentChar) {
            result += "${currentChar}${currentCount}"
            currentChar = it
            currentCount = 1
        } else currentCount++
    }

    result += "${currentChar}${currentCount}"
    return if (result.length >= S.length) S else result
}

// https://leetcode-cn.com/problems/rotate-matrix-lcci/
fun rotate(matrix: Array<IntArray>): Unit {
    if (matrix.isEmpty()) return

    // size x size matrix.
    val sizeIndex = matrix.size - 1

    val tempMatrix = matrix.map { it.clone() }
    tempMatrix.forEachIndexed { index, ints ->
        val insertIndex = sizeIndex - index
        for (i in 0..sizeIndex) {
            matrix[i][insertIndex] = ints[i]
        }
    }
}

// https://leetcode-cn.com/problems/zero-matrix-lcci/
fun setZeroes(matrix: Array<IntArray>): Unit {
    if (matrix.isEmpty()) return

    // Matrix: m x n.
    val m = matrix.size
    val n = matrix[0].size

    val zeroRowIndex = LinkedHashSet<Int>()
    val zeroColumnIndex = LinkedHashSet<Int>()
    matrix.forEachIndexed { rowIndex, ints ->
        ints.forEachIndexed { columnIndex, i ->
            if (i == 0) {
                zeroRowIndex.add(rowIndex)
                zeroColumnIndex.add(columnIndex)
            }
        }
    }

    zeroRowIndex.forEach { matrix[it] = IntArray(n) { 0 } }
    zeroColumnIndex.forEach { matrix.forEach { ints -> ints[it] = 0 } }
}

// https://leetcode-cn.com/problems/string-rotation-lcci/
fun isFlipedString(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) return false
    if (s1.isEmpty()) return true

    // Rotate string to right given times, times should < string length.
    fun rotate(rawString: String, times: Int): String {
        val targetIndex = rawString.length - times
        return rawString.substring(targetIndex) + rawString.substring(0, targetIndex)
    }

    for (i in 1 until s1.length)
        if (rotate(s1, i) == s2)
            return true

    // s1.length == 1 && s1 == s2.
    if (s1 == s2) return true
    return false
}

fun main() {
    println(canPermutePalindrome("code"))

    println(oneEditAway("pale", "ple"))
    println(oneEditAway("pales", "pal"))
    println(oneEditAway("a", "ab"))

    rotate(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))

    setZeroes(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1)))
    setZeroes(arrayOf(intArrayOf(0, 1, 2, 0), intArrayOf(3, 4, 5, 2), intArrayOf(1, 3, 1, 5)))
}
