package bader.testbla;
import java.util.Random;

public class FooFactory {
    public static FooObject createFoo() {
        Random random = new Random();
        int semiRandomInt = random.nextInt(500);
        FooObject result = new FooObject();
        result.name = "I am semi-random Foo #" + semiRandomInt;
        return result;
    }
}