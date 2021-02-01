package com.wenlincheng.pika.common.core.util;

import java.math.BigDecimal;

/**
 * BigDecimal精度问题
 *
 * Created by YangLD on 2017/11/3.
 */
public class MathUtil {

    /*************
     * 是否免费
     * @param i
     * @return
     */
    public static final boolean isZeroPrice(final BigDecimal i){
        final double v = i == null ? 0 : i.doubleValue();
        return isZeroPrice(v);
    }
    public static final boolean isZeroPrice(final double v){
        return v < 0.0001 && v > -0.0001;
    }
    public static final boolean isGreaterThanZero(final BigDecimal o){
        return o.doubleValue() >= 0.0001;
    }
    public static final boolean isGreaterThanZero(final double o){
        return o >= 0.0001;
    }
    public static final boolean isEqualORLessThanZero(final BigDecimal o){
        return o.doubleValue() < 0.0001;
    }
    public static final boolean isEqualORLessThanZero(final double o){
        return o < 0.0001;
    }
    public static final boolean isLessThanZero(final BigDecimal o){
        return o.doubleValue() <= -0.0001;
    }
    public static final boolean isLessThanZero(final double o){
        return o <= -0.0001;
    }

    public static final boolean isLeftEqualORLessThanRight(final BigDecimal left, final BigDecimal right){
        final double v = left.doubleValue() - right.doubleValue();
        return v < 0.0001;
    }
    public static final boolean isLeftEqualORLessThanRight(final double left, final double right){
        final double v = left - right;
        return v < 0.0001;
    }
    public static final boolean isLeftLessThanRight(final BigDecimal left, final BigDecimal right){
        final double v = left.doubleValue() - right.doubleValue();
        return v <= -0.0001;
    }
    public static final boolean isLeftLessThanRight(final double left, final double right){
        final double v = left - right;
        return v <= -0.0001;
    }
    public static final boolean isEqualPrice(final BigDecimal b1, final BigDecimal b2){
        if(b1 == null){
            if(b2 == null){
                return true;
            }else{
                return false;
            }
        }else if(b2 == null){
            return false;
        }
        final double v = b1.doubleValue() - b2.doubleValue();
        return v < 0.0001 && v > -0.0001;
    }

    public static final boolean isEqualPrice(final double b1, final double b2){
        final double v = b1 - b2 ;
        return v < 0.0001 && v > -0.0001;
    }
}
