using CSDLPTTH01.Models;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class KHACHHANGController : Controller
    {
        DataModel db = new DataModel();

        public JsonResult Index()
        {
            string sql = "SELECT * FROM KHACHHANG";
            return Json(db.get(sql), JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult Add(string maKH, string tenKH, string diaChi, string sdt)
        {
            string sql = $"INSERT INTO KHACHHANG(maKH, tenKH, diaChi, sdt) VALUES('{maKH}', N'{tenKH}', N'{diaChi}', '{sdt}')";
            db.exec(sql);
            return Json(new { success = true, message = "Thêm khách hàng thành công!" });
        }

        [HttpPost]
        public JsonResult Update(string maKH, string tenKH, string diaChi, string sdt)
        {
            string sql = $"UPDATE KHACHHANG SET tenKH=N'{tenKH}', diaChi=N'{diaChi}', sdt='{sdt}' WHERE maKH='{maKH}'";
            db.exec(sql);
            return Json(new { success = true, message = "Cập nhật khách hàng thành công!" });
        }

        [HttpPost]
        public JsonResult Delete(string maKH)
        {
            string sql = $"DELETE FROM KHACHHANG WHERE maKH='{maKH}'";
            db.exec(sql);
            return Json(new { success = true, message = "Xóa khách hàng thành công!" });
        }
    }
}
