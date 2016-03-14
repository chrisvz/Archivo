package database;

/**
 * Created by Chris on 3/14/2016.
 */
public class DbSchema {

    public static final class GoalBase {
        public static final String NAME = "goals";

        public static final class COLS {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DESCRIPTION = "description";
            public static final String SUCCESSFUL = "successful";
        }
    }
}
