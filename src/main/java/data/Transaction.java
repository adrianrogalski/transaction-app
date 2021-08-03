package data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Transaction {
    private LocalDate date;
    private BigDecimal amount;
    private String workerID;

    private Transaction(LocalDate date, BigDecimal amount, String workerID) {
        this.date = date;
        this.amount = amount;
        this.workerID = workerID;
    }

    public static Transaction of(String userInput) throws DateTimeParseException, NumberFormatException {
        String[] transaction = userInput.split(" ");

        LocalDate date = LocalDate.parse(transaction[0]);
        BigDecimal amount = new BigDecimal(transaction[1].replace(',','.'));
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeNumberException();
        }
        String workerID = transaction[2];
        return new Transaction(date,amount,workerID);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
