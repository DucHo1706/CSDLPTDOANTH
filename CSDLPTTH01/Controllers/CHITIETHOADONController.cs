using CSDLPTTH01.Models;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class CHITIETHOADONController : Controller
    {
        DataModel db = new DataModel();

        // Lấy tất cả chi tiết của một hóa đơn (theo mã hóa đơn)
        public JsonResult Index(int soHDN)
        {
            string sql = $"SELECT * FROM CHITIET_HOADON WHERE soHDN = {soHDN}";
            return Json(db.get(sql), JsonRequestBehavior.AllowGet);
        }

        // Thêm một chi tiết vào hóa đơn có sẵn
        [HttpPost]
        public JsonResult Add(int soHDN, string maKH, decimal soLuong, decimal donGia)
        {
            string sql = $"INSERT INTO CHITIET_HOADON(soHDN, maKH, soLuong, donGia) VALUES({soHDN}, '{maKH}', {soLuong}, {donGia})";
            db.exec(sql);
            return Json(new { success = true, message = "Thêm chi tiết cho hóa đơn thành công!" });
        }

        // Cập nhật chi tiết hóa đơn
        [HttpPost]
        public JsonResult Update(int id, decimal soLuong, decimal donGia)
        {
            string sql = $"UPDATE CHITIET_HOADON SET soLuong = {soLuong}, donGia = {donGia} WHERE id = {id}";
            db.exec(sql);
            return Json(new { success = true, message = "Cập nhật chi tiết hóa đơn thành công!" });
        }

        // Xóa chi tiết hóa đơn
        [HttpPost]
        public JsonResult Delete(int id)
        {
            string sql = $"DELETE FROM CHITIET_HOADON WHERE id = {id}";
            db.exec(sql);
            return Json(new { success = true, message = "Xóa chi tiết hóa đơn thành công!" });
        }
    }
}
