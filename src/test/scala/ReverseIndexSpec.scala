import dstructures._
import org.scalatest._

class ReverseIndexSpec extends FunSpec with Matchers {

	describe("when created") {
		it("should have default bucketcount of 100") {
			val ri = new ReverseIndex()
			ri.bucketCount() should be (100)
			
		}
		it("should give bid of 79 for key 'kissa' (bucketcount 100)") {
			val ri = new ReverseIndex()
			ri.bid("kissa") should be (79)																																																																																												
		}
		it("should give bid of 9 for key 'kissa' (bucketcount 10)") {
			val ri = new ReverseIndex(10)
			ri.bid("kissa") should be (9)
		}
		it("should set/get featuremap") {
			val ri = new ReverseIndex(10)
			ri.set(new FeatureMap("kaarme"))
			val ko = new FeatureMap("koira")
			ri.set(ko)
			val ki = new FeatureMap("kissa")
			ri.set(ki);
			ri.get("kissa") should be (Some(ki))
			ri.get("koira") should be (Some(ko))
		}
		it("should return None if key is unknown") {
			val ri = new ReverseIndex(10)
			ri.get("ssa") should equal (None)
		}
		it("should allow adding string for already assigned key") {
			val ri = new ReverseIndex(10)
			ri.set(new FeatureMap("ssa"))
			ri.get("ssa") match {
				case Some(fmap: FeatureMap) => fmap.length() should be (0)
				case _ => throw new RuntimeException("should never happen")
			}
			ri.addString("ssa", "kissa")
			ri.addString("ssa", "kassa")
			ri.get("ssa") match {
				case Some(fmap: FeatureMap) => fmap.length() should be (2)
				case _ => throw new RuntimeException("should never happen") 
			}
		}
		it("should allow adding string for not yet assigned key") {
			val ri = new ReverseIndex(10)
			ri.get("ssa") should equal (None)
			ri.addString("ssa", "kissa")
			ri.addString("ssa", "kassa")
			ri.get("ssa") match {
				case Some(fmap: FeatureMap) => fmap.length() should be (2)
				case _ => throw new RuntimeException("should never happen") 
			}
		}
		it("should return strings if they are assigned") {
			val ri = new ReverseIndex(10)
			ri.get("ssa") should equal (None)
			ri.getStrings("ssa") should have length(0)
			ri.addString("ssa", "kissa")
			ri.addString("ssa", "kassa")
			ri.getStrings("ssa") should have length(2)
		}
	}

}