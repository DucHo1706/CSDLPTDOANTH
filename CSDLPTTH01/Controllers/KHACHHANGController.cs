using CSDLPTTH01.Models;
using System; // Thêm thư viện này
using System.Data.SqlClient; // Thêm thư viện này
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
        public JsonResult Add(string maKH, string tenKH, string diaChi, string sdt)
        {
            try
            {
                string sql = "INSERT INTO KHACHHANG(maKH, tenKH, diaChi, sdt) VALUES(@maKH, @tenKH, @diaChi, @sdt)";

                SqlParameter[] parameters = {
                    new SqlParameter("@maKH", maKH),
                    new SqlParameter("@tenKH", tenKH),
                    new SqlParameter("@diaChi", diaChi),
                    new SqlParameter("@sdt", sdt)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm khách hàng thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 2627 || ex.Number == 2601) // Lỗi trùng khóa chính
                {
                    return Json(new { success = false, message = "Lỗi: Mã khách hàng này đã tồn tại." });
                }
                // Nếu bạn có khóa ngoại 'maCN' ở đây, hãy thêm một (if ex.Number == 547)
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Update(string maKH, string tenKH, string diaChi, string sdt)
        {
            try
            {
                string sql = "UPDATE KHACHHANG SET tenKH=@tenKH, diaChi=@diaChi, sdt=@sdt WHERE maKH=@maKH";

                SqlParameter[] parameters = {
                    new SqlParameter("@tenKH", tenKH),
                    new SqlParameter("@diaChi", diaChi),
                    new SqlParameter("@sdt", sdt),
                    new SqlParameter("@maKH", maKH)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật khách hàng thành công!" });
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
                    new SqlParameter("@maKH", maKH)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa khách hàng thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547) // Lỗi khóa ngoại
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