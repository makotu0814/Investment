package com.binary.simulate;


public class Normal extends InvestMethod{

    @Override
    protected double getPayMoney(InputData input, Result result) {
        return input.getPayUnit();
    }

    @Override
    protected void win(double pay, Result result) {
    }

    @Override
    protected void lost(double pay, Result result) {
    }
}
