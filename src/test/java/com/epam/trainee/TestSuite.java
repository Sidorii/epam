package com.epam.trainee;

import com.epam.trainee.controller.SentenceControllerTest;
import com.epam.trainee.model.SentenceTest;
import com.epam.trainee.view.ConsoleViewTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SentenceControllerTest.class, SentenceTest.class, ConsoleViewTest.class
})
public class TestSuite {
}
