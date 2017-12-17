package com.epam.trainee.mvcblocks.view.block01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;

import static org.easymock.EasyMock.*;

public class ConsoleViewTest {

    private ConsoleView consoleView;
    private OutputStream outputStream;

    @Before
    public void setUp() {
        outputStream = createMock(OutputStream.class);
        consoleView = new ConsoleView(outputStream);
    }

    @Test
    public void testCommonCase() throws IOException {

        outputStream.write(anyObject());
        expectLastCall().once();

        outputStream.flush();
        expectLastCall().once();

        replay(outputStream);
        consoleView.renderView("testData");
    }

    @Test
    public void testRenderThrewException() throws IOException {

        outputStream.write(anyObject());
        expectLastCall().andThrow(new IOException());

        replay(outputStream);
        consoleView.renderView("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() throws IOException {

        replay(outputStream);

        ConsoleView nullableView = new ConsoleView(null);
        nullableView.renderView("test");
    }

    @After
    public void tearDown() {
        verify(outputStream);
    }

}