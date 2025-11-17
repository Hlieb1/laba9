package ua.lab.parallel;

public class MessageConverter extends Thread {

    private final CircularBuffer<String> source;
    private final CircularBuffer<String> target;
    private final int id;

    public MessageConverter(int id, CircularBuffer<String> src, CircularBuffer<String> dst) {
        this.id = id;
        this.source = src;
        this.target = dst;
        setDaemon(true);
        setName("Mapper-" + id);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String incoming = source.read();
                String modified = "[Mapper " + id + "] processed -> " + incoming;
                target.write(modified);
            }
        } catch (Exception ignored) {}
    }
}
