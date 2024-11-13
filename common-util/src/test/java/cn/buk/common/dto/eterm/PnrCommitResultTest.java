package cn.buk.common.dto.eterm;

import cn.buk.common.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 14-8-30
 * Time: 下午11:12
 * To change this template use File | Settings | File Templates.
 */
public class PnrCommitResultTest {

    private PnrDto pnr;
    private Date opTime;

    @BeforeEach
    public void setUp() throws Exception {
        pnr = new PnrDto();
        opTime = DateUtil.convertToDateTime("2014-06-10 10:08:04");
    }

    @Test
    public void testPnrCommitResult1() {
        String pnrDetail = "JTD1KW -EOT SUCCESSFUL, BUT ASR UNUSED FOR 1 OR MORE SEGMENTS   \n" +
                "  CZ3901  Z TH04SEP  PEKKMG DK1   0910 1230 \n" +
                "  航空公司使用自动出票时限, 请检查PNR   \n" +
                " ";


        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("JTD1KW", pnr.getPnrNo());
        assertEquals(1, pnr.getSegCount());
    }

    @Test
    public void testPnrCommitResult2() {
        String pnrDetail = "  CZ6210  V SA13SEP  PVGHRB HK3   1400 1705 \n" +
                "  CZ6251  Z FR19SEP  HRBPVG HK3   1510 1805 \n" +
                "JN38QH -   航空公司使用自动出票时限, 请检查PNR  \n" +
                "  ";

        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("JN38QH", pnr.getPnrNo());
        assertEquals(2, pnr.getSegCount());
    }

    @Test
    public void testPnrCommitResult3() {
        String pnrDetail = "  CA 967  H MO07JUL  PVGMXP RR1   0130 0805 \n" +
                "  CA 968  V TH10JUL  MXPPVG HK1   1230 0550+1   \n" +
                "HPETK1  \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***   ";

        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("HPETK1", pnr.getPnrNo());
        assertEquals(2, pnr.getSegCount());
    }


    @Test
    public void testPnrCommitResult4() {
        String pnrDetail = "  NX 110  L FR27JUN  MFMSHA DK1   1040 1255 \n" +
                "HMC4SH  \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***  ";

        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("HMC4SH", pnr.getPnrNo());
        assertEquals(1, pnr.getSegCount());
    }

    @Test
    public void testPnrCommitResult5() {
        String pnrDetail = " *MU8984  N TU01JUL  PVGHKG DK1   0755 1020 \n" +
                "HT4B46 -EOT SUCCESSFUL, BUT ASR UNUSED FOR 1 OR MORE SEGMENTS   \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***";

        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("HT4B46", pnr.getPnrNo());
        assertEquals(1, pnr.getSegCount());
    }

    @Test
    public void testPnrCommitResult6() {
        String pnrDetail = "  CZ 313  W FR11JUL  PVGICN HK1 \n" +
                "  CZ 314  L TU15JUL  ICNPVG HK1   1200 1300 \n" +
                "HRF03W  \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***";

        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("HRF03W", pnr.getPnrNo());
        assertEquals(2, pnr.getSegCount());
    }

    @Test
    public void testPnrCommitResult7() {
        String pnrDetail = "  CZ3524  R TH28AUG  SHACAN RR2   1145 1410 \n" +
                "  CZ 387  U TH28AUG  CANCGK RR2   1700 2050 \n" +
                "  CZ 388  M SA13SEP  CGKCAN HK2   0905 1505 \n" +
                "  CZ3547  R SA13SEP  CANSHA HK2   1900 2120 \n" +
                "HRZP98  ";

        try {
            opTime = DateUtil.convertToDateTime("2014-09-04 12:50:35");
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("HRZP98", pnr.getPnrNo());
        assertEquals(4, pnr.getSegCount());
        assertEquals("2014-09-13", DateUtil.formatDate(pnr.getMaxDepartureDate(),"yyyy-MM-dd"));
    }

    @Test
    public void testPnrCommitResult8() {
        String pnrDetail = "  QF 130  M MO20OCT  PVGSYD DK2   1955 0930+1   \n" +
                "  QF 129  S SU16AUG  SYDPVG DK2   0935 1830 \n" +
                "JEBD8G  \n" +
                "  \n";

        try {
            opTime = DateUtil.convertToDateTime("2014-09-26 17:32:49");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        pnr.setOpTime(opTime);
        pnr.setPnrCommitResult(pnrDetail);
        assertEquals("JEBD8G", pnr.getPnrNo());
        assertEquals(2, pnr.getSegCount());
        assertEquals("2014-10-20", DateUtil.formatDate(pnr.getMinDepartureDate(),"yyyy-MM-dd"));
        assertEquals("2015-08-16", DateUtil.formatDate(pnr.getMaxDepartureDate(),"yyyy-MM-dd"));
    }


}
