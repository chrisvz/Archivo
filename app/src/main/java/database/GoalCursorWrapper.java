package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import model.Goal;

import static database.DbSchema.*;

/**
 * Created by Chris on 3/15/2016.
 */
public class GoalCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public GoalCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Goal getGoal() {
        String uuid = getString(getColumnIndex(GoalBase.COLS.UUID));
        String title = getString(getColumnIndex(GoalBase.COLS.TITLE));
        String description = getString(getColumnIndex(GoalBase.COLS.DESCRIPTION));
        int successful = getInt(getColumnIndex(GoalBase.COLS.SUCCESSFUL));

        Goal g = new Goal(UUID.fromString(uuid));
        g.setTitle(title);
        g.setDescription(description);
        g.setSuccessful(successful != 0);
        return g;
    }
}
