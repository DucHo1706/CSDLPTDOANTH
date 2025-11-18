using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Collections;
using System.Data;

namespace CSDLPTTH01.Models
{
    public class DataModel
    {
        public static string connectionString = "Server=HAIDANG\\HAIDANGCSDLPTTH ;Database=QL_DienLuc;User Id=sa; Password=123; Encrypt=False;TrustServerCertificate=True;MultipleActiveResultSets=True";

        /**
         * Hàm get (SELECT) đã được cập nhật
         * - Sửa lỗi: Trả về một List<List<object>> (Array của Arrays)
         * - Không còn trả về tên cột
         */
        public List<List<object>> get(string sql, SqlParameter[] parameters = null)
        {
            // Thay đổi 1: Kiểu trả về là List<List<object>>
            var dataList = new List<List<object>>();

            using (SqlConnection connection = new SqlConnection(connectionString))
            using (SqlCommand command = new SqlCommand(sql, connection))
            {
                if (parameters != null)
                {
                    command.Parameters.AddRange(parameters);
                }

                connection.Open();
                using (SqlDataReader reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        // Thay đổi 2: 'row' bây giờ là một List<object> (mảng giá trị)
                        var row = new List<object>();
                        for (int i = 0; i < reader.FieldCount; i++)
                        {
                            var value = reader.GetValue(i);

                            // Tự động cắt khoảng trắng nếu là string
                            if (value is string)
                            {
                                // Thay đổi 3: Chỉ thêm giá trị vào mảng, không thêm key
                                row.Add(((string)value).TrimEnd());
                            }
                            else
                            {
                                // Thay đổi 3 (tiếp): Chỉ thêm giá trị
                                row.Add(value);
                            }
                        }
                        dataList.Add(row); // Thêm mảng giá trị vào kết quả
                    }
                }
            }
            return dataList;
        }

        /**
         * Hàm exec (INSERT, UPDATE, DELETE) an toàn
         */
        public void exec(string sql, SqlParameter[] parameters)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            using (SqlCommand command = new SqlCommand(sql, connection))
            {
                if (parameters != null)
                {
                    command.Parameters.AddRange(parameters);
                }

                connection.Open();
                command.ExecuteNonQuery();
            }
        }
    }
}