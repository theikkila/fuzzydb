import dstructures.ReverseIndex
import org.scalatest._

class ReverseIndexSpec extends FunSpec with Matchers {

	describe("when created") {
		it("should have default bucketcount of 100") {
			val ri = new ReverseIndex()
			ri.bucketCount() should be (100)
			
		}
	}

}