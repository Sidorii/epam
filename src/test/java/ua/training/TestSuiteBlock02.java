package ua.training;

import ua.training.mvcblocks.controller.block02.GameControllerTest;
import ua.training.mvcblocks.model.block02.BoundsTest;
import ua.training.mvcblocks.model.block02.GameLogicTest;
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
