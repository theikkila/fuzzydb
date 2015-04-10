package dstructures

/*
* Heapable traits
*/

trait Heapable {
	def value: Int
	def diff(b: Heapable): Int = value - b.value
}

trait MaxHeapable extends Heapable{
	override def diff(b: Heapable): Int = b.value - value
}

/*
* Implements Binary Heap (Min or Max from traits)
* http://en.wikipedia.org/wiki/Heap_(data_structure)
*/

class Heap(size: Int = 100) {
	var heapsize = 0;
	var len = size;
	var A = new Array[Heapable](size);

	def find():Option[Heapable] = {
		if(heapsize <= 0) return None		
		A(0) match {
			case first:Heapable => Some(first)
			case _ => None
		}
	}
	def del():Option[Heapable] = {
		val m = find()
		heapsize -= 1;
		if(heapsize < 0) return None
		A(0) = A(heapsize)
		if(heapsize > 0){
			heapify(0)
		}
		m
	}
	def insert(item: Heapable) = {
		heapsize += 1;
		if (heapsize >= len) resize()
		A(heapsize-1) = item
		decreaseKey(heapsize-1)
	}
	def decreaseKey(i: Int) = {
		var index = i;
		while (index > 0 && A(parent(index)).diff(A(index)) >= 0) {
			swap(index, parent(index))
			index = parent(index)
		}
	}
	def merge() = None
	def length():Int = heapsize

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

	def resize(): Unit = {
		A = A ++ new Array[Heapable](len);
		len = len*2
	}
}