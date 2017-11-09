package com.epam.trainee.view;

import java.io.IOException;
import java.io.OutputStream;

public class ConsoleView implements View<String>{

    private OutputStream out;

    public ConsoleView(OutputStream out) {
        this.out = out;
    }

    @Override
    public void renderView(String data) {
        try {
            out.write(data.getBytes());
            out.flush();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}
