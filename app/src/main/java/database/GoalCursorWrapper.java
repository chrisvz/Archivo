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
        String uuid = getString(getColumnIndex(GoalTable.Cols.UUID));
        String title = getString(getColumnIndex(GoalTable.Cols.TITLE));
        String description = getString(getColumnIndex(GoalTable.Cols.DESCRIPTION));
        int successful = getInt(getColumnIndex(GoalTable.Cols.SUCCESSFUL));

        Goal g = new Goal(UUID.fromString(uuid));
        g.setTitle(title);
        g.setDescription(description);
        g.setSuccessful(successful != 0);
        return g;
    }
}
