package ua.lab.parallel;

public class MessageProducer extends Thread {

    private final CircularBuffer<String> destination;
    private final int id;

    public MessageProducer(int id, CircularBuffer<String> dest) {
        this.id = id;
        this.destination = dest;
        setDaemon(true);
        setName("Writer-" + id);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String text = "[Generator " + id + "] -> msg#" + System.nanoTime();
                destination.write(text);
                Thread.sleep(7);
            }
        } catch (Exception ignored) {}
    }
}
