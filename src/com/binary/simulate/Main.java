package com.binary.simulate;




public class Main {

    private static final int days = 1;
    private static final int months = 1;

    /**
     * @param args
     */
    public static void main(String[] args) {
        InputData input = new InputData();
        input.setBalance(50000);
        input.setNeedProhit(1300);
        input.setPayoutRate(1.8);
        input.setPayUnit(1800);
        input.setProhit(0);
        input.setWinProb(60);
        //input.setMaxEntry(10);
        //input.setLostCut(6000);
        //input.setMaxLostChain(7);

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
                System.out.println("--------------------------------------------------" + (i + 1) + "ƒJŒ–Ú------------------------------------------------");
                for (int j = 0; j < days; j++) {

                    InvestMethod method = new Martingale();
                    Result result = method.invest(input);

                    if (result.getBalance() <= 0) {
                        System.out.println((i + 1) + "ƒJŒ–Ú" + (j + 1) + "“ú–Ú" + result.getEntryCount() + "‰ñ–Ú‚Å‘Şê");
                        System.out.println("æˆø‰ñ” : " + result.getEntryCount());
                        System.out.println("—˜‰v : " + result.getProhit());
                        System.out.println("˜A”s‹L˜^ : " + result.getChainLost());
                        throw new InvestException("‘‹à‚ª’ê‚ğ‚Â‚«‚½");
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

                    System.out.println("-----------------" + (j + 1) + "“ú–Ú-----------------");
                    if (result.isEndTrade()) {
                        System.out.println("—\‘zˆÈã‚É•‰‚¯‚½‚Ì‚Å‘¹Ø‚µ‚½");
                    }

                    System.out.println("—˜‰v : " + result.getProhit());
                    System.out.println("ŒûÀ: " + result.getBalance());
                    System.out.println("ƒGƒ“ƒgƒŠ[‰ñ”:" + result.getEntryCount());
                    System.out.println("Œ¸‚Á‚½‚ÌŒûÀ : " + result.getMinBalance());
                    System.out.println("Å‘å‚Ì‘¹¸ : " + result.getMaxLost());
                    System.out.println("Å‘å‚Ì˜A”s : " + result.getChainLost());
                    System.out.println("---------------------------------------");

                    sumEntry += result.getEntryCount();

                    sumProhit += result.getProhit();

                    input.setBalance(result.getBalance());
                }
                System.out.println("---------------------------------------------------------------------------------------------------------");
            }
            System.out.println("ŒûÀ : " + input.getBalance());
            System.out.println("1‚©Œ‚Ì•½‹Ï—˜‰v : " + sumProhit / (months));
            System.out.println("Å‘åæˆø‰ñ” : " + maxEntry);
            System.out.println("Å­æˆø‰ñ” : " + minEntry);
            System.out.println("•½‹Ïæˆø‰ñ” : " + sumEntry / (days * months));
            System.out.println("Å‘å˜A”s‹L˜^ : "+ maxchainLost);
            System.out.println("Å‘å‚Ì‘¹¸  : " + maxLost);
            System.out.println("’Zæˆø‰ñ” : " + shortEntry);
            System.out.println(Consts.shortEntry + "‰ñˆÈ“à‚ÅI‚í‚éŠm—¦:" + (int)((double)shortEntry / (days * months) * 100) + "%");
            System.out.println(Consts.shortEntry + "‰ñ‚æ‚è‘½‚­" + Consts.longEntry + "‰ñˆÈ“à‚ÅI‚í‚éŠm—¦" + (int)((double)middleEntry / (days * months) * 100) + "%");
            System.out.println("‚»‚ê‚æ‚è‚à‘½‚­æˆø‚·‚é‰ñ” :"+ (int)((double)longEntry / (days * months) * 100) + "%");

        } catch (InvestException e) {
            System.out.println(e.toString());
        }
    }
}
