package lazi.oracle.common.giatri;

import inet.util.constant.CommonConst;
import inet.util.my.ConsoleLogger;
import inet.util.string.StringUtil;
import java.util.List;
import javax.persistence.Table;
import lazi.model.common.DmDmucGtri;
import lazi.oracle.adapter.OracleBaseDAO;
import org.hibernate.Query;

/**
 *
 * @author professional
 */
public class DmDmucGtriDAO extends OracleBaseDAO {

    private static class DmucGtriDAOHelper {
        private static final DmDmucGtriDAO instance = new DmDmucGtriDAO();
    }

    public static DmDmucGtriDAO getInstance() {
        return DmucGtriDAOHelper.instance;
    }

    public DmDmucGtriDAO() {
        table = "DmDmucGtri";
        tableRealName=DmDmucGtri.class.getAnnotation(Table.class).name();
    }

    /**
     * trả về danh sách các giá trị của 1 mã danh mục loại
     * @param maDviQly
     * @param maDmucLoai
     * @return 
     */
    public List<DmDmucGtri> getDmucGtri(String maDviQly, String maDmucLoai) {
        List<DmDmucGtri> list = null;
        try {
            openSession();
            Query query = session.createQuery("from " + table + " c where c.maDmucLoai = :maDmucLoai and c.maDviQly = :maDviQly and c.tthaiBghi = :tthaiBghi and c.tthaiNvu = :tthaiNvu")
                    .setString("maDmucLoai", maDmucLoai)
                    .setString("maDviQly", maDviQly)
                    .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                    .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
            list = query.list();
            return list;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmDmucGtriDAO > getDmucGtri > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public List<DmDmucGtri> getDmucGtri(String maDviQly, String maDmucLoai,String tthaiBghi) {
        List<DmDmucGtri> list = null;
        try {
            openSession();
            if(StringUtil.isNullOrEmpty(tthaiBghi)){
                Query query = session.createQuery("from " + table + " c where c.maDmucLoai = :maDmucLoai and c.maDviQly = :maDviQly  and c.tthaiNvu = :tthaiNvu")
                    .setString("maDmucLoai", maDmucLoai)
                    .setString("maDviQly", maDviQly)
                    .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                list = query.list();
            }else{
                Query query = session.createQuery("from " + table + " c where c.maDmucLoai = :maDmucLoai and c.maDviQly = :maDviQly and upper(c.tthaiBghi) = upper(:tthaiBghi) and c.tthaiNvu = :tthaiNvu")
                    .setString("maDmucLoai", maDmucLoai)
                    .setString("maDviQly", maDviQly)
                    .setString("tthaiBghi", tthaiBghi)
                    .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                list = query.list();
            }
            
            return list;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmDmucGtriDAO > getDmucGtri > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    @Override
    public List<DmDmucGtri> findList(){
        List<DmDmucGtri> list = null;
        try {
            openSession();
            Query q=session.createQuery("from "+table+" where tthaiBghi=:tthaiBghi and tthaiNvu =:tthaiNvu")
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
            list = q.list();
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmDmucGtriDAO > findAll > ", e);
        } finally {
            closeSession();
        }
        return list;
    }
    
    public List<DmDmucGtri> findAll(String maDviQly,String txtSearch,long  idDmucLoai){
        List<DmDmucGtri> list = null;
        try {
            openSession();
            if(session!=null){
                if(StringUtil.isNullOrEmpty(txtSearch)){
                    Query q=session.createQuery("from "+table+" where idDmucLoai=:idDmucLoai and  tthaiBghi=:tthaiBghi and tthaiNvu =:tthaiNvu and maDviQly =:maDviQly")
                        .setLong("idDmucLoai", idDmucLoai)
                        .setString("maDviQly", maDviQly)
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                
                    list = q.list();
                }else{
                     Query q=session.createQuery("from "+table+" where idDmucLoai=:idDmucLoai and tthaiBghi=:tthaiBghi and tthaiNvu =:tthaiNvu and maDviQly =:maDviQly "
                             + "and (upper(maDmuc) like upper(:maDmuc) or upper(tenDmuc) like upper(:tenDmuc))")
                        .setLong("idDmucLoai", idDmucLoai)
                        .setString("maDviQly", maDviQly)
                        .setString("maDmuc", "%"+txtSearch+"%")
                        .setString("tenDmuc", "%"+txtSearch+"%")     
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                
                    list = q.list();               
                    
                }
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmDmucGtriDAO > findAll > ", e);
        }finally{
            closeSession();
        }
        
        return list;
    }
    
    public boolean checkExited(String maDviQly, String ma,String ten,long id){
        boolean result=false;
        try {
            openSession();
            if(session!=null){
                Query q=session.createQuery("from "+table+" where  maDviQly =:maDviQly and (maDmuc=:maDmuc or tenDmuc=:tenDmuc ) and id!=:id")
                        .setString("maDviQly", maDviQly)
                        .setString("maDmuc", ma)
                        .setString("tenDmuc", ten)
                        .setLong("id", id);
                
                List<DmDmucGtri> list= q.list();
                if(list!=null&&!list.isEmpty()){result=true;}
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmDmucGtriDAO > checkExited > ", e);
        }finally{
            closeSession();
        }
        
        return result;
    }
    
    public boolean checkChangeCode(String maDviQly, String ma,long id){
        boolean result=false;
        try {
            openSession();
            if(session!=null){
                Query q=session.createQuery("from "+table+" where  maDviQly =:maDviQly and maDmuc=:maDmuc  and id=:id")
                        .setString("maDviQly", maDviQly)
                        .setString("maDmuc", ma)
                        .setLong("id", id);
                
                List<DmDmucGtri> list= q.list();
                if(list==null||list.isEmpty()){result=true;}
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmDmucGtriDAO > checkChangeCode > ", e);
        }finally{
            closeSession();
        }
        
        return result;
    }
    
    
    public static void main(String[] args) {
        DmDmucGtriDAO dao = new DmDmucGtriDAO();
        List<DmDmucGtri> list = dao.findList();
        System.out.println("");
        if(list != null) {
            System.out.println(list.size());
//            for(DmDmucGtri dvi : list) {
//                System.out.println("dvi= "+ dvi.getTenDmuc() + ", " + dvi.getMaDmuc());
//            }
        }
    }

}
