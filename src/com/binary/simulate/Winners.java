package com.binary.simulate;

import java.util.ArrayList;
import java.util.List;

public class Winners extends InvestMethod {

    private List<Double> lostMoney = new ArrayList<Double>();

    @Override
    protected double getPayMoney(InputData input, Result result) {
        if (lostMoney.size() >= 2) {
            return lostMoney.get(0) * 2;
        }

        return input.getPayUnit();
    }

    @Override
    protected void win(double pay, Result result) {
        if (lostMoney.size() >= 1) {
        	lostMoney.remove(0);
        }
    }

    @Override
    protected void lost(double pay, Result result) {
        lostMoney.add(pay);
    }
}
