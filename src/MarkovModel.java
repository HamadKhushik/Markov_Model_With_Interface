import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    //private String myText;
    //private Random myRandom;
    private int number;

    public MarkovModel(int n) {
        myRandom = new Random();
        number = n;
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
                int index = myRandom.nextInt(myText.length() - number);
                sb.append(myText.substring(index, index + number));
                key = myText.substring(index, index + number);
            } else {
                ArrayList<String> followed = getFollows(key);
                if (followed.size() == 0) {
                    break;
                }
                int index = myRandom.nextInt(followed.size());
                sb.append(followed.get(index));
                key = key.substring(1) + followed.get(index);
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
        return "markov Model of order " + number;
    }
}


