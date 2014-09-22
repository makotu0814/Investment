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
                System.out.println("--------------------------------------------------" + (i + 1) + "�J����------------------------------------------------");
                for (int j = 0; j < days; j++) {

                    InvestMethod method = new Normal();
                    Result result = method.invest(input);

                    if (result.getBalance() <= 0) {
                        System.out.println((i + 1) + "�J����" + (j + 1) + "����" + result.getEntryCount() + "��ڂőޏ�");
                        System.out.println("����� : " + result.getEntryCount());
                        throw new InvestException("�������������");
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

                    System.out.println("-----------------" + (j + 1) + "����-----------------");
                    if (result.isEndTrade()) {
                        System.out.println("�\�z�ȏ�ɕ������̂ő��؂���");
                    }

                    System.out.println("���v : " + result.getProhit());
                    System.out.println("����: " + result.getBalance());
                    System.out.println("�G���g���[��:" + result.getEntryCount());
                    System.out.println("���������̌��� : " + result.getMinBalance());
                    System.out.println("�ő�̑��� : " + result.getMaxLost());
                    System.out.println("�ő�̘A�s : " + result.getChainLost());
                    System.out.println("---------------------------------------");

                    sumEntry += result.getEntryCount();

                    sumProhit += result.getProhit();

                    input.setBalance(result.getBalance());
                }
                System.out.println("---------------------------------------------------------------------------------------------------------");
            }
            System.out.println("���� : " + input.getBalance());
            System.out.println("1�����̕��ϗ��v : " + sumProhit / (months));
            System.out.println("�ő����� : " + maxEntry);
            System.out.println("�ŏ������ : " + minEntry);
            System.out.println("���ώ���� : " + sumEntry / (days * months));
            System.out.println("�ő�A�s�L�^ : "+ maxchainLost);
            System.out.println("�ő�̑���  : " + maxLost);
            System.out.println("�Z����� : " + shortEntry);
            System.out.println(Consts.shortEntry + "��ȓ��ŏI���m��:" + (int)((double)shortEntry / (days * months) * 100) + "%");
            System.out.println(Consts.shortEntry + "���葽��" + Consts.longEntry + "��ȓ��ŏI���m��" + (int)((double)middleEntry / (days * months) * 100) + "%");
            System.out.println("������������������� :"+ (int)((double)longEntry / (days * months) * 100) + "%");

        } catch (InvestException e) {
            System.out.println(e.toString());
        }
    }
}
