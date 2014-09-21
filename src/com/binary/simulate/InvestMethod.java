package com.binary.simulate;

public abstract class InvestMethod {

    public Result invest(InputData input) {
    
        Result result = new Result();
        result.setBalance(input.getBalance());
        result.setMinBalance(999999999);
        result.setMaxLost(0);

        int chainLost  = 0;
        while (result.getBalance() > 0
                && input.getNeedProhit() >= result.getProhit()) {

            double pay = getPayMoney(input, result);
            if (result.getBalance() < pay) {
                pay = result.getBalance();
            }
            result.setBalance(result.getBalance() - pay);
            result.setMinBalance(Math.min(result.getMinBalance(), result.getBalance()));

            double judge = Math.random() * 100;
            if (judge < input.getWinProb()) {
                // Ÿ‚¿
                win(pay, result);
                result.win();
                result.addPayout(input.getPayoutRate() * pay);

                double prohit = (input.getPayoutRate() - 1) * pay;
                result.addProhit(prohit);

                result.setChainLost(Math.max(chainLost, result.getChainLost()));
                chainLost = 0;
            } else {
                // •‰‚¯
                lost(pay, result);
                result.lost();
                chainLost++;
                result.addProhit(-pay);
                result.setMaxLost(Math.min(result.getProhit(), result.getMaxLost()));
            }
            result.addEntryCount();
        }

        return result;
    }

    protected abstract void win(double pay, Result result);
    protected abstract void lost(double pay, Result result);
    protected abstract double getPayMoney(InputData input, Result result);
}
