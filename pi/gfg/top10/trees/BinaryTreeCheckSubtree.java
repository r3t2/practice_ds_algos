public class BinaryTreeCheckSubtree
{
    /*

Check if a binary tree is subtree of another binary tree | Set 2
3.4

Given two binary trees, check if the first tree is subtree of the second one. A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.

The subtree corresponding to the root node is the entire tree; the subtree corresponding to any other node is called a proper subtree.

For example, in the following case, Tree1 is a subtree of Tree2.


        Tree1
          x 
        /    \
      a       b
       \
        c


        Tree2
              z
            /   \
          x      e
        /    \     \
      a       b      k
       \
        c
    */

    public boolean isSubtree(Node root2)
    {
        int [] preOrder1 = getPreOrder(root);
        int [] inOrder1 = getInOrder(root);

        int [] preOrder2 = getPreOrder(root2);
        int [] inOrder2 = getInOrder(root2);

        if(isSubString(preOrder1, preOrder2) &&
            isSubString(inOrder1, inOrder2)) return true;
        else return false;
    }
}