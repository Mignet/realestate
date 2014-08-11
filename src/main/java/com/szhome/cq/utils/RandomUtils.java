package com.szhome.cq.utils;

public class RandomUtils {

	/**
	 * �ɳ�����=��СΪ����[950��1300]��һ�����ֵ 
	 * 950~1100 ��ͨ 85 
	 * 1100~1150 ���� 9 
	 * 1150~1200 �ܳ� 5
	 * 1200~1250 ׿Խ 0.9 
	 * 1250~1300 ���� 0.1
	 * 
	 * @return
	 */
	public static int getGrowthAdvantage() {
		int i = PercentageRandom();
		switch(i){
			case 0:return getRandomInt(950,1100);
			case 1:return getRandomInt(1100,1150);
			case 2:return getRandomInt(1150,1200);
			case 3:return getRandomInt(1200,1250);
			case 4:return getRandomInt(1250,1300);
		}
		return 950;
	}

	private static double rate0 = 0.85;
	private static double rate1 = 0.09;
	private static double rate2 = 0.05;
	private static double rate3 = 0.009;
	private static double rate4 = 0.001;
	/**
	 * ���ʰٷֱ�
	 * @return int
	 */
	private static int PercentageRandom() {
		double randomNumber;
		randomNumber = Math.random();
		if (randomNumber >= 0 && randomNumber <= rate0) {
			return 0;
		} else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
			return 1;
		} else if (randomNumber >= rate0 + rate1
				&& randomNumber <= rate0 + rate1 + rate2) {
			return 2;
		} else if (randomNumber >= rate0 + rate1 + rate2
				&& randomNumber <= rate0 + rate1 + rate2 + rate3) {
			return 3;
		} else if (randomNumber >= rate0 + rate1 + rate2 + rate3
				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
			return 4;
		} 
		return -1;
	}
	
	public static int getRandomInt(int minInt,int maxInt){
		int num=(int)(Math.round(Math.random()*(maxInt-minInt)+minInt));
		return num;
	}
	
	public static double getRandomDouble(double minInt,double maxInt){
		double num=Math.random()*(maxInt-minInt)+minInt;
		return num;
	}

	
	private static int INIT_HP = 43;
	private static double OFFSET_HP = 2.06;
	private static double FACTOR_HP = 0.018;
	/**
	 * ����ֵ =  Math.round( �������� * ����ϵ��(��1.80%) * �������� + ����ɳ� * �ɳ�ϵ�� * �ȼ� * �����ɳ����� + ��ʼ����(43) )
	 * @param physicalrate ��������
	 * @param stamina ��������
	 * @param basegrowth ����ɳ�
	 * @param growthadvantage �ɳ�����
	 * @param level �ȼ�
	 * @return
	 */
	public static long caculateHP(String physicalrate, String stamina, String basegrowth, String growthadvantage,String level) {
		int iphysicalrate = Integer.parseInt(physicalrate);
		int istamina = Integer.parseInt(stamina);
		int ibasegrowth = Integer.parseInt(basegrowth);
		int igrowthadvantage = Integer.parseInt(growthadvantage);
		int ilevel = Integer.parseInt(level);
		return Math.round(iphysicalrate*FACTOR_HP*istamina+ibasegrowth*igrowthadvantage/1000*ilevel*OFFSET_HP+INIT_HP);
	}

}

