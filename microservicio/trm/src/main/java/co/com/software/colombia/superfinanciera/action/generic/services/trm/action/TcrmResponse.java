
package co.com.software.colombia.superfinanciera.action.generic.services.trm.action;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

import javax.xml.namespace.QName;
import java.io.Serializable;

/**
 * Business TRM response
 * @author Alex Vicente Chacon Jimenez (alex.chacon@software-colombia.com)
 *
 */
public class TcrmResponse extends Tcrm implements Serializable
{
	private static final String W3_XMLSCHEMA_URI = "http://www.w3.org/2001/XMLSchema";

	private static final long serialVersionUID = 1L;

	private String message;

	private Boolean success;

	private static final TypeDesc typeDesc = new TypeDesc(TcrmResponse.class, true);

	/**
	 * Empty constructor. Please do not delete. Is used by introspection.
	 */
	public TcrmResponse()
	{
		//
		// Empty constructor used by introspection
	}

	/**
	 * Gets the message value for this TcrmResponse.
	 * 
	 * @return message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message value for this TcrmResponse.
	 * 
	 * @param message message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Gets the success value for this TcrmResponse.
	 * 
	 * @return success
	 */
	public Boolean getSuccess()
	{
		return success;
	}

	/**
	 * Sets the success value for this TcrmResponse.
	 * 
	 * @param success the flag to set
	 */
	public void setSuccess(Boolean success)
	{
		this.success = success;
	}

	static
	{
		typeDesc.setXmlType(new QName( "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/", "tcrmResponse"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("message");
		elemField.setXmlName(new QName("", "message"));
		elemField.setXmlType(new QName( W3_XMLSCHEMA_URI, "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);

		elemField = new ElementDesc();
		elemField.setFieldName("success");
		elemField.setXmlName(new QName("", "success"));
		elemField.setXmlType(new QName(W3_XMLSCHEMA_URI, "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static Serializer getSerializer(String machType, Class<?> javaType, QName xmlType)
	{
		return new BeanSerializer(javaType, xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static Deserializer getDeserializer(String machType, Class<?> javaType, QName xmlType)
	{
		return new BeanDeserializer(javaType, xmlType, typeDesc);
	}

}
