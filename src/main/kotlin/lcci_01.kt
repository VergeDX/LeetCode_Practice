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

}

fun main() {
    println(canPermutePalindrome("code"))

    println(oneEditAway("pale", "ple"))
    println(oneEditAway("pales", "pal"))
    println(oneEditAway("a", "ab"))
}
