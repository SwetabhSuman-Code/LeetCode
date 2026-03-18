import java.util.*;

class Solution {

    Map<Integer, Integer> map = new HashMap<>();
    int postIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        postIndex = postorder.length - 1;

        return build(postorder, 0, inorder.length - 1);
    }

    TreeNode build(int[] postorder, int left, int right) {

        if (left > right) return null;

        int val = postorder[postIndex--];
        TreeNode root = new TreeNode(val);

        int mid = map.get(val);

        root.right = build(postorder, mid + 1, right);
        root.left = build(postorder, left, mid - 1);

        return root;
    }
}