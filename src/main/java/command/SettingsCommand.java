package command;

import exception.CommandException;
import parser.CommandFlag;
import settings.Settings;
import ui.Printer;


/**
 * Command to change the behavior of the application.
 * Currently only supports changing default color scheme.
 */
public class SettingsCommand extends Command {
    private Settings settings;
    public static final String invoker = "/set";

    /**
     * Constructor for the SettingsCommand.
     * Required argument: COLORSCHEME.
     *
     * @param printer The printer object to handle user interaction
     */
    public SettingsCommand(Printer printer, Settings settings) {
        super(printer);
        this.settings = settings;
        flagsToDescriptions.put(CommandFlag.COLORSCHEME, null);
        flagsToDescriptions.put(CommandFlag.HELPMESSAGE, null);
        alternativeArguments.add(CommandFlag.COLORSCHEME);
        alternativeArguments.add(CommandFlag.HELPMESSAGE);
    }

    /**
     * Changes the color scheme by calling setColor method.
     *
     * @throws CommandException if the input cannot be parsed as integer
     */
    @Override
    public void execute() throws CommandException {
        if (flagsToDescriptions.get(CommandFlag.COLORSCHEME) != null) {
            try {
                int option = Integer.parseInt(flagsToDescriptions.get(CommandFlag.COLORSCHEME));
                printer.setColor(option);
            } catch (NumberFormatException e) {
                throw new CommandException("Please enter a valid option");
            }
        }
        if (flagsToDescriptions.get(CommandFlag.HELPMESSAGE) != null) {
            String option = flagsToDescriptions.get(CommandFlag.HELPMESSAGE);
            if (option.equals("remove")) {
                settings.setDisplayingHelpMessages(false);
            } else if (option.equals("include")) {
                settings.setDisplayingHelpMessages(true);
            } else {
                throw new CommandException("Please enter a valid option (\"remove\" or \"include\")");
            }
        }
    }
}
