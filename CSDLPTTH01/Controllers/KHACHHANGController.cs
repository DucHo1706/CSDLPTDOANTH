using CSDLPTTH01.Models;
using System;
using System.Data; // Thêm thư viện này
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
        public JsonResult Add(string tenKH, string maCN)
        {
            try
            {
                string sql = "EXEC sp_TaoKhachHangTuDong @tenKH, @maCN, @MaKHMoi_Output = @MaKHMoi OUTPUT";

                SqlParameter[] parameters = {
          new SqlParameter("@tenKH", tenKH),
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }, // Thêm Size
                    // === SỬA (10 -> 15) ===
                    new SqlParameter("@MaKHMoi", SqlDbType.Char, 15) { Direction = ParameterDirection.Output }
        };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm khách hàng thành công (Mã đã được tạo tự động)!" });
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
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }, // Thêm Size
                    new SqlParameter("@maKH", SqlDbType.Char, 15) { Value = maKH } // Thêm Size
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
                    new SqlParameter("@maKH", SqlDbType.Char, 15) { Value = maKH } // Thêm Size
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
    }
}