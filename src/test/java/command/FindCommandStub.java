package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;
import ui.TablePrinter;

import java.util.ArrayList;

public class FindCommandStub extends FinderCommand {
    private CheatSheetList cheatSheetList;
    public static final String invoker = "/find";

    public FindCommandStub(Printer printer, CheatSheetList cheatSheetList) {
        super(printer, cheatSheetList);
        this.cheatSheetList = cheatSheetList;
        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.SUBJECT, null);
        flagsToDescriptions.put(CommandFlag.SECTIONKEYWORD, null);
        alternativeFlags.add(CommandFlag.NAME);
        alternativeFlags.add(CommandFlag.SUBJECT);
        alternativeFlags.add(CommandFlag.SECTIONKEYWORD);
    }

    @Override
    public void execute() {
    }

    public void populateFlagsToDescription(String name, String subject, String keyword) {
        flagsToDescriptions.put(CommandFlag.NAME, name);
        flagsToDescriptions.put(CommandFlag.SUBJECT, subject);
        flagsToDescriptions.put(CommandFlag.SECTIONKEYWORD, keyword);
    }

    public ArrayList<CheatSheet> executeStub() throws CommandException {
        ArrayList<CheatSheet> matchedContents = new ArrayList<>();

        String name = flagsToDescriptions.get(CommandFlag.NAME);
        String subject = flagsToDescriptions.get(CommandFlag.SUBJECT);
        String keyword = flagsToDescriptions.get(CommandFlag.SECTIONKEYWORD);

        for (CheatSheet cs : cheatSheetList.getList()) {
            if (checkCheatSheetMatchesWithFields(cs, name, subject, keyword)) {
                matchedContents.add(cs);
            }
        }

        if (matchedContents.isEmpty()) {
            throw new CommandException("No matching content found");
        }

        return matchedContents;
    }
}

