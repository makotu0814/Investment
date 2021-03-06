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

            if (EndTrade(result, input)) {
                result.setEndTrade(true);
                break;
            }

            double pay = getPayMoney(input, result);
            if (result.getBalance() < pay) {
                System.out.println(pay + "払いたいけどお金が足りない");
                pay = result.getBalance();
                System.out.println("有り金を全てつぎ込む :" + pay);
            }
            result.setBalance(result.getBalance() - pay);
            result.setMinBalance(Math.min(result.getMinBalance(), result.getBalance()));

            double judge = Math.random() * 100;
            if (judge < input.getWinProb()) {
                // 勝ち
                win(pay, result);
                result.win();
                result.addPayout(input.getPayoutRate() * pay);

                double prohit = (input.getPayoutRate() - 1) * pay;
                result.addProhit(prohit);

                result.setChainLost(Math.max(chainLost, result.getChainLost()));
                chainLost = 0;
            } else {
                // 負け
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

    protected boolean EndTrade(Result result, InputData input) {

        boolean isMaxEntry = result.getEntryCount() >= input.getMaxEntry();
        boolean isMaxChainLost = result.getChainLost() >= input.getMaxLostChain();
        boolean isLostCut = result.getMaxLost() <= -input.getLostCut();

        return isMaxEntry || isMaxChainLost || isLostCut;
    }

    protected abstract void win(double pay, Result result);
    protected abstract void lost(double pay, Result result);
    protected abstract double getPayMoney(InputData input, Result result);
}
