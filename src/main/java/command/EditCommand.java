package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import exception.EditorException;
import parser.CommandFlag;
import ui.Printer;

/**
 * Command to edit an existing cheatsheet in CheatSheetList.
 */
public class EditCommand extends FinderCommand {
    private final Editor editor;

    public static final String invoker = "/edit";

    /**
     * Constructor for EditCommand.
     * Required argument: either NAME or INDEX.
     *
     * @param printer        The printer object handles user interaction
     * @param cheatSheetList The cheatSheetList object holds the current list of cheat sheets
     * @param editor         The editor object opens the text editor
     */
    public EditCommand(Printer printer, CheatSheetList cheatSheetList, Editor editor) {
        super(printer, cheatSheetList);
        this.editor = editor;
        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.INDEX);
    }

    /**
     * Checks whether the EditCommand contains required flag arguments as stated in constructor.
     *
     * @return A boolean on whether the EditCommand contains the required flag arguments
     */
    @Override
    public boolean hasRequiredArguments() {
        return flagsToDescriptions.get(CommandFlag.NAME) != null
                || flagsToDescriptions.get(CommandFlag.INDEX) != null;
    }

    /**
     * Gets a cheatsheet from cheatSheetList according to name or index, whichever the user entered.
     * Opens the cheatsheet via the editor object for user to edit.
     *
     * @throws CommandException Thrown if for index, user enters non-numerical characters or is out of bound.
     */
    @Override
    public void execute() throws CommandException {
        try {
            CheatSheet desiredCheatSheet = getCheatSheetFromNameOrIndex();
            callContentEditor(desiredCheatSheet);
            printer.printViewCheatSheetMessage(desiredCheatSheet);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException("Please enter a valid index");
        }
    }

    /**
     * Opens the editor.
     *
     * @param desiredCheatSheet The cheatsheet specified by the user
     */
    private void callContentEditor(CheatSheet desiredCheatSheet) {
        editor.open();
        editor.setContent(desiredCheatSheet);
        editor.waitForClose();

        try {
            desiredCheatSheet.setDetails(editor.getContent());
        } catch (EditorException e) {
            printer.print(e.getMessage());
        }
    }
}
