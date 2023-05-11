package ru.ifmo.statisticsapi.config;

import org.springframework.core.convert.converter.Converter;
import ru.ifmo.statisticsapi.model.AggregationType;

public class EnumConverter implements Converter<String, AggregationType> {
    @Override
    public AggregationType convert(String source) {
        return AggregationType.valueOf(source.toUpperCase());
    }
}
