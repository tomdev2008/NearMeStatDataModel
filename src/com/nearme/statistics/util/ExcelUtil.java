package com.nearme.statistics.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Blank;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

/**
 * Excel辅助类
 *
 * @author 80053813 罗勇
 *
 */
public class ExcelUtil {

	private static Logger log = Logger.getLogger(ExcelUtil.class);// 日志记录
	/**
	 * 临时文件存放目录
	 */
	public static final String EXCEL_FILE_TMP_DIR = System
			.getProperty("user.home")
			+ File.separator + "tmp" + File.separator;

	private File file = null;
	private WritableWorkbook wb = null;
	private WritableSheet[] writeSheets = null;
	private int[] rowIndexs = null;// 记录每个工作簿已写入的行数
	private int[] cellIndexs = null;// 记录每个工作簿已写入的列数
	private int currSheetIndex = 0;// 记录当前工作簿索引
	private WritableFont currFont = null;// 当前字体
	private boolean deleteExists = true;// 目标文件存在时是否删除

	// 控制输出格式用的几个变量
	WritableCellFormat textCellFormat = new jxl.write.WritableCellFormat(
			getBodyFont(), NumberFormats.TEXT);
	NumberFormat numberFormat = new jxl.write.NumberFormat("#");
	WritableCellFormat numberCellFormat = new jxl.write.WritableCellFormat(
			numberFormat);

	/**
	 * 构造Excel （指定文件存在时执行删除操作）
	 *
	 * @param fileName
	 *            目标文件名（当文件名中不包含/\时,则文件放在默认临时存放目录中）
	 */
	public ExcelUtil(String fileName) {
		this(fileName, true);
	}

	/**
	 * 构造函数
	 *
	 * @param fileName
	 * @param deleteExists
	 */
	public ExcelUtil(String fileName, boolean deleteExists) {
		this.deleteExists = deleteExists;

		if (!fileName.contains("/") && !fileName.contains("\\")) {
			fileName = EXCEL_FILE_TMP_DIR + fileName;
		}
		this.file = new File(fileName);
		init(this.file);
	}

	/**
	 * 构造函数
	 *
	 * @param fileFullPath
	 *            目标文件路径
	 * @param templateFullPath
	 *            模板文件路径
	 * @throws IOException
	 * @throws BiffException
	 */
	public ExcelUtil(String fileFullPath, String templateFullPath)
			throws IOException, BiffException {
		// 获取模板
		Workbook rwb = Workbook.getWorkbook(new File(templateFullPath));
		// 目标文件
		this.wb = Workbook.createWorkbook(new File(fileFullPath), rwb);

		// 加载工作簿
		this.writeSheets = this.wb.getSheets();
		this.rowIndexs = new int[this.writeSheets.length];
		this.cellIndexs = new int[this.writeSheets.length];

		// 初始化光标
		for (int i = 0; i < this.writeSheets.length; i++) {
			this.rowIndexs[i] = 0;// 都是从第一行开始的，不是从0开始！
			this.cellIndexs[i] = 0;// 都是从第一列开始的，不是从0开始！
		}
	}

	/**
	 * 创建工作簿
	 *
	 * @param count
	 * @param sNames
	 * @return
	 * @throws Exception
	 */
	public boolean createSheets(int count, String[] sNames) throws Exception {
		if (count <= 0) {
			throw new Exception("Params is Null or not in range!");
		}
		String[] sheetNames = sNames;
		if (sNames == null) {
			sheetNames = new String[count];
			for (int i = 0; i < count; i++)
				sheetNames[i] = ("sheet" + i);
		} else if (sNames.length < count) {
			sheetNames = new String[count];
			for (int i = 0; i < sNames.length; i++)
				sheetNames[i] = sNames[i];
			for (int i = sNames.length; i < count; i++)
				sheetNames[i] = ("sheet" + i);
		}
		this.writeSheets = new WritableSheet[count];
		this.rowIndexs = new int[count];
		this.cellIndexs = new int[count];

		for (int i = 0; i < count; i++) {
			this.rowIndexs[i] = 0;// 都是从第一行开始的，不是从0开始！
			this.cellIndexs[i] = 0;// 都是从第一列开始的，不是从0开始！
			this.writeSheets[i] = this.wb.createSheet(sNames[i], i);
		}
		return true;
	}

	public int getSheetsCount() {
		return this.writeSheets.length;
	}

	/**
	 * 设置当前活动工作簿
	 *
	 * @param index
	 * @throws Exception
	 */
	public void setWorkingSheet(int index) throws Exception {
		if (this.writeSheets == null || this.writeSheets.length == 0) {
			throw new Exception("Worksheet is null or empty");
		}
		if (index >= this.writeSheets.length) {
			throw new Exception("index out of range");
		}
		this.currSheetIndex = index;
	}

	/**
	 * 设置光标
	 *
	 * @param row
	 * @param cell
	 */
	public void setLocation(int row, int cell) {
		this.rowIndexs[this.currSheetIndex] = row;
		this.cellIndexs[this.currSheetIndex] = cell;
	}

	private WritableSheet getWorkingSheet() {
		return this.writeSheets[this.currSheetIndex];
	}

	/**
	 * 设置字体
	 *
	 * @param font
	 */
	public void setFont(WritableFont font) {
		this.currFont = font;
		if (font != null) {
			textCellFormat = new jxl.write.WritableCellFormat(font,
					NumberFormats.TEXT);
			numberCellFormat.setFont(font);
		}
	}

	@SuppressWarnings("unused")
	private WritableFont getFont() {
		if (this.currFont == null) {
			return getBodyFont();
		}
		return this.currFont;
	}

	/**
	 * 读取内容
	 *
	 * @param row
	 * @param cell
	 * @return
	 */
	public String readContent(int row, int cell) {
		return this.wb.getSheet(this.currSheetIndex).getCell(cell, row)
				.getContents();
	}

	/**
	 * 读取内容
	 *
	 * @param row
	 * @param startCell
	 * @param len
	 * @return
	 */
	public String[] readContent(int row, int startCell, int len) {
		String[] result = new String[len];
		for (int i = 0; i < len; i++) {
			result[i] = this.wb.getSheet(this.currSheetIndex).getCell(
					startCell + i, row).getContents().trim();
		}
		return result;
	}

	/**
	 * 写入内容
	 *
	 * @param content
	 */
	public void writeLine(String content) {
		writeLine(new String[] { content });
	}

	/**
	 * 写入内容
	 *
	 * @param contents
	 */
	public void writeLine(String[] contents) {
		if (contents == null) {
			contents = new String[] { "" };
		}

		for (int i = 0; i < contents.length; i++) {// 循环添加列表头
			// 设置单元格宽度
			// getWorkingSheet().setColumnView(i, 20);
			try {
				// 设置单元格内容，第一个字段表示列，第二表示行
				if (isInteger(contents[i])) {
					jxl.write.Number numberC = new jxl.write.Number(i
							+ this.cellIndexs[this.currSheetIndex],
							rowIndexs[currSheetIndex], Integer
									.parseInt(contents[i]), numberCellFormat);
					getWorkingSheet().addCell(numberC);
				} else {
					jxl.write.Label labelC = new jxl.write.Label(i
							+ this.cellIndexs[this.currSheetIndex],
							rowIndexs[currSheetIndex], contents[i],
							textCellFormat);
					// 将生成的一个单元格添加到工作簿的单元格集合中
					getWorkingSheet().addCell(labelC);
				}
			} catch (Exception exp) {

			}
		}
		rowIndexs[currSheetIndex]++;
	}

	/**
	 * 写入内容
	 *
	 * @param contents
	 */
	public void writeLines(List<String[]> contents) {
		for (String[] content : contents) {
			writeLine(content);
		}
	}

	/**
	 * 合并单元格
	 *
	 * @param column
	 *            起始格列索引,从0开始
	 * @param cell
	 *            起始行索引,从0开始
	 * @param column1
	 *            终止列索引,从0开始
	 * @param cell1
	 *            终止列索引,从0开始
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void mergeCells(int column, int cell, int column1, int cell1)
			throws RowsExceededException, WriteException {
		if (getWorkingSheet() != null) {
			if (column == column1 && cell == cell1) {
				return;// 啥都没做
			}
			// 强制清除被合并单元格的数据
			for (int i = column; i <= column1; i++) {
				for (int j = cell; j <= cell1; j++) {
					if (i == column && j == cell) {
						continue;// 这个的需要保留
					}
					jxl.write.Blank blank = new Blank(i, j);
					getWorkingSheet().addCell(blank);
				}
			}
			getWorkingSheet().mergeCells(column, cell, column1, cell1);
		}
	}

	/**
	 * 结束写入,释放资源
	 */
	public void endWrite() {
		try {
			currFont = null;
			// 写入文件
			wb.write();
			// 关闭工作簿
			wb.close();
		} catch (Exception exp) {
			log.error(exp.getMessage(), exp);
		}
	}

	/**
	 * 初始化函数
	 *
	 * @param file
	 */
	private void init(File file) {
		try {
			if (file.exists() && deleteExists) {
				file.delete();
			}
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			this.wb = Workbook.createWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void export(HttpServletResponse response) throws IOException {
		exportFile(response, this.file, this.file.getName());
	}

	public static WritableFont getTitleFont() {
		return new jxl.write.WritableFont(WritableFont.ARIAL, 15,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
	}

	public static WritableFont getBodyFont() {
		return new jxl.write.WritableFont(WritableFont.ARIAL, 13,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
	}

	public static void exportFile(HttpServletResponse response, File file,
			String fileName, boolean delete) throws IOException {
		// 取得输出流
		OutputStream out = response.getOutputStream();
		// 清空输出流
		response.reset();
		// 设置响应头和下载保存的文件名
		response.setHeader("content-disposition", "attachment;filename="
				+ toUtf8String(fileName)
				+ (fileName.endsWith(".xls") ? "" : ".xls"));
		// 定义输出类型
		response.setContentType("application/msexcel");

		// 读取临时文件
		FileInputStream finput = new FileInputStream(file);
		BufferedInputStream buffin = new BufferedInputStream(finput);
		// 转换为输出流
		BufferedOutputStream buffout = new BufferedOutputStream(out);
		// 定义中间存储的字节数组
		byte[] buffer = new byte[4096];
		int count = 0;
		// 将文件字节数据读入中间存储字节数组，在从字节数组中写入输出流
		while ((count = buffin.read(buffer, 0, buffer.length)) > 0) {
			buffout.write(buffer, 0, count);
		}

		// 关闭输入流
		finput.close();
		buffin.close();
		// 关闭输出流
		buffout.close();
		out.close();

		if (delete) {
			// 删除临时文件
			if (!file.delete()) {
				log.error("删除EXCEL临时文件失败!");
			}
		}
		// 输出缓存
		response.flushBuffer();
	}

	public static void exportFile(HttpServletResponse response, File file,
			String fileName) throws IOException {
		exportFile(response, file, fileName, true);
	}

	/**
	 * 将字符转换成UTF-8的字符
	 *
	 * @param s
	 *            待转换的字符串
	 * @return 转换后的字符串
	 */
	public static String toUtf8String(String s) {
		StringBuilder sb = new StringBuilder(256);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	private static boolean isInteger(String input) {
		if (input == null || (input.startsWith("0") && input.length() > 1)) {
			return false;
		}
		return StringUtil.isMatch(input, "^\\d+$");
	}

	/*
	 * sample code
	 */
	public static void main(String[] args) throws Exception {

		ExcelUtil excel = new ExcelUtil("c:/testExcel.xls");
		excel.createSheets(2, new String[] { "shit1", "shit2" });
		// excel.setWorkingSheet(0);//default=0
		excel.writeLine("Hello world");
		excel.setFont(ExcelUtil.getBodyFont());
		excel.writeLine(new String[] { "ahfdsjakl", "5689", "#$%^&*()" });
		excel.setWorkingSheet(1);
		excel.setFont(ExcelUtil.getTitleFont());
		excel.writeLine(new String[] { "1", "2", "3", "4", "5" });
		excel.setFont(ExcelUtil.getBodyFont());
		excel.writeLine(new String[] { "11", "22", "33", "44", "55" });
		excel.setWorkingSheet(0);
		excel.setFont(ExcelUtil.getTitleFont());
		excel.writeLine(new String[] { "11", "22", "33", "44", "55" });
		excel.mergeCells(2, 2, 11, 4);
		excel.endWrite();

//		ExcelUtil excel = new ExcelUtil("c:/tmp/567.xls",
//				"c:/tmp/AllDefaultReport.xls");
//		for (int i = 0; i < 10; i++) {
//			System.out.print(i + ":" + excel.readContent(i, 3));
//			System.out.println("\r\n");
//		}
//		excel.setLocation(6, 6);
//		excel.writeLine("hello God");
//
//		for (int i = 2; i < 10; i++) {
//			for (int j = 2; j < 10; j++) {
//				excel.setLocation(i, j);
//				excel.writeLine("ppsition:" + i + "," + j);
//			}
//		}
//		excel.endWrite();
	}
}
