package dstructures
/*
* Implementation of FeatureMap
*/
class FeatureMap(key:String)  {
	var listlength = 0;
	var strings = List[String]();
	
	def getKey(): String = { key }

	def addString(s: String) = {
		strings = strings ::: List(s);
		listlength += 1;
	}

	def getStrings():List[String] = {
		strings
	}

	def length():Int = {
		listlength
	}

}