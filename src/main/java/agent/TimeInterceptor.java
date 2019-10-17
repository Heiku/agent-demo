package agent;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @Author: Heiku
 * @Date: 2019/10/17
 */
public class TimeInterceptor {

    @RuntimeType
    public static Object interceptor(@Origin Method method, @SuperCall Callable<?> callable) throws Exception{
        long start = System.currentTimeMillis();

        try {
            // 原函数执行
            return callable.call();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(method + " : took " + (System.currentTimeMillis() - start) + "ms");
        }
        return null;
    }
}
