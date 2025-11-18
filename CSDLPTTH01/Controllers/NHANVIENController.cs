using CSDLPTTH01.Models;
using System;
using System.Data;
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
        public JsonResult Add(string maNV, string hoten, string maCN)
        {
            try
            {
                // Sử dụng INSERT INTO trực tiếp
                string sql = "INSERT INTO NHANVIEN (maNV, hoten, maCN) VALUES (@maNV, @hoten, @maCN)";

                SqlParameter[] parameters = {
            new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV },
            new SqlParameter("@hoten", hoten),
            new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }
        };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm nhân viên thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 2627 || ex.Number == 2601)
                {
                    return Json(new { success = false, message = "Lỗi: Mã nhân viên '" + maNV + "' đã tồn tại." });
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
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN },
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV }
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
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV }
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

        // Thêm action để lấy danh sách chi nhánh (nếu cần cho dropdown)
        public JsonResult GetChiNhanh()
        {
            try
            {
                string sql = "SELECT maCN, tenCN FROM CHINHANH";
                return Json(db.get(sql), JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi khi tải danh sách chi nhánh: " + ex.Message });
            }
        }
    }
}