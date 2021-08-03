package data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private BigDecimal amount;
    private String workerID;

    private Transaction(LocalDate date, BigDecimal amount, String workerID) {
        this.date = date;
        this.amount = amount;
        this.workerID = workerID;
    }

    public static Transaction of(String userInput) {
        String[] transaction = userInput.split(" ");
        LocalDate date = LocalDate.parse(transaction[0]);
        BigDecimal amount = new BigDecimal(transaction[1].replace(',','.'));
        String workerID = transaction[2];
        return new Transaction(date,amount,workerID);
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getWorkerID() {
        return workerID;
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", workerID='" + workerID + '\'' +
                '}';
    }
}
