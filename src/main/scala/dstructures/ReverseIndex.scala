package dstructures


trait KeyStore {
  def key: String
}

/** Implementation of HashMap (ReverseIndex)
  *
  * @constructor create ReverseIndex with initial size of 100 buckets
  * @param buckets_count Count of buckets
  */
class ReverseIndex(buckets_count: Int = 100)  {
	var buckets = new Array[List[KeyStore]](buckets_count);
	buckets = buckets.map(idx => { List[KeyStore]()})

	// Get bucket index by key
	def bid(key: String): Int = { math.abs(key.hashCode() % buckets_count) }
	/*
	* Set K-V pair
	*/
	def set(kv: KeyStore) = {
		val key = kv.key;
		var b = bucket(key);
		val bd = bid(key);
		buckets(bd) = b.filter(fm => {fm.key != key})
		buckets(bd) = buckets(bd) ::: List(kv)
	}
	// Get kv by key
	def get(key: String):Option[KeyStore] = {
		for(slot <- bucket(key)) {
			if(slot.key == key) return Some(slot)
		}
		None
	}

	// Get bucket by key
	def bucket(key: String): List[KeyStore] = { buckets(bid(key)) }
	def getBuckets(): List[List[KeyStore]] = buckets.toList
	def bucketCount(): Integer = { buckets_count }
}


/** ReverseIndex which stores Features
  *
  */
class ReverseFeatureIndex(buckets_count: Int = 100) extends ReverseIndex (buckets_count) {

	def getLength(key: String): Int = {
		get(key) match {
			case Some(fmap: FeatureMap) => fmap.length()
			case _ => 0
		}
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

	// Get strings from featuremap @ key
	def getStringsOfLength(key: String, le: Int): List[String] = {
		get(key) match {
			case Some(fmap: FeatureMap) => fmap.getStringsOfLength(le)
			case _ => List[String]()
		}
	}

}

/** ReverseIndex for Key-Count pairs
  *
  */
class ReverseKeyCountIndex(buckets_count: Int = 100) extends ReverseIndex (buckets_count) {

	// Add +1 count
	def ++(key:String) = {
		get(key) match {
			case Some(cmap: CountMap) => cmap ++
			case _ => {
				val n = new CountMap(key)
				n ++;
				set(n)
			}
		}
	}

	// Get strings from featuremap @ key
	def count(key: String): Int = {
		get(key) match {
			case Some(cmap: CountMap) => cmap.count
			case _ => 0
		}
	}
	// Todo; Rewrite all heapsorts
	def ordered_keys(): List[CountMap] = {
		val h = new Heap()
		var li = getBuckets().flatten match {
			case k: List[CountMap] => k
			case _ => throw new ClassCastException
		}
		li.foreach(h.insert(_));
		var ld = List[CountMap]();
		for (l <- 0 to h.length()) {
			h.del() match {
				case Some(cm: CountMap) => ld = ld ::: List(cm)
				case _ => None
			}
		}
		ld
	}

}