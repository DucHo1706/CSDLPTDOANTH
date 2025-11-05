using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Collections;

namespace CSDLPTTH01.Models
{
    public class DataModel
    {
        public static string connectionString = "Server=HAIDANG\\HAIDANGCSDLPTLT;Database=QL_DienLuc;User Id=sa; Password=123; Encrypt=False;TrustServerCertificate=True;MultipleActiveResultSets=True";

        public ArrayList get(String sql)
        {
            ArrayList datalist = new ArrayList();
            SqlConnection connection = new SqlConnection(connectionString);
            SqlCommand command = new SqlCommand(sql, connection);
            connection.Open();
            using (SqlDataReader r = command.ExecuteReader())
            {
                while (r.Read())
                {
                    ArrayList row = new ArrayList();
                    for (int i = 0; i < r.FieldCount; i++)
                    {
                        row.Add(xulydulieu(r.GetValue(i).ToString()));
                    }
                    datalist.Add(row);
                }
            }
            connection.Close();
            return datalist;
        }
        public string xulydulieu(string text)
        {
            String s = text.Replace(",", "&44;");
            s = s.Replace("\"", "&34;");
            s = s.Replace("'", "&39;");
            s = s.Replace("\r", "");
            s = s.Replace("\n", "");
            return s;
        }
        public void exec(string sql)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                SqlCommand command = new SqlCommand(sql, connection);
                connection.Open();
                command.ExecuteNonQuery();
            }
        }

    }
}