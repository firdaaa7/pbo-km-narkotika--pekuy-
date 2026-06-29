package app;

import controller.KnowledgeController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        KnowledgeController controller = new KnowledgeController();
        controller.muatDataAwal();
        ConsoleView view = new ConsoleView(controller);
        view.mulai();
    }
}