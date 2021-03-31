package org.hydev.lcci

import ListNode.Companion.joinNode
import java.math.BigInteger

// https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
class ListNode(var `val`: Int) {
    var next: ListNode? = null

    companion object {
        fun removeDuplicateNodes(head: ListNode?): ListNode? {
            if (head == null) return null

            // Linked Hash Set has order.
            val emptyOrderedSet = LinkedHashSet<Int>()

            // Here, head != null, so current value = head.`val`.
            var currentNode = head
            while (currentNode != null) {
                // For each node's val.
                val currentVal = currentNode.`val`
                emptyOrderedSet.add(currentVal)
                currentNode = currentNode.next
            }

            val resultNodeList = emptyOrderedSet.map { ListNode(it) }
            resultNodeList.forEachIndexed { index, listNode ->
                if (index != resultNodeList.size - 1) listNode.next = resultNodeList[index + 1]
            }

            return resultNodeList[0]
        }

        fun ListNode?.joinNode(x: Int): ListNode {
            if (this == null) return ListNode(x)

            var currentNode = this
            while (currentNode != null) {
                if (currentNode.next == null) break
                currentNode = currentNode.next
            }

            // Here, currentNode.next == null.
            currentNode!!.next = ListNode(x)

            return this
        }

        // https://leetcode-cn.com/problems/partition-list-lcci/
        fun partition(head: ListNode?, x: Int): ListNode? {
            if (head == null) return head
            if (head.next == null) return head

            var smallPart: ListNode? = null
            var bigPart: ListNode? = null

            var currentNode = head
            while (currentNode != null) {
                val currentVal = currentNode.`val`

                when {
                    currentVal < x -> smallPart = smallPart.joinNode(currentVal)
                    else -> bigPart = bigPart.joinNode(currentVal)
                }

                currentNode = currentNode.next
            }

            currentNode = smallPart
            while (currentNode != null) {
                if (currentNode.next == null) break
                currentNode = currentNode.next
            }

            currentNode?.next = bigPart
            return smallPart ?: bigPart
        }

        // https://leetcode-cn.com/problems/sum-lists-lcci/
        fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
            var nl1 = ""
            var nl2 = ""

            var currentNode = l1
            while (currentNode != null) {
                val currentVal = currentNode.`val`
                nl1 += currentVal.toString()
                currentNode = currentNode.next
            }
            currentNode = l2
            while (currentNode != null) {
                val currentVal = currentNode.`val`
                nl2 += currentVal.toString()
                currentNode = currentNode.next
            }

            // Null check.
            if (nl1.isBlank()) nl1 = "0"
            if (nl2.isBlank()) nl2 = "0"

            // result  maybe 0.
            val bnl1 = BigInteger(nl1.reversed())
            val bnl2 = BigInteger(nl2.reversed())
            val resultStringReversed = bnl1.add(bnl2).toString().reversed()

            // Then turn result.toString().reversed() -> ListNode.
            val result = ListNode(resultStringReversed[0].toString().toInt())
            // if (result.`val`.toString().length == 1) return result

            currentNode = result
            for (index in resultStringReversed.indices) {
                // Index == 0, continue.
                if (index == 0) continue

                val tempNode = ListNode(resultStringReversed[index].toString().toInt())
                currentNode!!.next = tempNode
                currentNode = tempNode
            }

            return result
        }

        // https://leetcode-cn.com/problems/palindrome-linked-list-lcci/
        fun isPalindrome(head: ListNode?): Boolean {
            val InputNumberList = ArrayList<Int>()

            var currentNode = head
            while (currentNode != null) {
                val currentVal = currentNode.`val`
                // if (currentVal < 0) input += "-"
                InputNumberList += currentVal
                currentNode = currentNode.next
            }

            return InputNumberList == InputNumberList.reversed()
        }
    }
}

// https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
fun kthToLast(head: ListNode?, k: Int): Int {
    val resultList = ArrayList<Int>()
    var currentNode = head
    while (currentNode != null) {
        val currentVal = currentNode.`val`
        resultList.add(currentVal)
        currentNode = currentNode.next
    }

    return resultList[resultList.size - k]
}

fun main() {
    val example1 = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply {
                next = ListNode(3).apply {
                    next = ListNode(2).apply {
                        next = ListNode(1)
                    }
                }
            }
        }
    }
    val example2 = ListNode(1).apply {
        next = ListNode(1).apply {
            next = ListNode(1).apply {
                next = ListNode(1).apply {
                    next = ListNode(2)
                }
            }
        }
    }
    ListNode.removeDuplicateNodes(example1)
    ListNode.removeDuplicateNodes(example2)

    ListNode(0).joinNode(2)
    ListNode.partition(example1, 3)

    val nl1 = ListNode(7).apply {
        next = ListNode(1).apply {
            next = ListNode(6)
        }
    }
    val nl2 = ListNode(5).apply {
        next = ListNode(9).apply {
            next = ListNode(2)
        }
    }
    ListNode.addTwoNumbers(nl1, nl2)
    ListNode.addTwoNumbers(null, null)

    val negative = ListNode(-129).apply {
        next = ListNode(-129)
    }
    ListNode.isPalindrome(negative)
}
