package com.binary.simulate;

public class InputData {

    private double balance = 0;
    private double payUnit = 0;
    private double payoutRate = 1;
    private double prohit = 0;
    private double winProb = 0;
    private double needProhit = 0;
    private double lostCut = 99999999;
    private double maxEntry = 9999;
    private double maxLostChain = 9999999;

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getPayUnit() {
        return payUnit;
    }
    public void setPayUnit(double payUnit) {
        this.payUnit = payUnit;
    }
    public double getPayoutRate() {
        return payoutRate;
    }
    public void setPayoutRate(double payoutRate) {
        this.payoutRate = payoutRate;
    }
    public double getProhit() {
        return prohit;
    }
    public void setProhit(double prohit) {
        this.prohit = prohit;
    }
    public double getWinProb() {
        return winProb;
    }
    public void setWinProb(double winProb) {
        this.winProb = winProb;
    }
    public double getNeedProhit() {
        return needProhit;
    }
    public void setNeedProhit(double needProhit) {
        this.needProhit = needProhit;
    }
    public double getLostCut() {
        return lostCut;
    }
    public void setLostCut(double lostCut) {
        this.lostCut = lostCut;
    }
    public double getMaxEntry() {
        return maxEntry;
    }
    public void setMaxEntry(double maxEntry) {
        this.maxEntry = maxEntry;
    }
    public double getMaxLostChain() {
        return maxLostChain;
    }
    public void setMaxLostChain(double maxLostChain) {
        this.maxLostChain = maxLostChain;
    }


}
