package org.bank.manager.server;

import java.util.Hashtable;
import com.ontimize.locator.SecureReferenceLocator;

public class ServerLocator extends SecureReferenceLocator {

    public ServerLocator(int port, Hashtable parameters) throws Exception {
        super(port, parameters);
    }
}