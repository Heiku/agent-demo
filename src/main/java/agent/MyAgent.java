package agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @Author: Heiku
 * @Date: 2019/10/17
 */
public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("This is an perform monitor agent.");

        // 添加 Transformer
        ClassFileTransformer transformer = new PerformMonitorTransformer();
        inst.addTransformer(transformer);
    }
}
