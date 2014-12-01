import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CounterVisitor implements Visitor {

    private String[] positiveWords = {"love", "awesome", "wonderful", "beautiful"};
    private int userCount = 0;
    private int groupCount = 0;
    private int messageCount = 0;
    private double positiveMsg = 0.0;

    public int getUserCount() {
        return userCount;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public double getPositiveMsg() {
        return ((positiveMsg / messageCount) * 100);
    }

    public void visitUser(User user) {
        userCount++;
    }

    public void visitGroup(Group group) {
        groupCount++;
    }

    public void visitMsg(User user) {
        messageCount += user.getTweetCount();
    }

    /* 
     Iterates through each tweet, then through each word, then through the postiiveWords array
     to try to find a match.
     */
    public void visitPositiveMsg(User user) {
        List<String> check = user.getMyTweets();

        check.stream().map((s) -> s.split(" ")).forEach((arr) -> {
            for (String a : arr) {
                for (String j : positiveWords) {
                    if (a.equalsIgnoreCase(j)) {
                        positiveMsg++;
                    }
                }
            }
        });

    }
}
