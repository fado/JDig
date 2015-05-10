package main;

import data.Level;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public enum ApplicationFactory {
    INSTANCE;

    private Level level;

    public void initialiseNewLevel() { this.level = new Level(); }

    public Level getLevel() { return this.level; }

}
