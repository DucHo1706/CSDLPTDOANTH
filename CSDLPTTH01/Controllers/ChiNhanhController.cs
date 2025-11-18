using CSDLPTTH01.Models;
using System;
using System.Data;
using System.Data.SqlClient;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class ChiNhanhController : Controller
    {
        DataModel db = new DataModel();

        public JsonResult Index()
        {
            try
            {
                string sql = "SELECT * FROM chinhanh";
                return Json(db.get(sql), JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi khi tải danh sách: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Add(string maCN, string tenCN, string thanhpho)
        {
            try
            {
                 string sql = "INSERT INTO chinhanh (maCN, tenCN, thanhpho) VALUES (@maCN, @tenCN, @thanhpho)";

                SqlParameter[] parameters = {
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN },
                    new SqlParameter("@tenCN", tenCN),
                    new SqlParameter("@thanhpho", thanhpho)
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Thêm chi nhánh thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 50000)
                {
                    return Json(new { success = false, message = "Lỗi logic: " + ex.Message });
                }
                if (ex.Number == 2627 || ex.Number == 2601)
                {
                    return Json(new { success = false, message = "Lỗi: Mã chi nhánh này đã tồn tại." });
                }
                return Json(new { success = false, message = "Lỗi SQL: " + ex.Message });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi hệ thống: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Update(string maCN, string tenCN, string thanhpho)
        {
            try
            {
                string sql = "UPDATE chinhanh SET tenCN=@tenCN, thanhpho=@thanhpho WHERE maCN=@maCN";

                SqlParameter[] parameters = {
                    new SqlParameter("@tenCN", tenCN),
                    new SqlParameter("@thanhpho", thanhpho),
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Cập nhật chi nhánh thành công!" });
            }
            catch (Exception ex)
            {
                return Json(new { success = false, message = "Lỗi: " + ex.Message });
            }
        }

        [HttpPost]
        public JsonResult Delete(string maCN)
        {
            try
            {
                string sql = "DELETE FROM chinhanh WHERE maCN=@maCN";

                SqlParameter[] parameters = {
                    new SqlParameter("@maCN", SqlDbType.Char, 15) { Value = maCN }
                };

                db.exec(sql, parameters);
                return Json(new { success = true, message = "Xóa chi nhánh thành công!" });
            }
            catch (SqlException ex)
            {
                if (ex.Number == 547)
                {
                    return Json(new { success = false, message = "Không thể xóa. Chi nhánh này vẫn còn dữ liệu liên quan (ví dụ: nhân viên, khách hàng)." });
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