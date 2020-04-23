import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    //private String myText;
    //private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String key = "";
        for (int k = 0; k < numChars; k++) {
            if (k == 0) {
                int index = myRandom.nextInt(myText.length() - 1);
                sb.append(myText.charAt(index));
                key = myText.substring(index, index + 1);
            } else {
                ArrayList<String> followed = getFollows(key);
                if (followed.size() == 0) {
                    break;
                }
                int index = myRandom.nextInt(followed.size());
                sb.append(followed.get(index));
                key = followed.get(index);
            }

        }

        return sb.toString();
    }

    /*public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        for (int i = 0; i < myText.length() - key.length(); i++) {
            if (myText.substring(i, i + key.length()).equals(key)) {
                follows.add(myText.substring(i + key.length(), i + 1 + key.length()));
            }
        }
        return follows;
    }*/

    public String toString() {
        return "Markov Model of order 1";
    }
}
