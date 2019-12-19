package ru.sbt.sandbox.kma.impl;

import ru.sbt.sandbox.kma.api.KmaService;
import ru.sbt.sandbox.kmx.api.KmxService;

import static java.util.Objects.requireNonNull;

public class KmaServiceImpl implements KmaService {

    private final KmxService kmxService;

    public KmaServiceImpl(KmxService kmxService) {
        this.kmxService = requireNonNull(kmxService);
    }

    @Override
    public void doKma() {
        kmxService.doKmx();
    }
}
