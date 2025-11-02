/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package th03;

import java.net.ConnectException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SV
 */
public class SanPhamThread extends Thread{
    String ip="";
    final JTable tblResult;
    JTextArea txtError;
    
    public SanPhamThread(String a, JTable b, JTextArea c){
        ip=a;
        tblResult=b;
        txtError=c;
    }
    @Override
    public void run(){
        String url="http://"+ip;
        DataModel db = new DataModel();
        String tenCot[]={"Ma San Pham","Ten San Pham","Gia Ban","Ma Kho"};
        try{
            ArrayList<ArrayList<String>> a = db.get(url);
            synchronized(tblResult){
                DefaultTableModel b = db.getTableModel(tenCot, a);
                tblResult.setModel(b);
            }
        }
        catch(ConnectException e1){
            String s = txtError.getText();
            s+="\n";
            s+=url+" Down";
            txtError.setText(s);
        }
        catch(Exception e2){
            e2.printStackTrace();
        }
    }    
}
