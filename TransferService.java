package ua.lab.parallel;

public class TransferService {

    public void process(UserAccount accA, UserAccount accB, int value) {

        UserAccount firstLock = (accA.getNumber() < accB.getNumber()) ? accA : accB;
        UserAccount secondLock = (firstLock == accA) ? accB : accA;

        synchronized (firstLock) {
            synchronized (secondLock) {

                if (accA.currentFunds() >= value) {
                    accA.debit(value);
                    accB.credit(value);
                }
            }
        }
    }
}
