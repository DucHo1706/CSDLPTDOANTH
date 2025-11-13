using CSDLPTTH01.Models;
using System;
using System.Data; // Thêm thư viện này
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

        [HttpPost]
        public JsonResult Add(string hoten, string maCN)
        {
            try
            {
                string sql = "EXEC sp_TaoNhanVienTuDong @hoten, @maCN, @MaNVMoi_Output = @MaNVMoi OUTPUT";

                SqlParameter[] parameters = {
          new SqlParameter("@hoten", hoten),
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }, // Thêm Size
                    // === SỬA (10 -> 15) ===
                    new SqlParameter("@MaNVMoi", SqlDbType.Char, 15) { Direction = ParameterDirection.Output }
        };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm nhân viên thành công (Mã đã được tạo tự động)!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 50000)
                {
                    return Json(new { success = false, message = "Lỗi logic: " + ex.Message });
                    }
                if (ex.Number == 2627 || ex.Number == 2601)
                {
                    return Json(new { success = false, message = "Lỗi: Mã nhân viên này đã tồn tại." });
                }
                if (ex.Number == 547)
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

        [HttpPost]
        public JsonResult Update(string maNV, string hoten, string maCN)
        {
            try
            {
                string sql = "UPDATE NHANVIEN SET hoten=@hoten, maCN=@maCN WHERE maNV=@maNV";

                SqlParameter[] parameters = {
          new SqlParameter("@hoten", hoten),
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }, // Thêm Size
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV } // Thêm Size
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật nhân viên thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547)
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

        [HttpPost]
        public JsonResult Delete(string maNV)
        {
            try
            {
                string sql = "DELETE FROM NHANVIEN WHERE maNV=@maNV";

                SqlParameter[] parameters = {
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV } // Thêm Size
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa nhân viên thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547)
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