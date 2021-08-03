package repository;

import data.Transaction;
import data.TransactionsScanner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TransactionRepository {
    private List<Transaction> transactions;

    private TransactionRepository(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static TransactionRepository of(String fileName) {
        List<Transaction> transactionsList = new LinkedList<>();
        try {
            transactionsList = TransactionsScanner.load(fileName);
        }
        catch (IOException e) {
            System.err.println("Plik nie mógł zostać załadowany");
            System.exit(1);
        }
        return new TransactionRepository(transactionsList);
    }


    public String dailyNumberOfTransactions() {
        Map<LocalDate, List<Transaction>> numberOfTransactionsInADay = transactions.stream().collect(Collectors.groupingBy(t -> t.getDate()));
        String text = "Liczba transakcji\n";
        for (Map.Entry<LocalDate, List<Transaction>> entry : numberOfTransactionsInADay.entrySet()) {
            text = text.concat(entry.getKey() + "\t" + entry.getValue().stream().count()+"\n");
        }
        return text;
    }

    public String getNumberOfTransactionsByWorkers() {
        Map<String, List<Transaction>> numberOfTransactionsForEachWorker = transactions.stream().collect(Collectors.groupingBy(t -> t.getWorkerID()));
        String text = "Liczba transakcji pracowników:\n";
        for (Map.Entry<String, List<Transaction>> entry : numberOfTransactionsForEachWorker.entrySet()) {
            text = text.concat(entry.getKey() + "\t" + entry.getValue().stream().count() + "\n");
        }
        return text;
    }

    public String getMonthlyNumberOfTransactions() {
        Map<Month, List<Transaction>> monthlyNumberOfTransactions = transactions.stream().collect(Collectors.groupingBy(t -> t.getDate().getMonth()));
        String text = "Miesięczna suma kwot transakcji:\n";

        for (Map.Entry<Month, List<Transaction>> entry : monthlyNumberOfTransactions.entrySet()) {
            text = text.concat(entry.getKey() + "\t" + entry.getValue().stream().count() + "\n");
        }
        return text;
    }

}
