class WriteToFile {

	def "writingToFile"(){
		println("Begin writingToFile")
	
		def filePath = "D:/NewFile11.txt"
		def newFileString = "New File Is Created And Written"
		def existingFileString = "Written To Existing File"
		def file = new File(filePath)
		
		if (!file.exists()) {
			file.createNewFile()
			file.write(newFileString)
			println("End Of Create New File")
		}
		else{
			file.write(existingFileString)
			println("End Of writing to existing File")
		}
		println("End writingToFile")
	}
	
	static main( args ){
		println("Begin Main")
		def writeFile = new WriteToFile()
		writeFile."writingToFile"()
		println("End of Main")
	}
}
