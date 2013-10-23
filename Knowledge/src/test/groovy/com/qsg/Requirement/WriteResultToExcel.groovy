import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableCell
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableWorkbook
import jxl.CellType
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import groovy.sql.*

class WriteResultToExcelFuncSpec{

	static main (args) {
		WriteResultToExcelFuncSpec obj = new WriteResultToExcelFuncSpec()
		obj.WriteToExcel("1","text","Pass","",10,false,"C:\\Workspace\\git\\Knowledge\\ExcelFiles\\Result.xls","firefox","10/10/2013","2733","5643")
		obj.WriteToExcel("123","text","Pass","Null",202,true,"C:\\Workspace\\git\\Knowledge\\ExcelFiles\\Result.xls","firefox","10/10/2013","2733","5643")
	}

	def WriteToExcel(varTCId,varTO,  varResult,varReason, double timeTaken , boolean varWrite,varFILE_PATH ,varBrowser,varDate, Build,CycleEnviMapID){

		// Read the existing file
		def wb = Workbook.getWorkbook(new File(varFILE_PATH))

		// Create a writable workbook with the same name using the workbook
		// object that has been read from file
		def wwb = Workbook.createWorkbook(new File(varFILE_PATH), wb)

		// Total number of rows in the sheet
		int intRowCount = wwb.getSheet(0).getRows()
		for (int intRow = 4 ;intRow <= intRowCount ;intRow++) {
			// Target cell
			def cell = wwb.getSheet(0).getWritableCell(1, intRow)

			if (cell.getType() == CellType.EMPTY) {

				// Enter the TC ID
				def label1 = new Label(1, intRow, varTCId)
				wwb.getSheet(0).addCell(label1)

				// Enter Test Objective
				def label2 = new Label(2, intRow, varTO)
				wwb.getSheet(0).addCell(label2)

				// Enter Result
				def label3 = new Label(3, intRow, varResult)
				wwb.getSheet(0).addCell(label3)

				// Enter Reason
				def label4 = new Label(4, intRow, varReason)
				wwb.getSheet(0).addCell(label4)

				// Enter Time taken
				def strTime = String.valueOf(timeTaken)
				def label5 = new Label(5, intRow, strTime)
				wwb.getSheet(0).addCell(label5)

				// Write the data
				wwb.write()
				intRow = intRowCount + 1
			}
		}
		wwb.close()

		if (varWrite == true) {

			// Converts time taken value from Seconds to Minutes
			timeTaken = timeTaken / 60;

			// Write Results to Qnet Database.
			writeResultToDB(CycleEnviMapID, varTCId, Build, varResult,
					varReason, timeTaken, varFILE_PATH, varDate, varBrowser);

		}
	}

	def writeResultToDB(varCycleEnviMapID, varTestCaseID,
			varBuildID, varResult, varComments,
			double varTimeTaken,varFilePath, varExecutedDate,varBrowserName){
		def username = 'autoaccess', password = 'admin@456'
		def db = Sql.newInstance("jdbc:sqlserver://192.168.27.7;DatabaseName=QNET", username, password,"com.microsoft.sqlserver.jdbc.SQLServerDriver")
		def strInsertSQL = "insert into tblAutomationResults (CycleEnviMapID,TestCaseID,BuildID,Result,Comments,TimeTaken,FilePath,ExecutedDate,BrowserName) values (${varCycleEnviMapID}, ${varTestCaseID},${varBuildID},${varResult},${varComments},${varTimeTaken},${varFilePath},${varExecutedDate},${varBrowserName})"
		db.execute(strInsertSQL)
	}

	def TimeTaken(Date dtStartDate) {
		Date dtEndDate = new Date();
		double dtTimeTaken = dtEndDate.getTime() - dtStartDate.getTime();
		dtTimeTaken = dtTimeTaken / 1000;
		return dtTimeTaken;
	}

}