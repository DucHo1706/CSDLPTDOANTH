using CSDLPTTH01.Models;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class HopDongController : Controller
    {
        DataModel db = new DataModel();

        public JsonResult Index()
        {
            string sql = "SELECT * FROM hopdong";
            return Json(db.get(sql), JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult Add(string soHD, string ngayKy, string maKH, string soDienKe, int kwDinhMuc, decimal dongiaKW)
        {
            string sql = $"INSERT INTO hopdong(soHD, ngayKy, maKH, soDienKe, kwDinhMuc, dongiaKW) " +
                         $"VALUES('{soHD}', '{ngayKy}', '{maKH}', '{soDienKe}', {kwDinhMuc}, {dongiaKW})";
            db.exec(sql);
            return Json(new { success = true, message = "Thêm hợp đồng thành công!" });
        }

        [HttpPost]
        public JsonResult Update(string soHD, string ngayKy, string maKH, string soDienKe, int kwDinhMuc, decimal dongiaKW)
        {
            string sql = $"UPDATE hopdong SET ngayKy='{ngayKy}', maKH='{maKH}', soDienKe='{soDienKe}', kwDinhMuc={kwDinhMuc}, dongiaKW={dongiaKW} WHERE soHD='{soHD}'";
            db.exec(sql);
            return Json(new { success = true, message = "Cập nhật hợp đồng thành công!" });
        }

        [HttpPost]
        public JsonResult Delete(string soHD)
        {
            string sql = $"DELETE FROM hopdong WHERE soHD='{soHD}'";
            db.exec(sql);
            return Json(new { success = true, message = "Xóa hợp đồng thành công!" });
        }
    }
}
