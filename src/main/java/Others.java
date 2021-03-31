import java.util.*;

public class Others {
    private static List<TreeNode> inOrderList = new ArrayList<>();
    // https://leetcode-cn.com/problems/first-common-ancestor-lcci/
    Map<TreeNode, TreeNode> tnsFatherIs = new HashMap<>();
    Set<TreeNode> visited = new HashSet<>();

    public static void inorderSuccessorHelper(TreeNode node) {
        if (node != null) {
            inorderSuccessorHelper(node.left);
            inOrderList.add(node);
            inorderSuccessorHelper(node.right);
        }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorderSuccessorHelper(root);
        int inOrderIndex = inOrderList.indexOf(p);
        if (inOrderIndex == inOrderList.size() - 1) return null;
        else return inOrderList.get(inOrderIndex + 1);
    }

    // https://stackoverflow.com/questions/6359847/convert-double-to-binary-representation
    public static String toBinary(double d, int precision) {
        long wholePart = (long) d;
        return wholeToBinary(wholePart) + '.' + fractionalToBinary(d - wholePart, precision);
    }

    private static String wholeToBinary(long l) {
        return Long.toBinaryString(l);
    }

    private static String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();
        while (num > 0 && binary.length() < precision) {
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        System.out.println(toBinary(0.1, 32));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // First, do DFS and make the map.
        dfsToBuildMap(root);

        while (p != null) {
            visited.add(p);
            p = tnsFatherIs.get(p);
        }

        while (q != null) {
            if (visited.contains(q)) return q;
            q = tnsFatherIs.get(q);
        }
        return null;
    }

    // https://leetcode-cn.com/problems/first-common-ancestor-lcci/solution/shou-ge-gong-tong-zu-xian-by-leetcode-so-c2sl/
    private void dfsToBuildMap(TreeNode node) {
        if (node.left != null) {
            tnsFatherIs.put(node.left, node);
            dfsToBuildMap(node.left);
        }
        if (node.right != null) {
            tnsFatherIs.put(node.right, node);
            dfsToBuildMap(node.right);
        }
    }

    public String printBin(double num) {
        String preResult = toBinary(num, 32);
        if (preResult.length() == 34) return "ERROR";
        else return preResult;
    }

    // https://leetcode-cn.com/problems/delete-middle-node-lcci/submissions/
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        // https://leetcode-cn.com/problems/delete-middle-node-lcci/
        public static void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }

        // https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
        public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode currentNode = headA;
            while (currentNode != null) {
                ListNode tempCurrentNode = headB;
                while (tempCurrentNode != null) {
                    if (currentNode == tempCurrentNode) return currentNode;

                    tempCurrentNode = tempCurrentNode.next;
                }

                currentNode = currentNode.next;
            }

            return null;
        }

        // https://leetcode-cn.com/problems/linked-list-cycle-lcci/
        public ListNode detectCycle(ListNode head) {
            List<ListNode> foredNode = new ArrayList<>();

            ListNode currentNode = head;
            while (currentNode != null) {
                if (foredNode.contains(currentNode)) return currentNode;

                foredNode.add(currentNode);
                currentNode = currentNode.next;
            }

            return null;
        }
    }

    // https://leetcode-cn.com/problems/successor-lcci/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
