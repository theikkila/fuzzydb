package dstructures
/*
* Implementation of FeatureMap
*/
class FeatureMap(k:String) extends KeyStore {
	val key = k;
	var listlength = 0;
	var strings = List[String]();

	def addString(s: String) = {
		strings = strings ::: List(s);
		listlength += 1;
	}

	def getStrings():List[String] = strings
	def getStringsOfLength(le: Int):List[String] = strings.filter(s => featureCount(s) == le)
	def length():Int = listlength
	def featureCount(s: String): Int = {
		s.length + 2
	}

	override def toString(): String =  key 
	
}


class CountMap(k:String, c:Int = 0) extends KeyStore with MaxHeapable {
	val key = k;
	var count = c;
	override def value = count;
	def ++() = {
		count += 1;
	}
	def canEqual(a: Any) = a.isInstanceOf[CountMap]
	override def toString(): String =  key
	override def equals(b: Any): Boolean =
		b match {
			case b: CountMap => b.canEqual(this) && key == b.key && count == b.count
			case _ => false
		}
}