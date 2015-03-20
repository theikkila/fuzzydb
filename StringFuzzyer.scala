package manipulators;

class StringFuzzyer(plainstr: String)  {
	val str = plainstr;
	val alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖabcdefghijklmnopqrstuvwxyzåäö'&,.-_+!`´";
	var strings = List[String]();
	/*
	* Removes parts of string
	*/
	def remove() = {
		var s = "";
		for( i <- 0 to str.length) {
			s = str.slice(0, i-1) ++ str.slice(i, str.length)
			strings = strings ::: List(s);
		}
	}
	/*
	* Adds parts of string
	*/
	def add() = {
		var s = "";
		for( i <- 0 to str.length) {
			for( c <- alpha) {	
				s = str.slice(0, i) + c + str.slice(i, str.length)
				strings = strings ::: List(s);
			}
		}
	}
	/*
	* Substitute parts of string
	*/
	def substitute() = {
		var s = "";
		for( i <- 0 to str.length) {
			for( c <- alpha) {	
				s = str.slice(0, i-1) + c + str.slice(i, str.length)
				strings = strings ::: List(s);
			}
		}
	}
}