public class Others {
    // https://leetcode-cn.com/problems/delete-middle-node-lcci/submissions/
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
