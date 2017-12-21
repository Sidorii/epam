package ua.training;

import ua.training.mvcblocks.controller.block01.SentenceControllerTest;
import ua.training.mvcblocks.model.block01.SentenceTest;
import ua.training.mvcblocks.view.block01.ConsoleViewTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SentenceControllerTest.class, SentenceTest.class, ConsoleViewTest.class
})
public class TestSuiteBlock01 {
}
