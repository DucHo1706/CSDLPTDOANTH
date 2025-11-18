using CSDLPTTH01.Models;
using System;
using System.Data;
using System.Data.SqlClient;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class KHACHHANGController : Controller
    {
        DataModel db = new DataModel();

        public JsonResult Index()
        {
            try
            {
                string sql = "SELECT * FROM KHACHHANG";
                return Json(db.get(sql), JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi khi tải danh sách: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Add(string maKH, string tenKH, string maCN)
        {
            try
            {
                // Sử dụng INSERT INTO với mã khách hàng được truyền từ client
                string sql = "INSERT INTO KHACHHANG (maKH, tenKH, maCN) VALUES (@maKH, @tenKH, @maCN)";

                SqlParameter[] parameters = {
                    new SqlParameter("@maKH", SqlDbType.Char, 15) { Value = maKH },
                    new SqlParameter("@tenKH", tenKH),
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm khách hàng thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 50000)
                {
                    return Json(new { success = false, message = "Lỗi logic: " + ex.Message });
                }
                if (ex.Number == 2627 || ex.Number == 2601)
                {
                    return Json(new { success = false, message = "Lỗi: Mã khách hàng này đã tồn tại." });
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
        public JsonResult Update(string maKH, string tenKH, string maCN)
        {
            try
            {
                string sql = "UPDATE KHACHHANG SET tenKH=@tenKH, maCN=@maCN WHERE maKH=@maKH";

                SqlParameter[] parameters = {
                    new SqlParameter("@tenKH", tenKH),
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN },
                    new SqlParameter("@maKH", SqlDbType.Char, 15) { Value = maKH }
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật khách hàng thành công!" });
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
                return Json(new { success = false, message = "Lỗi: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Delete(string maKH)
        {
            try
            {
                string sql = "DELETE FROM KHACHHANG WHERE maKH=@maKH";

                SqlParameter[] parameters = {
                    new SqlParameter("@maKH", SqlDbType.Char, 15) { Value = maKH }
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa khách hàng thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547)
                {
                    return Json(new { success = false, message = "Không thể xóa. Khách hàng này vẫn còn dữ liệu liên quan (ví dụ: hợp đồng)." });
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