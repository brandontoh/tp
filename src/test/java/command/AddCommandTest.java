package command;

import cheatsheet.CheatSheetList;
import exception.CommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddCommandTest {

    @Test
    void addOneCheatSheet_allFieldsFilled_success() throws CommandException {
        CheatSheetList.clear();
        String userInput = "/add /n FirstTest /l Java /d Content1";
        Parser parser = new Parser(userInput);
        AddCommand addCommand = new AddCommand(parser);
        addCommand.execute();
        Assertions.assertAll(
            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getCheatSheetName()),
            () -> assertEquals("Java", CheatSheetList.getCheatSheet(1).getCheatSheetProgrammingLanguage()),
            () -> assertEquals("Content1", CheatSheetList.getCheatSheet(1).getCheatSheetDetails())
        );
    }

    @Test
    void addOneCheatSheet_nameAndLanguageFilled_success() throws CommandException {
        CheatSheetList.clear();
        String userInput = "/add /n FirstTest /l Java";
        Parser parser = new Parser(userInput);
        AddCommand addCommand = new AddCommand(parser);
        addCommand.execute();
        Assertions.assertAll(
            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getCheatSheetName()),
            () -> assertEquals("Java", CheatSheetList.getCheatSheet(1).getCheatSheetProgrammingLanguage()),
            () -> assertNull(CheatSheetList.getCheatSheet(1).getCheatSheetDetails())
        );
    }

    @Test
    void addOneCheatSheet_nameAndDescriptionFilled_success() throws CommandException {
        CheatSheetList.clear();
        String userInput = "/add /n FirstTest /d Content1";
        Parser parser = new Parser(userInput);
        AddCommand addCommand = new AddCommand(parser);
        addCommand.execute();
        Assertions.assertAll(
            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getCheatSheetName()),
            () -> assertNull(CheatSheetList.getCheatSheet(1).getCheatSheetProgrammingLanguage()),
            () -> assertEquals("Content1", CheatSheetList.getCheatSheet(1).getCheatSheetDetails())
        );
    }

    @Test
    void addOneCheatSheet_languageAndDescriptionFilled_CommandExceptionThrown() throws CommandException {
        CheatSheetList.clear();
        String userInput = "/add /l Java /d Content1";
        Parser parser = new Parser(userInput);
        AddCommand addCommand = new AddCommand(parser);
        try {
            addCommand.execute();
        } catch (CommandException c) {
            assertEquals("Please enter a name", c.getMessage());
        }
    }

    @Test
    void addOneCheatSheet_nameFilled_CommandExceptionThrown() throws CommandException {
        CheatSheetList.clear();
        String userInput = "/add /n FirstTest";
        Parser parser = new Parser(userInput);
        AddCommand addCommand = new AddCommand(parser);
        addCommand.execute();
        Assertions.assertAll(
            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getCheatSheetName()),
            () -> assertNull(CheatSheetList.getCheatSheet(1).getCheatSheetProgrammingLanguage()),
            () -> assertNull(CheatSheetList.getCheatSheet(1).getCheatSheetDetails())
        );
    }

    @Test
    void addOneCheatSheet_LanguageFilled_CommandExceptionThrown() throws CommandException {
        CheatSheetList.clear();
        String userInput = "/add /l Java";
        Parser parser = new Parser(userInput);
        AddCommand addCommand = new AddCommand(parser);
        try {
            addCommand.execute();
        } catch (CommandException c) {
            assertEquals("Please enter a name", c.getMessage());
        }
    }

    @Test
    void addOneCheatSheet_DescriptionFilled_CommandExceptionThrown() throws CommandException {
        CheatSheetList.clear();
        String userInput = "/add /d Content1";
        Parser parser = new Parser(userInput);
        AddCommand addCommand = new AddCommand(parser);
        try {
            addCommand.execute();
        } catch (CommandException c) {
            assertEquals("Please enter a name", c.getMessage());
        }
    }
}
