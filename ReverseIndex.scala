package dstructures
/*
* Implementation of HashMap (ReverseIndex)
*/
class ReverseIndex(buckets_count: Int = 100)  {
	var buckets = new Array[List[FeatureMap]](buckets_count);

	// Get bucket index by key
	def bid(key: String): Int = { key.hashCode() % buckets_count }
	/*
	* Set K-V pair
	*/
	def set(key: String, value: FeatureMap) = {
		var b = bucket(key)
		buckets(bid(key)) = b.filter(fm => {fm.getKey() != key})
	}
	// Get bucket by key
	def bucket(key: String): List[FeatureMap] = { buckets(bid(key)) }

	def bucketCount(): Integer = { buckets_count }
}