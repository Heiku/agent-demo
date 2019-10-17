## Java Agent

Java Agent 类似于一个拦截器，方便我们实现对程序的监控，增强等功能。

项目测试： mvn clean package -> 生成 agent.jar

对要拦截的 main() 的启动类上加上参数 -javaagent:target/my-agent.jar，开启拦截


### Instrumentation

`Instrumentation` 是实现 `agent` 组件，通过 `instrumentation`，可以构建一个代理，方便监控运行的程序

```
public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("This is an perform monitor agent.");

        // 添加 Transformer
        ClassFileTransformer transformer = new PerformMonitorTransformer();
        inst.addTransformer(transformer);
}
```

### ClassFileTransformer

定义：用于转变类文件，通常发生在 `JVM` 定义 `Class` 之前, 同时 `transform()` 可能会对类文件进行修改并产生一个新的类文件 