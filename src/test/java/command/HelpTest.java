package command;

import exception.CommandException;
import org.junit.jupiter.api.Test;
import ui.Printer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpTest {

    @Test
    void execute() throws InterruptedException, IOException, CommandException {
        Command commandToBeExecuted = new HelpCommand(new Printer());
        commandToBeExecuted.execute();
    }
}
