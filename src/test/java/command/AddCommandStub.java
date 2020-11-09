package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;

public class AddCommandStub extends Command {
    private final Editor editor;
    private static final String NO_SPECIAL_CHAR = "^[^`~!@#$%^&*()_+={}\\[\\]|\\\\:;“’<,>.?]*$";
    public static final String invoker = "/add";

    public AddCommandStub(Printer printer, CheatSheetList cheatSheetList, Editor editor) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
        this.editor = editor;

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.SUBJECT, null);
        alternativeFlags.add(CommandFlag.NAME);
        alternativeFlags.add(CommandFlag.SUBJECT);
    }

    public void populateFlagsToDescription(String name, String subject) {
        flagsToDescriptions.put(CommandFlag.NAME, name);
        flagsToDescriptions.put(CommandFlag.SUBJECT, subject);
    }

    public void executeStub(String details) throws CommandException {
        String name = flagsToDescriptions.get(CommandFlag.NAME);

        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new CommandException("Name cannot be blank");
        }

        name = name.trim();
        if (cheatSheetList.exists(name)) {
            throw new CommandException("Name already existed, please enter another name");
        }


        if (!name.matches(NO_SPECIAL_CHAR)) {
            throw new CommandException("Name can only contain alphanumeric characters");
        }

        String subject = flagsToDescriptions.get(CommandFlag.SUBJECT);
        if (subject != null) {
            if (!subject.matches(NO_SPECIAL_CHAR)) {
                throw new CommandException("Subject can only contain alphanumeric characters");
            }
            subject = convertToPascalCaseNoSpace(subject);
        } else {
            subject = "Unsorted";
        }

        CheatSheet cheatSheet = new CheatSheet(name, subject, details);
        cheatSheetList.add(cheatSheet);
        printer.printAddNewCheatSheetMessage(cheatSheet, cheatSheetList);
    }

    @Override
    public void execute() {
    }

    private String convertToPascalCaseNoSpace(String input) {
        if (input.length() == 0) {
            return "Unsorted";
        }
        String[] splitInput = input.split("\\p{IsWhite_Space}+");
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].substring(0, 1).toUpperCase() + splitInput[i].substring(1).toLowerCase();
        }
        return String.join("", splitInput);
    }
}
