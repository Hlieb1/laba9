package ua.lab.parallel;

public class CircularBuffer<T> {

    private final Object guard = new Object();
    private final Object[] array;

    private int readIdx = 0;
    private int writeIdx = 0;
    private int count = 0;

    public CircularBuffer(int size) {
        this.array = new Object[size];
    }

    public void write(T data) throws InterruptedException {
        synchronized (guard) {

            while (count == array.length) {
                guard.wait();
            }

            array[writeIdx] = data;
            writeIdx = (writeIdx + 1) % array.length;
            count++;

            guard.notifyAll();
        }
    }

    @SuppressWarnings("unchecked")
    public T read() throws InterruptedException {
        synchronized (guard) {

            while (count == 0) {
                guard.wait();
            }

            T item = (T) array[readIdx];
            readIdx = (readIdx + 1) % array.length;
            count--;

            guard.notifyAll();
            return item;
        }
    }
}
