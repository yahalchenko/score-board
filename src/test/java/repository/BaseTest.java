package repository;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class BaseTest {
    private static final EasyRandom EASY_RANDOM = new EasyRandom(new EasyRandomParameters()
            .excludeField(FieldPredicates.inClass(File.class)));
    private static final int DEFAULT_RANDOM_LIST_SIZE = 3;

    protected static <T> T random(Class<T> type) {
        return EASY_RANDOM.nextObject(type);
    }

    protected static String randomString() {
        return random(String.class);
    }

    protected static <T> List<T> randomList(Class<T> type) {
        return EASY_RANDOM.objects(type, DEFAULT_RANDOM_LIST_SIZE).collect(Collectors.toList());
    }
}