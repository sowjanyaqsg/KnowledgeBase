import jxl.Sheet
import jxl.Workbook
import jxl.CellType

class ReadExcel {

	static main(args){
		ReadExcel obj = new ReadExcel()
		
		def filePath = "C:\\Workspace\\git\\Knowledge\\ExcelFiles\\addresses.xls" //Excel file path
		def sheetName = "Test" //Excel sheet name
		def cellContent = obj.ReadData(filePath,sheetName,3,1)
		println (cellContent)
	}

	def ReadData(varPath,varSheetName, int varRow, int varColumn)
	{
		// Read the existing file
		def wb = Workbook.getWorkbook(new File(varPath))
		def ws = wb.getSheet(varSheetName)
		//Read content of the cell
		def varCellContent = ws.getCell(varColumn-1, varRow-1).getContents()
		wb.close()
		return varCellContent
	}
	
}
