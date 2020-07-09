package org.bank.manager.client.modules.management;

import java.util.Hashtable;

import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.field.FormComponent;
import com.ontimize.gui.manager.IFormManager;
import com.ontimize.gui.table.CellEditor;
import com.ontimize.gui.table.DateCellEditor;
import com.ontimize.gui.table.DateCellRenderer;
import com.ontimize.gui.table.Table;

public class IMCustomers extends BasicInteractionManager {
    @Override
    public void registerInteractionManager(Form form, IFormManager formManager) {
        super.registerInteractionManager(form, formManager);

        FormComponent comp = this.managedForm.getElementReference("ECustomerAccountBalance");

        DateCellEditor.SHOW_CALENDAR = true;

        if ((comp != null) && (comp instanceof Table)) {
            Hashtable hStart = new Hashtable();
            Hashtable hEnd = new Hashtable();
            hStart.put(CellEditor.COLUMN_PARAMETER, "STARTDATE");
            hEnd.put(CellEditor.COLUMN_PARAMETER, "ENDDATE");
            ((Table) comp).setColumnEditor("STARTDATE", new DateCellEditor(hStart));
            ((Table) comp).setColumnEditor("ENDDATE", new DateCellEditor(hEnd));

            DateCellRenderer rendererWithoutHour = new DateCellRenderer(false);
            ((Table) comp).setRendererForColumn("ENDDATE", rendererWithoutHour);
            ((Table) comp).setRendererForColumn("STARTDATE", rendererWithoutHour);
        }
    }
}