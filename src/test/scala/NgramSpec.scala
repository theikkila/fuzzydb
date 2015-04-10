import dstructures._
import org.scalatest._

class NgramSpec extends FunSpec with Matchers {

	describe("when created") {
		it("should have length of 7 if word is 'kissa'") {
			val ri = new Ngram("kissa")
			ri.length should be (7)
			ri.ngrams should have length(7)
		}
		it("should have '$$k' as a first gram if word is 'kissa'") {
			val ri = new Ngram("kissa")
			ri.ngrams()(0) should be ("$$k")
		}
		it("should have 'a$$' as a last gram if word is 'kissa'") {
			val ri = new Ngram("kissa")
			ri.ngrams()(6) should be ("a$$")
		}
		it("should set weight for ngrams") {
			val ri = new Ngram("kissa")
			ri.setWeight(0, 233);
			ri.setWeight(3, 11);
			ri.getWeight(0) should be (233)
			ri.getWeight(3) should be (11)
			ri.getWeights() should be (Array[Int](233,0,0,11,0,0,0))
		}
		it("should set all weights") {
			val ri = new Ngram("kissa")
			ri.setWeights(Array[Int](115,3,266,46,23,123,513))
			ri.getWeights() should be (Array[Int](115,3,266,46,23,123,513))
		}
		it("should sort ngrams in order of given weights") {
			val ri = new Ngram("kissa")
			ri.setWeights(Array[Int](115,3,266,46,23,123,513))
			ri.ngrams() should be (List[String]("$$k", "$ki", "kis", "iss", "ssa", "sa$", "a$$"))
			ri.ngrams_ordered() should be (List[String]("$ki", "ssa","iss","$$k","sa$","kis","a$$" ))
		}
	}

}