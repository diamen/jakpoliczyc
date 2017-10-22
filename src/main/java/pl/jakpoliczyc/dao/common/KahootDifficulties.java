package pl.jakpoliczyc.dao.common;

public enum KahootDifficulties {
    VERY_EASY(0),
    EASY(1),
    MIDDLE(2),
    DIFFICULT(3),
    VERY_DIFFICULT(4);

    private int dbCounterpart;

    KahootDifficulties(final int dbCounterpart) {
        this.dbCounterpart = dbCounterpart;
    }

    public int getDbCounterpart() {
        return dbCounterpart;
    }
}
