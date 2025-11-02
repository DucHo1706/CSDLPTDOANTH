/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package th03;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author SV
 */
public class DataModel {
    public ArrayList<ArrayList<String>> get ( String url ) 
        throws IOException, InterruptedException, JSONException{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request;
            request = HttpRequest.newBuilder().uri(URI.create(url)).build();
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
                //System.out.println(i+"-"+j);
            }
            tableModel.addRow(o); 
        }
        return tableModel;
    }


}
