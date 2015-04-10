package manipulators


/** NgramSplitter splits plain strings into ngrams and provides utilities for calculating min overlap and search range
  *
  * @constructor create list of ngrams
  * @param plainstr Original string
  */
class NgramSplitter(plainstr: String)  {
	val str = plainstr;
	var grams = List[String]();
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
	// Generates ngrams
	def generate_ngrams():List[String] = {
		var s = "";
		for( i <- 1 to str.length+2) {
			s = ""
			for (idx <- i-3 to i-1) s = s + getChar(idx)
			grams = grams ::: List(s);
		}
		grams
	}
	// Returns all ngrams as a List
	def ngrams():List[String] = {
		grams
	}
	// Return search range with threshold a
	// min: a^2 * |X|
	// max: |X| / a^2
	def search_range(a:Double):(Int, Int) = {
		((a*a*grams.length).toInt, (grams.length/(a*a)).toInt) 
	}
	def min_overlap(a:Double, l:Int):Int = {
		(a * Math.sqrt(grams.length * l)).toInt
	}
}