
/**
 * @author  Akash P Akki      netid: apa190001
 * @author  Anant Srivastava  netid: aps180006
 */

package aps180006;
import java.util.*;
import java.util.Iterator;
import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }
    
    Entry<T> root;
    int size;
    
    Stack<Entry<T>> st = new Stack<>(); //To keep track of path
    int compare; // for comparing T type objects
    
    public BinarySearchTree() {
    	root = null;
    	size = 0;
    }
    /**
     * Helper method to find entry containing x, or entry at which it failed 
     * in tree rooted at temp 
     * @param temp : starts with root of the tree in temp
     * @param x : the element we are looking for
     * @return temp : entry containing x, or entry at which it failed
     */
    public Entry<T> find(Entry<T> temp,T x) {
    	if(temp==null||temp.element==x) {
    		return temp;
    	}
    	while(true) {
    		compare = temp.element.compareTo(x);
    		if(compare==1) {          // temp is greater than x   
    			if(temp.left==null) {
    				break;
    			}
    			st.push(temp);
    			temp = temp.left;
    		}else if (compare==0) {   // temp is equal to x
    			break;
    		}else {					  // temp is smaller than x
    			if(temp.right==null) {
    				break;
    			}
    			st.push(temp);
    			temp= temp.right;
    		}
    	}
    	return temp;
    }
    
    /**
     * Helper method to find entry containing x, or entry at which it failed 
     * @param x : the element we are looking for
     * @return find(root,x): entry containing x, or entry at which it failed starting from root
     */
    public Entry<T> find(T x) {
    	st.push(null);
    	return find(root,x);
    }
 

    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {
    	Entry<T> temp = find(x);
    	if(temp==null||temp.element!=x) {
    		return false;
    	}
    	return true;
    }

    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) {
    	st.clear();
    	if(!contains(x)) {
    		return null;
    	}else {
    		return find(x).element;
    	}
    }

    /** TO DO: Add x to tree. 
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */
    public boolean add(T x) {
    	if(size==0) {
    		root = new Entry<T>(x,null,null);
    	}else {
    		Entry<T> temp = find(x);
    		if(temp.element==x) {
    			return false;
    		}
    		compare = temp.element.compareTo(x);
    		if(compare==1) {
    			temp.left = new Entry<T>(x,null,null);
    		}else {
    			temp.right = new Entry<T>(x,null,null);
    		}
    	}
    	size++;
    	return true;
    }
    /**
     * Helper method to link the parent of the removed node with the child of the removed node 
     * @param temp : the node to be removed
     */
    public void splice(Entry<T> temp) {
    	Entry<T> parent = st.peek();
    	Entry<T> child = (temp.left==null?temp.right:temp.left);
    	
    	if(parent==null) {
    		root = child;
    	}else if (parent.left==temp) {
    		parent.left = child;
    	}else {
    		parent.right = child;
    	}
    }

    /** TO DO: Remove x from tree. 
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {
    	if(size==0) {
    		return null;
    	}
    	Entry<T> temp = find(x);
    	if(temp.element!=x) {
    		return null;
    	}
    	if(temp.left==null||temp.right==null) {
    		splice(temp);
    	}else {
    		st.push(temp);
    		Entry<T> minRight = find(temp.right,x);
    		temp.element = minRight.element;
    		splice(minRight);
    	}
    	size--;
    	return x;	
    }
    /*
     * Returns the minimum element of tree which is the leftmost element
     */
    public T min() {
    	if(root==null) {
    		return null;
    	}
    	Entry<T> node = root;
    	while(node.left!=null) {
    		node = node.left;
    	}
    	return node.element;
    }
    /*
     * Returns the maximum element of tree which is the rightmost element
     */
    public T max() {
    	if(root==null) {
    		return null;
    	}
    	Entry<T> node = root;
    	while(node.right!=null) {
    		node = node.right;
    	}
    	return node.element;
    }

    // TODO: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
    	Comparable[] arr = new Comparable[size];
    	/* write code to place elements in array here */
    	if(root==null) {
    		return arr;
    	}
    	Entry<T> node = root;
    	int counter = 0;
    	st.clear();
    	while (node!=null || st.size() > 0){ 
            /* Reach the left most Node of the curr Node */
            while (node!=null) { 
                st.push(node); 
                node = node.left; 
            } 
  
            /* Node must be NULL at this point */
            node = st.pop(); 
  
            arr[counter] = node.element;
            counter++;
  
            /* we have visited the node and its 
               left subtree.  Now, it's right 
               subtree's turn */
            node = node.right; 
        } 
    	return arr;
    }
    
    // Start of Optional problem 2
    /** Optional problem 2: Iterate elements in sorted order of keys
	Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
    	return null;
    }

    // Optional problem 2.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem 2.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem 2.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem 2.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

// End of Optional problem 2

    public static void main(String[] args) {
    	BinarySearchTree<Integer> t = new BinarySearchTree<>();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
							System.out.println("min is "+t.max());
							System.out.println("max is "+t.min());
                break;
            }           
        }
        //test purposes



        

    }


    public void printTree() {
    	System.out.print("[" + size + "]");
    	printTree(root);
    	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
    	if(node != null) {
    		printTree(node.left);
    		System.out.print(" " + node.element);
    		printTree(node.right);
    	}
    }

}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/
