package hu.szoftverfolyamat.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractMapper<T, R> implements Function<T, R> {

    public List<R> apply(final List<T> entities) {
        final List<R> result = new ArrayList<R>();

        for (final T entity : entities) {
            result.add(apply(entity));
        }

        return result;
    }
}
