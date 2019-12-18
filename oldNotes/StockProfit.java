package oldNotes;

/**
 *
 * Maximum profit by buying and selling a share at most k times
 * In share trading, a buyer buys shares and sells on a future date. Given the stock price of n days,
 * the trader is allowed to make at most k transactions, where a new transaction can only start after
 * the previous transaction is complete, find out the maximum profit that a share trader could have made.
 *
 * Examples:
 *
 * Input:
 * Price = [10, 22, 5, 75, 65, 80]; K = 2
 * Output:  87
 * Trader earns 87 as sum of 12 and 75
 * Buy at price 10, sell at 22, buy at
 * 5 and sell at 80
 *
 * Input:
 * Price = [12, 14, 17, 10, 14, 13, 12, 15]; K = 3
 * Output:  12
 * Trader earns 12 as the sum of 5, 4 and 3
 * Buy at price 12, sell at 17, buy at 10
 * and sell at 14 and buy at 12 and sell
 * at 15
 *
 * @author Anil Chowdhury
 * Created on 10/20/2019
 */
public class StockProfit {

    public static void main(String[] args) {
        StockProfit driver = new StockProfit();
        int[] stockPrice = {10, 22, 5, 75, 65, 80};
        int[] stockPrice1 = {12, 14, 17, 10, 14, 13, 12, 15};
        int[] stockPrice2 = {100, 30, 15, 10, 8, 25, 80};
        int[] stockPrice3 = {90, 80, 70, 60, 50};
        System.out.println(String.format("Max profit using 2 transactions = %d",
                driver.maxStockProfit(stockPrice, 2)));
        System.out.println(String.format("Max profit using 3 transactions = %d",
                driver.maxStockProfit(stockPrice1, 3)));
        System.out.println(String.format("Max profit using 3 transactions = %d",
                driver.maxStockProfit(stockPrice2, 3)));
        System.out.println(String.format("Max profit using 1 transactions = %d",
                driver.maxStockProfit(stockPrice3, 1)));
    }

    private int maxStockProfit(int[] stockPrice, int numOfTransactions) {
        if (stockPrice == null || stockPrice.length == 0) {
            return 0;
        }
        return maxStockProfit(stockPrice, numOfTransactions, stockPrice.length - 1);
    }

    private int maxStockProfit(int[] stockPrice, int numOfTransactions, int numberOfDays) {

        if (numberOfDays <= 0 || numOfTransactions <= 0) {
            //it there is only one day we cannot perform any transaction
            return 0;
        }
        int maxTotalProfit = 0;

        for (int numOfTransaction = 1; numOfTransaction <= numOfTransactions; numOfTransaction++) {

            for (int day = 1; day <= numberOfDays; day++) {

                //max profit achieved till (day - 1) with (numOfTransaction) if we don't do any transaction today
                int profitWithoutTransaction =
                        maxStockProfit(stockPrice, numOfTransactions, day - 1);

                //max profit achieved till (day - 1) with (numOfTransaction - 1) if we do transaction today
                int profitWithTransaction = 0;

                for (int i = 0; i < day; i++) {
                    profitWithTransaction = Math.max(profitWithTransaction, stockPrice[day] - stockPrice[i] +
                                    maxStockProfit(stockPrice, numOfTransactions - 1, i));
                }

                int currentTransactionProfit = Math.max(profitWithTransaction, profitWithoutTransaction);
                maxTotalProfit = Math.max(currentTransactionProfit, maxTotalProfit);
            }
        }
        return maxTotalProfit;
    }
}



