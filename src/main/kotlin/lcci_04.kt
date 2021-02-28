import kotlin.math.abs

// https://leetcode-cn.com/problems/route-between-nodes-lcci/
fun findWhetherExistsPath(n: Int, graph: Array<IntArray>, start: Int, target: Int): Boolean {
    val filteredGraph = graph.distinctBy { (it[0] to it[1]).hashCode() }.toTypedArray()
    val nodeEdgeMap = HashMap<Int, HashSet<Int>>()

    // Init nodeEdgeMap by using given n, for i in [0, n).
    for (i in 0 until n) nodeEdgeMap[i] = HashSet()

    // nodeEdgeMap[it[0]] should be exists, because it[0] in range [0, n) too.
    graph.forEach { nodeEdgeMap[it[0]]!!.add(it[1]) }

    if (start == target) return true

    val visitedSet = HashSet<Int>()

    // https://oi-wiki.org/graph/dfs/
    fun Array<IntArray>.DFS(v: Int) {
        visitedSet.add(v)
        for (u in nodeEdgeMap[v]!!) {
            if (u !in visitedSet) DFS(u)
        }
    }

    filteredGraph.DFS(start)

    return target in visitedSet
}

// https://leetcode-cn.com/problems/minimum-height-tree-lcci/
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    companion object {
        fun getLayerNodeList(root: TreeNode): List<List<TreeNode>> {
            val layerNodeList = ArrayList<List<TreeNode>>().apply { add(listOf(root)) }
            while (true) {
                val lastGroup = layerNodeList.last()
                val allChildList = lastGroup.getAllChildList()
                if (allChildList.isEmpty()) break
                layerNodeList.add(allChildList)
            }

            return layerNodeList
        }
    }

    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        // https://leetcode-cn.com/problems/minimum-height-tree-lcci/solution/qing-xi-yi-dong-javadi-gui-shi-xian-by-w-i764/

        if (nums.isEmpty()) return null
        return createMinimalTree(nums, 0, nums.size - 1)
    }

    // left & right are index of element. (nums is sorted array)
    private fun createMinimalTree(nums: IntArray, left: Int, right: Int): TreeNode? {
        if (left < 0 || right >= nums.size || left > right) return null

        val mid = (left + right) / 2
        val n = TreeNode(nums[mid])

        n.left = createMinimalTree(nums, left, mid - 1)
        n.right = createMinimalTree(nums, mid + 1, right)
        return n
    }

    // https://leetcode-cn.com/problems/list-of-depth-lcci/
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun listOfDepth(tree: TreeNode?): Array<ListNode?> {
        if (tree == null) return arrayOf()

        val layerNodeList = getLayerNodeList(tree)

        // Cast TN -> LN, then do link.
        val layerTreeNodeList = layerNodeList.map { treeNodeList -> treeNodeList.map { ListNode(it.`val`) } }
        layerTreeNodeList.forEach { eachListNodeList ->
            eachListNodeList.forEachIndexed { index, listNode ->
                val size = eachListNodeList.size

                // Last element, default next is null.
                if (index == size - 1) return@forEachIndexed

                listNode.next = eachListNodeList[index + 1]
            }
        }

        return layerTreeNodeList.map { it[0] }.toTypedArray()
    }
}

fun List<TreeNode>.getAllChildList(): List<TreeNode> {
    val result = ArrayList<TreeNode>()
    this.forEach {
        it.left?.let { node -> result.add(node) }
        it.right?.let { node -> result.add(node) }
    }
    return result
}

// https://leetcode-cn.com/problems/check-balance-lcci/
fun isBalancedHelper(root: TreeNode?): Boolean {
    if (root == null) return true

    val leftChilds =
        if (root.left != null) ArrayList<List<TreeNode>>().apply { add(listOf(root.left!!)) } else ArrayList()
    val rightChilds =
        if (root.right != null) ArrayList<List<TreeNode>>().apply { add(listOf(root.right!!)) } else ArrayList()

    while (leftChilds.isNotEmpty()) {
        val temp = leftChilds.last().getAllChildList()
        if (temp.isEmpty()) break
        else leftChilds += temp
    }

    while (rightChilds.isNotEmpty()) {
        val temp = rightChilds.last().getAllChildList()
        if (temp.isEmpty()) break
        else rightChilds += temp
    }

    return abs(leftChilds.size - rightChilds.size) <= 1
}

var booleanFlag = true

fun isBalancedRecursion(root: TreeNode?) {
    if (root != null && booleanFlag) {
        isBalanced(root.left)
        isBalanced(root.right)
        if (!isBalancedHelper(root)) booleanFlag = false
    }
}

fun isBalanced(root: TreeNode?): Boolean {
    isBalancedRecursion(root)
    return booleanFlag
}

val shouldBeOrder = ArrayList<Int>()
fun isValidBSTHelper(root: TreeNode?) {
    if (root != null) {
        isValidBSTHelper(root.left)
        shouldBeOrder.add(root.`val`)
        isValidBSTHelper(root.right)
    }
}

// https://leetcode-cn.com/problems/legal-binary-search-tree-lcci/
fun isValidBST(root: TreeNode?): Boolean {
    isValidBSTHelper(root)

    // After sort, order different x.
    // Element duplicate x.
    if (shouldBeOrder != shouldBeOrder.sorted() || shouldBeOrder.toSet().size != shouldBeOrder.size) return false
    return true
}

// https://leetcode-cn.com/problems/bst-sequences-lcci/
fun BSTSequences(root: TreeNode?): List<List<Int>> {
    TODO()
}

// https://leetcode-cn.com/problems/check-subtree-lcci/
fun checkSubTree(t1: TreeNode?, t2: TreeNode?): Boolean {
    // https://leetcode-cn.com/problems/check-subtree-lcci/solution/8xing-dai-ma-jie-fa-by-you-yu-ai/

    // t2 > t1, because when [null, null], should return true.
    if (t2 == null) return true
    if (t1 == null) return false

    return if (t1.`val` == t2.`val`) checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right)
    else checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2)
}

// https://leetcode-cn.com/problems/paths-with-sum-lcci/
fun pathSum(root: TreeNode?, sum: Int): Int {
    TODO()
}

fun main() {
    println(
        findWhetherExistsPath(
            3, arrayOf(
                intArrayOf(0, 1), intArrayOf(0, 2),
                intArrayOf(1, 2), intArrayOf(1, 2)
            ), 0, 2
        )
    )

    val debug = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(3).apply {
                left = TreeNode(4)
            }
        }
        right = TreeNode(2).apply {
            right = TreeNode(3).apply {
                right = TreeNode(4)
            }
        }
    }
    println(isBalanced(debug))

    val lcci0405E1 = TreeNode(2).apply {
        left = TreeNode(1)
        right = TreeNode(3)
    }
    val lcci0405E2 = TreeNode(5).apply {
        left = TreeNode(1)
        right = TreeNode(4).apply {
            left = TreeNode(3)
            right = TreeNode(6)
        }
    }
    // isValidBST(lcci0405E1)
    // isValidBST(lcci0405E2)

    isValidBSTHelper(lcci0405E2)
    println(shouldBeOrder)

    // pathSum(lcci0405E2, 0)
}
