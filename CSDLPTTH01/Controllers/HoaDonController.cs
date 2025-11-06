using CSDLPTTH01.Models;
using System; // Thêm thư viện này
using System.Data.SqlClient; // Thêm thư viện này
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
        public JsonResult Add(int thang, int nam, string soHD, string maNV, double soTien)
        {
            try
            {
                string sql = "INSERT INTO HOADON(thang, nam, soHD, maNV, soTien) " +
                             "VALUES(@thang, @nam, @soHD, @maNV, @soTien)";

                SqlParameter[] parameters = {
                    new SqlParameter("@thang", thang),
                    new SqlParameter("@nam", nam),
                    new SqlParameter("@soHD", soHD),
                    new SqlParameter("@maNV", maNV),
                    new SqlParameter("@soTien", soTien)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm hóa đơn thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547) // Lỗi khóa ngoại
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
        public JsonResult Update(int soHDN, int thang, int nam, string soHD, string maNV, double soTien)
        {
            try
            {
                string sql = "UPDATE HOADON " +
                             "SET thang=@thang, nam=@nam, soHD=@soHD, maNV=@maNV, soTien=@soTien " +
                             "WHERE soHDN=@soHDN";

                SqlParameter[] parameters = {
                    new SqlParameter("@thang", thang),
                    new SqlParameter("@nam", nam),
                    new SqlParameter("@soHD", soHD),
                    new SqlParameter("@maNV", maNV),
                    new SqlParameter("@soTien", soTien),
                    new SqlParameter("@soHDN", soHDN)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật hóa đơn thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547) // Lỗi khóa ngoại
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
        public JsonResult Delete(int soHDN)
        {
            try
            {
                string sql = "DELETE FROM HOADON WHERE soHDN=@soHDN";

                SqlParameter[] parameters = {
                    new SqlParameter("@soHDN", soHDN)
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