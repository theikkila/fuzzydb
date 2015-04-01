import dstructures.ReverseIndex
import org.scalatest._

class ReverseIndexSpec extends FlatSpec with Matchers {

  "A ReverseIndex" should "have default bucketcount of 100" in {
    val ri = new ReverseIndex()
    ri.bucketCount() should be (100)
  }

}