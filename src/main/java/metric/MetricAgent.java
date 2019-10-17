package metric;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Heiku
 * @Date: 2019/10/17
 */
public class MetricAgent {

    public static void premain(String agentArgs, Instrumentation inst){
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            Metric.printMemoryInfo();
            Metric.printGCInfo();
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }
}
