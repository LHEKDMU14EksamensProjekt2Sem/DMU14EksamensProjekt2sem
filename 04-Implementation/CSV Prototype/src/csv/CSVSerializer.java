package csv;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface CSVSerializer<T> extends Function<T, List<String>> {
}
