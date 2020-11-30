package com.michaelbotelho;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        /*
        LinkedList<String> placesToVisit = new LinkedList<String>();
        addInOrder(placesToVisit, "Sydney");
        addInOrder(placesToVisit, "Melbourne");
        addInOrder(placesToVisit, "Brisbane");
        addInOrder(placesToVisit, "Perth");
        addInOrder(placesToVisit, "Canberra");
        addInOrder(placesToVisit, "Adelaide");
        addInOrder(placesToVisit, "Alice Springs");
        printList(placesToVisit);
        */
        System.out.println(caesarCypherEncryptorA("xyz", 2));
    }

    /* Linked List Demonstration */
    public static void printList(LinkedList<String> list) {
        for (String s : list) {
            System.out.println("Now Visiting: " + s);
        }
    }

    public static boolean addInOrder(LinkedList<String> list, String city) {
        ListIterator<String> linkedListIterator = list.listIterator();
        while (linkedListIterator.hasNext()) {
            int comparison = linkedListIterator.next().compareTo(city);
            if (comparison == 0) {
                return false;
            } else if (comparison > 0) {
                linkedListIterator.previous();
                linkedListIterator.add(city);
                return true;
            }
        }
        linkedListIterator.add(city);
        return true;
    }

    /* TWO NUMBER SUM
      Write a function that takes in a non-empty array of distinct integers and an
      integer representing a target sum. If any two numbers in the input array sum
      up to the target sum, the function should return them in an array, in any
      order. If no two numbers sum up to the target sum, the function should return
      an empty array.

      Note that the target sum has to be obtained by summing two different integers
      in the array; you can't add a single integer to itself in order to obtain the
      target sum.You can assume that there will be at most one pair of numbers summing up to
      the target sum.

      Solution # 1
      O(n^2) time | O(1) space
    */
    public static int[] twoNumberSumA(int[] array, int targetSum) {
        for (int i = 0; i < array.length; i++) {
            int firstValue = array[i];
            for (int j = i + 1; j < array.length; j++) {
                int secondValue = array[j];
                if (firstValue + secondValue == targetSum) {
                    return new int[]{firstValue, secondValue};
                }
            }
        }
        return new int[0];
    }

    /*
       Solution # 2
       O(n) time | O(n) space
    */
    public static int[] twoNumberSumB(int[] array, int targetSum) {
        Set<Integer> nums = new HashSet<>();
        for (int num : array) {
            int potentialMatch = targetSum - num;
            if (nums.contains(potentialMatch)) {
                return new int[]{potentialMatch, num};
            } else {
                nums.add(num);
            }
        }
        return new int[0];
    }

    /*
     Solution # 3
     O(nlog(n)) time | O(1) space
  */
    public static int[] twoNumberSumC(int[] array, int targetSum) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        for (int num : array) {
            int sum = array[left] + array[right];
            if (sum == targetSum) {
                return new int[]{array[left], array[right]};
            } else if (sum > targetSum) {
                right--;
            } else if (sum < targetSum) {
                left++;
            }
        }
        return new int[0];
    }

    /* VALIDATE SUBSEQUENCE
    Given two non-empty arrays of integers, write a function that determines
    whether the second array is a subsequence of the first one.

    A subsequence of an array is a set of numbers that aren't necessarily adjacent
    in the array but that are in the same order as they appear in the array. For
    instance, the numbers [1, 3, 4]  form a subsequence of the array [1, 2, 3, 4]
    and so do the numbers [2, 4]. Note that a single number in an array and the array itself are both valid
    subsequences of the array.`

    Solution # 1
    O(n) time | O(1) space
    */
    public static boolean isValidSubsequenceA(List<Integer> array, List<Integer> sequence) {
        int arrIdx = 0;
        int seqIdx = 0;
        while (arrIdx < array.size() && seqIdx < sequence.size()) {
            if (array.get(arrIdx).equals(sequence.get(seqIdx))) {
                seqIdx += 1;
            }
            arrIdx += 1;
        }
        return seqIdx == sequence.size();
    }

    /*
    Solution # 2
    O(n) time | O(1) space
    */
    public static boolean isValidSubsequenceB(List<Integer> array, List<Integer> sequence) {
        int seqIdx = 0;
        for (int num : array) {
            if (seqIdx == sequence.size()) {
                break;
            }
            if (sequence.get(seqIdx).equals(num)) {
                seqIdx += 1;
            }
        }
        return seqIdx == sequence.size();
    }

    /* FIND CLOSEST VALUE IN BST

    Write a function that takes in a Binary Search Tree (BST) and a target integer
    value and returns the closest value to that target value contained in the BST.
    You can assume that there will only be one closest value.

    Solution # 1 Recursive
    O(log(n)) time | O(log(n)) space
    */
    public static int findClosestValueInBstA(BST tree, int target) {
        return findClosestValueInBstA(tree, target, tree.value);
    }

    public static int findClosestValueInBstA(BST tree, int target, int closest) {
        if (Math.abs(target - closest) > Math.abs(target - tree.value)) {
            closest = tree.value;
        }
        if (target < tree.value && tree.left != null) {
            return findClosestValueInBstA(tree.left, target, closest);
        } else if (target > tree.value && tree.right != null) {
            return findClosestValueInBstA(tree.right, target, closest);
        } else {
            return closest;
        }
    }

    /*
    Solution # 2 Iterative
    O(log(n)) time | O(1) space
    */
    public static int findClosestValueInBstB(BST tree, int target) {
        return findClosestValueInBstB(tree, target, tree.value);
    }

    public static int findClosestValueInBstB(BST tree, int target, int closest) {
        BST currentNode = tree;
        while (currentNode != null) {
            if (Math.abs(target - closest) > Math.abs(target - currentNode.value)) {
                closest = currentNode.value;
            }
            if (target < currentNode.value) {
                currentNode = currentNode.left;
            } else if (target > currentNode.value) {
                currentNode = currentNode.right;
            } else {
                break;
            }
        }
        return closest;
    }

    /* BST class */
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    /* BRANCH SUMS

    Write a function that takes in a Binary Tree and returns a list of its branch
    sums ordered from leftmost branch sum to rightmost branch sum.

    A branch sum is the sum of all values in a Binary Tree branch. A Binary Tree
    branch is a path of nodes in a tree that starts at the root node and ends at
    any leaf node.

    Solution # 1 Recursive
    O(n) time | O(n) space
    */
    public static List<Integer> branchSumsA(BinaryTree root) {
        List<Integer> sums = new ArrayList<Integer>();
        calculateBranchSumsA(root, 0, sums);
        return sums;

    }

    public static void calculateBranchSumsA(BinaryTree node, int runningSum, List<Integer> sums) {
        if (node == null)
            return;
        int newRunningSum = runningSum + node.value;
        if (node.left == null && node.right == null) {
            sums.add(newRunningSum);
            return;
        }
        calculateBranchSumsA(node.left, newRunningSum, sums);
        calculateBranchSumsA(node.right, newRunningSum, sums);
    }

    /*
    The distance between a node in a Binary Tree and the tree's root is called the
    node's depth.
    Write a function that takes in a Binary Tree and returns the sum of its nodes'
    depths.

    Solution # 1 O(n) time | O(h) space
    */
    public static int nodeDepthsA(BinaryTree root) {
        int sumDepths = 0;
        ArrayList<Level> stack = new ArrayList<Level>();
        stack.add(new Level(root, 0));
        while (stack.size() > 0) {
            Level top = stack.remove(stack.size() - 1);
            BinaryTree node = top.root;
            int depth = top.depth;
            if (node == null) continue;
            sumDepths += depth;
            stack.add(new Level(node.left, depth + 1));
            stack.add(new Level(node.right, depth + 1));
        }
        return sumDepths;
    }

    /*
     Solution # 2 O(n) time | O(h) space
    */
    public static int nodeDepthsB(BinaryTree root) {
        // Write your code here.
        return nodeDepthsHelper(root, 0);
    }

    public static int nodeDepthsHelper(BinaryTree node, int depth) {
        if (node == null) return 0;
        return depth + nodeDepthsHelper(node.left, depth + 1) + nodeDepthsHelper(node.right, depth + 1);
    }

    /* Level Class */
    static class Level {
        public BinaryTree root;
        int depth;

        public Level(BinaryTree root, int depth) {
            this.root = root;
            this.depth = depth;
        }
    }

    /* Binary Tree Class */
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /*
    Depth First Search

    Solution # 1 O(v+e) time | O(v) space
    */
    class DFS {
        public class Node {
            String name;
            List<Node> children = new ArrayList<Node>();

            public Node(String name) {
                this.name = name;
            }

            public List<String> depthFirstSearch(List<String> array) {
                array.add(this.name);
                for (int i = 0; i < children.size(); i++) {
                    children.get(i).depthFirstSearch(array);
                }
                return array;
            }

            public Node addChild(String name) {
                Node child = new Node(name);
                children.add(child);
                return this;
            }
        }
    }

    /*
    Get Nth Fibonacci Number

    Solution # 1 O(2^n) time | O(n) space
    */
    public static int getNthFibA(int n) {
        Map<Integer, Integer> memoize = new HashMap<Integer, Integer>();
        memoize.put(1, 0);
        memoize.put(2, 1);
        return getNthFibA(n, memoize);
    }

    /*
      Solution # 2 Recursive Memoization O(n) time | O(n) space
    */
    public static int getNthFibA(int n, Map<Integer, Integer> memoize) {
        if (memoize.containsKey(n)) {
            return memoize.get(n);
        } else {
            memoize.put(n, getNthFibA(n - 1, memoize) + getNthFibA(n - 2, memoize));
            return memoize.get(n);
        }
    }

    /*
      Solution # 3 Iterative O(n) time | O(1) space
    */
    public static int getNthFibC(int n) {
        int[] lastTwo = {0, 1};
        int counter = 3;

        while (counter <= n) {
            int nextFib = lastTwo[0] + lastTwo[1];
            lastTwo[0] = lastTwo[1];
            lastTwo[1] = nextFib;
            counter++;
        }
        return n > 1 ? lastTwo[1] : lastTwo[0];
    }

    private ArrayList<Integer> reverseArray(ArrayList<Integer> array) {
        return array;
    }

    /*
      Product Sum

      Solution # 1 O(n) time | O(d) space d is equal to greatest depth
    */
    public static int productSum(List<Object> array) {
        return productSumHelper(array, 1);
    }

    public static int productSumHelper(List<Object> array, int multiplier) {
        int sum = 0;
        for (Object el : array) {
            if (el instanceof ArrayList) {
                @SuppressWarnings("unchecked")
                ArrayList<Object> ls = (ArrayList<Object>) el;
                sum += productSumHelper(ls, multiplier + 1);
            } else {
                sum += (int) el;
            }
        }
        return sum * multiplier;
    }

    /*
     Binary Search
     Solution # 1 O(n) | O(1) space Iterative
    */
    public static int binarySearchA(int[] array, int target) {
        return binarySearchA(array, target, 0, array.length - 1);
    }

    public static int binarySearchA(int[] array, int target, int left, int right) {
        while (left <= right) {
            int middle = (left + right) / 2;
            int potentialMatch = array[middle];
            if (target == potentialMatch) {
                return middle;
            } else if (target < potentialMatch) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    /*
     Solution # 2 O(n) time | O(Log(n)) Recursive
    */
    public static int binarySearchB(int[] array, int target) {
        // Write your code here.
        return binarySearchB(array, target, 0, array.length - 1);
    }

    public static int binarySearchB(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        int potentialMatch = array[middle];
        if (target == potentialMatch) {
            return middle;
        } else if (target < potentialMatch) {
            return binarySearchB(array, target, left, middle - 1);
        } else if (target > potentialMatch) {
            return binarySearchB(array, target, middle + 1, right);
        } else {
            return -1;
        }
    }

    /* Find three largest numbers (do not sort array)
       Solution # 1 O(n) time | O(1) space
    */
    public static int[] findThreeLargestNumbers(int[] array) {
        int[] threeLargest = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int num : array) {
            updateLargest(threeLargest, num);
        }
        return threeLargest;
    }

    public static void updateLargest(int[] threeLargest, int num) {
        if (threeLargest[2] == 0 || num > threeLargest[2]) {
            shiftAndUpdate(threeLargest, num, 2);
        } else if (num > threeLargest[1]) {
            shiftAndUpdate(threeLargest, num, 1);
        } else if (num > threeLargest[0]) {
            shiftAndUpdate(threeLargest, num, 0);
        }
    }

    public static void shiftAndUpdate(int[] array, int num, int idx) {
        for (int i = 0; i <= idx; i++) {
            if (i == idx) {
                array[i] = num;
            } else {
                array[i] = array[i + 1];
            }
        }
    }

    /* Bubble Sort
       Solution # 1 O(n^2) time | O(1) space
    */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return new int[]{};
        boolean isSorted = false;
        int counter = 0;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1 - counter; i++) {
                if (array[i] > array[i + 1]) {
                    swap(i, i + 1, array);
                    isSorted = false;
                }
            }
            counter++;
        }
        return array;
    }



    /* Insertion Sort
       Solution # 1 O(n^2) time | O(1) space
    */
    public static int[] insertionSort(int[] array) {
        // Write your code here.
        int j, k;
        for (int i = 1; i < array.length; i++) {
            k = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > k) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = k;
        }
        return array;
    }

    /* Selection Sort
       Solution # 1 O(n^2) time | O(1) space
    */
    public static int[] selectionSort(int[] array) {
        int startIdx = 0;
        while(startIdx < array.length - 1) {
            int smallestIdx = startIdx;
            for (int i=startIdx + 1; i < array.length; i++) {
                if (array[smallestIdx] > array[i]) {
                    smallestIdx = i;
                }
            }
            swap(smallestIdx, startIdx, array);
            startIdx++;
        }
      return array;
    }


    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /* Is Palindrome
       Solution # 1 O(n^2) time | O(1) space
    */
    public static boolean isPalindrome(String str) {
        String reversedStr = "";
        for (int i=str.length() - 1; i >= 0; i--) {
            reversedStr += str.charAt(i);
        }
        return str.equals(reversedStr);
    }

    /* Solution # 2 Recursive
        O(n) time | O(n) space
    */
    public static boolean isPalindromeA(String str) {
        return isPalindromeA(str, 0);
    }

    public static boolean isPalindromeA(String str, int i) {
        int j = str.length() - 1 - i;
        return (i >= j) ? true : str.charAt(i) == str.charAt(j) && isPalindromeA(str, i + 1);
    }

    /* Solution # 3 Pointers
        O(n) time | O(1) space
    */
    public static boolean isPalindromeB(String str) {
        int leftIdx = 0;
        int rightIdx = str.length() - 1;
        while (leftIdx < rightIdx) {
          if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
              return false;
          }
          leftIdx++;
          rightIdx--;
        }
        return true;
    }

    /*  Caesar cypher
        Solution # 1
        O(n) time | O(n) space
    */
    public static String caesarCypherEncryptor(String str, int key) {
        char[] newLetters = new char[str.length()];
        int newKey = key % 26;
        for (int i=0; i<str.length(); i++) {
            newLetters[i] += getNewLetter(str.charAt(i), newKey);
        }
        return new String(newLetters);

    }
    public static char getNewLetter(char letter, int key) {
        int newLetterCode = letter + key;
        return newLetterCode <= 122 ? (char) newLetterCode : (char) (96 + newLetterCode % 122);
    }

    /*  Caesar cypher
        Solution # 2
        O(n) time | O(n) space
    */

    public static String caesarCypherEncryptorA(String str, int key) {
        char[] newLetters = new char[str.length()];
        int newKey = key % 26;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i =0; i<str.length(); i++) {
            newLetters[i] = getNewLetter(str.charAt(i), newKey, alphabet);
        }
        return new String(newLetters);
    }

    public static char getNewLetter(char letter, int key, String alphabet) {
        int newLetterCode = alphabet.indexOf(letter) + key;
        return alphabet.charAt(newLetterCode % 26);
    }

}
