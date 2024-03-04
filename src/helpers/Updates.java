package helpers;

import dto.Shipping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.DbQueries;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class Updates {

    public static void updateTransportTable(JTable transportTable) {
        try {

            ArrayList<Shipping> updatedTransportList = new DbQueries().getShipping();

            if (updatedTransportList != null) {
                int attributeCount = Shipping.class.getDeclaredFields().length;
                Object[][] updatedDataTransport = new Object[updatedTransportList.size()][attributeCount];
                int cnt = 0;
                for (Shipping shipping : updatedTransportList) {
                    updatedDataTransport[cnt][0] = shipping.getBdf_referenz();
                    updatedDataTransport[cnt][1] = shipping.getDatum();
                    updatedDataTransport[cnt][2] = shipping.getKn_referenz();
                    updatedDataTransport[cnt][3] = shipping.getAbsender();
                    updatedDataTransport[cnt][4] = shipping.getEmpfaenger();
                    updatedDataTransport[cnt][5] = shipping.getBeladung_s();
                    updatedDataTransport[cnt][6] = shipping.getBeladung_e();
                    updatedDataTransport[cnt][7] = shipping.getEntladen_s();
                    updatedDataTransport[cnt][8] = shipping.getEntladen_e();
                    updatedDataTransport[cnt][9] = shipping.getStellplaetze();
                    updatedDataTransport[cnt][10] = shipping.getAnzahl();
                    updatedDataTransport[cnt][11] = shipping.isLiquid();
                    updatedDataTransport[cnt][12] = shipping.isAdr();
                    updatedDataTransport[cnt][13] = shipping.isRundlauf();
                    updatedDataTransport[cnt][14] = shipping.getBemerkung();
                    cnt++;
                }
                DefaultTableModel model = new DefaultTableModel(updatedDataTransport, getColumnNames());
                transportTable.setModel(model);
                model.fireTableDataChanged();
            }
        } catch (SQLException e) {
            e.getMessage();

        }
    }
    private static Object[] getColumnNames() {
        return new Object[]{"BDF Referenz", "Datum", "K&N Referenz", "Absender", "Empfänger", "Beladung Start", "Ende", "Entladen Start", "Ende", "Stellplätze (EP)", "Anzahl EPal", "LQ", "ADR", "Rundlauf", "Bemerkung"};
    }
}
