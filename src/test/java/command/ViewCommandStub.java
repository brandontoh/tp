package command;
/*
import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;

public class ViewCommandStub extends FinderCommand {

    public ViewCommandStub(Printer printer, CheatSheetList cheatSheetList) {
        super(printer, cheatSheetList);

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.INDEX);
    }

    @Override
    public void execute() {
    }

    public void populateFlagsToDescription(String name, String index) {
        flagsToDescriptions.put(CommandFlag.NAME, name);
        flagsToDescriptions.put(CommandFlag.INDEX, index);
    }

    public String executeStub() throws CommandException {
        try {
            CheatSheet desiredCheatSheet = getCheatSheetFromNameOrIndex();
            return desiredCheatSheet.getName() + desiredCheatSheet.getSubject() + desiredCheatSheet.getDetails();
        } catch (IndexOutOfBoundsException | NumberFormatException | CommandException e) {
            throw new CommandException("Please enter a valid name or/and index");
        }
    }
}
*/
