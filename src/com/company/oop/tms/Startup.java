package com.company.oop.tms;

import com.company.oop.tms.core.SystemEngineImpl;

public class Startup {
    public static void main(String[] args) {

        SystemEngineImpl engine = new SystemEngineImpl();
        engine.start();

    }

}
