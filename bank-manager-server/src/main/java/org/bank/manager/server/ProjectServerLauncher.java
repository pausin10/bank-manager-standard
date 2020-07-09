package org.bank.manager.server;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.SQLStatementBuilder;
import com.ontimize.db.SQLStatementBuilder.ExtendedSQLConditionValuesProcessor;
import com.ontimize.gui.ServerLauncher;

public class ProjectServerLauncher {
    public static void main(String[] args) {
        DatabaseConnectionManager.offset = 5;
        DatabaseConnectionManager.offsetString = "quickstart";
        ExtendedSQLConditionValuesProcessor exprProcessor = new ExtendedSQLConditionValuesProcessor();
        // SQLStatementBuilder.setSQLConditionValuesProcessor(exprProcessor);
        SQLStatementBuilder.getSQLStatementHandler(SQLStatementBuilder.HSQLDB_HANDLER).setSQLConditionValuesProcessor(exprProcessor);
        try {
            ServerLauncher.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}