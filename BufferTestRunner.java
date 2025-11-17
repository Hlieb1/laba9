package ua.lab.parallel;

public class BufferTestRunner {

    public static void main(String[] args) throws Exception {

        CircularBuffer<String> bufA = new CircularBuffer<>(40);
        CircularBuffer<String> bufB = new CircularBuffer<>(40);

        for (int i = 1; i <= 5; i++) {
            new MessageProducer(i, bufA).start();
        }

        for (int i = 1; i <= 2; i++) {
            new MessageConverter(i, bufA, bufB).start();
        }

        for (int i = 1; i <= 100; i++) {
            String s = bufB.read();
            System.out.println(i + " >> " + s);
        }

        System.out.println("DONE");
    }
}
