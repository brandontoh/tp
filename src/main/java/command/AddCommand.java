package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import exception.EditorException;
import parser.CommandFlag;
import ui.Printer;

/**
 * Command to add user-defined cheat sheet to CheatSheetList.
 */
public class AddCommand extends Command {
    private final Editor editor;
    private static final String NO_SPECIAL_CHAR = "^[^`~!@#$%^&*()_+={}\\[\\]|\\\\:;“’<,>.?]*$";
    public static final String invoker = "/add";

    /**
     * Constructor for the AddCommand.
     * Required argument: NAME.
     * Optional argument: SUBJECT.
     *
     * @param printer        The printer object to handle user interaction
     * @param cheatSheetList The current list of cheat sheets
     * @param editor         The editor object to open the text editor
     */
    public AddCommand(Printer printer, CheatSheetList cheatSheetList, Editor editor) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
        this.editor = editor;

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.SUBJECT, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.SUBJECT);
    }

    /**
     * Adds the cheat sheet to the list.
     * Name cannot be empty, must be unique, and can only contain alphanumeric characters.
     * If the subject is not specified, it will be put under "Unsorted".
     */
    @Override
    public void execute() throws CommandException {
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

        callContentEditor(name,subject);

        try {
            String description = editor.getContent();
            CheatSheet cheatSheet = new CheatSheet(name, subject, description);
            cheatSheetList.add(cheatSheet);
            printer.printAddNewCheatSheetMessage(cheatSheet, cheatSheetList);
        } catch (EditorException | NullPointerException e) {
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Opens the GUI-based text editor.
     */
    private void callContentEditor(String name, String subject) {
        editor.setEditingContentAttributes(name, subject);
        editor.open();
        editor.waitForClose();
    }

    /**
     * Removes spaces from the SUBJECT field and converts it to PascalCase without spaces between words.
     *
     * @param input The current subject to be converted
     */
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
