/*
 * @(#)MetaMethod.java 1.2 2010/04/08
 */
package classes;

import java.util.ArrayList;

/**
 * The <code>MetaMethod</code> class is designed to represent
 * a method in a object oriented program. It contains all of the 
 * necessary features of a program method.
 * 
 * @author William Croft
 * @version 1.1 2010/03/24
 * @see MetaClass
 */
public class MetaMethod{
	/** The value is used to store the access modifier of the Method */
	private Accessibility accessibility;
	/** The value is used to hold the boolean of whether or not this method is static */
	private boolean isStatic = false;
	/** The value is used to hold the boolean of whether or not this method is abstract */
	private boolean isAbstract = false;
	/** The value is used to hold the boolean of whether or not this method is final */
	private boolean isFinal = false;
	/** The value is used to hold a String representation of the methods return type */
	private String returnType;
	/** The value is used to hold the name of the method */
	private String name;
	/** The value is used to hold the parameters of the class */
	private ArrayList<Parameter> parameters;

	/** Creates a MetaMethod */
	public MetaMethod(){}

	/** @return The accessibility of the method */
	public Accessibility getAccessibility() {
		return accessibility;
	}

	/** 
	 * @return <strong>true</strong> if the method is static <br />
	 * 		   <strong>false</strong> if the method is not static
	 */
	public boolean isStatic() {
		return isStatic;
	}

	/** 
	 * @return <strong>true</strong> if the method is abstract <br />
	 * 		   <strong>false</strong> if the method is not abstract
	 */
	public boolean isAbstract() {
		return isAbstract;
	}
	
	/** 
	 * @return <strong>true</strong> if the method is final <br />
	 * 		   <strong>false</strong> if the method is not final
	 */
	public boolean isFinal() {
		return isFinal;
	}
	
	/** @return The data type the method will return */
	public String getReturnType() {
		return returnType;
	}
	
	/** @return The name of the method */
	public String getName() {
		return name;
	}
    
	/** @return The ArrayList containing the Methods parameters */
	public ArrayList<Parameter> getParameters() {
		return parameters;
	}
	
	/** @param accessibility The Accessibility of the method to be set*/
	public void setAccessibility(Accessibility accessibility) {
		this.accessibility = accessibility;
	}
	
	/** @param isStatic <strong>true</strong> to set the method as static <br />
 	 *	                <strong>false</strong> to set the method as non-static
     */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	/** @param isStatic <strong>true</strong> to set the method as abstract <br />
 	 *	                <strong>false</strong> to set the method as non-abstract
     */
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	/** @param isStatic <strong>true</strong> to set the method as final <br />
 	 *	                <strong>false</strong> to set the method as mutable
     */
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	/** @param returnType The data type the method is expected to return to be set*/
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	/** 
	 * Sets the name of the method. The name should be name only without parameters
	 * or modifiers.
	 * <p>
	 * Example: The name for <code>public void setName(String name)</code> should be 
	 * entered as <code>setName</code>
	 * 
	 * @param name The name of the method to be set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	 * @param parameters The <code>ArrayList</code> containing the parameters
	 * of the method to be set
	 */
	public void setParameters(ArrayList<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * The method will check to see if <code>parameters</code> has been
	 * initialized and then add the given parameter to the parameter list.
	 * If <code>parameters</code> has not been initialized, then the method
	 * will initialize the variable before adding the given parameter.
	 * 
	 * @param parameter the parameter to add to the parameters.
	 */
	public void addParameter(Parameter parameter){
		if(parameters == null)
			parameters = new ArrayList<Parameter>();
		parameters.add(parameter);
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append(accessibility.toString().toLowerCase());
		out.append(" ");
		out.append((isFinal?"final ":""));
		out.append((isAbstract?"abstract ":""));
		out.append((isStatic?"static ":""));
		out.append(returnType);
		out.append(" ");
		out.append(name);
		out.append("(");
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i ++){
				out.append(parameters.get(i).toString());
				if(i != parameters.size() - 1)
					out.append(", ");
			}
		}
		out.append(")");
		return out.toString();
	}

	/**
	 * The public inner class <code>Parameter</code> represents the parameter of a method.
	 * It is designed to be used only with this class when defining a methods parameters.
	 * 
	 * @author William Croft
	 */
	public static class Parameter{
		/** The value is used to hold the String representation of the parameter's data type*/
		private String dataType;
		/** The value is used to hold the name of the parameter */
		private String name;
		
		/** Constructs a default parameter with no attributes */
		public Parameter(){}
		
		/** 
		 * Constructs a new parameter with a data type and a name.
		 * 
		 * @param dataType The data type of the parameter
		 * @param name The name of the parameter
		 */
		public Parameter(String dataType, String name){
			setDataType(dataType);
			setName(name);
		}
		
		/** @return The name of the parameter */
		public String getName() {
			return name;
		}
		
		/** @return The data type of the parameter */
		public String getDataType() {
			return dataType;
		}
		
		/** @param name The name of the parameter to be set*/
		public void setName(String name) {
			this.name = name;
		}
		
		/** @param dataType The data type of the parameter to be set*/
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		
		public String toString(){
			return dataType + " " + name;
		}
	}
}