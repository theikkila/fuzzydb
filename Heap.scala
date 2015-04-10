package dstructures

/*
* Heapable trait
*/

trait Heapable {
  def value: Int
  def diff(b: Heapable): Int = value - b.value
}

trait MaxHeapable extends Heapable{
  override def diff(b: Heapable): Int = b.value - value
}

/*
* Implements Binary MinHeap
* http://en.wikipedia.org/wiki/Heap_(data_structure)
*/

class Heap(size: Int = 100) {
	var heapsize = -1;
	var len = size;
	var A = new Array[Heapable](size);

	def find():Option[Heapable] = {
		A(0) match {
			case first:Heapable => Some(first)
			case _ => None
		}
		
	}
	def del():Option[Heapable] = {
		val m = find()
		A(0) = A(heapsize)
		heapify(0)
		m
	}
	def insert(item: Heapable) = {
		if (heapsize == len) throw new IllegalStateException("Heap buffer overflow")
		var i = heapsize+1
		heapsize = i
		while (i > 0 && A(parent(i)).diff(item) > 0) {
			A(i) = A(parent(i))
			i = parent(i)
		}
		A(i) = item

		
	}
	def decreaseKey() = None
	def merge() = None
	def length():Int = heapsize + 1

	// Return left child
	def left(i: Int): Int = (2 * i)

	// Return right child
	def right(i: Int): Int = (2 * i + 1)

	// Return parents index
	def parent(i: Int): Int = {
		(i / 2)
	}
	// swap
	def swap(i: Int, j: Int) = {
		val iv = A(i)
		A(i) = A(j)
		A(j) = iv
	}

	// heapify
	def heapify(i: Int): Unit = {
		val l = left(i)
		val r = right(i)
		var smallest = i
		if (l < heapsize && A(l).diff(A(i)) < 0) smallest = l
		else smallest = i

		if (r < heapsize && A(r).diff(A(smallest)) < 0) smallest = r
		if (smallest != i) {
			swap(i, smallest)
			heapify(smallest)
		}
	}
}