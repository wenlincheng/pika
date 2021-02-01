package com.wenlincheng.pika.item;

import cn.hutool.core.util.RandomUtil;
import com.wenlincheng.pika.item.util.PasswordEncodeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.MockUtil;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 密码加密
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/27 9:42 下午
 */
@RunWith(PowerMockRunner.class) // 告诉JUnit使用PowerMockRunner进行测试
@PrepareForTest({PasswordEncodeUtil.class}) // 所有需要测试的类列在此处，适用于模拟final类或有final, private, static, native方法的类
@PowerMockIgnore("javax.management.*") //为了解决使用powermock后，提示classloader错误
public class PasswordEncodeUtilTests {

    @Test
    public void testEncode() {
        PowerMockito.mockStatic(PasswordEncodeUtil.class);
        PowerMockito.when(PasswordEncodeUtil.encode("123456")).thenReturn("$2a$04$OB/Xe3vuzWlltg6svj1Pf.1xDcTEqb4P8CvBawmJSCun2QiOs2t3S");
        Assert.assertEquals("$2a$04$OB/Xe3vuzWlltg6svj1Pf.1xDcTEqb4P8CvBawmJSCun2QiOs2t3S", PasswordEncodeUtil.encode("123456"));
    }
}
