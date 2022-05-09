package PolynomialMath;

import java.math.BigInteger;
import java.util.Iterator;

public class Polynomial implements DeepClone<Polynomial>
{


	private SLinkedList<Term> polynomial;
	public int size()
	{ 
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}

	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}

	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{ 
		return new Polynomial(polynomial.deepClone());
	}

	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t)
	{ 
		if (t.getCoefficient().equals(BigInteger.valueOf(0))) {
			throw new IllegalArgumentException("input coefficient cannot be zero");
		}
		int index = 0; 
		Boolean isUpdated = false;

		for (Term currentTerm : polynomial) {
			if (currentTerm.getExponent()==t.getExponent()) {
				//Big Integer addition
				BigInteger d = currentTerm.getCoefficient().add(t.getCoefficient());
				currentTerm.setCoefficient(d);
				//((currentTerm.getCoefficient()) + (t.getCoefficient())); //polynomial at the given index adds the input coefficien to itself
				isUpdated = true;
				break;
			} else if (t.getExponent()>currentTerm.getExponent()) {
				polynomial.add(index,t);
				isUpdated = true;
				break;
			} else {
				index++;
			}
		}
		if (isUpdated == false) {
			polynomial.addLast(t);
		}
	}
	// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
	// so if this method were to call the get(index) 
	// method n times then the method would be O(n^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.
	/*
  for (Term currentTerm: polynomial)
  {
   // The for loop iterates over each term in the polynomial!!
   // Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
  }
	 */


	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}

	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{

		Polynomial sum = new Polynomial();
		boolean sameExponent = false;
		for (int i=0; i<p1.size(); i++) {
			for(int j=0; j<p2.size(); j++) {
				if(p1.getTerm(i).getExponent()==p2.getTerm(j).getExponent()) {
					Term temp = new Term(p1.getTerm(i).getExponent(), p1.getTerm(i).getCoefficient().add(p2.getTerm(j).getCoefficient()));
					sum.addTerm(temp);
					sameExponent = true;
					break;
				} 
				sameExponent = false;
			}
			if (sameExponent == false) {
				sum.addTerm(p1.getTerm(i));
			}
		}

		boolean extra = false;
		for (int i=0; i<p2.size(); i++) {
			for (int j=0; j<p1.size(); j++) {
				if (p2.getTerm(i).getExponent() == p1.getTerm(j).getExponent()) {
					extra = true;
					break;
				}
				extra = false;
			}
			if (extra == false) {
				sum.addTerm(p2.getTerm(i));
			}
		}

		return sum;

		/*
		int p1HighTerm = p1.getTerm(0).getExponent();
		int p2HighTerm = p2.getTerm(0).getExponent();
		while (p1HighTerm>=0) {


			for (int j=0; j<p2.size(); j++) {
				if (p1.getTerm(i).getExponent()==p2.getTerm(j).getExponent()) {
					sum.setTerm
				}
			}
		}


		/*
		Polynomial sum = new Polynomial();
		Term pointer1 = p1.getTerm(0);
		SNode p11 = p1.getNode(0);
		Term pointer2 = p2.getTerm(0);
		Term T = new Term(0, BigInteger.valueOf(0));
		while (pointer1 != null) {
			if (pointer1.getExponent() == pointer2.getExponent()) {
				//add summed result to sum.
				T.setCoefficient(pointer1.getCoefficient().add(pointer2.getCoefficient()));
				T.setExponent(pointer1.getExponent()); 
				sum.addTermLast(T);
				pointer1 = pointer1.next();
				pointer2 = pointer2.next();
			} else if (pointer1.getExponent() > pointer2.getExponent()){
				//Add highest exponent as is
				sum.addLast(pointer1.getElement());
				//move p1 over
				pointer1 = pointer1.next;
			} else if (pointer1.getExponent() < pointer2.getExponent()) {
				sum.addLast(pointer2.getElement());
				pointer2 = pointer2.next;
			}
		}
		while (pointer 1!= null) {

		}
		 */


	}


	//TODO: multiply this polynomial by a given term.
	public void multiplyTerm(Term t)
	{ 
		/**** ADD CODE HERE ****/ 

		for (Term currentTerm : polynomial) {
			BigInteger newCoefficient = (currentTerm.getCoefficient()).multiply(t.getCoefficient());
			int newExponent = (currentTerm.getExponent()+t.getExponent());
			currentTerm.setCoefficient(newCoefficient);
			currentTerm.setExponent(newExponent);

		}
	}
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		
		Polynomial product = new Polynomial();
		for (int i=0; i<p1.size(); i++) {
			for (int j=0; j<p2.size(); j++) {
				int tempE = (p1.getTerm(i).getExponent() + p2.getTerm(j).getExponent());
				BigInteger tempC = (p1.getTerm(i).getCoefficient().multiply(p2.getTerm(j).getCoefficient()));
				Term temp = new Term(tempE, tempC);
				product.addTerm(temp);
			}
		
			
		}
		return product; 
		/*

		Polynomial p1copy = p1.deepClone();
		Polynomial p2copy = p2.deepClone();
		Polynomial product = new Polynomial();
		Polynomial finalproduct = new Polynomial();
		//SLLIterator p1iter = p1.iterator(); 


		for (Term currentTermp1 : p1copy) {
			for (Term currentTermp2 : p2copy) {
				Term thisterm = new Term((currentTermp1.getExponent() + currentTermp2.getExponent()),currentTermp1.getCoefficient().multiply(currentTermp2.getCoefficient()));
				product.addTerm(thisterm);

			}
			finalproduct = add(product,finalproduct);
		}


		 */

	}

	// TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		/**** ADD CODE HERE ****/

		BigInteger result = this.polynomial.get(0).getCoefficient();
		int exponent = 0;

		for (Term currentTerm: this.polynomial) {
			if (polynomial.get(0)==currentTerm) {
				exponent = currentTerm.getExponent();
			}
			if (exponent - currentTerm.getExponent() == 1) {
				result= (result.multiply(x));
				result = result.add(currentTerm.getCoefficient());
				exponent = currentTerm.getExponent();
			} else if(exponent-currentTerm.getExponent()>1){
				int power = exponent - currentTerm.getExponent(); 
				result=(result.multiply((x).pow(power)));
				result = result.add(currentTerm.getCoefficient());
				exponent = currentTerm.getExponent();
			}

		}

		if(exponent != 0) {
			result=(result.multiply((x).pow(exponent)));
		}

		return result;

	}

	// Checks if this polynomial is a clone of the input polynomial
	public boolean isDeepClone(Polynomial p)
	{ 
		if (p == null || polynomial == null || p.polynomial == null || this.size() != p.size())
			return false;

		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);

			// making sure that p is a deep clone of this
			if (term0.getExponent() != term1.getExponent() ||
					term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)  
				return false;

			index++;
		}
		return true;
	}

	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{ 
		polynomial.addLast(t);
	}


	@Override
	public String toString()
	{ 
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}