package pl.jakpoliczyc.dao.common;

public enum Difficulty {
    VERY_EASY(0),
    EASY(1),
    MIDDLE(2),
    DIFFICULT(3),
    VERY_DIFFICULT(4);

    private int dbCounterpart;

    Difficulty(final int dbCounterpart) {
        this.dbCounterpart = dbCounterpart;
    }

    public int getDbCounterpart() {
        return dbCounterpart;
    }
}
