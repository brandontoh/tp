package command;

import cheatsheet.CheatSheetList;
import exception.CommandException;

import parser.CommandFlag;
import settings.Settings;
import ui.Printer;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * The base class for all Commands.
 */
public abstract class Command {
    protected Printer printer;
    protected CheatSheetList cheatSheetList;

    protected ArrayList<CommandFlag> alternativeArguments;
    protected LinkedHashMap<CommandFlag, String> flagsToDescriptions;
    public boolean isExitCommand;

    public Command() {
    }

    /**
     * Constructor for Command.
     *
     * @param printer The printer object handles the user interaction
     */
    public Command(Printer printer) {
        this.printer = printer;
        this.alternativeArguments = new ArrayList<>();
        this.flagsToDescriptions = new LinkedHashMap<>();
        isExitCommand = false;
    }

    public LinkedHashMap<CommandFlag, String> getFlagstodescriptionsMap() {
        return flagsToDescriptions;
    }

    public void setFlagstodescriptionsMap(HashMap<CommandFlag, String> flagstodescriptions) {
        this.flagsToDescriptions.putAll(flagstodescriptions);
    }

    public ArrayList<CommandFlag> getAlternativeArguments() {
        return alternativeArguments;
    }

    /**
     * Checks if the command does not have any required argument.
     * Checks if the user entered all the required arguments for the command.
     *
     * @return A boolean whether the user inputted all required arguments
     */
    public boolean hasAlternativeArgument() {
        if (alternativeArguments.size() == 0) {
            return true;
        }

        for (CommandFlag arg : alternativeArguments) {
            if (flagsToDescriptions.get(arg) != null) {
                return true;
            }
        }
        return false;
    }
    
    public abstract void execute() throws CommandException, InterruptedException, IOException;
}
