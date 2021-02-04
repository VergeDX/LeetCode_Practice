import java.util.ArrayList;
import java.util.List;

public class Others {
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
}
