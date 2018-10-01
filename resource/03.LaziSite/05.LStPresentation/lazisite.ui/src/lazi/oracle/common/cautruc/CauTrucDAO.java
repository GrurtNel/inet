package lazi.oracle.common.cautruc;

import inet.util.string.StringUtil;
import java.util.List;
import java.util.Map;
import lazi.model.common.DcCtruc;
import lazi.model.common.DcCtrucCtiet;
import org.hibernate.Session;

/**
 *
 * @author professional
 */
public class CauTrucDAO {
    
    public static String updateCodeRecord(String table, String column, String prefix, String maDviQly, int minSuffix, long id) throws Exception{
        if (id <= 0 || StringUtil.isNullOrEmpty(table) || StringUtil.isNullOrEmpty(column) || StringUtil.isNullOrEmpty(prefix)) return null;
        return DCCauTrucDAO.getInstance().updateCode(table, column, prefix, maDviQly, minSuffix, id);
    }
    
    public static String updateCodeRecord(String table, String column, String prefix, String maDviQly, int minSuffix, long id, Session session) throws Exception{
        if (id <= 0 || StringUtil.isNullOrEmpty(table) || StringUtil.isNullOrEmpty(column) ) return null;//|| StringUtil.isNullOrEmpty(prefix)
        return DCCauTrucDAO.getInstance().updateCode(table, column, prefix, maDviQly, minSuffix, id, session);
    }
    
    public static String getPrefix(String maDviQly, String maCauTruc, Map<String, Object> params) {
        
        if( StringUtil.isNullOrEmpty(maCauTruc) )
            return "";
        
        DcCtruc cautruc = null;
        if( maDviQly != null )
            cautruc = DCCauTrucDAO.getInstance().getRow(maCauTruc, maDviQly);
        else
            cautruc = DCCauTrucDAO.getInstance().getRow(maCauTruc);
        
        if(cautruc != null) {
            System.out.println("cautruc>>>"+cautruc.getId());
            List<DcCtrucCtiet> lsCauTrucCt = DCCauTrucCTietDAO.getInstance().findCauTrucChiTiet(cautruc.getId());
            if(lsCauTrucCt != null) {
                String maGiaoDich = "";
                String paramVal = null;
                for(DcCtrucCtiet ctChiTiet : lsCauTrucCt) {
                    if(ctChiTiet == null) continue;
//                    if("STT".equals(ctChiTiet.getMaTphan()) || "SO_THU_TU".equals(ctChiTiet.getMaTphan()) || "SO_TIEP_THEO".equals(ctChiTiet.getMaTphan())) {
//                        String giaTri = ctChiTiet.getGiaTri();
//                    }
                    if("HANG_SO".equals(ctChiTiet.getLoaiGtri())) {
                        maGiaoDich += ctChiTiet.getGiaTri();
                    } else if("THAM_CHIEU".equals(ctChiTiet.getLoaiGtri())) {
                        if(params != null) {
                            paramVal = (String) params.get(ctChiTiet.getGiaTri());
                            if(paramVal != null) {
                                maGiaoDich += paramVal;
                            }
                        }
                    }
                }
                return maGiaoDich;
            }
        }
        return "";
    }
    
}