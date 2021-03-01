import kotlin.math.max

// https://leetcode-cn.com/problems/insert-into-bits-lcci/
fun insertBits(N: Int, M: Int, i: Int, j: Int): Int {
    // https://stackoverflow.com/questions/50173028/how-to-get-binary-representation-of-int-in-kotlin
    var rawNReversed = Integer.toBinaryString(N).reversed()
    val insertM = Integer.toBinaryString(M)

    if (rawNReversed.length <= insertM.length) return M

    val replaceLength = j - i + 1

    // Maybe we should insert 0 to last. @see println(insertBits(1143207437, 1017033, 11, 31)).
    // Extreme case, j >= rawNReversed.length, means should += "0".
    if (j >= rawNReversed.length) rawNReversed += "0".repeat(j - rawNReversed.length + 1)

    val afterCleaned = rawNReversed.replaceRange(IntRange(i, j), "0".repeat(replaceLength))

    val newLength = insertM.length
    val newRange = IntRange(i, i + newLength - 1)
    val afterReplaced = afterCleaned.replaceRange(newRange, insertM.reversed())

    val resultString = afterReplaced.reversed()
    return Integer.parseInt(resultString, 2)
}

// https://leetcode-cn.com/problems/reverse-bits-lcci/
fun reverseBits(num: Int): Int {
    var binary = Integer.toBinaryString(num)
    while (binary.length < 32) binary += "0"

    val result = ArrayList<Int>()
    for (index in binary.indices) {
        val check = binary.replaceRange(IntRange(index, index), "1")
        check.split("0").map { it.length }.max()?.let { result += it }
    }

    return result.max()!!
}

// https://leetcode-cn.com/problems/closed-number-lcci/
fun findClosedNumbers(num: Int): IntArray {
    TODO()
}

// https://leetcode-cn.com/problems/convert-integer-lcci/
fun convertInteger(A: Int, B: Int): Int {
    var aBinary = Integer.toBinaryString(A)
    var bBinary = Integer.toBinaryString(B)

    // Fill width, maxLength can also be constant 32.
    val maxLength = max(aBinary.length, bBinary.length)
    while (aBinary.length < maxLength) aBinary = "0$aBinary"
    while (bBinary.length < maxLength) bBinary = "0$bBinary"

    var result = 0
    for ((aChar, bChar) in aBinary zip bBinary)
        if (aChar != bChar) result++

    return result
}

// https://leetcode-cn.com/problems/exchange-lcci/
fun exchangeBits(num: Int): Int {
    var binary = Integer.toBinaryString(num)
    if (binary.length % 2 != 0) binary = "0$binary"

    for (index in 0 until binary.length step 2)
        binary = binary.replaceRange(IntRange(index, index + 1), "${binary[index + 1]}${binary[index]}")

    return Integer.parseInt(binary, 2)
}

fun main() {
    // println(insertBits(1024, 19, 2, 6))
    // println(insertBits(0, 31, 0, 4))
    println(insertBits(1143207437, 1017033, 11, 31))

    println(reverseBits(2147483647))

    println(exchangeBits(1))
}
