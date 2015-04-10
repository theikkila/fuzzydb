package dstructures
/*
* Implementation of HashMap (ReverseIndex)
*/
class ReverseIndex(buckets_count: Int = 100)  {
	var buckets = new Array[List[FeatureMap]](buckets_count);
	buckets = buckets.map(idx => { List[FeatureMap]()})

	// Get bucket index by key
	def bid(key: String): Int = { math.abs(key.hashCode() % buckets_count) }
	/*
	* Set K-V pair
	*/
	def set(fmap: FeatureMap) = {
		val key = fmap.getKey();
		var b = bucket(key);
		val bd = bid(key);
		buckets(bd) = b.filter(fm => {fm.getKey() != key})
		buckets(bd) = buckets(bd) ::: List(fmap)
	}
	// Get featuremap by key
	def get(key: String):Option[FeatureMap] = {
		for(slot <- bucket(key)) {
			if(slot.getKey() == key) return Some(slot)
		}
		None
	}

	// Add string into featuremap @ key
	def addString(key:String, value: String) = {
		get(key) match {
			case Some(fmap: FeatureMap) => fmap.addString(value) 
			case _ => {
				val n = new FeatureMap(key)
				n.addString(value)
				set(n)
			}
		}
	}

	// Get strings from featuremap @ key
	def getStrings(key: String): List[String] = {
		get(key) match {
			case Some(fmap: FeatureMap) => fmap.getStrings()
			case _ => List[String]()
		}
	}

	// Get bucket by key
	def bucket(key: String): List[FeatureMap] = { buckets(bid(key)) }

	def bucketCount(): Integer = { buckets_count }
}