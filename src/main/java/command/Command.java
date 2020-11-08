package command;

import cheatsheet.CheatSheetList;
import exception.CommandException;

import parser.CommandFlag;
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

    protected ArrayList<CommandFlag> alternativeArguments; // At least one of these must be filled
    protected ArrayList<CommandFlag> necessaryArguments; // All of these must be filled

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
        this.necessaryArguments = new ArrayList<>();
        this.flagsToDescriptions = new LinkedHashMap<>();
        isExitCommand = false;
    }

    public LinkedHashMap<CommandFlag, String> getFlagsToDescriptionsMap() {
        return flagsToDescriptions;
    }

    public void setFlagsToDescriptionsMap(HashMap<CommandFlag, String> flagsToDescriptions) {
        this.flagsToDescriptions.putAll(flagsToDescriptions);
    }

    public ArrayList<CommandFlag> getAlternativeArguments() {
        return alternativeArguments;
    }

    public ArrayList<CommandFlag> getNecessaryArguments() {
        return necessaryArguments;
    }

    public boolean hasRequiredArguments() {
        return hasNecessaryArguments() && hasAlternativeArguments();
    }

    public boolean hasNecessaryArguments() {
        for (CommandFlag arg : necessaryArguments) {
            if (flagsToDescriptions.get(arg) == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the user has at least one of the alternative arguments.
     *
     * @return A boolean whether the user inputted at least one of the alternative arguments
     */
    public boolean hasAlternativeArguments() {
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
