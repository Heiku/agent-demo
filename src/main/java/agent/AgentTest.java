package agent;

/**
 * @Author: Heiku
 * @Date: 2019/10/17
 */
public class AgentTest {

    private void fun1() {
        System.out.println("this is fun 1.");
    }

    private void fun2() {
        System.out.println("this is fun 2.");
    }

    public static void main(String[] args) {
        AgentTest agentTest = new AgentTest();
        agentTest.fun1();
        agentTest.fun2();
    }
}
