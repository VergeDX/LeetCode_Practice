import java.util.*
import kotlin.collections.ArrayList

// https://leetcode-cn.com/problems/three-in-one-lcci/
class TripleInOne(val stackSize: Int) {
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
        if (this.size == this@TripleInOne.stackSize) return false
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

// https://leetcode-cn.com/problems/stack-of-plates-lcci/
class StackOfPlates(val cap: Int) {
    val stackList = ArrayList<Stack<Int>>()

    fun push(`val`: Int) {
        if (cap == 0) return

        // cap != 0 here.
        if (stackList.isEmpty() || stackList.last().size == cap)
            stackList += Stack<Int>().apply { push(`val`) }
        else stackList.last().push(`val`)
    }

    fun pop(): Int {
        // last() will raise exception if list empty.
        if (stackList.isEmpty()) return -1

        // Last stack size == 1, pop & remove it.
        if (stackList.last().size == 1) {
            val lastStack = stackList.last()
            val result = lastStack.pop()
            stackList.remove(lastStack)
            return result
        }

        // stackList.last() exists, and it size != 1.
        return stackList.last().pop()
    }

    fun popAt(index: Int): Int {
        // index is legal.
        if (stackList.size > index) {
            val stack = stackList[index]
            val result = stack.pop()
            if (stack.isEmpty()) stackList.remove(stack)
            return result
        }
        return -1
    }
}

// https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci/
class MyQueue() {
    val queue = ArrayList<Int>()

    fun push(x: Int) {
        queue += x
    }

    fun pop(): Int {
        return queue.removeAt(0)
    }

    fun peek(): Int {
        return queue[0]
    }

    fun empty(): Boolean {
        return queue.isEmpty()
    }
}

// https://leetcode-cn.com/problems/sort-of-stacks-lcci/
class SortedStack() {
    val innerStack = Stack<Int>()

    fun push(`val`: Int) {
        innerStack.push(`val`)

        // Only push can mess up the order of stack.
        innerStack.sortByDescending { it }
    }

    fun pop() {
        // Ignore EmptyStackException.
        innerStack.runCatching { pop() }
    }


    fun peek(): Int {
        return innerStack.runCatching { peek() }
            // Return -1 if stack empty.
            .getOrDefault(-1)
    }

    fun isEmpty(): Boolean {
        return innerStack.isEmpty()
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

    // Stack<Int>().last()
}
