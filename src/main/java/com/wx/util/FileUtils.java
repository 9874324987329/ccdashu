package com.wx.util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileUtils {
    protected static final Log log = LogFactory.getLog(FileUtils.class);

    public static String readFileString(String file) {
        String contents = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String lineStr = null;
            while ((lineStr = br.readLine()) != null) {
                lineStr = lineStr.trim();
                contents += lineStr;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }


    public static List<String> readFile(File file) {
        List<String> contents = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String lineStr = null;
            while ((lineStr = br.readLine()) != null) {
                lineStr = lineStr.trim();
                contents.add(lineStr);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return contents;
    }

    //<userid,uuid>
    public static Map<Integer, String> readFileMap(File file) {
        Map<Integer, String> contents = new HashMap<Integer, String>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String lineStr = null;
            while ((lineStr = br.readLine()) != null) {
                lineStr = lineStr.trim();
                //uuid,userid
                String[] arr = lineStr.split(",");
                if (arr.length != 2) {
                    continue;
                }
                contents.put(Integer.valueOf(arr[1].trim()), arr[0].trim());
            }
            br.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    public static void writeIntoFile(File file, String content, boolean append) {

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
        }
    }

    public static void writeIntoFile(File file, String content) {

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
        }
    }

    public static boolean writeExcel(List<String> list, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            WritableWorkbook wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("Sheet1", 0);
            for (int i = 0; i < list.size(); i++) {
                Label label = new Label(0, i, list.get(i));
                ws.addCell(label);
            }
            wwb.write();
            wwb.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public static void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

    public static boolean createFile(File file) throws IOException {
        if (!file.exists()) {
            makeDir(file.getParentFile());
        }
        return file.createNewFile();
    }

    public static void copyFile(File file,File dest){
        try {
            org.apache.commons.io.FileUtils.copyFile(file, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        String str="帅丰集成灶 V5-3-70###13@@@集成灶V5-3-70款###1827@@@集成灶V5-3-90款###1828@@@集成灶UX8-3-76款###1829@@@集成灶UX8-3B-76款###1830@@@集成灶UX8-3-76F35玻璃款###1831@@@集成灶UX8-3B-76F35玻璃款###1832@@@集成灶UX8-3-90款###1833@@@集成灶UX8-3B-90款###1834@@@集成灶UX8-3-90F35玻璃款###1835@@@集成灶UX8-3B-90F35玻璃款###1836@@@集成灶UX8-3-B款###1837@@@集成灶UX8-3B-B款###1838@@@集成灶UX8-3-F35B玻璃款###1839@@@集成灶UX8-3B-F35B玻璃款###1840@@@集成灶UX8-3-A款###1841@@@集成灶UX8-3B-A款###1842@@@集成灶UX8-3-F35A玻璃款###1843@@@集成灶UX8-3B-F35A玻璃款###1844@@@集成灶UX8-8款###1845@@@集成灶UX8-8B款###1846@@@集成灶UX8-8-F35玻璃款###1847@@@集成灶UX8-8B-F35玻璃款###1848@@@集成灶UX8-9款###1849@@@集成灶UX8-9B款###1850@@@集成灶UX8-9-F35玻璃款###1851@@@集成灶UX8-9B-F35玻璃款###1852@@@集成灶FU2-3B-90###1853@@@集成灶U80-3###1854@@@集成灶U90-3###1855@@@集成水槽JS72###1856@@@集成水槽JS90###1857@@@集成水槽900晶爱###1858@@@墨林不锈钢单槽套餐1###7781@@@墨林不锈钢单槽套餐2###7782@@@墨林不锈钢单槽套餐3###7783@@@墨林不锈钢单槽套餐4###7784@@@墨林不锈钢单槽套餐5###7785@@@墨林不锈钢单槽套餐6###7786@@@墨林不锈钢单槽套餐7###7787@@@墨林不锈钢单槽套餐8###7788@@@墨林不锈钢单槽套餐9###7789@@@墨林不锈钢单槽套餐10###7790@@@墨林不锈钢单槽套餐11###7791@@@墨林不锈钢单槽套餐12###7792@@@墨林不锈钢单槽套餐13###7793@@@墨林不锈钢单槽套餐14###7794@@@墨林不锈钢单槽套餐15###7795@@@墨林不锈钢单槽套餐16###7796@@@墨林不锈钢单槽套餐17###7797@@@墨林不锈钢单槽套餐18###7798@@@墨林不锈钢单槽套餐19###7799@@@墨林不锈钢单槽套餐20###7800@@@墨林不锈钢单槽套餐21###7801@@@墨林不锈钢单槽套餐22###7802@@@墨林不锈钢单槽套餐23###7803@@@墨林不锈钢单槽套餐24###7804@@@墨林不锈钢单槽套餐25###7805@@@墨林不锈钢单槽套餐26###7806@@@墨林不锈钢单槽套餐27###7807@@@墨林不锈钢单槽套餐28###7808@@@墨林不锈钢单槽套餐29###7809@@@墨林不锈钢单槽套餐30###7810@@@墨林不锈钢单槽套餐31###7811@@@墨林不锈钢单槽套餐32###7812@@@墨林淋浴柱花洒1###7813@@@墨林淋浴柱花洒2###7814@@@墨林淋浴柱花洒3###7815@@@墨林淋浴柱花洒4###7816@@@墨林淋浴柱花洒5###7817@@@墨林淋浴柱花洒6###7818@@@墨林台盆龙头1###7819@@@墨林台盆龙头2###7820@@@墨林台盆龙头3###7821@@@墨林台盆龙头4###7822@@@墨林除铅净水器###7823@@@墨林厨房龙头1###7824@@@墨林厨房龙头2###7825@@@墨林厨房龙头3###7826@@@墨林厨房龙头4###7827@@@墨林厨房龙头5###7828@@@墨林厨房龙头6###7829@@@墨林厨房龙头7###7830@@@墨林厨房龙头8###7831@@@墨林厨房龙头9###7832@@@墨林厨房龙头10###7833@@@墨林厨房龙头11###7834@@@墨林皂液器1###7835@@@墨林皂液器2###7836@@@墨林水槽套餐###13299@@@A.O.史密斯小厨宝6L1###3241@@@A.O.史密斯小厨宝6L2###3242@@@A.O.史密斯小厨宝10L3###3243@@@A.O.史密斯小厨宝10L4###3244@@@A.O.史密斯电热水器50L5###3245@@@A.O.史密斯电热水器50L6###3246@@@A.O.史密斯电热水器60L7###3247@@@A.O.史密斯电热水器80L8###3248@@@A.O.史密斯电热水器40L9###3249@@@A.O.史密斯电热水器50L10###3250@@@A.O.史密斯电热水器60L11###3251@@@A.O.史密斯电热水器80L12###3252@@@A.O.史密斯电热水器50L13###3253@@@A.O.史密斯电热水器60L14###3254@@@15###3255@@@A.O.史密斯电热水器50L16###3256@@@A.O.史密斯电热水器60L17###3257@@@A.O.史密斯电热水器80L18###3258@@@A.O.史密斯电热水器100L19###3259@@@A.O.史密斯电热水器50L20###3260@@@A.O.史密斯电热水器60L21###3261@@@A.O.史密斯电热水器80L22###3262@@@A.O.史密斯电热水器100L23###3263@@@A.O.史密斯电热水器50L24###3264@@@A.O.史密斯电热水器60L25###3265@@@A.O.史密斯电热水器60L26###3266@@@A.O.史密斯电热水器60L27###3267@@@A.O.史密斯电热水器80L28###3268@@@A.O.史密斯电热水器100L29###3269@@@A.O.史密斯电热水器50L30###3270@@@A.O.史密斯电热水器60L31###3271@@@A.O.史密斯电热水器60L32###3272@@@A.O.史密斯电热水器60L33###3273@@@A.O.史密斯电热水器80L34###3274@@@A.O.史密斯电热水器100L35###3275@@@A.O.史密斯电热水器50L36###3276@@@A.O.史密斯电热水器60L37###3277@@@A.O.史密斯电热水器60L38###3278@@@A.O.史密斯电热水器80L39###3279@@@A.O.史密斯电热水器100L40###3280@@@A.O.史密斯燃气热水器28L41###3281@@@A.O.史密斯燃气热水器20L42###3282@@@A.O.史密斯燃气热水器11L43###3283@@@A.O.史密斯燃气热水器12L44###3284@@@A.O.史密斯燃气热水器16L45###3285@@@A.O.史密斯燃气热水器11L46###3286@@@A.O.史密斯燃气热水器12L47###3287@@@A.O.史密斯燃气热水器13L48###3288@@@A.O.史密斯燃气热水器16L49###3289@@@A.O.史密斯燃气热水器11L50###3290@@@A.O.史密斯燃气热水器12L51###3291@@@A.O.史密斯燃气热水器13L52###3292@@@A.O.史密斯燃气热水器16L53###3293@@@A.O.史密斯燃气热水器11L54###3294@@@A.O.史密斯燃气热水器12L55###3295@@@A.O.史密斯燃气热水器13L56###3296@@@A.O.史密斯燃气热水器16L57###3297@@@A.O.史密斯燃气热水器12L58###3298@@@A.O.史密斯燃气热水器10L60###3299@@@A.O.史密斯燃气热水器11L61###3300@@@A.O.史密斯燃气热水器12L62###3301@@@前置过滤器1###3302@@@前置过滤器2###3303@@@前置过滤器3###3304@@@中央净水机4###3305@@@中央净水机5###3306@@@软水机1###3307@@@软水机2###3308@@@超大流量反渗透###3309@@@超薄一体式MAX3.0###3310@@@长效反渗透直饮水机1###3311@@@长效反渗透直饮水机2###3312@@@长效反渗透直饮水机3###3313@@@长效反渗透直饮水机4###3314@@@全抛一体反渗透5###3315@@@轻型商用净饮一体机6###3316@@@轻型商用净饮一体机7###3317@@@家用净饮一体机8###3318@@@家用净饮一体机9###3319@@@A.O.史密斯家庭中央电热水器120L63###3320@@@A.O.史密斯家庭中央电热水器150L64###3321@@@A.O.史密斯家庭中央电热水器190L65###3322@@@A.O.史密斯家庭中央电热水器120L66###3323@@@A.O.史密斯家庭中央电热水器150L67###3324@@@A.O.史密斯家庭中央电热水器190L68###3325@@@A.O.史密斯家庭中央电热水器55L69###3326@@@A.O.史密斯家庭中央电热水器75L70###3327@@@A.O.史密斯家庭中央电热水器100L71###3328@@@A.O.史密斯家庭中央电热水器190L72###3329@@@A.O.史密斯家庭中央电热水器300L73###3330@@@A.O.史密斯家庭中央电热水器455L74###3331@@@A.O.史密斯燃气中央热水机100L75###3332@@@家庭中央燃气热水器110L###3333@@@家庭中央燃气热水器150L###3334@@@家庭中央燃气热水器190L###3335@@@家庭中央燃气热水器285L###3336@@@家庭中央燃气热水器380L###3337@@@美大集成灶—飞月JCZH-1202F###21@@@时尚先锋###5060@@@博爱经典###5061@@@博爱时尚###5062@@@豪华双喜###5063@@@豪华之星###5064@@@飞月###5065@@@飞天###5066@@@飞弧###5067@@@750###5068@@@喜临门（不带蒸箱）###5069@@@喜临门（电烤箱）###5070@@@喜临门（电蒸箱）###5071@@@大富豪###5072@@@美大-飞虹###12263@@@美大-飞虹（烤箱）###12266@@@美大-时尚米兰###12267@@@集成水槽801###12268@@@集成水槽9035###12269@@@集成水槽101###12270@@@亿田专用清洁剂###110@@@亿田集成灶B15###13360@@@亿田集成灶B5 ###13361@@@亿田集成灶B6###13362@@@亿田集成灶F1###13363@@@亿田集成灶F2###13364@@@亿田集成灶A1###13365";
//
//        for(String s :str.split("@@@")){
//            String param2 = "ABCDE";
//            String param3 = String.format("%08d", Integer.parseInt(s.split("###")[1]));
//            String qrValue = "http://www.51jiabo.com/qr?c=";
//            qrValue += "02" + param2 + param3;
//            CodeUtil.generateCodeStream(qrValue,s.split("###")[0] , 400, 400);
//        }
//        System.out.println("完成");
    }
}
