package javassist;

/**
 * @Author: Heiku
 * @Date: 2019/10/17
 */
public class HelloTest {
    public static void main(String[] args) throws Exception{
        // Hello 不能在 toClass() 之前调用，因为 toClass() 会调用当前线程的上下文类加载器，加载对应的 Hello 类,
        // 因为前面已经加载了，当后面加载的时候，classLoader 因为不能加载两个不同版本的 Hello 类
        //Hello hello = new Hello();

        ClassPool classPool = ClassPool.getDefault();
        CtClass cc = classPool.get("javassist.Hello");
        CtMethod method = cc.getDeclaredMethod("say");

        method.insertBefore("{ System.out.println(\"Hello.say():\"); }");

        Class c = cc.toClass();
        Hello hello = (Hello)c.newInstance();
        hello.say();
    }
}
