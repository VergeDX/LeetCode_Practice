// https://leetcode-cn.com/problems/route-between-nodes-lcci/
fun findWhetherExistsPath(n: Int, graph: Array<IntArray>, start: Int, target: Int): Boolean {
    val filteredGraph = graph.distinctBy { (it[0] to it[1]).hashCode() }.toTypedArray()

    if (start == target) return true

    val visitedSet = HashSet<Int>()

    // Helper function.
    fun Array<IntArray>.findEdge(v: Int): Set<Int> {
        val resultSet = HashSet<Int>()
        // Use Pair, avoid IntArray in Set issues.
        this.forEach { if (it[0] == v) resultSet += it[1] }
        return resultSet
    }

    // https://oi-wiki.org/graph/dfs/
    fun Array<IntArray>.DFS(v: Int) {
        visitedSet.add(v)
        for (u in this.findEdge(v)) {
            if (u !in visitedSet) DFS(u)
        }
    }

    // https://oi-wiki.org/graph/bfs/
    fun Array<IntArray>.BFS(v: Int) {
        val Q = ArrayList<Int>().apply { add(v) }
        while (Q.isNotEmpty()) {
            // LeetCode: Kotlin 1.3.10. val currentNode = Q.removeFirst()
            val currentNode = Q.removeAt(0)

            visitedSet.add(currentNode)
            Q.addAll(this.findEdge(currentNode))
        }
    }

    // filteredGraph.DFS(start)
    filteredGraph.BFS(start)

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
