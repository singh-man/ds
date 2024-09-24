package dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Decorator {

    @Test
    public void test1() {
        Function<String, String> decorate_1 = s -> s + " 1";
        Function<String, String> decorate_2 = s -> s + " 2";
        Function<String, String> decorate_3 = s -> s + " 3";

        Optional<Function<String, String>> reduce = Stream.of(decorate_1, decorate_2, decorate_3)
                .reduce((f1, f2) -> f1.andThen(f2));

        Assertions.assertEquals("me 1 2 3", reduce.get().apply("me"));
    }

    @Test
    public void test2() {
        Function<String, String> decorate_1 = s -> s + " 1";
        Function<String, String> decorate_2 = s -> s + " 2";
        Function<String, String> decorate_3 = s -> s + " 3";

        Function<String, String> decorated = decorate_1.andThen(decorate_2).andThen(decorate_3);

        Assertions.assertEquals("me 1 2 3", decorated.apply("me"));
    }

}
