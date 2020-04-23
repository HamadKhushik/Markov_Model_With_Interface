import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public String getRandomText(int numChars);

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        for (int i = 0; i < myText.length() - key.length(); i++) {
            if (myText.substring(i, i + key.length()).equals(key)) {
                follows.add(myText.substring(i + key.length(), i + 1 + key.length()));
            }
        }
        return follows;
    }
}
