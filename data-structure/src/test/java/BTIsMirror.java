import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *       1
 *   2       2
 * 4   5   5  4
 */
public class BTIsMirror {

    boolean isMirror(BTNode node1, BTNode node2) {
        if (node1 == null && node2 == null) return true;

        if (node1 != null && node2 != null
            && node1.data == node2.data) {
            return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
        }
        return false;
    }

    @Test
    public void testMirror() {
        BTNode root = new BTNode(1,
                new BTNode(2,
                        new BTNode(4, null, null),
                        new BTNode(5, null, null)),
                new BTNode(2,
                        new BTNode(5, null, null),
                        new BTNode(4, null, null))
        );
        Assertions.assertEquals(true, isMirror(root, root));

        root.left.left.left = new BTNode(6, null, null);
        Assertions.assertEquals(false, isMirror(root, root));
    }

    public class BTNode {
        int data;
        BTNode left;
        BTNode right;

        public BTNode(int data, BTNode left, BTNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "BTNode{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
