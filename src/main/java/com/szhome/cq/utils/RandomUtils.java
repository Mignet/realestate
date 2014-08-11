package com.szhome.cq.utils;

public class RandomUtils {

	/**
	 * 成长优势=大小为区间[950，1300]中一个随机值 
	 * 950~1100 普通 85 
	 * 1100~1150 优秀 9 
	 * 1150~1200 杰出 5
	 * 1200~1250 卓越 0.9 
	 * 1250~1300 完美 0.1
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
	 * 概率百分比
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
	 * 生命值 =  Math.round( 体力资质 * 生命系数(即1.80%) * 体力点数 + 种族成长 * 成长系数 * 等级 * 生命成长修正 + 初始生命(43) )
	 * @param physicalrate 体力资质
	 * @param stamina 体力点数
	 * @param basegrowth 种族成长
	 * @param growthadvantage 成长优势
	 * @param level 等级
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

