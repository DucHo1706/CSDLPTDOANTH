using CSDLPTTH01.Models;
using System;
using System.Data; // Thêm thư viện này
using System.Data.SqlClient;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class HoaDonController : Controller
    {
        DataModel db = new DataModel();
        public JsonResult Index()
        {
            try
            {
                string sql = "SELECT * FROM HOADON";
                return Json(db.get(sql), JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi khi tải danh sách: " + ex.Message });
            }
        }

        // === SỬA TOÀN BỘ ===
        [HttpPost]
        // 1. Đổi 'double soTien' -> 'decimal soTien' (khớp với CSDL)
        public JsonResult Add(int thang, int nam, string soHD, string maNV, decimal soTien)
        {
            try
            {
                // 2. Sửa SQL để gọi Stored Procedure
                string sql = "EXEC sp_TaoHoaDonTuDong @thang, @nam, @soHD, @maNV, @soTien, @SoHDNMoi_Output = @SoHDNMoi OUTPUT";

                SqlParameter[] parameters = {
          new SqlParameter("@thang", thang),
          new SqlParameter("@nam", nam),
                    new SqlParameter("@soHD", SqlDbType.Char, 15) { Value = soHD }, // Thêm Size
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV }, // Thêm Size
                    new SqlParameter("@soTien", soTien),
                    // 3. Thêm tham số Output (15 KÝ TỰ)
                    new SqlParameter("@SoHDNMoi", SqlDbType.Char, 15) { Direction = ParameterDirection.Output }
        };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm hóa đơn thành công (Mã đã được tạo tự động)!" });
            }
            catch (SqlException ex)
            {
                // 4. Thêm bắt lỗi 50000 (RAISERROR từ SP)
                if (ex.Number == 50000)
                {
                    return Json(new { success = false, message = "Lỗi logic: " + ex.Message });
                }
                if (ex.Number == 547)
                {
                    return Json(new { success = false, message = "Lỗi: Số hợp đồng hoặc Mã nhân viên không tồn tại." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        [HttpPost]
        // 5. Sửa 'int soHDN' -> 'string soHDN'
        // 6. Sửa 'double soTien' -> 'decimal soTien'
        public JsonResult Update(string soHDN, int thang, int nam, string soHD, string maNV, decimal soTien)
        {
            try
            {
                string sql = "UPDATE HOADON " +
                      "SET thang=@thang, nam=@nam, soHD=@soHD, maNV=@maNV, soTien=@soTien " +
                      "WHERE soHDN=@soHDN";

                SqlParameter[] parameters = {
          new SqlParameter("@thang", thang),
          new SqlParameter("@nam", nam),
                    new SqlParameter("@soHD", SqlDbType.Char, 15) { Value = soHD }, // Thêm Size
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV }, // Thêm Size
                    new SqlParameter("@soTien", soTien),
                    new SqlParameter("@soHDN", SqlDbType.Char, 15) { Value = soHDN } // Thêm Size
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật hóa đơn thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547)
                {
                    return Json(new { success = false, message = "Lỗi: Số hợp đồng hoặc Mã nhân viên không tồn tại." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        [HttpPost]
        // 7. Sửa 'int soHDN' -> 'string soHDN'
        public JsonResult Delete(string soHDN)
        {
            try
            {
                string sql = "DELETE FROM HOADON WHERE soHDN=@soHDN";

                SqlParameter[] parameters = {
                    new SqlParameter("@soHDN", SqlDbType.Char, 15) { Value = soHDN } // Thêm Size
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa hóa đơn thành công!" });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi: " + ex.Message });
            }
        }
    }
}