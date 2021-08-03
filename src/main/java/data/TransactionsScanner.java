package data;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class TransactionsScanner {
    public static List<Transaction> load(String fileName) throws IOException {
        List<Transaction> transactions = new LinkedList<>();
        File file = new File(fileName);

        boolean fileExists = file.exists();
        if (!fileExists) {
            throw new IOException();
        }
        Scanner scan = new Scanner(file);

        int lines = 0;
        Transaction transaction;

        while (scan.hasNextLine()) {
            transaction = Transaction.of(scan.nextLine());
            transactions.add(transaction);
        }

        return transactions;
    }
}
