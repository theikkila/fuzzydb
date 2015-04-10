import dstructures._
import org.scalatest._

class MinHeapSpec extends FunSpec with Matchers {
	class HeapObj(v: Int, p: String) extends Heapable {
		var va = v
		var payload = p
		override def value() = va;
	}

	describe("when created") {
		it("should be") {
			val ri = new MinHeap()
			ri should not be (None)
		}
		it("should have working find-min") {
			val ri = new MinHeap()
			val a = new HeapObj(2, "eka")
			val c = new HeapObj(8, "kasi")
			val d = new HeapObj(2, "toka")
			ri.insert(new HeapObj(3, "kolmas"))
			ri.insert(a)
			ri.insert(c)
			ri.insert(d)
			ri.findmin() should be (Some(a))
		}
		it("should have working delete-min") {
			val ri = new MinHeap()
			val a = new HeapObj(2, "eka")
			val b = new HeapObj(3, "kolmas")
			val c = new HeapObj(8, "kasi")
			val d = new HeapObj(1, "ihaeka")
			ri.insert(b)
			ri.insert(a)
			ri.insert(c)
			ri.insert(d)
			ri.findmin() should be (Some(d))
			ri.delmin()
			ri.findmin() should be (Some(a))
		}
		it("should have working insert") {
			val ri = new MinHeap()
			val a = new HeapObj(2, "eka")
			ri.insert(new HeapObj(3, "toka"))
			ri.insert(a)
			ri.length() should be (2)
		}
		it("should have working merge") {
			val ri = new MinHeap()

		}
		it("should return right child index") {
			val ri = new MinHeap()

		}
		it("should return left child index") {
			val ri = new MinHeap()

		}
		it("should not return right child index") {
			val ri = new MinHeap()

		}
		it("should not return left child index") {
			val ri = new MinHeap()

		}
		it("should return parent index") {
			val ri = new MinHeap()

		}
	}

}