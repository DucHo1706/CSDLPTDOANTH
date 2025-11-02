using CSDLPTTH01.Models;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class NHANVIENController : Controller
    {
        DataModel db = new DataModel();

        // Lấy danh sách nhân viên
        public JsonResult Index()
        {
            string sql = "SELECT * FROM NHANVIEN";
            return Json(db.get(sql), JsonRequestBehavior.AllowGet);
        }

        // Thêm nhân viên
        [HttpPost]
        public JsonResult Add(string maNV, string tenNV, string diaChi, string sdt)
        {
            string sql = $"INSERT INTO NHANVIEN(maNV, tenNV, diaChi, sdt) VALUES('{maNV}', N'{tenNV}', N'{diaChi}', '{sdt}')";
            db.exec(sql);
            return Json(new { success = true, message = "Thêm nhân viên thành công!" });
        }

        // Cập nhật nhân viên
        [HttpPost]
        public JsonResult Update(string maNV, string tenNV, string diaChi, string sdt)
        {
            string sql = $"UPDATE NHANVIEN SET tenNV=N'{tenNV}', diaChi=N'{diaChi}', sdt='{sdt}' WHERE maNV='{maNV}'";
            db.exec(sql);
            return Json(new { success = true, message = "Cập nhật nhân viên thành công!" });
        }

        // Xóa nhân viên
        [HttpPost]
        public JsonResult Delete(string maNV)
        {
            string sql = $"DELETE FROM NHANVIEN WHERE maNV='{maNV}'";
            db.exec(sql);
            return Json(new { success = true, message = "Xóa nhân viên thành công!" });
        }
    }
}
