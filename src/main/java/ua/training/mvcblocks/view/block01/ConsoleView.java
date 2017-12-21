package ua.training.mvcblocks.view.block01;

import java.io.IOException;
import java.io.OutputStream;

import static ua.training.mvcblocks.utils.block01.ValidationUtils.throwIfNull;

public class ConsoleView implements View<String>{

    private OutputStream out;

    public ConsoleView(OutputStream out) {
        throwIfNull(out, "Output stream");
        this.out = out;
    }

    @Override
    public void renderView(String data) {
        throwIfNull(data,"String data");

        try {
            out.write(data.getBytes());
            out.flush();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }


}
