package app;


import data.NegativeNumberException;
import repository.TransactionRepository;
import java.io.File;
import java.time.format.DateTimeParseException;


public class TransactionApp {
    public static void main(String[] args) {
        String fileName = "src/main/resources/transactions.txt";
        File file = new File(fileName);
        try {
            TransactionRepository repository = TransactionRepository.of(fileName);
            System.out.println(repository.dailyNumberOfTransactions());
            System.out.println(repository.getMonthlyNumberOfTransactions());
            System.out.println(repository.getNumberOfTransactionsByWorkers());
        }
        catch (DateTimeParseException e) {
            System.err.println("Wprowadzono niepoprawną date");
            return;
        }
        catch (NumberFormatException e) {
            System.err.println("Wprowadzono niepoprawną kwotę transakcji");
            return;
        }
        catch (NegativeNumberException e) {
            System.err.println("Liczba nie może być ujemna!");
            return;
        }

    }
}
