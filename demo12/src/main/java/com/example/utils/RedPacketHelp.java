package com.example.utils;

import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/2.
 */

public class RedPacketHelp {
    public static final double MIN=0.01;
    /**
     * 分切红包
     * @param total
     * @param partCount
     */
    public static double[] clipRedPacket(double total, int partCount){
        int[] ints = new int[partCount];
        Random random = new Random();
        for (int i=0;i<ints.length;i++){
            ints[i] = random.nextInt(100);
        }
        double[] percentes=new double[partCount];
        fillPercentes(ints,percentes);
        double[] result = clip(total, percentes);
        return result;
    }

    private static double[] clip(double total, double[] percentes) {
        double[] tempResult = new double[percentes.length];
        for (int i=0;i<percentes.length;i++){
            tempResult[i]=total*percentes[i];
        }
        //判断各种情况
        //判断是否有小于0.01的
        checkLessThanMin(tempResult);
        checkMoreThanMax(tempResult,total);
        //判断是否所有值加起来小于总值
        checkAllNorm(tempResult);
        checkAllEqualToTotal(tempResult,total);
        checkAllNorm(tempResult);
        return tempResult;
    }

    /**
     * 规范所有值保留小数点后两位
     * @param tempResult
     */
    private static void checkAllNorm(double[] tempResult) {
        for (int i=0;i<tempResult.length;i++){
            tempResult[i]=format(tempResult[i]);
        }
    }
    private static double format(double d){
        NumberFormat ddf1=NumberFormat.getNumberInstance() ;
        ddf1.setMaximumFractionDigits(2);
        String resultStr= ddf1.format(d) ;
        double result = StringUtils.parseDouble(resultStr);
        return result;
    }

    /**
     * 判断所有值加起来是否等于总值
     * @param tempResult
     * @param total
     */
    private static void checkAllEqualToTotal(double[] tempResult, double total) {
        double tempTotal=0;
        for (int i=0;i<tempResult.length;i++){
            tempTotal+=tempResult[i];
        }
        if (tempTotal>total){
            double offerValue = Math.abs(tempTotal - total);
            subtractOfferValue(tempResult,offerValue);
        }else if (tempTotal<total){
            double offerValue = Math.abs(tempTotal - total);
            addOfferValue(tempResult,offerValue);
        }

    }

    /**
     * 加上不足的数值
     * @param tempResult
     * @param offerValue
     */
    private static void addOfferValue(double[] tempResult, double offerValue) {
        Random random = new Random();
        int i = random.nextInt(tempResult.length);
        tempResult[i]+=offerValue;
    }

    /**
     * 减去多余的数值
     * @param tempResult
     * @param offerValue
     */
    private static void subtractOfferValue(double[] tempResult, double offerValue) {
        for (int i=0;i<tempResult.length;i++){
            double tempValue = tempResult[i];
            if (tempValue>=offerValue+MIN){
                tempResult[i]=tempValue-offerValue;
                return;
            }
        }
    }

    /**
     * 判断是否大于最大值
     * @param tempResult
     * @param total
     */
    private static void checkMoreThanMax(double[] tempResult, double total) {
        double max=total-(tempResult.length-1)*MIN;
        for (int i=0;i<tempResult.length;i++){
            if (tempResult[i]>max){
                tempResult[i]=max;
            }
        }
    }

    /**
     * 判断是否小于最小数值
     * @param tempResult
     */
    private static void checkLessThanMin(double[] tempResult) {
        for (int i=0;i<tempResult.length;i++){
            if (tempResult[i]<MIN){
                tempResult[i]=MIN;
            }
        }
    }

    private static void fillPercentes(int[] ints, double[] percentes) {
        int totalRandom=getTotal(ints);
        for (int i=0;i<ints.length;i++){
            percentes[i]=(double) ints[i]/(double) totalRandom;
        }
    }

    private static int getTotal(int[] ints) {
        int total=0;
        for (int i=0;i<ints.length;i++){
            total+=ints[i];
        }
        return total;
    }
}
