package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import storage.DataFileDestroyer;
import ui.Printer;
import ui.Ui;

/**
 * Command to delete a cheat sheet from the CheatSheetList.
 */
public class DeleteCommand extends FinderCommand {
    protected DataFileDestroyer fileDestroyer;
    public static final String invoker = "/delete";

    /**
     * Constructor for the DeleteCommand.
     * Required argument: either NAME or SUBJECT.
     *
     * @param printer        The printer object to handle user interaction
     * @param cheatSheetList The current list of cheat sheets
     * @param fileDestroyer  The DataFileDestroyer object to remove the cheat sheet from the /data folder
     */
    public DeleteCommand(Printer printer, CheatSheetList cheatSheetList, DataFileDestroyer fileDestroyer) {
        super(printer, cheatSheetList);
        this.fileDestroyer = fileDestroyer;

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.INDEX);
    }

    /**
     * Deletes the cheat sheet from the list and delete the corresponding file from the /data folder.
     */
    @Override
    public void execute() throws CommandException {
        try {
            CheatSheet cheatSheetToDelete = getCheatSheetFromNameOrIndex();
            printer.printDeleteConfirmation(cheatSheetToDelete);
            if (isDeleteConfirmed()) {
                fileDestroyer.executeFunction(cheatSheetToDelete.getName());
                cheatSheetList.remove(cheatSheetToDelete.getName());
                printer.printDeleteCheatSheetMessage(cheatSheetToDelete, cheatSheetList);
            }
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException("Please enter a valid name or/and index");
        }
    }

    private boolean isDeleteConfirmed() {
        String userAnswer = new Ui().getUserInput();
        userAnswer = userAnswer.toLowerCase();
        return userAnswer.equals("yes") || userAnswer.equals("y");
    }

}
