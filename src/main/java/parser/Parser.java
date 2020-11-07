package parser;

import cheatsheet.CheatSheetList;

import command.Command;
import command.AddCommand;
import command.ClearCommand;
import command.DeleteCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FavouriteCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.SettingsCommand;
import command.ViewCommand;
import editor.Editor;
import exception.CommandException;
import settings.Settings;
import storage.DataFileDestroyer;
import ui.Ui;
import ui.Printer;

import java.util.LinkedHashMap;

public class Parser {
    private CheatSheetList cheatSheetList;
    private DataFileDestroyer fileDestroyer;
    private Editor editor;
    private Printer printer;
    private Ui ui;
    private Settings settings;

    private static final String FLAG_REGEX = "(?=(/[a-z]))";

    public Parser(Printer printer, Settings settings) {
        this.printer = printer;
        this.settings = settings;
    }

    public Parser(CheatSheetList cheatSheetList, Editor editor,
                  DataFileDestroyer fileDestroyer, Printer printer, Ui ui, Settings settings) {
        this.cheatSheetList = cheatSheetList;
        this.editor = editor;
        this.fileDestroyer = fileDestroyer;
        this.printer = printer;
        this.ui = ui;
        this.settings = settings;
    }

    public Command parse(String userInput) throws CommandException {
        Command commandToBeExecuted = parseCommandType(userInput);
        LinkedHashMap<CommandFlag, String> flagsToDescriptions = parseFlagDescriptions(commandToBeExecuted, userInput);

        commandToBeExecuted.setFlagstodescriptionsMap(flagsToDescriptions);
        setMissingDescriptions(commandToBeExecuted);
        return commandToBeExecuted;
    }

    private Command parseCommandType(String userInput) throws CommandException {
        String parsedInput = userInput.split(" ")[0];
        switch (parsedInput) {
        case AddCommand.invoker:
            return new AddCommand(printer, cheatSheetList, editor);
        case ClearCommand.invoker:
            return new ClearCommand(printer, cheatSheetList, fileDestroyer);
        case DeleteCommand.invoker:
            return new DeleteCommand(printer, cheatSheetList, fileDestroyer);
        case EditCommand.invoker:
            return new EditCommand(printer, cheatSheetList, editor);
        case ExitCommand.invoker:
            return new ExitCommand(printer);
        case FavouriteCommand.invoker:
            return new FavouriteCommand(printer, cheatSheetList);
        case FindCommand.invoker:
            return new FindCommand(printer, cheatSheetList);
        case HelpCommand.invoker:
            return new HelpCommand(printer);
        case ListCommand.invoker:
            return new ListCommand(printer, cheatSheetList);
        case SettingsCommand.invoker:
            return new SettingsCommand(printer, settings);
        case ViewCommand.invoker:
            return new ViewCommand(printer, cheatSheetList);
        default:
            throw new CommandException("Please enter a valid command");
        }
    }

    private LinkedHashMap<CommandFlag, String> parseFlagDescriptions(Command command, String userInput)
            throws CommandException {
        LinkedHashMap<CommandFlag, String> flagsToDescriptions = new LinkedHashMap<>();

        try {
            String[] details = userInput.split(FLAG_REGEX);

            // For each flag /<flag> {description}
            for (int i = 1; i < details.length; i++) {
                int descriptionStartIdx = getDescriptionStartIdx(details[i]);

                // Get the <flag> and {description}
                String flag;
                String flagDescription;
                if (descriptionStartIdx == -1) {
                    flag = details[i];
                    flagDescription = "";
                } else {
                    flag = details[i].substring(0, descriptionStartIdx);
                    flagDescription = details[i].substring(descriptionStartIdx).trim();
                }

                // Validate that <flag> matches required Command flags
                boolean isValidFlag = false;
                for (CommandFlag c : command.getFlagstodescriptionsMap().keySet()) {
                    if (c.getFlag().equals(flag)) {
                        flagsToDescriptions.put(c, flagDescription.trim());
                        isValidFlag = true;
                        break;
                    }
                }
                if (!isValidFlag) {
                    throw new CommandException("Please input the correct flags");
                }
            }
        } catch (IndexOutOfBoundsException i) {
            throw new CommandException("Flag indexing error");
        }

        return flagsToDescriptions;
    }

    private void setMissingDescriptions(Command commandToBeExecuted) {
        LinkedHashMap<CommandFlag, String> map = commandToBeExecuted.getFlagstodescriptionsMap();
        if (!commandToBeExecuted.hasAlternativeArgument() && settings.getDisplayingHelpMessages()) {
            printer.printCommandHelpMessage(commandToBeExecuted.getClass());
        } else if (settings.getDisplayingHelpMessages()
                && (commandToBeExecuted.getClass().equals(ListCommand.class)
                || (commandToBeExecuted.getClass().equals(ClearCommand.class)))) {
            printer.printCommandHelpMessage(commandToBeExecuted.getClass());
        }

        while (!commandToBeExecuted.hasAlternativeArgument()) {
            printer.printAlternativeArgumentPrompt(commandToBeExecuted);

            for (CommandFlag key : map.keySet()) {
                /* If the key is an alternative argument of the command
                  but the command already has another alternative argument filled
                   skip this key*/
                if (commandToBeExecuted.getAlternativeArguments().contains(key)
                    && commandToBeExecuted.hasAlternativeArgument()) {
                    continue;
                } else if ((map.get(key) == null || map.get(key).isBlank()) && key != CommandFlag.DELETE) {

                    printer.printMissingArgument(key);
                    String newArgVal = ui.getUserInput();
                    if (newArgVal.isBlank()) {
                        newArgVal = null;
                    }
                    map.replace(key, newArgVal);
                }
            }
        }
    }

    private int getDescriptionStartIdx(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isWhitespace(input.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
