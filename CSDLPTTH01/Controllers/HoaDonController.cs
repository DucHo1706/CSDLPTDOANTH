using CSDLPTTH01.Models;
using System;
using System.Data;
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

        [HttpPost]
        public JsonResult Add(string soHDN, int thang, int nam, string soHD, string maNV, decimal soTien)
        {
            try
            {
                // Sử dụng INSERT INTO với số hóa đơn được truyền từ client
                string sql = "INSERT INTO HOADON (soHDN, thang, nam, soHD, maNV, soTien) VALUES (@soHDN, @thang, @nam, @soHD, @maNV, @soTien)";

                SqlParameter[] parameters = {
                    new SqlParameter("@soHDN", SqlDbType.Char, 15) { Value = soHDN },
                    new SqlParameter("@thang", thang),
                    new SqlParameter("@nam", nam),
                    new SqlParameter("@soHD", SqlDbType.Char, 15) { Value = soHD },
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV },
                    new SqlParameter("@soTien", soTien)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm hóa đơn thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 50000)
                {
                    return Json(new { success = false, message = "Lỗi logic: " + ex.Message });
                }
                if (ex.Number == 2627 || ex.Number == 2601)
                {
                    return Json(new { success = false, message = "Lỗi: Số hóa đơn này đã tồn tại." });
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
                    new SqlParameter("@soHD", SqlDbType.Char, 15) { Value = soHD },
                    new SqlParameter("@maNV", SqlDbType.Char, 15) { Value = maNV },
                    new SqlParameter("@soTien", soTien),
                    new SqlParameter("@soHDN", SqlDbType.Char, 15) { Value = soHDN }
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
        public JsonResult Delete(string soHDN)
        {
            try
            {
                string sql = "DELETE FROM HOADON WHERE soHDN=@soHDN";

                SqlParameter[] parameters = {
                    new SqlParameter("@soHDN", SqlDbType.Char, 15) { Value = soHDN }
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa hóa đơn thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547)
                {
                    return Json(new { success = false, message = "Không thể xóa hóa đơn do có dữ liệu liên quan." });
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