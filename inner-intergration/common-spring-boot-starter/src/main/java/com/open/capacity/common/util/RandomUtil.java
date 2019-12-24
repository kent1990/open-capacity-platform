package com.open.capacity.common.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数生成util
 **/
public class RandomUtil {

    private static final ThreadLocalRandom random=ThreadLocalRandom.current();

  

    //num为随机数流水号
    public static String generateNumber(final int num){
        StringBuffer sb=new StringBuffer();
        for (int i=1;i<=num;i++){
            sb.append(random.nextInt(9));
        }
        return sb.toString();
        
    }


}