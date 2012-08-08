/*
 * @(#)MetaAttribute.java 1.2 2010/04/08
 */
package classes;

/**
 * The <code>MetaAttribute</code> class is designed to represent
 * an attribute for a class in an Object Oriented program.
 * 
 * @author William Croft
 * @version 1.1 2010/03/24
 * @see MetaClass
 */
public class MetaAttribute {
	/** The value is used to store the access modifier of the attribute */
	private Accessibility accessibility;
	/** The value is used to hold the boolean for whether or not this attribute is static */
	private boolean isStatic = false;
	/** The value is used to hold the boolean for whether or not this attribute is abstract */
	private boolean isAbstract = false;
	/** The value is used to hold the boolean for whether or not this attribute is final */
	private boolean isFinal = false;
	/** The value is used to hold a String representation of this attributes data type */
	private String dataType;
	/** The value is used to hold the name of the attribute */
	private String name;
	
	/** Creates a MetaAttribute */
	public MetaAttribute(){}
	
	
	/** @return The accessibility of the attribute */
	public Accessibility getAccessibility() {
		return accessibility;
	}

	/** 
	 * @return <strong>true</strong> if the attribute is static <br />
	 * 		   <strong>false</strong> if the attribute is not static
	 */
	public boolean isStatic() {
		return isStatic;
	}

	/** 
	 * @return <strong>true</strong> if the attribute is abstract <br />
	 * 		   <strong>false</strong> if the attribute is not abstract
	 */
	public boolean isAbstract() {
		return isAbstract;
	}
	
	/** 
	 * @return <strong>true</strong> if the attribute is final <br />
	 * 		   <strong>false</strong> if the attribute is not final
	 */
	public boolean isFinal() {
		return isFinal;
	}
	
	/** @return The data type of the attribute */
	public String getDataType() {
		return dataType;
	}
	
	/** @return The name of the attribute */
	public String getName() {
		return name;
	}
	
	/** @param accessibility The Accessibility of the attribute to be set*/
	public void setAccessibility(Accessibility accessibility) {
		this.accessibility = accessibility;
	}
	
	/** @param isStatic <strong>true</strong> to set the attribute as static <br />
 	 *	                <strong>false</strong> to set the attribute as non-static
     */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	/** @param isStatic <strong>true</strong> to set the attribute as abstract <br />
 	 *	                <strong>false</strong> to set the attribute as non-abstract
     */
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	/** @param isStatic <strong>true</strong> to set the attribute as final <br />
 	 *	                <strong>false</strong> to set the attribute as mutable
     */
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	/** @param returnType The data type of the attribute to be set*/
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	/** @param name The name of the attribute to be set */
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return accessibility.toString().toLowerCase() + " " + (isFinal?"final ":"") +
		(isAbstract?"abstract ":"") + (isStatic?"static ":"") + dataType + " " + name;
	}
}