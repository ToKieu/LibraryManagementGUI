package sellBook.BUS;

import Book.DAO.BookDAO;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import sellBook.DAO.CTHDDao;
import sellBook.DTO.CTHD;

import java.util.ArrayList;
import java.util.List;

public class CTHDBus {
    private CTHDDao cthd ;

    public BookDAO book = new BookDAO();

    public CTHDBus() {
        this.cthd = new CTHDDao();
    }
    public List<CTHD> getDsCTHD(String ma){
        return cthd.getDsCTHD(ma);
    }

    public List<String> getAllMaHD(String id){
        return cthd.getMaHD(id);
    }

    public List<String> getAllMaSeries(String id){
        return cthd.getMaSeries(id);
    }
    public List<Double> getAllHeSo(String id){
        return cthd.getHeSo(id);
    }
    public List<CTHD> filterMaCTHD(String id,String maSeri){
        return cthd.locCTHD(id,maSeri);
    }

    public List<CTHD> filterHeSo(String id,double hs){
        return cthd.locHeSo(id,hs);
    }
    public List<CTHD> filterMaSeri(String id,String ma){
        return cthd.locMaSeri(id,ma);
    }
    public int remove(String maCTHD,String maSeri){
        return cthd.removeCTHD(maCTHD,maSeri);
    }
    public int insert(CTHD hd){
        return cthd.insertCTHD(hd);
    }
    public int update(CTHD hd){
        return cthd.updateCTHD(hd);
    }
    public String goiYTenSach(String maSeri){
        return cthd.goiYTenSach(maSeri);
    }

    public long tinhTienSach(String maHD,String maSeri){
        return cthd.tinhTienSach(maHD,maSeri);
    }
    public long tienSach(String maSeri){
        return cthd.layGiaSach(maSeri);
    }
    public int updateStatusBook(String MaSeri,String tt){
        return cthd.changeTrangThaiSach(MaSeri,tt);
    }

    public void xoaHD(String maHD){
        cthd.hiddenCTHD(maHD);
        ArrayList<CTHD> dsCthd = cthd.getDsCTHD(maHD);
        if(!dsCthd.isEmpty()){
            for (CTHD ct:dsCthd) {
                System.out.println("Hello");
                System.out.println(ct.getMa_series());
                book.changeTrangThaiSach(ct.getMa_series(),"AVAILABLE");
            }
        }

    }
    public DefaultCategoryDataset thongKeSlgSachBan(){
        return cthd.thongKeSLGSachBanTheoThang();
    }

    public DefaultPieDataset thongKeSoLoaiDcBan(){
        return cthd.thongKeSoLoaiSach();
    }
    public DefaultCategoryDataset thongKeSachBanTheoNam(int nam){
        return cthd.thongKeSachBanTheoNam(nam);
    }

    public static void main(String[] args) {
        CTHDBus t = new CTHDBus();
//        List<CTHD> ds = t.filterMaCTHD("1","2");
//        for(CTHD h:ds){
//            System.out.println(h.getMa_series());
//        }
        System.out.println(t.goiYTenSach("1"));
    }
}