package manipulators

class NgramSplitter(plainstr: String)  {
	val str = plainstr;
	/*
	* Splits string to ngrams
	*/
	def getChar(index:Int):Char = {
		if (index < 0 || index >= str.length) '$'
		else {
			val s = str charAt index
			if (s == ' ') '_'
			else s
		}
	}
	def ngrams():List[String] = {
		var grams = List[String]();
		var s = "";
		for( i <- 1 to str.length+2) {
			s = ""
			for (idx <- i-3 to i-1) s = s + getChar(idx)
			grams = grams ::: List(s);
		}
		grams
	}
}