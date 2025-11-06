using System;
using System.Data;
using System.Data.SqlClient;
using System.Web.Mvc;
using System.Collections.Generic;

namespace CSDLPTTH01.Controllers
{
    public class NHANVIENController : Controller
    {
        private string connectionString = "Your-Connection-String-Here";

        // GET: /NHANVIEN/
        public JsonResult Index()
        {
            try
            {
                var data = new List<object>();
                using (var connection = new SqlConnection(connectionString))
                {
                    connection.Open();
                    string sql = "SELECT maNV, hoten, maCN FROM NHANVIEN";
                    using (var command = new SqlCommand(sql, connection))
                    using (var reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            data.Add(new
                            {
                                maNV = reader["maNV"].ToString(),
                                hoten = reader["hoten"].ToString(),
                                maCN = reader["maCN"].ToString()
                            });
                        }
                    }
                }
                return Json(data, JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi: " + ex.Message }, JsonRequestBehavior.AllowGet);
            }
        }

        // POST: /NHANVIEN/Add
        [HttpPost]
        public JsonResult Add(string maNV, string hoten, string maCN)
        {
            try
            {
                // Validate input
                if (string.IsNullOrEmpty(maNV) || string.IsNullOrEmpty(hoten) || string.IsNullOrEmpty(maCN))
                {
                    return Json(new { success = false, message = "Vui lòng nhập đầy đủ thông tin!" });
                }

                using (var connection = new SqlConnection(connectionString))
                {
                    connection.Open();

                    // Sử dụng parameterized query để tránh SQL Injection
                    string sql = @"INSERT INTO NHANVIEN(maNV, hoten, maCN) 
                                   VALUES(@maNV, @hoten, @maCN)";

                    using (var command = new SqlCommand(sql, connection))
                    {
                        command.Parameters.AddWithValue("@maNV", maNV);
                        command.Parameters.AddWithValue("@hoten", hoten);
                        command.Parameters.AddWithValue("@maCN", maCN);

                        int rowsAffected = command.ExecuteNonQuery();

                        if (rowsAffected > 0)
                        {
                            return Json(new { success = true, message = "Thêm nhân viên thành công!" });
                        }
                        else
                        {
                            return Json(new { success = false, message = "Thêm nhân viên thất bại!" });
                        }
                    }
                }
            }
            catch (SqlException sqlEx)
            {
                string errorMessage = sqlEx.Number == 2627 ?
                    "Mã nhân viên đã tồn tại!" : "Lỗi database: " + sqlEx.Message;
                return Json(new { success = false, message = errorMessage });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi: " + ex.Message });
            }
        }

        // POST: /NHANVIEN/Update
        [HttpPost]
        public JsonResult Update(string maNV, string hoten, string maCN)
        {
            try
            {
                if (string.IsNullOrEmpty(maNV))
                {
                    return Json(new { success = false, message = "Mã nhân viên không được để trống!" });
                }

                using (var connection = new SqlConnection(connectionString))
                {
                    connection.Open();

                    string sql = @"UPDATE NHANVIEN 
                                   SET hoten = @hoten, maCN = @maCN 
                                   WHERE maNV = @maNV";

                    using (var command = new SqlCommand(sql, connection))
                    {
                        command.Parameters.AddWithValue("@maNV", maNV);
                        command.Parameters.AddWithValue("@hoten", hoten);
                        command.Parameters.AddWithValue("@maCN", maCN);

                        int rowsAffected = command.ExecuteNonQuery();

                        if (rowsAffected > 0)
                        {
                            return Json(new { success = true, message = "Cập nhật nhân viên thành công!" });
                        }
                        else
                        {
                            return Json(new { success = false, message = "Không tìm thấy nhân viên để cập nhật!" });
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi: " + ex.Message });
            }
        }

        // POST: /NHANVIEN/Delete
        [HttpPost]
        public JsonResult Delete(string maNV)
        {
            try
            {
                if (string.IsNullOrEmpty(maNV))
                {
                    return Json(new { success = false, message = "Mã nhân viên không được để trống!" });
                }

                using (var connection = new SqlConnection(connectionString))
                {
                    connection.Open();

                    // Kiểm tra khóa ngoại trước khi xóa
                    string checkSql = "SELECT COUNT(*) FROM HOADON WHERE maNV = @maNV";
                    using (var checkCommand = new SqlCommand(checkSql, connection))
                    {
                        checkCommand.Parameters.AddWithValue("@maNV", maNV);
                        int referenceCount = (int)checkCommand.ExecuteScalar();

                        if (referenceCount > 0)
                        {
                            return Json(new
                            {
                                success = false,
                                message = "Không thể xóa nhân viên vì đã có dữ liệu hóa đơn liên quan!"
                            });
                        }
                    }

                    string sql = "DELETE FROM NHANVIEN WHERE maNV = @maNV";
                    using (var command = new SqlCommand(sql, connection))
                    {
                        command.Parameters.AddWithValue("@maNV", maNV);

                        int rowsAffected = command.ExecuteNonQuery();

                        if (rowsAffected > 0)
                        {
                            return Json(new { success = true, message = "Xóa nhân viên thành công!" });
                        }
                        else
                        {
                            return Json(new { success = false, message = "Không tìm thấy nhân viên để xóa!" });
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi: " + ex.Message });
            }
        }
    }
}