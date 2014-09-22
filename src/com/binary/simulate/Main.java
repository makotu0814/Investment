package com.binary.simulate;




public class Main {

    private static final int days = 20;
    private static final int months = 12;

    /**
     * @param args
     */
    public static void main(String[] args) {
        InputData input = new InputData();
        input.setBalance(50000);
        input.setNeedProhit(700);
        input.setPayoutRate(1.8);
        input.setPayUnit(1000);
        input.setProhit(0);
        input.setWinProb(60);
        //input.setMaxEntry(10);
        //input.setLostCut(6000);
        //input.setMaxLostChain(5);

        try {
            int sumEntry = 0;
            int maxEntry = 0;
            int minEntry = 999999;
            int maxchainLost = 0;
            int shortEntry = 0;
            int middleEntry = 0;
            int longEntry = 0;
            double minBalance = 999999999;
            double maxLost = 0;
            double sumProhit = 0;

            for (int i = 0; i < months; i++) {
                System.out.println("--------------------------------------------------" + (i + 1) + "カ月目------------------------------------------------");
                for (int j = 0; j < days; j++) {

                    InvestMethod method = new Normal();
                    Result result = method.invest(input);

                    if (result.getBalance() <= 0) {
                        System.out.println((i + 1) + "カ月目" + (j + 1) + "日目" + result.getEntryCount() + "回目で退場");
                        System.out.println("取引回数 : " + result.getEntryCount());
                        throw new InvestException("資金が底をつきた");
                    }

                    if (result.getEntryCount() <= Consts.shortEntry) {
                        shortEntry++;
                    }

                    if (Consts.shortEntry < result.getEntryCount() && result.getEntryCount() <= Consts.longEntry) {
                        middleEntry++;
                    }

                    if (result.getEntryCount() > Consts.longEntry) {
                        longEntry++;
                    }

                    maxEntry = Math.max(result.getEntryCount(), maxEntry);
                    minEntry = Math.min(result.getEntryCount(), minEntry);
                    maxchainLost = Math.max(result.getChainLost(), maxchainLost);
                    minBalance = Math.min(result.getMinBalance(), minBalance);
                    maxLost = Math.min(result.getMaxLost(), maxLost);

                    System.out.println("-----------------" + (j + 1) + "日目-----------------");
                    if (result.isEndTrade()) {
                        System.out.println("予想以上に負けたので損切した");
                    }

                    System.out.println("利益 : " + result.getProhit());
                    System.out.println("口座: " + result.getBalance());
                    System.out.println("エントリー回数:" + result.getEntryCount());
                    System.out.println("減った時の口座 : " + result.getMinBalance());
                    System.out.println("最大の損失 : " + result.getMaxLost());
                    System.out.println("最大の連敗 : " + result.getChainLost());
                    System.out.println("---------------------------------------");

                    sumEntry += result.getEntryCount();

                    sumProhit += result.getProhit();

                    input.setBalance(result.getBalance());
                }
                System.out.println("---------------------------------------------------------------------------------------------------------");
            }
            System.out.println("口座 : " + input.getBalance());
            System.out.println("1か月の平均利益 : " + sumProhit / (months));
            System.out.println("最大取引回数 : " + maxEntry);
            System.out.println("最少取引回数 : " + minEntry);
            System.out.println("平均取引回数 : " + sumEntry / (days * months));
            System.out.println("最大連敗記録 : "+ maxchainLost);
            System.out.println("最大の損失  : " + maxLost);
            System.out.println("短取引回数 : " + shortEntry);
            System.out.println(Consts.shortEntry + "回以内で終わる確率:" + (int)((double)shortEntry / (days * months) * 100) + "%");
            System.out.println(Consts.shortEntry + "回より多く" + Consts.longEntry + "回以内で終わる確率" + (int)((double)middleEntry / (days * months) * 100) + "%");
            System.out.println("それよりも多く取引する回数 :"+ (int)((double)longEntry / (days * months) * 100) + "%");

        } catch (InvestException e) {
            System.out.println(e.toString());
        }
    }
}
