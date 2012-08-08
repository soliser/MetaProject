/*
 * @(#)MetaClass.java 1.2 2010/04/08
 */
package classes;

import java.util.ArrayList;

/**
 * The <code>MetaClass</code> class is designed to represent
 * a class in a object oriented program. It contains all of the 
 * necessary features of a program class. If the class is marked as
 * final then the abstract modifier will auto update to <code>false</code>
 * The isInterface, isEnum, and isClass are also linked in the same manner.
 * If one is set as true, the others are automatically set to <code>false</code>
 * .
 * 
 * @author William Croft
 * @version 1.1 2010/03/23
 */
public class MetaClass{
	/** The value is used to store the name of the class' package */
	private String packageName;
	/** The value is used to store the access modifier of the class */
	private Accessibility accessibility;
	/** The value is used to hold the boolean for whether or not this class is static */
	private boolean isStatic;
	/** The value is used to hold the boolean for whether or not this class is abstract */
	private boolean isAbstract;
	/** The value is used to hold the boolean for whether or not this class is final */
	private boolean isFinal;
	/** The value is used to hold the boolean for whether or not this class is a standard class file */
	private boolean isClass;
	/** The value is used to hold the boolean for whether or not this class is an interface */
	private boolean isInterface;
	/** The value is used to hold the boolean for whether or not this class is an enum class */
	private boolean isEnum;
	/** The value is used to hold the name of the class */
	private String name;
	/** The value is used to hold the parent class of this class */
	private MetaClass parent;
	/** The value is used to hold interfaces implemented by this class */
	private ArrayList<MetaClass> interfaces;
	/** The value is used to hold the list of the class' attributes */
	private ArrayList<MetaAttribute> attributeList;
	/** The value is used to hold the list of the class' methods */
	private ArrayList<MetaMethod> methodList;

	/** The default constructor */
	public MetaClass(){}

	/** 
	 * Constructs a meta class with a set list of attributes and methods 
	 *
	 * @param name The name of the class.
	 * @param attributeList The class attributes.
	 * @param methodList The class methods
	 */
	public MetaClass(String name, ArrayList<MetaAttribute> attributeList,
			ArrayList<MetaMethod> methodList){
		setName(name);
		setAttributeList(attributeList);
		setMethodList(methodList);
	}
	
	/**
	 * Constructs a standard meta class with an access modifier and name. The constructed
	 * class is assumed to be a standard class so the <code>isClass()</code> method will
	 * return <code>true</code>
	 * 
	 * @param accessibility The access modifier of the class
	 * @param name The name of the class
	 */
	public MetaClass(Accessibility accessibility, String name){
		setAccessibility(accessibility);
		setClass(true);
		setName(name);
	}

	/**
	 * @return the name of the class' package.
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @return the the access modifier for the class.
	 */
	public Accessibility getAccessibility() {
		return accessibility;
	}

	/**
	 * @return <strong>true</strong> if the class is static
	 *         <strong>false</strong> if the class is not static
	 */
	public boolean isStatic() {
		return isStatic;
	}

	/**
	 * @return <strong>true</strong> if the class is abstract
	 *         <strong>false</strong> if the class is not abstract      
	 */
	public boolean isAbstract() {
		return isAbstract;
	}

	/**
	 * @return <strong>true</strong> if the class is final
	 *         <strong>false</strong> if the class is not final
	 */
	public boolean isFinal() {
		return isFinal;
	}

	/**
	 * @return <strong>true</strong> if the class is a standard class
	 *         <strong>false</strong> if the class is not a standard class
	 */
	public boolean isClass() {
		return isClass;
	}

	/**
	 * @return <strong>true</strong> if the class is an interface
	 *         <strong>false</strong> if the class is not an interface
	 */
	public boolean isInterface() {
		return isInterface;
	}

	/**
	 * @return <strong>true</strong> if the class is an enum
	 *         <strong>false</strong> if the class is not an enum
	 */
	public boolean isEnum() {
		return isEnum;
	}

	/**
	 * @return the name of the class.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the parent class.
	 */
	public MetaClass getParent() {
		return parent;
	}

	/**
	 * @return the interfaces.
	 */
	public ArrayList<MetaClass> getInterfaces() {
		return interfaces;
	}

	/**
	 * @return the attribute list.
	 */
	public ArrayList<MetaAttribute> getAttributeList() {
		return attributeList;
	}

	/**
	 * @return the method list.
	 */
	public ArrayList<MetaMethod> getMethodList() {
		return methodList;
	}

	/**
	 * @param packageName the name of the package to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @param accessibility the accessibility to set
	 */
	public void setAccessibility(Accessibility accessibility) {
		this.accessibility = accessibility;
	}

	/**
	 * @param isStatic set <strong>true</strong> if the class is static
	 *                 <strong>false</strong> if the class is not static
	 */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	/**
	 * @param isAbstract set <strong>true</strong> if the class is abstract
	 *                   <strong>false</strong> if the class is not abstract
	 */
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
		if(isAbstract)
			this.isFinal = false;
	}

	/**
	 * @param isFinal set <strong>true</strong> if the class is final
	 *                <strong>false</strong> if the class is not final
	 */
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
		if(isFinal)
			this.isAbstract = false;
	}

	/**
	 * @param isClass set <strong>true</strong> if the class is a standard class
	 *                    <strong>false</strong> if the class is not a standard class
	 */
	public void setClass(boolean isClass) {
		this.isClass = isClass;
		if(isClass){
			this.isInterface = false;
			this.isEnum = false;
		}
	}

	/**
	 * @param isInterface set <strong>true</strong> if the class is an interface
	 *                        <strong>false</strong> if the class is not an interface
	 */
	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
		if(isInterface){
			this.isClass = false;
			this.isEnum = false;
		}
	}

	/**
	 * @param isEnum set <strong>true</strong> if the class is an enum
	 *                   <strong>false</strong> if the class is not an enum
	 */
	public void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
		if(isEnum){
			this.isClass = false;
			this.isInterface = false;
		}
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(MetaClass parent) {
		this.parent = parent;
	}

	/**
	 * @param interfaces the interfaces to set
	 */
	public void setInterfaces(ArrayList<MetaClass> interfaces) {
		this.interfaces = interfaces;
	}
	
	/**
	 * The method will check to see if <code>interfaces</code> has been
	 * initialized and if the interface given is marked as an interface
	 * and then add the given interface to the interface list.
	 * If <code>interfaces</code> has not been initialized, then the method
	 * will initialize the variable before adding the given interfaces.
	 * 
	 * 
	 * @param anInterface the interface to add
	 * @return if the class being added was an interface and was added
	 */
	public boolean addInterface(MetaClass anInterface){
		if(interfaces == null)
			interfaces = new ArrayList<MetaClass>();
		if(anInterface.isInterface()){
			interfaces.add(anInterface);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @param attributeList the attribute list to set
	 */
	public void setAttributeList(ArrayList<MetaAttribute> attributeList) {
		this.attributeList = attributeList;
	}
	
	/**
	 * The method will check to see if <code>attributeList</code> has been
	 * initialized and then add the given attribute to the attribute list.
	 * If <code>attributeList</code> has not been initialized, then the method
	 * will initialize the variable before adding the given attribute.
	 * 
	 * @param attribute the attribute to add to the attribute list.
	 */
	public void addAttribute(MetaAttribute attribute){
		if(attributeList == null)
			attributeList = new ArrayList<MetaAttribute>();
		attributeList.add(attribute);
	}

	/**
	 * @param methodList the method list to set
	 */
	public void setMethodList(ArrayList<MetaMethod> methodList) {
		this.methodList = methodList;
	}
	
	/**
	 * The method will check to see if <code>methodList</code> has been
	 * initialized and then add the given method to the method list.
	 * If <code>methodList</code> has not been initialized, then the method
	 * will initialize the variable before adding the given method.
	 * 
	 * @param method the method to add to the method list.
	 */
	public void addMethod(MetaMethod method){
		if(methodList == null)
			methodList = new ArrayList<MetaMethod>();
		methodList.add(method);
	}
	
	public String toString(){
		return accessibility.toString().toLowerCase() + " " + (isFinal?"final ":"") +
		(isAbstract?"abstract ":"") + (isStatic?"static ":"") + 
		(isClass?"class":isInterface?"interface":isEnum?"enum":"??") + " " + packageName +
		"." + name;
	}
}