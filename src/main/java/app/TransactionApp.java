package app;


import repository.TransactionRepository;
import java.io.File;


public class TransactionApp {
    public static void main(String[] args) {
        String fileName = "src/main/resources/transactions.txt";
        File file = new File(fileName);
        System.out.println(file.exists());
        TransactionRepository repository = TransactionRepository.of(fileName);

        System.out.println(repository.dailyNumberOfTransactions());
        System.out.println(repository.getMonthlyNumberOfTransactions());
        System.out.println(repository.getNumberOfTransactionsByWorkers());

    }
}
