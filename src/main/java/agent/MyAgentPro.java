package agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @Author: Heiku
 * @Date: 2019/10/17
 */
public class MyAgentPro {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("This is an perform monitor agent.");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader) {

                return builder.method(ElementMatchers.<MethodDescription>any())      // 拦截认任意方法
                        .intercept(MethodDelegation.to(TimeInterceptor.class));     // 添加拦截器
            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, DynamicType dynamicType) {
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule) {

            }
        };

        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("agent"))
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }
}
