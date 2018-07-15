package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 Chef Watson uses a social network called ChefBook, which has a new feed consisting of posts by his friends. Each post
 can be characterized by f - the identifier of the friend who created the post, p - the popularity of the
 post(which is pre-calculated by ChefBook platform using some machine learning algorithm) and s - the contents of the
 post which is a string of lower and uppercase English alphabets.

 Also, Chef has some friends, which he has marked as special.

 The algorithm used by ChefBook for determining the order of posts in news feed is as follows:

 Posts of special friends should be shown first, irrespective of popularity. Among all such posts the popular ones
 should be shown earlier. Among all other posts, popular posts should be shown earlier.
 Given, a list of identifiers of Chef's special friends and a list of posts, you have to implement this algorithm for
 engineers of ChefBook and output the correct ordering of posts in the new feed.

 Input
 -----
 First line contains N, number of special friends of Chef and M, the number of posts. Next line contains N integers
 A1, A2, ..., AN denoting the identifiers of special friends of Chef. Each of the next M lines contains a pair of
 integers and a string denoting f, p and s, identifier of the friend who created the post, the popularity of the post
 and the contents of the post, respectively. It is guaranteed that no two posts have same popularity.

 Output
 ------
 Output correct ordering of posts in news feed in M lines. Output only the contents of a post.

 Constraints
 -----------
 1 ≤ N, M ≤ 10^3
 1 ≤ Ai, f, p ≤ 10^5
 1 ≤ length(s) ≤ 100

 Example
 Input:
 2 4
 1 2
 1 1 WhoDoesntLoveChefBook
 2 2 WinterIsComing
 3 10 TheseViolentDelightsHaveViolentEnds
 4 3 ComeAtTheKingBestNotMiss

 Output:
 WinterIsComing
 WhoDoesntLoveChefBook
 TheseViolentDelightsHaveViolentEnds
 ComeAtTheKingBestNotMiss
 */
class ChefWatsonSocialNetwork {

    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] inputs = getSpecialFriendAndPostCount(reader);
            int specialFriendCount = Integer.parseInt(inputs[0]);
            int totalPost = Integer.parseInt(inputs[1]);
            Set<Integer> specialFriendIDs = getListOfSpecialFriendIDs(reader, specialFriendCount);
            List<FeedData> allFeeds = getAllFeeds(reader, totalPost);
            Collections.sort(allFeeds, Collections.reverseOrder(new FeedComparator(specialFriendIDs)));
            printPost(allFeeds);
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printPost(List<FeedData> allFeeds) {
        for (FeedData data : allFeeds) {
            System.out.println(data.getContent());
        }
    }

    private static String getNextLine(BufferedReader reader) throws CodeChefException {
        String nextLine;
        try {
            nextLine = reader.readLine().trim();
        } catch (IOException ex) {
            throw new CodeChefException(ex.getMessage());
        }
        return nextLine;
    }

    private static String[] getSpecialFriendAndPostCount(BufferedReader reader) throws CodeChefException {
        String[] inputs;
        String nextLine = getNextLine(reader);
        inputs = nextLine.split("\\s");
        if (inputs.length != 2) {
            throw new CodeChefException("Invalid input count");
        }
        return inputs;
    }

    private static Set<Integer> getListOfSpecialFriendIDs(BufferedReader reader, int specialFriendCount)
            throws CodeChefException {
        Set<Integer> specialFriendIDs = new HashSet<>();
        String nextLine = getNextLine(reader);
        String[] split = nextLine.split("\\s");
        if (specialFriendCount != split.length) {
            throw new CodeChefException("Number of special friend count did not match");
        }
        for (String id : split) {
            specialFriendIDs.add(Integer.parseInt(id));
        }
        return specialFriendIDs;
    }

    private static List<FeedData> getAllFeeds(BufferedReader reader, int totalPost)
            throws CodeChefException {
        int counter = 0;
        List<FeedData> allFeeds = new ArrayList<>();
        while (counter < totalPost) {
            String nextLine = getNextLine(reader);
            String[] split = nextLine.split("\\s");
            if (split.length != 3) {
                throw new CodeChefException("Invalid data");
            }
            int friendID = Integer.parseInt(split[0]);
            int popularity = Integer.parseInt(split[1]);
            String content = split[2];
            allFeeds.add(new FeedData(friendID, popularity, content));
            counter++;
        }
        return allFeeds;
    }

    private static class FeedComparator implements Comparator<FeedData> {
        private Set<Integer> specialFriendIDs;

        FeedComparator(Set<Integer> specialFriendIDs) {
            this.specialFriendIDs = specialFriendIDs;
        }

        @Override
        public int compare(FeedData o1, FeedData o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }

            if (specialFriendIDs.contains(o1.getFriendID()) && specialFriendIDs.contains(o2.getFriendID())) {
                return Integer.compare(o1.getPopularity(), o2.getPopularity());
            } else if (!specialFriendIDs.contains(o1.getFriendID()) && !specialFriendIDs.contains(o2.getFriendID())) {
                return Integer.compare(o1.getPopularity(), o2.getPopularity());
            }
            if (specialFriendIDs.contains(o1.getFriendID())) {
                return 1;
            }
            if (specialFriendIDs.contains(o2.getFriendID())) {
                return -1;
            }
            return 0;
        }
    }

    private static class FeedData {
        private int friendID;
        private int popularity;
        private String content;

        FeedData(int friendID, int popularity, String content) {
            this.friendID = friendID;
            this.content = content;
            this.popularity = popularity;
        }

        String getContent() {
            return content;
        }

        int getPopularity() {
            return popularity;
        }

        int getFriendID() {
            return friendID;
        }
    }

    private static class CodeChefException extends Exception {
        CodeChefException(String message) {
            super(message);
        }
    }
}
