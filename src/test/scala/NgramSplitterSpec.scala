

import manipulators.NgramSplitter
import org.scalatest._

class NgramSplitterSpec extends FunSpec with Matchers {

	describe("when created") {
		it("should return empty list when ngrams are not yet generated") {
			val ng = new NgramSplitter("kissa")
			ng.ngrams().length should be (0)
		}
		describe("after generating ngrams") {
			val ng = new NgramSplitter("kissa")
			ng.generate_ngrams()
			it("should have ngrams generated") {
				ng.ngrams().length should be (7)
			}
			it("should have '$$k' first") {
				ng.ngrams()(0) should be ("$$k")
			}
			it("should have 'iss' in middle") {
				ng.ngrams()(3) should be ("iss")
			}
			it("should have search_range of 3 to 14 with threshold 0.7") {
				ng.search_range(0.7) should be ((3, 14))
			}
			it("should have search_range of 1 to 43 with threshold 0.4") {
				ng.search_range(0.4) should be ((1, 43))
			}
			it("should have search_range of 7 to 7 with threshold 1") {
				ng.search_range(1) should be ((7, 7))
			}
			it("should have search_range of 1 to 14 with threshold 0.1") {
				ng.search_range(0.1) should be ((0, 699))
			}
		}

	}

}