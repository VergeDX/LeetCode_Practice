import java.util.*

// https://leetcode-cn.com/problems/three-in-one-lcci/
class TripleInOne(stackSize: Int) {
    val temp = stackSize
    val stackOne = Stack<Int>()
    val stackTwo = Stack<Int>()
    val stackThree = Stack<Int>()

    private fun whichStack(stackNum: Int): Stack<Int> {
        return when (stackNum) {
            0 -> stackOne
            1 -> stackTwo
            2 -> stackThree
            else -> throw AssertionError()
        }
    }

    private fun Stack<Int>.canPush(): Boolean {
        if (this.size == this@TripleInOne.temp) return false
        return true
    }

    fun push(stackNum: Int, value: Int) {
        val stack = whichStack(stackNum)
        if (stack.canPush()) stack.push(value)
    }

    fun pop(stackNum: Int): Int {
        val stack = whichStack(stackNum)
        return stack.runCatching { pop() }.getOrDefault(-1)
    }

    fun peek(stackNum: Int): Int {
        val stack = whichStack(stackNum)
        return stack.runCatching { peek() }.getOrDefault(-1)
    }

    fun isEmpty(stackNum: Int): Boolean {
        return whichStack(stackNum).isEmpty()
    }
}

// https://leetcode-cn.com/problems/min-stack-lcci/
class MinStack() {
    val stack = Stack<Int>()

    fun push(x: Int) {
        stack.push(x)
    }

    fun pop() {
        stack.pop()
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return Collections.min(stack)
    }
}

fun main() {
    val tio = TripleInOne(1)
    tio.apply {
        push(0, 1)
        push(0, 2)
        pop(0)
        pop(0)
        pop(0)
        isEmpty(0)
    }
}
