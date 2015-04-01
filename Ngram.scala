package ngram

/*
* Encapsulates N-grams and implements few similarity methods
*/
class Ngram(ngrams: List[String])  {
	val grams = ngrams;
	/*
	* Returns all grams as a list
	*/
	def ngrams():List[String] = { grams }
	/*
	* Calculates similarity cosine between X (this) and Y
	* cosine(X, Y) = |X intersect Y| / sqrt(|X||Y|)
	*/
	def similarity_cosine(Y:Ngram):Double = {
		intersect(Y) / Math.sqrt(grams.length*Y.ngrams().length)
	}
	/*
	* Calculates how many equal elements two ngrams have
	* |X intersect Y|
	*/
	def intersect(Y:Ngram):Int = {
		grams.intersect(Y.ngrams()).length
	}
}