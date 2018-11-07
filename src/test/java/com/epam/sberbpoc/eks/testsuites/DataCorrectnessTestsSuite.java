package com.epam.sberbpoc.eks.testsuites;

import com.epam.sberbpoc.eks.tests.EKSDM_BALANCE_DataCorrectnessTest;
import com.epam.sberbpoc.eks.tests.Z_AC_FIN_DataCorrectnessTest;
import com.epam.sberbpoc.eks.tests.Z_RECORDS_DataCorrectnessTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        Z_AC_FIN_DataCorrectnessTest.class,
        Z_RECORDS_DataCorrectnessTest.class,
        EKSDM_BALANCE_DataCorrectnessTest.class
})
public class DataCorrectnessTestsSuite {
}

