package main;

import gui.MainUI;

public class Jdig {

    public static void main(String[] args) {
        MainUI ui = new MainUI(new Map(40, 40));
        ui.run();
    }
}
