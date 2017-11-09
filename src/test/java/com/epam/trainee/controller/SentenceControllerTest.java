package com.epam.trainee.controller;

import com.epam.trainee.model.Sentence;
import com.epam.trainee.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class SentenceControllerTest {


    private SentenceController controller;
    private Sentence sentence;
    private View<String> view;

    @Before
    public void setUp() {
        sentence = new Sentence("test1 test2", " ");
        view = createMock(View.class);
        controller = new SentenceController(sentence, view);
    }

    @Test
    public void testProceedInput() {


        view.renderView(anyObject());
        expectLastCall().once();

        view.renderView(anyObject());
        expectLastCall().once();

        replay(view);

        controller.proceedInput("test1");
        controller.proceedInput("test2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructController() {
        controller = new SentenceController(null, null);
    }

    @Test
    public void testProceedNull() {
        view.renderView(anyObject());
        expectLastCall().once();
        replay(view);

        controller.proceedInput(null);
    }
}