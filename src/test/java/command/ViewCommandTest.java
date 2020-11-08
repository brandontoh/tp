package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import parser.Parser;
import settings.Settings;
import ui.Printer;

import javax.swing.text.View;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ViewCommandTest {

    @Test
    void execute_validInput_success() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        final String userInput = "/view /i 2";
        //cheatSheetList.clear();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            Parser parser = new Parser(new Printer(), new Settings(new Printer()));
            Command viewCommand = parser.parse(userInput);
            viewCommand.execute();
        } catch (CommandException e) {
            fail();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void execute_noArgument_exceptionThrown() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        final String userInput = "/view something";
        //cheatSheetList.clear();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            Parser parser = new Parser(new Printer(), new Settings(new Printer()));
            Command viewCommand = parser.parse(userInput);
            viewCommand.execute();
            //fail();
        } catch (CommandException | InterruptedException | IOException e) {
            assertEquals("Please enter a name or an index", e.getMessage());
        }
    }
}