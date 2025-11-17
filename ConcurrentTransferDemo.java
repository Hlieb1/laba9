package ua.lab.parallel;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentTransferDemo {

    public static void main(String[] args) throws Exception {

        Random rnd = new Random();
        TransferService service = new TransferService();

        int accCount = 180;
        List<UserAccount> storage = new ArrayList<>();

        for (int i = 0; i < accCount; i++) {
            storage.add(new UserAccount(i, 200 + rnd.nextInt(800)));
        }

        int sumBefore = storage.stream().mapToInt(UserAccount::currentFunds).sum();
        System.out.println("Total before = " + sumBefore);

        ExecutorService executor = Executors.newFixedThreadPool(120);

        for (int i = 0; i < 3500; i++) {
            executor.submit(() -> {
                UserAccount x = storage.get(rnd.nextInt(accCount));
                UserAccount y = storage.get(rnd.nextInt(accCount));

                if (x != y) {
                    int v = rnd.nextInt(30);
                    service.process(x, y, v);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        int sumAfter = storage.stream().mapToInt(UserAccount::currentFunds).sum();
        System.out.println("Total after = " + sumAfter);

        System.out.println(sumBefore == sumAfter ? "OK" : "FAIL");
    }
}
