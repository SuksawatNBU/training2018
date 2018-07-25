package util.file;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.bundle.BundleUtil;
import util.database.CCTConnection;
import util.date.DateUtil;
import util.encoding.EncodingUtil;
import util.type.DatabaseType.DbType;

import com.aspose.cells.PageSetup;
import com.aspose.cells.Workbook;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;


public class ExcelManagerUtil {
	public XSSFWorkbook addWaterMark(String username, String stationIP ,String file ,CCTConnection conn) throws Exception{
		//วันเวลา ให้ดึงผ่าน method
		try{
			com.aspose.cells.License license = new com.aspose.cells.License();
			//กรณี ที่มี license File ในการทำงาน
			if(ParameterConfig.getParameter().getReport().getPathLicense()!=null
				&& !ParameterConfig.getParameter().getReport().getPathLicense().equals("")){
				license.setLicense(ParameterConfig.getParameter().getReport().getPathLicense()+ "Aspose.Cells.lic");
			}


			//1. สร้างภาพ creatWaterMarkPicture โดยภาพที่ได้กลับมาเป็น Byte Array
			byte[] data = creatWaterMarkPicture(username ,stationIP ,conn);

			//2. ใช้ aspose อ่าน  excel file ขึ้นมา
			Workbook workbook = new Workbook(file);

			//3. add ภาพ ลงส่วน Header Excel
			for(int i=0; i<workbook.getWorksheets().getCount(); i++)
			{
				PageSetup pageSetup = workbook.getWorksheets().get(i).getPageSetup();
				pageSetup.setHeader(1, "&G");
				pageSetup.setHeaderPicture(1, data);
			}

			//4. write file excel
			workbook.save(file);
			workbook.dispose();

			//5. ใช้ POI อ่าน Excel ขึ้นมา
			File tmpFile = new File(file);
			FileInputStream workbookFile = new FileInputStream(tmpFile);
			XSSFWorkbook wb2 = new XSSFWorkbook(workbookFile);

			//6. ลบ sheet ที่เป็นลิขสิทธิ์ออก (get sheet จากชื่อ หรือ sheet สุดท้าย) ให้ตรวจสอบ file ที่ได้ก่อน
			int numOfSheets = wb2.getNumberOfSheets();
			//กรณี ไม่มีที่มี license File ในการทำงาน
			if(ParameterConfig.getParameter().getReport().getPathLicense()==null
				|| ParameterConfig.getParameter().getReport().getPathLicense().equals("")){
				wb2.removeSheetAt(numOfSheets - 1);
			}
			workbookFile.close();

			//7. ลบ Temp File ที่ใช้
			tmpFile.delete();

			return wb2;

		}catch(Exception e){
			throw e;
		}

	}
	private byte[] creatWaterMarkPicture(String username,String stationIP ,CCTConnection conn) throws Exception{
		ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.Message", new Locale("en","EN"));
		String curdate = DateUtil.getCurrentDateDB(conn.getConn(), "dd/mm/yyyy hh24:mi", DbType.ORA);
		//Path path = Paths.get("C:/waterreport.png");
		byte[] data = null;
		try {
			//data = Files.readAllBytes(path);

			 int w = 2400;
			 int h = 3600;
			 BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			 Graphics2D g = bi.createGraphics();

			 // 2) ระบายสีพื้นหลังรูปภาพเป็นสีขาว
			 g.setColor(Color.white);
			 g.fillRect(0, 0, w, h);


			 // 3) ข้อความที่จะเขียนลงไปที่รูปภาพ เช่น ชื่อผู้พิมพ์ วัน เวลา
			 String printByName = bundle.getString("printby") + " : " + username;
			 //String sdf = "Date/time : 21/10/2015 16:46";
			 String strDateTime = bundle.getString("datetime") + " : " + curdate;
			 String strStationBy = bundle.getString("station") + " : " + new EncodingUtil().encoding(stationIP);

			 // 3.1) เตรียมเมตริกซ์หมุน 30 องศา
			 AffineTransform tx = new AffineTransform();
			 tx.rotate(Math.toRadians(30), 0, 0); // หมุนตัวอักษรไป 30 องศ

			 // 3.2) ฟอนต์สำหรับอักษรบรรทัดแรก
			 Font font1 = new Font("Tahoma", Font.PLAIN, 12);
			 //Tahoma
			 Font rotatedFont1 = font1.deriveFont(tx);
			 g.setFont(rotatedFont1);
			 Color myColor = new Color(233, 232, 232); // สีเทา
			 g.setColor(myColor);


			 for (int x = 10; x < bi.getWidth(); x += 300 ) {
				 for (int y = 50; y < bi.getHeight(); y += 200) {
					 g.drawString(printByName, x, y); // วาดตัวอักษรบรรทัดแรก
					 g.drawString(strDateTime, x, y + 30);	// วาดตัวอักษรบรรทัดที่ 2
					 //g.drawString(strStationBy, x, y + 60);	// วาดตัวอักษรบรรทัดที่ 3
				 }
			 }

			 g.dispose();

			 // 4) แปลงรูปภาพไปเป็นข้อมูลไบต์แบบ JPEG
			 ByteArrayOutputStream bout = new ByteArrayOutputStream();
			 try {

				 ImageIO.write(bi, "PNG", bout);
				 bout.flush();
				 data = bout.toByteArray();
				 bout.close();

				 //FileOutputStream fileOutput = new FileOutputStream("D:/PIC/imageSmall.png");
				 //fileOutput.write(raw);
				 //fileOutput.close();

			 } catch (IOException e) {
				 e.printStackTrace();
			 }


		} catch (Exception e) {
			throw e;
		}
		return data;
	}

}
