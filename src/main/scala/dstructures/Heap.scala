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


/** Implements Binary Heap
  * http://en.wikipedia.org/wiki/Heap_(data_structure)
  *
  * @constructor create a new heap with initial size of 100
  * @param size Heap initial size
  */
class Heap(size: Int = 100) {
	var heapsize = 0;
	var len = size;
	var A = new Array[Heapable](size);

	/** Find min/max
	  *
	  * This method returns heap max/min in time O(1)
	  * If there is no max/min then it returns None as Option
	  */
	def find():Option[Heapable] = {
		if(heapsize <= 0) return None		
		A(0) match {
			case first:Heapable => Some(first)
			case _ => None
		}
	}
	/** Delete min/max
	  *
	  * This method returns heap max/min and removes it in time O(log n)
	  * If there is no max/min then it returns None as Option
	  */
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
	/** Insert
	  *
	  * This method insert heap max/min and removes it in time O(log n)
	  * If there is no max/min then it returns None as Option
	  */
	def insert(item: Heapable) = {
		heapsize += 1;
		if (heapsize >= len) resize()
		A(heapsize-1) = item
		decreaseKey(heapsize-1)
	}
	/** Decrease-key
	  *
	  * This method decreases key in time O(log n)
	  */
	def decreaseKey(i: Int) = {
		var index = i;
		while (index > 0 && A(parent(index)).diff(A(index)) >= 0) {
			swap(index, parent(index))
			index = parent(index)
		}
	}
	def merge() = None
	
	/** Length
	  *
	  * Returns heap size in time O(1)
	  */
	def length():Int = heapsize

	/** Return left child index */
	def left(i: Int): Int = (2 * i)

	/** Return right child index */
	def right(i: Int): Int = (2 * i + 1)

	/** Return parent index */
	def parent(i: Int): Int = {
		(i / 2)
	}
	/** Swap two keys */
	def swap(i: Int, j: Int) = {
		val iv = A(i)
		A(i) = A(j)
		A(j) = iv
	}

	/** Heapify
	  *
	  * This method heapifys array
	  */
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
	/** Resize
	  *
	  * This method resizes heap space
	  */
	def resize(): Unit = {
		A = A ++ new Array[Heapable](len);
		len = len*2
	}
}