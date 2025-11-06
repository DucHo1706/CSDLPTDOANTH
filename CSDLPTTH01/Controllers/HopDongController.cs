using CSDLPTTH01.Models;
using System; // Thêm thư viện này
using System.Data.SqlClient; // Thêm thư viện này
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class HopDongController : Controller
    {
        DataModel db = new DataModel();

        public JsonResult Index()
        {
            try
            {
                string sql = "SELECT * FROM hopdong";
                return Json(db.get(sql), JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi khi tải danh sách: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Add(string soHD, string ngayKy, string maKH, string soDienKe, int kwDinhMuc, decimal dongiaKW)
        {
            try
            {
                string sql = "INSERT INTO hopdong(soHD, ngayKy, maKH, soDienKe, kwDinhMuc, dongiaKW) " +
                             "VALUES(@soHD, @ngayKy, @maKH, @soDienKe, @kwDinhMuc, @dongiaKW)";

                SqlParameter[] parameters = {
                    new SqlParameter("@soHD", soHD),
                    new SqlParameter("@ngayKy", ngayKy), // Giả sử ngayKy là string (YYYY-MM-DD), nếu là DateTime thì nên parse
                    new SqlParameter("@maKH", maKH),
                    new SqlParameter("@soDienKe", soDienKe),
                    new SqlParameter("@kwDinhMuc", kwDinhMuc),
                    new SqlParameter("@dongiaKW", dongiaKW)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm hợp đồng thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 2627 || ex.Number == 2601) // Lỗi trùng khóa chính
                {
                    return Json(new { success = false, message = "Lỗi: Số hợp đồng này đã tồn tại." });
                }
                if (ex.Number == 547) // Lỗi khóa ngoại
                {
                    return Json(new { success = false, message = "Lỗi: Mã khách hàng không tồn tại." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Update(string soHD, string ngayKy, string maKH, string soDienKe, int kwDinhMuc, decimal dongiaKW)
        {
            try
            {
                string sql = "UPDATE hopdong SET ngayKy=@ngayKy, maKH=@maKH, soDienKe=@soDienKe, kwDinhMuc=@kwDinhMuc, dongiaKW=@dongiaKW WHERE soHD=@soHD";

                SqlParameter[] parameters = {
                    new SqlParameter("@ngayKy", ngayKy),
                    new SqlParameter("@maKH", maKH),
                    new SqlParameter("@soDienKe", soDienKe),
                    new SqlParameter("@kwDinhMuc", kwDinhMuc),
                    new SqlParameter("@dongiaKW", dongiaKW),
                    new SqlParameter("@soHD", soHD)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật hợp đồng thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547) // Lỗi khóa ngoại
                {
                    return Json(new { success = false, message = "Lỗi: Mã khách hàng không tồn tại." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Delete(string soHD)
        {
            try
            {
                string sql = "DELETE FROM hopdong WHERE soHD=@soHD";

                SqlParameter[] parameters = {
                    new SqlParameter("@soHD", soHD)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa hợp đồng thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547) // Lỗi khóa ngoại
                {
                    return Json(new { success = false, message = "Không thể xóa. Hợp đồng này vẫn còn dữ liệu liên quan (ví dụ: hóa đơn)." });
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