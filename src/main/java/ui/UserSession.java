package ui;

import cheatsheet.CheatSheetList;
import command.Command;
import editor.Editor;
import exception.CommandException;
import parser.Parser;
import settings.Settings;
import storage.DataFileReader;
import storage.DataFileWriter;
import storage.DataFileDestroyer;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;

public class UserSession {
    public boolean isFirstRun;
    /*
     * These are objects that will be injected to command subclasses
     * that allow them to execute.
     */
    CheatSheetList cheatSheetList;
    DataFileReader fileReader;
    DataFileWriter fileWriter;
    DataFileDestroyer fileDestroyer;
    Editor editor;
    Parser userCommandParser;
    Printer printer;
    Ui ui;
    Settings settings;

    public UserSession() {
        cheatSheetList = new CheatSheetList();
        editor = new Editor();
        ui = new Ui();
        printer = new Printer();
        settings = new Settings(printer);
        fileReader = new DataFileReader(settings, printer, cheatSheetList);
        fileWriter = new DataFileWriter(settings, printer, cheatSheetList);

        fileDestroyer = new DataFileDestroyer(printer, cheatSheetList);
        userCommandParser = new Parser(cheatSheetList, editor, fileDestroyer, printer, ui, settings);
        isFirstRun = false;
    }

    public void runProgramSequence() {
        AnsiConsole.systemInstall();
        if (isFirstRun) {
            fileReader.extractPreloadedCheatSheets();
        }

        fileReader.executeFunction();
        printer.printWelcomeScreen();
        if (settings.getDisplayingHelpMessages()) {
            printer.printStartHelpMessage();
        }

        // Ask for new user input and executes it until user types the exit command
        do {
            String userInput = ui.getUserInput();
            try {
                Command parsedUserCommand = userCommandParser.parse(userInput);
                parsedUserCommand.execute();

                if (parsedUserCommand.isExitCommand) {
                    return;
                }
            } catch (CommandException | InterruptedException | IOException c) {
                printer.print(c.getMessage());
                continue;
            }
            fileWriter.executeFunction();
        } while (true);
    }

    public void exit() {
        ui.closeScanner();
        editor.dispose();
        printer.printExitLogo();
        AnsiConsole.systemUninstall();
    }
}
