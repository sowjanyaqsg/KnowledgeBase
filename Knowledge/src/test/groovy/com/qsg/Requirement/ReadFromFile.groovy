class ReadFromFile {
	
	def readingFromFile(){
		println("Begin readingFromFile")

		def myFile = new File("C:/NewFile1.txt")
		
		if(myFile.exists()){
			println(myFile.readLines())
		}
		else{
			println("FILE DOES NOT EXISTS")
		}
		println("End readingFromFile")
	}

	static main( args ){
		println("Begin Main")
		def readFile = new ReadFromFile()
		readFile.readingFromFile()
		println("End of Main")
	}
}