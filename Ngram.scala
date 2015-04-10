package dstructures
import manipulators._

class SWPair(s: String, w: Int) extends Heapable {
	val string = s;
	val weight = w;
	override def value() = weight;
}


/*
* Encapsulates N-grams and implements few similarity methods
*/
class Ngram(ngrams: List[String], plainstr: String)  {
	val grams = ngrams;
	var weights = new Array[Int](grams.length)

	def this(plainstr: String) = this(new NgramSplitter(plainstr).generate_ngrams(), plainstr);
	/*
	* Gets all grams as a list
	*/
	def ngrams():List[String] = { grams }

	def ngrams_ordered():List[String] = {
		val h = new Heap();
		var i = 0
		grams.foreach((ng: String) => {
			val k = new SWPair(ng, weights(i))
			h.insert(k);
			i = i + 1;
			});
		var ordered_grams = List[String]()
		for(k <- 1 to h.length()) {
			h.del() match {
				case Some(swp: SWPair) => { ordered_grams = ordered_grams ::: List[String](swp.string)}
				case _ => throw new ClassCastException
			}
		}
		ordered_grams
	}
	/*
	* Gets how many features does this string have
	*/
	def length() = grams.length

	/*
	* Gets the weight on i:th feature
	*/
	def getWeight(i:Int) = weights(i)
	/*
	* Sets the weight on i:th feature
	*/
	def setWeight(i:Int, v:Int) = weights(i) = v
	/*
	* Gets all feature weights
	*/
	def getWeights() = weights
	/*
	* Sets all feature weights
	*/
	def setWeights(a: Array[Int]) = weights = a
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