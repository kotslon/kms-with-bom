package ru.sbt.sandbox.kmb.impl;

import ru.sbt.sandbox.kmb.api.KmbService;
import ru.sbt.sandbox.kmx.api.KmxService;

import static java.util.Objects.requireNonNull;

public class KmbServiceImpl implements KmbService {

    private final KmxService kmxService;

    public KmbServiceImpl(KmxService kmxService) {
        this.kmxService = requireNonNull(kmxService);
    }

    @Override
    public void doKmb() {
        kmxService.doKmx();
    }
}
