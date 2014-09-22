package com.binary.simulate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonteCarlo extends InvestMethod {

    List<Integer> list = new ArrayList<Integer>(Arrays.<Integer>asList(1, 2, 3));

    @Override
    protected void win(double pay, Result result) {
        list.remove(0);
        list.remove(list.size()-1);
    }

    @Override
    protected void lost(double pay, Result result) {
        list.add(list.get(0) + list.get(list.size() - 1));
    }

    @Override
    protected double getPayMoney(InputData input, Result result) {

        if (list.size() <= 1) {
            list.clear();
            list.add(1);
            list.add(2);
            list.add(3);
        }

        int rate = list.get(0) + list.get(list.size()-1);

        return input.getPayUnit() * rate;
    }

}
