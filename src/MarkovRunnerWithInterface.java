import edu.duke.FileResource;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 12;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 615;
        int size = 50;
        EfficientMarkovModel mTwo = new EfficientMarkovModel(5);
        runModel(mTwo, st, size, seed);
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;

        long start = System.currentTimeMillis();
        MarkovModel mModel = new MarkovModel(2);
        runModel(mModel, st, size, seed);
        long end = System.currentTimeMillis();
        System.out.println("Time take : " + (end - start) / 1000F);

        start = System.currentTimeMillis();
        EfficientMarkovModel emModel = new EfficientMarkovModel(2);
        runModel(emModel, st, size, seed);
        end = System.currentTimeMillis();
        System.out.println("Time Taken : " + (end - start) / 1000F);
    }

    public static void main(String[] args) {
        MarkovRunnerWithInterface mrw = new MarkovRunnerWithInterface();
        mrw.testHashMap();
        //MarkovModel mm = new MarkovModel(2);
        //mrw.runModel(mm, "yes-this-is-a-thin-pretty-pink-thistle", 40, 42);
        //mrw.compareMethods();
    }

}

