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

    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        fun listOfDepth(tree: TreeNode?): Array<ListNode?> {
            if (tree == null) return arrayOf()

            val layerNodeList = ArrayList<List<TreeNode>>().apply { add(listOf(tree)) }
            while (true) {
                val lastGroup = layerNodeList.last()

                val allChildList = lastGroup.getAllChildList()
                if (allChildList.isEmpty()) break

                layerNodeList.add(allChildList)
            }

            TODO()
        }

        fun List<TreeNode>.getAllChildList(): List<TreeNode> {
            val result = ArrayList<TreeNode>()
            this.forEach {
                it.left?.let { node -> result.add(node) }
                it.right?.let { node -> result.add(node) }
            }
            return result
        }
    }
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
}
