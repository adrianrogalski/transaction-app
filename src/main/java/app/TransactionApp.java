package app;

import data.Transaction;
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionApp {
    public static void main(String[] args) {
        String transactionInput1 = "2021-07-10 124,65 AB01";
        String transactionInput2 = "2021-07-10 24,65 AB01";
        String transactionInput3 = "2021-07-11 64,65 AB02";
        String transactionInput4 = "2021-08-11 64,65 AB02";

        Transaction transaction = Transaction.of(transactionInput1);
        Transaction transaction2 = Transaction.of(transactionInput2);
        Transaction transaction3 = Transaction.of(transactionInput3);
        Transaction transaction4 = Transaction.of(transactionInput4);

        List<Transaction> transactions = new LinkedList<>();
        transactions.add(transaction);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);

        Map<LocalDate, List<Transaction>> numberOfTransactionsInADay = transactions.stream().collect(Collectors.groupingBy(t -> t.getDate()));
        Map<String, List<Transaction>> numberOfTransactionsForEachWorker = transactions.stream().collect(Collectors.groupingBy(t -> t.getWorkerID()));
        BigDecimal sumOfAllTransactions = transactions.stream().map(t -> t.getAmount()).reduce(BigDecimal.ZERO, (sum, value) -> sum.add(value));
        System.out.println(transactionInput1);
        System.out.println(transactionInput2);
        System.out.println(transactionInput3);

        System.out.println("Liczba transakcji");
        for (Map.Entry<LocalDate, List<Transaction>> entry : numberOfTransactionsInADay.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue().stream().count());
        }
        System.out.println("Liczba transakcji pracowników:");
        for (Map.Entry<String, List<Transaction>> entry : numberOfTransactionsForEachWorker.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue().stream().count());
        }
        System.out.println("Miesięczna suma kwot transakcji:");
        Map<Month, List<Transaction>> sumOfMonthlyTransactionsNumber = transactions.stream().collect(Collectors.groupingBy(t -> t.getDate().getMonth()));
        for (Map.Entry<Month, List<Transaction>> entry : sumOfMonthlyTransactionsNumber.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue().stream().count());

        }

    }
}
