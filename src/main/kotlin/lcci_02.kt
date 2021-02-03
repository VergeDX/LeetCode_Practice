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
}
