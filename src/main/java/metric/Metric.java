package metric;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Heiku
 * @Date: 2019/10/17
 */
public class Metric {
    private static final long MB = 1024 * 1024L;

    public static void printMemoryInfo(){
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memory.getHeapMemoryUsage();

        String info = String.format("\ninit: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                headMemory.getInit() / MB + "MB",
                headMemory.getMax() / MB + "MB",
                headMemory.getUsed() / MB + "MB",
                headMemory.getCommitted() / MB + "MB",
                headMemory.getUsed() * 100 / headMemory.getCommitted() + "%"
                );
        System.out.print(info);

        MemoryUsage noHeadMemory = memory.getNonHeapMemoryUsage();

        info = String.format("\ninit: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                headMemory.getInit() / MB + "MB",
                headMemory.getMax() / MB + "MB",
                headMemory.getUsed() / MB + "MB",
                headMemory.getCommitted() / MB + "MB",
                headMemory.getUsed() * 100 / headMemory.getCommitted() + "%"
        );
        System.out.println(info);
    }


    public static void printGCInfo(){
        List<GarbageCollectorMXBean> garbages = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbage : garbages) {
            String info = String.format("name: %s\t count: %s\t took: %s\t poolName: %s\n",
                    garbage.getName(),
                    garbage.getCollectionCount(),
                    garbage.getCollectionTime(),
                    Arrays.deepToString(garbage.getMemoryPoolNames())
                    );
            System.out.println(info);
        }
    }
}
