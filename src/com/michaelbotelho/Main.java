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

        int array[][] = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};

        //[[1, 2, 3, 4], [12, 13, 14, 5], [11, 16, 15, 6], [10, 9, 8, 7]]
        System.out.println(spiralTraverse(array));
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
        while (startIdx < array.length - 1) {
            int smallestIdx = startIdx;
            for (int i = startIdx + 1; i < array.length; i++) {
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
        for (int i = str.length() - 1; i >= 0; i--) {
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
        for (int i = 0; i < str.length(); i++) {
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
        for (int i = 0; i < str.length(); i++) {
            newLetters[i] = getNewLetter(str.charAt(i), newKey, alphabet);
        }
        return new String(newLetters);
    }

    public static char getNewLetter(char letter, int key, String alphabet) {
        int newLetterCode = alphabet.indexOf(letter) + key;
        return alphabet.charAt(newLetterCode % 26);
    }

    /*  Run Length Encoding
        Solution # 1
        O(n) time | O(n) space
    */
    public static String runLengthEncoding(String string) {
        ArrayList<String> encodedStringCharacters = new ArrayList<String>();
        int runLength = 1;
        for (int i = 1; i < string.length(); i++) {
            char currentCharacter = string.charAt(i);
            char previousCharacter = string.charAt(i - 1);
            if (currentCharacter != previousCharacter || runLength == 9) {
                encodedStringCharacters.add(String.valueOf(runLength));
                encodedStringCharacters.add(String.valueOf(previousCharacter));
                runLength = 0;
            }
            runLength += 1;
        }
        encodedStringCharacters.add(String.valueOf(runLength));
        encodedStringCharacters.add(String.valueOf(string.charAt(string.length() - 1)));
        return String.join(",", encodedStringCharacters);
    }

    /*  Three Number Sum
        Solution # 1
        O(n) time | O(n) space
    */
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        Arrays.sort(array);
        ArrayList<Integer[]> triplets = new ArrayList<Integer[]>();
        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if (currentSum == targetSum) {
                    Integer[] newTriplet = {array[i], array[left], array[right]};
                    triplets.add(newTriplet);
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else if (currentSum > targetSum) {
                    right--;
                }
            }
        }
        return triplets;
    }

    /* Smallest Difference
       Solution # 1
       O(n) time | O(1) space
   */
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int idxOne = 0;
        int idxTwo = 0;

        int current = Integer.MAX_VALUE;
        int smallest = Integer.MAX_VALUE;

        int[] smallestPair = new int[2];

        while (idxOne < arrayOne.length && idxTwo < arrayTwo.length) {
            int firstNum = arrayOne[idxOne];
            int secondNum = arrayTwo[idxTwo];
            if (firstNum < secondNum) {
                current = secondNum - firstNum;
                idxOne++;
            } else if (secondNum < firstNum) {
                current = firstNum - secondNum;
                idxTwo++;
            } else {
                return new int[]{firstNum, secondNum};
            }
            if (smallest > current) {
                smallest = current;
                smallestPair = new int[]{firstNum, secondNum};
            }
        }
        return smallestPair;
    }

    /* Move Element to End
       Solution # 1
       O(n) time | O(1) space
   */
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int i = 0;
        int j = array.size() - 1;
        while (i < j) {

            while (i < j && array.get(j) == toMove) {
                j--;
            }
            if (array.get(i) == toMove) {
                swap(i, j, array);
            }
            i++;

        }
        return array;
    }

    public static void swap(int i, int j, List<Integer> array) {
        int temp = array.get(j);
        array.set(j, array.get(i));
        array.set(i, temp);
    }

    /* Is Monotonic
          Solution # 1
          O(n) time | O(1) space
    */
    public static boolean isMonotonic(int[] array) {
        if (array.length <= 2) return true;
        int direction = array[1] - array[0];
        for (int i=2; i<array.length; i++) {
            if (direction == 0) {
                direction = array[i] - array[i-1];
                continue;
            }
            if (breaksDirection(direction, array[i-1], array[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean breaksDirection(int direction, int prev, int current) {
        int difference  = current - prev;
        if (direction > 0) return difference < 0;
        return difference > 0;
    }

    /* Is Monotonic
          Solution # 2
          O(n) time | O(1) space
    */
    public static boolean isMonotonic2(int[] array) {
        boolean isNotAscending = true;
        boolean isNotDescending = true;
        for (int i=1; i<array.length; i++) {
            if (array[i] < array[i - 1]) {
                isNotDescending = false;
            } else if (array[i] > array[i - 1]) {
                isNotAscending = false;
            }
        }
        return isNotAscending || isNotDescending;
    }

    /*  Spiral Traverese
        Solution # 1
        O(n) time | O(1) space
    */
    public static List<Integer> spiralTraverse(int[][] array) {
        // check if array length is 0, return empty array
        if (array.length == 0) return new ArrayList<Integer>();

        // declare results array
        ArrayList<Integer> results = new ArrayList<Integer>();
        // declare  startRow and EndRow variables : endRow will be the length of the row - 1
        int startRow = 0;
        int endRow = array.length - 1;
        // declare startCol and endCol variables : enCol is equal to the length of the first column - 1
        int startCol = 0;
        int endCol = array[0].length - 1;
        // setup main while loop while starCol is less than endCol, the same for startRow and endRow
        while(startRow <= endRow && startCol <= endCol) {
            // in main loop setup  for loops to traverse the matrix
            // loop #1
            // looping from 0 to endCol
            // spin through length of the matrix and add from each column to current row
            // this loops from startCol to endCol in the first subarray and stores the results
				/*
				XXXX
				****
				****
			*/
            for(int col = startCol; col <= endCol; col++) {
                results.add(array[startRow][col]);
            }
            //
            // loop #2
            // this loops from startRow + 1 to endRow gets the row values of the last column in the matrix
            /*
             ***X
             ***X
             ***X
             */
            for(int row = startRow + 1; row <= endRow; row++)  {
                results.add(array[row][endCol]);

            }
            // loop #3
            // this loops from endCol - 1 to startCol in reverse
            // if startRow is ever endRow break the loop
            // add the results of each column in the last row
			/*
				****
				****
				XXXX
			*/
            for(int col = endCol - 1; col >= startCol; col--) {
                if (startRow == endRow) break;
                results.add(array[endRow][col]);
            }
            // loop #4
            // this loops from endRow - 1 to startRow in reverse
            // if startCol is ever endCol break the loop
            // add th e result of each row in the first column
            //
            // increment startRow and startCol and decrement endRow and endCol
			/*
				****
				X***
				X***
			*/
            for (int row = endRow - 1; row > startRow; row--) {
                if (startCol == endCol) break;
                results.add(array[row][startCol]);
            }
            // Shrink area of influence in matrix by a factor of 1 and repeat the above algorithm
            startRow++;
            startCol++;
            endRow--;
            endCol--;
            // repeat for the inner cells of the matrix at col + 1 and row + 1
        }
        // return results
        return results;
    }
}
