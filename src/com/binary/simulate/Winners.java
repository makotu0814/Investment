package com.binary.simulate;

public class Winners extends InvestMethod {

    @Override
    protected double getPayMoney(InputData input, Result result) {
        if (result.getLostMoney().size() >= 2) {
            return result.getLostMoney().get(0) * 2;
        }
    
        return input.getPayUnit();
    }

    @Override
    protected void win(double pay, Result result) {
        if ( result.getLostMoney().size() > 1) {
            result.getLostMoney().remove(0);
        }
    }

    @Override
    protected void lost(double pay, Result result) {
        result.getLostMoney().add(pay);
    }
}
