package com.binary.simulate;

import java.util.ArrayList;
import java.util.List;

public class Martingale extends InvestMethod {

    private List<Double> lostMoney = new ArrayList<Double>();

    @Override
    protected void win(double pay, Result result) {
        if (lostMoney.size() > 1) {
            lostMoney.clear();
        }
    }

    @Override
    protected void lost(double pay, Result result) {
        lostMoney.add(pay);
    }

    @Override
    protected double getPayMoney(InputData input, Result result) {
        if (lostMoney.size() > 1) {
            System.out.println("pay : " + lostMoney.get(lostMoney.size() - 1) * 2.0f);
            return lostMoney.get(lostMoney.size() - 1) * 2.0f;
        }
        return input.getPayUnit();
    }

}
