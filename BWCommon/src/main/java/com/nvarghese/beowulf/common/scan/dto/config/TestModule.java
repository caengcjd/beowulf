//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2012.10.01 at 01:12:11 AM IST
//

package com.nvarghese.beowulf.common.scan.dto.config;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="module_number" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="module_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{}options" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="modulegroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "moduleNumber", "moduleName", "enabled", "options", "modulegroup" })
@XmlRootElement(name = "test_module")
public class TestModule {

	@XmlElement(name = "module_number", required = true)
	protected BigInteger moduleNumber;
	@XmlElement(name = "module_name", required = true)
	protected String moduleName;
	@XmlElement(name = "module_enabled", required = true)
	protected boolean enabled;
	protected List<Options> options;
	@XmlAttribute(name = "test_category")
	protected String modulegroup;

	/**
	 * Gets the value of the moduleNumber property.
	 * 
	 * @return possible object is {@link BigInteger }
	 * 
	 */
	public BigInteger getModuleNumber() {

		return moduleNumber;
	}

	/**
	 * Sets the value of the moduleNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link BigInteger }
	 * 
	 */
	public void setModuleNumber(BigInteger value) {

		this.moduleNumber = value;
	}

	/**
	 * Gets the value of the moduleName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getModuleName() {

		return moduleName;
	}

	/**
	 * Sets the value of the moduleName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setModuleName(String value) {

		this.moduleName = value;
	}

	/**
	 * Gets the value of the enabled property.
	 * 
	 */
	public boolean isEnabled() {

		return enabled;
	}

	/**
	 * Sets the value of the enabled property.
	 * 
	 */
	public void setEnabled(boolean value) {

		this.enabled = value;
	}

	/**
	 * Gets the value of the options property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the options property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getOptions().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Options }
	 * 
	 * 
	 */
	public List<Options> getOptions() {

		if (options == null) {
			options = new ArrayList<Options>();
		}
		return this.options;
	}

	/**
	 * Gets the value of the modulegroup property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getModulegroup() {

		return modulegroup;
	}

	/**
	 * Sets the value of the modulegroup property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setModulegroup(String value) {

		this.modulegroup = value;
	}

}
