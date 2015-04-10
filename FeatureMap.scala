package dstructures
/*
* Implementation of FeatureMap
*/
class FeatureMap(key:String)  {
	val k = key;
	var listlength = 0;
	var strings = List[String]();

	// Returns key like 'aab'
	def getKey(): String = k

	def addString(s: String) = {
		strings = strings ::: List(s);
		listlength += 1;
	}

	def getStrings():List[String] = strings
	def length():Int = listlength

	override def toString(): String =  key 
	
}