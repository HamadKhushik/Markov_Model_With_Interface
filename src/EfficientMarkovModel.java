import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    //private String myText;
    //private Random myRandom;
    private int number;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        number = n;
        map = new HashMap<String, ArrayList<String>>();
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
        buildMap();
        printHashMapInfo();
        StringBuilder sb = new StringBuilder();
        String key = "";
        for (int k = 0; k < numChars; k++) {
            if (k == 0) {
                int index = myRandom.nextInt(myText.length() - number);
                sb.append(myText.substring(index, index + number));
                key = myText.substring(index, index + number);
            } else {
                ArrayList<String> followed = getFollows(key);
                if (followed.isEmpty()) {
                    break;
                }
                int index = myRandom.nextInt(followed.size());
                sb.append(followed.get(index));
                key = key.substring(1) + followed.get(index);
            }
        }
        return sb.toString();
    }


    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }

    public void buildMap() {
        map.clear();
        for (int i = 0; i < myText.length() - number; i++) {
            String mapKey = myText.substring(i, i + number);
            String mapValue = myText.substring(i + number, i + number + 1);
            //System.out.println(mapKey + " : " + mapValue);
            if (!map.containsKey(mapKey)) {
                ArrayList<String> followed = new ArrayList<String>();
                followed.add(mapValue);
                map.put(mapKey, followed);
            } else {
                ArrayList<String> followed = map.get(mapKey);
                followed.add(mapValue);
                map.put(mapKey, followed);
            }
        }
        String mapKey = myText.substring(myText.length() - number);
        ArrayList<String> followed = new ArrayList<String>();
        map.put(mapKey, followed);
    }

    private void printHashMapInfo() {
        String maxKey = "";
        int maxValue = 0;
        ArrayList<String> maxValueInMap = new ArrayList<String>();
        for (String k : map.keySet()) {
            ArrayList<String> values = map.get(k);
            if (values.size() > maxValue) {
                maxKey = k;
                maxValue = values.size();
                maxValueInMap.clear();
                maxValueInMap.add(maxKey);
            } else if (values.size() == maxValue) {
                maxKey = k;
                maxValueInMap.add(maxKey);
            }
            //System.out.println("The key is :" + k + " The value is " + values);
        }
        System.out.println("the number of keys in Map are :" + map.size());
        System.out.println("The size of largest value in the Map is :" + maxValue);
        System.out.println("The keys with max values are :" + maxValueInMap);
    }

    public String toString() {
        return "Efficient Markov Model of order " + number;
    }
}



