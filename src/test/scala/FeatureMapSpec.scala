import dstructures._
import org.scalatest._

class FeatureMapSpec extends FunSpec with Matchers {

	describe("when created") {
		it("should have default length of 0") {
			val ri = new FeatureMap("ssa")
			ri.length() should be (0)
		}
		it("should return key") {
			val ri = new FeatureMap("ssa")
			ri.getKey() should be ("ssa")																																																																																												
		}
		it("should allow adding strings") {
			val ri = new FeatureMap("ssa")
			ri.addString("Kissa")
			ri.addString("Kassa")
			ri.length() should be (2)
		}
		it("should return added strings") {
			val ri = new FeatureMap("ssa")
			ri.addString("Kissa")
			ri.addString("Kassa")
			ri.getStrings().length should be (2)
		}
	}

}