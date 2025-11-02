using CSDLPTTH01.Models;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class HomeController : Controller
    {
        DataModel db = new DataModel();
        public JsonResult Index()
        {
            string sql = "SELECT * FROM HOADON";
            return Json(db.get(sql), JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public JsonResult Add(int thang, int nam, string soHD, string maNV, double soTien)
        {
            string sql = $"INSERT INTO HOADON(thang, nam, soHD, maNV, soTien) " +
                         $"VALUES({thang}, {nam}, '{soHD}', '{maNV}', {soTien})";
            db.exec(sql);
            return Json(new { success = true, message = "Thêm hóa đơn thành công!" });
        }
        [HttpPost]
        public JsonResult Update(int soHDN, int thang, int nam, string soHD, string maNV, double soTien)
        {
            string sql = $"UPDATE HOADON " +
                         $"SET thang={thang}, nam={nam}, soHD='{soHD}', maNV='{maNV}', soTien={soTien} " +
                         $"WHERE soHDN={soHDN}";
            db.exec(sql);
            return Json(new { success = true, message = "Cập nhật hóa đơn thành công!" });
        }
        [HttpPost]
        public JsonResult Delete(int soHDN)
        {
            string sql = $"DELETE FROM HOADON WHERE soHDN={soHDN}";
            db.exec(sql);
            return Json(new { success = true, message = "Xóa hóa đơn thành công!" });
        }
    }
}
