using CSDLPTTH01.Models;
using System;
using System.Data.SqlClient;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class NHANVIENController : Controller
    {
        DataModel db = new DataModel();

        public JsonResult Index()
        {

            try
            {
                string sql = "SELECT * FROM NHANVIEN";
                return Json(db.get(sql), JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi khi tải danh sách: " + ex.Message });
            }

        }

        // Thêm nhân viên (Đã sửa để khớp database)
        [HttpPost]
        public JsonResult Add(string maNV, string hoten, string maCN) // Sửa tham số
        {
            try
            {
                // Sửa câu SQL
                string sql = "INSERT INTO NHANVIEN(maNV, hoten, maCN) VALUES(@maNV, @hoten, @maCN)";

                SqlParameter[] parameters = {
                    new SqlParameter("@maNV", maNV),
                    new SqlParameter("@hoten", hoten), // Sửa tham số
                    new SqlParameter("@maCN", maCN)   // Sửa tham số
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm nhân viên thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 2627 || ex.Number == 2601) // Lỗi trùng khóa chính
                {
                    return Json(new { success = false, message = "Lỗi: Mã nhân viên này đã tồn tại." });
                }
                if (ex.Number == 547) // Lỗi khóa ngoại
                {
                    return Json(new { success = false, message = "Lỗi: Mã chi nhánh không tồn tại." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        // Cập nhật nhân viên (Đã sửa để khớp database)
        [HttpPost]
        public JsonResult Update(string maNV, string hoten, string maCN) // Sửa tham số
        {
            try
            {
                // Sửa câu SQL (loại bỏ sdt và diaChi)
                string sql = "UPDATE NHANVIEN SET hoten=@hoten, maCN=@maCN WHERE maNV=@maNV";

                SqlParameter[] parameters = {
                    new SqlParameter("@hoten", hoten), // Sửa tham số
                    new SqlParameter("@maCN", maCN),   // Sửa tham số
                    new SqlParameter("@maNV", maNV)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật nhân viên thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547) // Lỗi khóa ngoại
                {
                    return Json(new { success = false, message = "Lỗi: Mã chi nhánh không tồn tại." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        // Xóa nhân viên (Hàm này đã đúng)
        [HttpPost]
        public JsonResult Delete(string maNV)
        {
            try
            {
                string sql = "DELETE FROM NHANVIEN WHERE maNV=@maNV";

                SqlParameter[] parameters = {
                    new SqlParameter("@maNV", maNV)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa nhân viên thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547) // Lỗi khóa ngoại
                {
                    return Json(new { success = false, message = "Không thể xóa. Nhân viên này đã có dữ liệu hóa đơn liên quan." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }
    }
}