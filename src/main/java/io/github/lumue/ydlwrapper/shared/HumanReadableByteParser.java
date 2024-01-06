package io.github.lumue.ydlwrapper.shared;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by lm on 06.07.16.
 */
public class HumanReadableByteParser implements Function<String, Long> {


    public static class ValueConverter implements Function<BigDecimal, Long> {
        private final BigDecimal faktor;

        private ValueConverter(BigDecimal faktor) {
            this.faktor = Objects.requireNonNull(faktor, "faktor must not be null");
        }

        public Long apply(BigDecimal input) {
            Objects.requireNonNull(input, "input must not be null");
            return input.multiply(faktor).longValue();
        }
    }

    private final static ValueConverter KIB_CONVERTER = new ValueConverter(new BigDecimal(1024));

    private final static ValueConverter MIB_CONVERTER = new ValueConverter(new BigDecimal(1024 * 1024));

    private final static ValueConverter GIB_CONVERTER = new ValueConverter(new BigDecimal(1024 * 1024 * 1024));


    @Override
    public Long apply(String input) {
        Objects.requireNonNull(input, "input must not be null");
        if (input.endsWith("MiB"))
            return MIB_CONVERTER.apply(new BigDecimal(input.substring(0, input.length() - 3)));
        if (input.endsWith("KiB"))
            return KIB_CONVERTER.apply(new BigDecimal(input.substring(0, input.length() - 3)));
        if (input.endsWith("GiB"))
            return KIB_CONVERTER.apply(new BigDecimal(input.substring(0, input.length() - 3)));
        throw new IllegalArgumentException("failure parsing " + input);
    }

}
