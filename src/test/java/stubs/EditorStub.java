package stubs;

import cheatsheet.CheatSheet;
import editor.Editor;
import exception.EditorException;

import javax.swing.JTextArea;
import javax.swing.JLabel;

public class EditorStub extends Editor {
    private String content;

    public EditorStub() {

    }
    public void setContent(CheatSheet cheatSheet) {
        content = cheatSheet.getDetails();
    }

    public String getContent() {
        return content;
    }

    public void writeContent(String text) {
        content = text;
    }

    public void setEditingContentAttributes(String name, String subject) {
    }

    public void open() {
    }

    public void waitForClose() {
    }
}
