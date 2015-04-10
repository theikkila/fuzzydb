import dstructures._
import org.scalatest._

class ReverseIndexSpec extends FunSpec with Matchers {

	describe("ReverseIndex") {
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
		it("should return bucket") {
			val ri = new ReverseIndex(10)
			ri.bucket("ssa") should be (List[KeyStore]())
		}
		it("should return all buckets") {
			val ri = new ReverseIndex(111)
			ri.getBuckets() should have length(111)
		}
		
	}

	describe("ReverseFeatureIndex") {
		it("should allow adding string for already assigned key") {
			val ri = new ReverseFeatureIndex()
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
			val ri = new ReverseFeatureIndex()
			ri.get("ssa") should equal (None)
			ri.addString("ssa", "kissa")
			ri.addString("ssa", "kassa")
			ri.get("ssa") match {
				case Some(fmap: FeatureMap) => fmap.length() should be (2)
				case _ => throw new RuntimeException("should never happen") 
			}
		}
		it("should return strings if they are assigned") {
			val ri = new ReverseFeatureIndex()
			ri.get("ssa") should equal (None)
			ri.getStrings("ssa") should have length(0)
			ri.addString("ssa", "kissa")
			ri.addString("ssa", "kassa")
			ri.getStrings("ssa") should have length(2)
		}
		it("should return strings of certain length") {
			val ri = new ReverseFeatureIndex()
			ri.get("ssa") should equal (None)
			ri.getStrings("ssa") should have length(0)
			ri.addString("ssa", "kissa")
			ri.addString("ssa", "kassa")
			ri.addString("ssa", "pressa")
			ri.getStrings("ssa") should have length(3)
			ri.getStringsOfLength("ssa", 7) should have length(2)
		}
		it("should return length of FeatureMap") {
			val ri = new ReverseFeatureIndex()
			ri.addString("ssa", "kissa")
			ri.addString("ssa", "kassa")
			ri.getLength("ssa") should be (2)
		}
	}

	describe("ReverseKeyCountIndex") {
		it("should allow adding into key") {
			val ri = new ReverseKeyCountIndex()
			ri ++ "kissa"
			ri ++ "kissa"
			ri ++ "kissa"
			ri ++ "koira"
			ri ++ "koira"
			ri ++ "käärme"


			ri count "sakaali" should be (0)
			ri count "kissa" should be (3)
			ri count "koira" should be (2)
			ri count "käärme" should be (1)
			
		}
		it("should return keys ordered by their count") {
			val ri = new ReverseKeyCountIndex()
			ri ++ "kissa"
			ri ++ "kissa"
			ri ++ "kissa"
			ri ++ "kissa"
			ri ++ "koira"
			ri ++ "koira"
			ri ++ "käärme"
			val sample = List[CountMap](
				new CountMap("kissa", 4),
				new CountMap("koira", 2),
				new CountMap("käärme", 1)
				)
			ri.ordered_keys() should contain theSameElementsAs (sample)
		}
		
	}

}