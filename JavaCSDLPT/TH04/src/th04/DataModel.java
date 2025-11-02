/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package th04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;

import org.json.JSONException;
public class DataModel {
    public ArrayList<ArrayList<String>> get ( String url )
        throws IOException, InterruptedException, JSONException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray ja =new JSONArray(new String(response.body().getBytes(),"utf-8"));
        
        ArrayList<ArrayList<String>> datalist = new ArrayList<>();
        for(int i=0; i<ja.length(); i++){
            String s = ja.get(i).toString();
            s=s.substring(1,s.length()-1);
            String []as = s.split(",");
            ArrayList<String> row = new ArrayList<>();
            for(int j=0;j<as.length;j++){
                as[j]=as[j].replace('"', ' ');
                row.add(as[j]);
            }
            datalist.add(row);
        }        
        return datalist;
    }
    
    public DefaultTableModel getTableModel(String[] tenCot, ArrayList<ArrayList<String>> d){
        DefaultTableModel tableModel = new DefaultTableModel(tenCot, 0);
        for(int i=0;i<d.size();i++){
            Object o[] =new Object[tenCot.length];
            for(int j=0;j<d.get(i).size();j++){
                o[j] = d.get(i).get(j);
            }
            tableModel.addRow(o); 
        }
        return tableModel;
    }
    
    public DefaultTableModel addTableModel(DefaultTableModel tableModel, ArrayList<ArrayList<String>> d,String tenCot[]){
        if(tableModel==null){
            tableModel = new DefaultTableModel(tenCot, 0);
        }
        for(int i=0;i<d.size();i++){
            Object o[] =new Object[tenCot.length];
            for(int j=0;j<d.get(i).size();j++){
                o[j] = d.get(i).get(j);
            }
            tableModel.addRow(o); 
        }
        return tableModel;
    }

    public void getDataSanPham(File f, DefaultTableModel tableModel,JTable tblResult,JTextArea txtError, String tenCot[]){
        if( f == null ){ return; }
        ArrayList<String> aIP = new ArrayList();
        try{
            BufferedReader bf = new BufferedReader(new FileReader(f));
            while( bf.ready() ){
                aIP.add( bf.readLine() );
            }
            bf.close();
        }catch(Exception e){
             e.printStackTrace();
        }
        for(int i=0; i < aIP.size(); i++){
            String url="http://" + aIP.get(i);
            DataModel db = new DataModel();            
            try{
                ArrayList<ArrayList<String>> a = db.get(url);
                DefaultTableModel b = db.addTableModel(tableModel, a,tenCot);
                tblResult.setModel(b);
                break;
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
    
}
