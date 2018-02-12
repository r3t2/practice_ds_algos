/*
Element Present in Tree?
You are provided a binary search tree with integers.  Each node has three primary members: An Integer (which it holds), a pointer to its left child, and a pointer to its right child. A function stub is provided in multiple languages that you need to complete so that the function will search for the presence of a specified integer in this tree. If the element (val) is found, return 1, otherwise return 0. 

Each function stub will have its prototype and an explanation of the data types or classes involved,  with reasonable documentation. The section of the program which parses the input and displays the output is complete in each language environment and will not need to be modified. Your task is to complete the body of the function provided so it returns the correct output.


Sample Test Cases: 

Input #1: 


val: 30

Output #1:

1

Explanation:
The element 30 is present in the given tree so return 1.

 

Input #2: 


val: 79

Output #2:
0
 

Explanation:
The element 79 is not present in the given tree so return 0.
*/



private static int isPresent(Node n, int val){
    if(n == null) return 0;
    if(n.data == val) return 1;
    else if(val <= n.data) return isPresent(n.left, val);
    else return isPresent(n.right, val);
/* For your reference
class Node {
        Node left, right;
        int data;
                Node(int newData) {
            left = right = null;
            data = newData;
        }
    }
*/
}