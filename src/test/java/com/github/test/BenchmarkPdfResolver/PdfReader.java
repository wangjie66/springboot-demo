package com.github.test.BenchmarkPdfResolver;

import com.google.common.annotations.VisibleForTesting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : JieWang
 * @Date : Created in 2019年05月10日10:15
 * @Email : wangjie_hf@seczone.cn
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//        "classpath*:file/BenchmarkOWASPTop10.pdf"
//         })
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class PdfReader {

    public static Set commandInjection = new HashSet();
    public static Set ldapInjection = new HashSet();
    public static Set sqlInjection = new HashSet();
    public static Set xPathInjection = new HashSet();
    public static Set headerManipulation = new HashSet();
    public static Set xmlEntityExpansionInjection = new HashSet();
    public static Set crossSiteScripting = new HashSet();
    public static Set pathManipulation = new HashSet();
    public static Set privacyViolation = new HashSet();
    public static Set passwordManagement = new HashSet();
    public static Set weakEncryption = new HashSet();
    public static Set defaultSet = new HashSet();
    public static Set currentSet = new HashSet() ;


    @Test
    public  void xx(){
        //File f = new File(this.getClass().getResource("").getPath());
        //File f1 = new File(this.getClass().getResource("/").getPath());

        File pdfFile = new File(this.getClass().getResource("/file/BenchmarkOWASPTop10.pdf").getPath());
        //byte[] buffer = new byte[inputStream.available()];

         PDDocument document = null;
        try
        {
            // 方式一：
            /**
             InputStream input = null;
             input = new FileInputStream( pdfFile );
             //加载 pdf 文档
             PDFParser parser = new PDFParser(new RandomAccessBuffer(input));
             parser.parse();
             document = parser.getPDDocument();
             **/

            // 方式二：
            document=PDDocument.load(pdfFile);

            // 获取页码
            int pages = document.getNumberOfPages();

            // 读文本内容
            PDFTextStripper stripper=new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pages);
            String content = stripper.getText(document);
            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
            String line;
            String pattern = "BenchmarkTest(\\d+)(\\.java)";
            Pattern r = Pattern.compile(pattern);
            currentSet = defaultSet ;
            VulnerabilityContants[] s = VulnerabilityContants.values();

            while ( (line = br.readLine()) != null ) {
                if(!line.trim().equals("")){
                     for(int i=0;i<s.length;i++){
                         if(line.contains(s[i].value)){
                             getSet(s[i].value) ;
                             break;
                         }
                     }

                     Matcher m = r.matcher(line);
                     if(m.find()){
                          currentSet.add(m.group());
                     }
                }
            }
            System.out.println(ldapInjection);
            excel() ;
            System.out.println("okok");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private  void excel() throws Exception {
        //excel
        InputStream inputStream = new FileInputStream(this.getClass().getResource("/file").getPath()+"/1.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook (inputStream);
        //XSSFSheet  sheetAt1 = workbook.getSheetAt(1);
        //XSSFSheet sheetAt2 = workbook.getSheetAt(2);
        //XSSFSheet  sheetAt3 = workbook.getSheetAt(3);
        boolean flag = true ;
        for(int i =0 ; i <workbook.getNumberOfSheets() ; i++){
            XSSFSheet sheet = workbook.getSheetAt( i ) ;
            for (Row row : sheet) {
                flag = true ;
                if (row.getRowNum() == 0) {
                    continue;
                }
                String testname = row.getCell(0).getStringCellValue();
                String category = row.getCell(1).getStringCellValue();
                getSet(category) ;
                boolean real_vulnerability = row.getCell(2).getBooleanCellValue() ;
                Iterator it = currentSet.iterator();
                while (it.hasNext()){
                    String value = (String) it.next();
                    if(value.contains(testname)){
                        if(real_vulnerability){
                            row.createCell(4).setCellValue("发现");
                        }else if(!real_vulnerability){
                            row.createCell(4).setCellValue("误报");
                        }
                        flag = false ;
                        break;
                    }
                }
                if(flag){
                    if(real_vulnerability){
                        row.createCell(4).setCellValue("未发现");
                    }else if(!real_vulnerability){
                        row.createCell(4).setCellValue("未误报");
                    }
                }

            }
        }
        FileOutputStream excelFileOutPutStream = new FileOutputStream(this.getClass().getResource("/file").getPath()+"/2.xlsx");
        workbook.write(excelFileOutPutStream);
        excelFileOutPutStream.flush();
        excelFileOutPutStream.close();
    }

    private static Set getSet(String type){

        if(VulnerabilityContants.Command_Injection.value.equals(type)){
            currentSet  = commandInjection ;
        }else if(VulnerabilityContants.LDAP_Injection.value.equals(type)){
            currentSet  = ldapInjection ;
        }else if(VulnerabilityContants.SQL_Injection.value.equals(type)){
            currentSet  = sqlInjection ;
        }else if(VulnerabilityContants.XPath_Injection.value.equals(type)){
            currentSet  = xPathInjection ;
        }else if(VulnerabilityContants.Header_Manipulation.value.equals(type)){
            currentSet  = headerManipulation ;
        }else if(VulnerabilityContants.XML_Entity_Expansion_Injection.value.equals(type)){
            currentSet  = xmlEntityExpansionInjection ;
        }else if(VulnerabilityContants.Cross_Site_Scripting.value.equals(type)){
            currentSet  = crossSiteScripting ;
        }else if(VulnerabilityContants.Path_Manipulation.value.equals(type)){
            currentSet  = pathManipulation ;
        }else if(VulnerabilityContants.Privacy_Violation.value.equals(type)){
            currentSet  = privacyViolation ;
        }else if(VulnerabilityContants.Password_Management.value.equals(type)){
            currentSet  = passwordManagement ;
        }else if(VulnerabilityContants.Weak_Encryption.value.equals(type)){
            currentSet  = weakEncryption ;
        }


        if("cmdi".equals(type)){
            currentSet  = commandInjection ;
        }else if("sqli".equals(type)){
            currentSet  = sqlInjection ;
        }else if("crypto".equals(type)){
            currentSet  = weakEncryption ;
        }else if("ldapi".equals(type)){
            currentSet  = ldapInjection ;
        }else if("pathtraver".equals(type)){
            currentSet  = pathManipulation ;
        }else if("xpathi".equals(type)){
            currentSet  = xPathInjection ;
        }else if("xss".equals(type)){
            currentSet  = crossSiteScripting ;
        }


        return null ;
     }
}
