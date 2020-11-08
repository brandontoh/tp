//package parser;
//
//import command.AddCommand;
//import command.Command;
//import exception.CommandException;
//import org.junit.jupiter.api.Test;
//import settings.Settings;
//import ui.Printer;
//
//import java.sql.SQLOutput;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//class ParserTest {
//
//    @Test
//    void parser_completeInput_success() {
//        final String userInput = "/add /n name /s subject";
//
//        ArrayList<CommandFlag> argEnumList = new ArrayList<>();
//        argEnumList.add(CommandFlag.NAME);
//        argEnumList.add(CommandFlag.SUBJECT);
//
//        ArrayList<String> detailsList = new ArrayList<>();
//        detailsList.add("name");
//        detailsList.add("subject");
//
//        HashMap<CommandFlag, String> flagsToDescriptions = new HashMap<>();
//        for (int i = 1; i <= 2; i++) {
//            flagsToDescriptions.put(argEnumList.get(i - 1), detailsList.get(i - 1));
//        }
//
//        try {
//            Printer printer = new Printer();
//            Parser parser = new Parser(printer, new Settings(printer));
//            Command command = parser.parse(userInput);
//            assertEquals(AddCommand.class, command.getClass());
//            assertEquals(flagsToDescriptions, command.getFlagstodescriptionsMap());
//        } catch (CommandException e) {
//            fail();
//        }
//    }
//
//    @Test
//    void parser_incompleteInput_exceptionThrown() {
//        String userInput = "dummy command";
//        try {
//            Printer printer = new Printer();
//            Parser parser = new Parser(printer, new Settings(printer));
//            parser.parse(userInput);
//            fail();
//        } catch (CommandException e) {
//            assertEquals("Please enter a valid command", e.getMessage());
//        }
//    }
//}
