import dstructures._
import org.scalatest._

class MinMaxHeapSpec extends FunSpec with Matchers {
	class HeapObjMin(v: Int, p: String) extends Heapable {
		var va = v
		var payload = p
		override def value = va;
		override def toString(): String =  value+":"+payload 
		
	}
	class HeapObjMax(v: Int, p: String) extends HeapObjMin (v, p) with MaxHeapable {}
	
	describe("heap") {
		it("should be") {
			val ri = new Heap()
			ri should not be (None)
		}
		it("should return right child index") {
			val ri = new Heap()
			ri.right(1) should be (3)
			ri.right(2) should be (5)
			ri.right(3) should be (7)
		}
		it("should return left child index") {
			val ri = new Heap()
			ri.left(1) should be (2)
			ri.left(2) should be (4)
			ri.left(3) should be (6)

		}
		it("should return parent index") {
			val ri = new Heap()
			ri.parent(2) should be (1)
			ri.parent(4) should be (2)
			ri.parent(3) should be (1)
			ri.parent(17) should be (8)
		}
		it("should have working swap") {
			val ri = new Heap()
			val a = new HeapObjMin(5, "A")
			val b = new HeapObjMin(1, "B")
			ri.insert(a)
			ri.insert(b)
			ri.find() should equal (Some(b))
			ri.find() should not equal (Some(a))
			ri.swap(0, 1)
			ri.find() should equal (Some(a))
			ri.find() should not equal (Some(b))
			ri.swap(0, 1)
			ri.find() should equal (Some(b))
			ri.find() should not equal (Some(a))
		}
	}

	describe("minheap") {

		it("should have working find-min") {
			val ri = new Heap()
			val a = new HeapObjMin(2, "eka")
			val c = new HeapObjMin(8, "kasi")
			val d = new HeapObjMin(2, "toka")
			ri.insert(new HeapObjMin(3, "test"))
			ri.insert(a)
			ri.insert(c)
			ri.insert(d)
			ri.find() should be (Some(a))
		}
		it("should have working delete-min") {
			val ri = new Heap()
			val a = new HeapObjMin(2, "eka")
			val b = new HeapObjMin(3, "kolmas")
			val c = new HeapObjMin(8, "kasi")
			val d = new HeapObjMin(1, "ihaeka")
			ri.insert(b)
			ri.insert(a)
			ri.insert(c)
			ri.insert(d)
			ri.find() should be (Some(d))
			ri.del()
			ri.find() should be (Some(a))
		}
		it("should have working insert") {
			val ri = new Heap()
			val a = new HeapObjMin(2, "eka")
			ri.insert(new HeapObjMin(3, "toka"))
			ri.insert(a)
			ri.length() should be (2)
		}
		it("should have working merge") {
			val ri = new Heap()

		}
	}
	describe("maxheap") {
		it("should have working find-max") {
			val ri = new Heap()
			val a = new HeapObjMax(2, "eka")
			val c = new HeapObjMax(8, "kasi")
			val d = new HeapObjMax(2, "toka")
			ri.insert(new HeapObjMax(3, "kolmas"))
			ri.insert(a)
			ri.insert(c)
			ri.insert(d)
			ri.find() should be (Some(c))
		}
		it("should have working delete-max") {
			val ri = new Heap()
			val a = new HeapObjMax(2, "eka")
			val b = new HeapObjMax(3, "kolmas")
			val c = new HeapObjMax(8, "kasi")
			val d = new HeapObjMax(1, "ihaeka")
			ri.insert(b)
			ri.insert(a)
			ri.insert(c)
			ri.insert(d)
			ri.find() should be (Some(c))
			ri.del() should be (Some(c))
			ri.find() should be (Some(b))
		}
		it("should have working insert") {
			val ri = new Heap()
			val a = new HeapObjMax(3, "eka")
			ri.insert(new HeapObjMax(2, "toka"))
			ri.insert(a)
			ri.length() should be (2)
		}
		it("should have working merge") {
			val ri = new Heap()

		}
		
	}

}