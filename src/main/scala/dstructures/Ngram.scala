package dstructures
import manipulators._


/** String-Weight pair
  *
  * @constructor creates string-weight pair
  * @param s String
  * @param w Weight
  */
class SWPair(s: String, w: Int) extends Heapable {
	val string = s;
	val weight = w;
	override def value() = weight;
}



/** Encapsulates N-grams and implements few similarity methods
  *
  * @constructor creates ngram from features or if only string is provided splits it into ngrams
  * @param ngrams list of features
  * @param plainstr original string
  * @todo Extract utility functions and add them in one object
  */
class Ngram(ngrams: List[String], plainstr: String)  {
	val grams = ngrams;
	var weights = new Array[Int](grams.length)

	def this(plainstr: String) = this(new NgramSplitter(plainstr).generate_ngrams(), plainstr);
	/**
	* Gets all ngrams as list
	*/
	def ngrams():List[String] = { grams }
	/**
	* Gets all ngrams as ordered list
	*/
	def ngrams_ordered():List[String] = {
		// Create new heap
		val h = new Heap();
		var i = 0
		// Add each ngram and weight into heap
		grams.foreach((ng: String) => {
			val k = new SWPair(ng, weights(i))
			h.insert(k);
			i = i + 1;
			});
		var ordered_grams = List[String]()
		// Return all ngrams in order from heap
		for(k <- 0 to h.length()) {
			h.del() match {
				case Some(swp: SWPair) => { ordered_grams = ordered_grams ::: List[String](swp.string)}
				case _ => None
			}
		}
		ordered_grams
	}
	/**
	* Gets how many features does this string have
	*/
	def length() = grams.length

	/**
	* Gets the weight on i:th feature
	*/
	def getWeight(i:Int) = weights(i)
	/**
	* Sets the weight on i:th feature
	*/
	def setWeight(i:Int, v:Int) = weights(i) = v
	/**
	* Gets all feature weights
	*/
	def getWeights() = weights
	/**
	* Sets all feature weights
	*/
	def setWeights(a: Array[Int]) = weights = a
	/**
	* Calculates similarity cosine between X (this) and Y
	* cosine(X, Y) = |X intersect Y| / sqrt(|X||Y|)
	*/
	def similarity_cosine(Y:Ngram):Double = {
		intersect(Y) / Math.sqrt(grams.length*Y.ngrams().length)
	}
	/**
	* Calculates how many equal elements two ngrams have
	* |X intersect Y|
	*/
	def intersect(Y:Ngram):Int = {
		grams.intersect(Y.ngrams()).length
	}
}