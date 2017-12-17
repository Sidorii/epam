package com.epam.trainee;

import com.epam.trainee.mvcblocks.controller.block02.GameControllerTest;
import com.epam.trainee.mvcblocks.model.block02.BoundsTest;
import com.epam.trainee.mvcblocks.model.block02.GameLogicTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameControllerTest.class,
        BoundsTest.class,
        GameLogicTest.class
})
public class TestSuiteBlock02 {
}
