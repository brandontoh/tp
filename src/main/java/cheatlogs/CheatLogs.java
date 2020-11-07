package cheatlogs;

import ui.UserSession;



public class CheatLogs {

    private static final String FIRST = "FIRST";
    private static final String EMPTY = "";

    public static void main(String[] args) {
        assert true;

        String argsCommand = args.length > 0
                ? args[0].toUpperCase()
                : EMPTY;
        UserSession session = new UserSession();

        if (argsCommand.equals(FIRST)) {
            session.isFirstRun = true;
        }

        session.runProgramSequence();
        session.exit();
    }
}
