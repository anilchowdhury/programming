package oldNotes;

/**
 * @author Anil Chowdhury
 *         Created on 2/1/2019.
 *
 * How many structurally unique combinations of binary tree is possible with given N values
 *
 */
public class UniqueBinaryTree {
    public static void main(String[] args) {
        UniqueBinaryTree driver = new UniqueBinaryTree();
        System.out.println(driver.uniqueBinaryTrees(2)); //2
        System.out.println(driver.uniqueBinaryTrees(3)); //5
        System.out.println(driver.uniqueBinaryTrees(4)); //14
        System.out.println(driver.uniqueBinaryTrees(5)); //42
        System.out.println(driver.uniqueBinaryTrees(6)); //132
    }

    private int uniqueBinaryTrees(int totalNodes) {
        if(totalNodes <=1) {
            return 1;
        }

        int totalUniqueCount = 0;
        for(int leftSubtree = 0 ; leftSubtree < totalNodes; leftSubtree++) {
            int leftUniqueCount = uniqueBinaryTrees(leftSubtree);
            //Root must always be present, hence subtract 1 from remaining size
            int rightUniqueCount = uniqueBinaryTrees(totalNodes - 1 - leftSubtree);
            totalUniqueCount += leftUniqueCount * rightUniqueCount;
        }
        return totalUniqueCount;
    }
}


