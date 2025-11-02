using CSDLPTTH01.Models;
using System.Web.Mvc;

namespace CSDLPTTH01.Controllers
{
    public class ChiNhanhController : Controller
    {
        DataModel db = new DataModel();

        public JsonResult Index()
        {
            string sql = "SELECT * FROM chinhanh";
            return Json(db.get(sql), JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult Add(string maCN, string tenCN, string thanhpho)
        {
            string sql = $"INSERT INTO chinhanh(maCN, tenCN, thanhpho) VALUES('{maCN}', N'{tenCN}', '{thanhpho}')";
            db.exec(sql);
            return Json(new { success = true, message = "Thêm chi nhánh thành công!" });
        }

        [HttpPost]
        public JsonResult Update(string maCN, string tenCN, string thanhpho)
        {
            string sql = $"UPDATE chinhanh SET tenCN=N'{tenCN}', thanhpho='{thanhpho}' WHERE maCN='{maCN}'";
            db.exec(sql);
            return Json(new { success = true, message = "Cập nhật chi nhánh thành công!" });
        }

        [HttpPost]
        public JsonResult Delete(string maCN)
        {
            string sql = $"DELETE FROM chinhanh WHERE maCN='{maCN}'";
            db.exec(sql);
            return Json(new { success = true, message = "Xóa chi nhánh thành công!" });
        }
    }
}
