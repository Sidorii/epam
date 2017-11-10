package com.epam.trainee;

import com.epam.trainee.controller.block01.SentenceControllerTest;
import com.epam.trainee.model.block01.SentenceTest;
import com.epam.trainee.view.block01.ConsoleViewTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SentenceControllerTest.class, SentenceTest.class, ConsoleViewTest.class
})
public class TestSuiteBlock01 {
}
