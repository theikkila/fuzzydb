import dstructures._
import org.scalatest._

class FeatureMapSpec extends FunSpec with Matchers {

	describe("FeatureMap") {
		it("should have default length of 0") {
			val ri = new FeatureMap("ssa")
			ri.length() should be (0)
		}
		it("should return key") {
			val ri = new FeatureMap("ssa")
			ri.key should be ("ssa")																																																																																												
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
		it("should calculate feature count") {
			val ri = new FeatureMap("ssa")
			ri.featureCount("kissa") should be (7)																																																																																											
			ri.featureCount("hiilihappo") should be (12)																																																																																											
			ri.featureCount("p") should be (3)																																																																																											
		}
		it("should return added strings of length n") {
			val ri = new FeatureMap("ssa")
			ri.addString("Kissa")
			ri.addString("Kassa")
			ri.addString("Puuhassa")
			ri.getStringsOfLength(7).length should be (2)
		}
	}

	describe("CountMap") {
		it("should have default count of 0") {
			val ri = new CountMap("kissa")
			ri.count should be (0)
			ri.value should be (0)
		}
		it("should have allow setting initial count") {
			val ri = new CountMap("kissa", 8)
			ri.count should be (8)
			ri.value should be (8)
		}
		it("should return key") {
			val ri = new CountMap("kissa")
			ri.key should be ("kissa")																																																																																												
		}
		it("should allow increasing counter") {
			val ri = new CountMap("kissa")
			ri.count should be (0)
			ri ++;
			ri.count should be (1)
			ri ++;
			ri.count should be (2)
		}
		it("should return true if two CountMaps are same") {
			val ri = new CountMap("kissa", 3)
			val rj = new CountMap("kissa", 3)
			ri == rj should be (true)
			rj ++;
			ri ++;
			ri == rj should be (true)
		}
		it("should return false if two CountMaps are different") {
			val ri = new CountMap("kissa", 3)
			val rj = new CountMap("kissa", 3)
			val rk = new CountMap("koira", 3)
			ri == rj should be (true)
			rj ++;
			ri == rj should be (false)
			ri == rk should be (false)
		}
	}


}