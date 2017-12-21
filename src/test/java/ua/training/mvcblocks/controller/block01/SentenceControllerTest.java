package ua.training.mvcblocks.controller.block01;

import ua.training.mvcblocks.model.block01.Sentence;
import ua.training.mvcblocks.view.block01.View;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

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

        controller.processInput("test1");
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

        controller.processInput(null);
    }
}