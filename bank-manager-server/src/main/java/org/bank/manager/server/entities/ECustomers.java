package org.bank.manager.server.entities;

import java.sql.Connection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.TableEntity;
import com.ontimize.locator.EntityReferenceLocator;

public class ECustomers extends TableEntity {

    public ECustomers(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port) throws Exception {
        super(locator, dbConnectionManager, port);
    }
    
    @Override
    public EntityResult delete(Hashtable keysValues, int sessionId, Connection con) throws Exception {

        TableEntity EAccounts = (TableEntity) this.getEntityReference("ECustomerAccounts");
        Hashtable queryFilter = new Hashtable();
        queryFilter.put("CUSTOMERID", keysValues.get("CUSTOMERID"));
        EntityResult accountsResult = EAccounts.query(queryFilter, new Vector(), sessionId, con);

        if (accountsResult.calculateRecordNumber() > 0) {
            EntityResult result = this.createEntityResultForSessionId(sessionId);
            result.setCode(EntityResult.OPERATION_WRONG);
            result.setMessage("M_ACOUNTS_EXIST");
            return result;
        } else {
            return super.delete(keysValues, sessionId, con);
        }
    }
    
    @Override
    public EntityResult insert(Hashtable attributesValues, int sessionId, Connection con) throws Exception {
        EntityResult insertResult =  super.insert(attributesValues, sessionId, con);
        if(insertResult.getCode() == EntityResult.OPERATION_SUCCESSFUL)
        {
            TableEntity EArchive = (TableEntity) this.getEntityReference("EArchive");
            // TABLE_NAME;RECORD_IDENTIFIER;INSERT_DATE;DELETE_DATE
            Hashtable archiveValues = new Hashtable();
            archiveValues.put("TABLE_NAME", this.getTable());
            archiveValues.put("RECORD_IDENTIFIER", insertResult.get(this.keys.get(0)));
            archiveValues.put("INSERT_DATE", new Date());

            EArchive.insert(archiveValues, sessionId, con);
        }
        return insertResult;
    }
    
    @Override
    public EntityResult query(Hashtable keysValues, Vector attributes, int sessionId, Connection con) throws Exception {

        System.out.println("ECustomers: Executing query.");

        return super.query(keysValues, attributes, sessionId, con);
    }
}