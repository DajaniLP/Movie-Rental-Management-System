package app;

import system.LoginSystem;
import system.MenuSystem;

public class Main {

    public static void main(String[] args) {

        LoginSystem login = new LoginSystem();
        MenuSystem menu = new MenuSystem();

        login.run(menu);
    }
}