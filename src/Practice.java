import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {
        int sum = 0;
        if(nums == null) return sum;
        for(int num : nums){
            if(num%2 == 1 || num%2 == -1){
                sum += num;
            }
        }
        return sum;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) {
        int wordLength = Integer.MAX_VALUE;
        String wordChamp = "";
        for (String word : words) {
            if(word.length() < wordLength){
                wordChamp = word;
                wordLength = word.length();
            }
            else if(word.length() == wordLength){
                if(word.compareTo(wordChamp) < 0){ 
                    wordChamp = word;
                }
            }
        }
        return wordChamp;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {
        Set<String> returnable = new HashSet<>();
        for(String name : ages.keySet()){
            if(ages.get(name) >= 18){
                returnable.add(name);
            }
        }
        return returnable;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
        int kingNum = Integer.MIN_VALUE;
        while(head != null){
            if(head.data > kingNum){
                kingNum = head.data;
            }
            head = head.next;
        }
        return kingNum;
    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y: 1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {
        Map<T, Integer> returnable = new HashMap<>();
        while(head != null){
            if(!returnable.containsKey(head.data)){
                returnable.put(head.data, 1);
            }
            else{
                returnable.replace(head.data, returnable.get(head.data)+1);
            }
            head = head.next;
        }
        return returnable;
    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {
        return levelCountHelper(root, 0);
    }

    public static int levelCountHelper(BinaryTreeNode<?> root, int level) {
        if (root == null) return level;
        int leftLevel = levelCountHelper(root.left, level + 1);
        int rightLevel = levelCountHelper(root.right, level + 1);
        return Math.max(leftLevel, rightLevel);
    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {
        return sumAtLevelHelper(root, level);
    }

    public static int sumAtLevelHelper(BinaryTreeNode<Integer> root, int level) {
        if (root == null) return 0;
        if(level == 1) return root.data;
        return sumAtLevelHelper(root.left, level - 1) + sumAtLevelHelper(root.right, level - 1);
    }


    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {
        int sumList = sumMatchList(head);
        int sumTree = sumMatchTree(root);
        if(sumList == sumTree) return true;
        return false;
    }

    public static int sumMatchList(ListNode<Integer> head) {
        int sum = 0;
        while(head != null){
            sum += head.data;
            head = head.next;
        }
        return sum;
    }

    public static int sumMatchTree(BinaryTreeNode<Integer> root) {
        if(root == null) return 0;
        int left = sumMatchTree(root.left);
        int right = sumMatchTree(root.right);
        return root.data + left + right;
    }

    /**
     * Returns the sum of all the vertices in a graph that are reachable from a given
     * starting vertex.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the starting vertex
     * @return the sum of all the vertices
     */
    public static int graphSum(Vertex<Integer> start) {
        if(start == null) return 0;
        int sum = 0;
        Set<Vertex<Integer>> visited = new HashSet<>();
        graphSumHelper(start, visited);
        for(Vertex<Integer> cur : visited){
            sum+= cur.data;
        }
        return sum;
    }
    public static void graphSumHelper(Vertex<Integer> start, Set<Vertex<Integer>> visited){
        if (start == null || visited.contains(start)) return;
        visited.add(start);
        for(Vertex<Integer> neighbor : start.neighbors){
            graphSumHelper(neighbor, visited);
        }
    }

    /**
     * Returns the count of vertices in a graph that have an outdegree of 0.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the entrypoint to the graph
     * @return the count of vertices with outdegree 0
     */
    public static int sinkCount(Vertex<Integer> start) {
        if(start == null) return 0;
        int sum = 0;
        Set<Vertex<Integer>> visited = new HashSet<>();
        sinkCountHelper(start, visited);
        for(Vertex<Integer> cur : visited){
            if(cur.neighbors.size() == 0 || cur.neighbors == null){
                sum++;
            }
        }
        return sum;
    }

    public static void sinkCountHelper(Vertex<Integer> start, Set<Vertex<Integer>> visited){
        if(start == null || visited.contains(start)) return;
        visited.add(start);
        for(Vertex<Integer> neighbor : start.neighbors){
            sinkCountHelper(neighbor, visited);
        }
    }
}