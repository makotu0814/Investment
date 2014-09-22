package com.binary.simulate;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private double balance = 0;
    private double minBalance = 0;
    private double prohit = 0;
    private double maxLost = 0;
    private int entryCount  = 0;
    private int chainLost = 0;
    private List<Boolean> winOrlost  = new ArrayList<Boolean>();
    private boolean endTrade = false;

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getMinBalance() {
        return minBalance;
    }
    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }
    public double getProhit() {
        return prohit;
    }
    public void setProhit(double prohit) {
        this.prohit = prohit;
    }
    public int getChainLost() {
        return chainLost;
    }
    public void setChainLost(int chainLost) {
        this.chainLost = chainLost;
    }
    public List<Boolean> getWinOrlost() {
        return winOrlost;
    }
    public void setWinOrlost(List<Boolean> winOrlost) {
        this.winOrlost = winOrlost;
    }
    public void addPayout(double payOut) {
        balance += payOut;
    }
    public void addProhit(double prohit) {
        this.prohit += prohit;
    }
    public void addEntryCount() {
        entryCount++;
    }
    public int getEntryCount() {
        return entryCount;
    }
    public void resetChainLost() {
        chainLost = 0;
    }
    public void addChainLost() {
        chainLost++;
    }
    public void win() {
        winOrlost.add(true);
    }
    public void lost() {
        winOrlost.add(false);
    }
    public double getMaxLost() {
        return maxLost;
    }
    public void setMaxLost(double maxLost) {
        this.maxLost = maxLost;
    }
    public boolean isEndTrade() {
        return endTrade;
    }
    public void setEndTrade(boolean endTrade) {
        this.endTrade = endTrade;
    }
}
