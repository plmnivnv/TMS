package com.company.oop.tms.core;

import com.company.oop.tms.core.contracts.SystemRepository;
import org.junit.jupiter.api.BeforeEach;


public class SystemRepositoryImplTests {

    SystemRepository systemRepository;


    @BeforeEach
    public void setup() {
        systemRepository = new SystemRepositoryImpl();
    }




}
