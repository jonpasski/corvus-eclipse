/**
 */
package us.coastalhacking.corvus.semiotics;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMarker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getId <em>Id</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getCreationTime <em>Creation Time</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getType <em>Type</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getResource <em>Resource</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getMessage <em>Message</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getLocation <em>Location</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getCharStart <em>Char Start</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getCharEnd <em>Char End</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IMarker#getLineNumber <em>Line Number</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker()
 * @model
 * @generated
 */
public interface IMarker extends Semiotics {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(long)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_Id()
	 * @model unique="false"
	 * @generated
	 */
	long getId();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.IMarker#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(long value);

	/**
	 * Returns the value of the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Time</em>' attribute.
	 * @see #setCreationTime(long)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_CreationTime()
	 * @model unique="false"
	 * @generated
	 */
	long getCreationTime();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.IMarker#getCreationTime <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Time</em>' attribute.
	 * @see #getCreationTime()
	 * @generated
	 */
	void setCreationTime(long value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_Type()
	 * @model unique="false"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.IMarker#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_Attributes()
	 * @model mapType="us.coastalhacking.corvus.semiotics.Attribute&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getAttributes();

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.semiotics.IResource#getMarkers <em>Markers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' container reference.
	 * @see #setResource(IResource)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_Resource()
	 * @see us.coastalhacking.corvus.semiotics.IResource#getMarkers
	 * @model opposite="markers" transient="false"
	 * @generated
	 */
	IResource getResource();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.IMarker#getResource <em>Resource</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' container reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(IResource value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_Message()
	 * @model unique="false" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='return this.getString(&lt;%org.eclipse.core.resources.IMarker%&gt;.MESSAGE);'"
	 * @generated
	 */
	String getMessage();

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_Location()
	 * @model unique="false" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='return this.getString(&lt;%org.eclipse.core.resources.IMarker%&gt;.LOCATION);'"
	 * @generated
	 */
	String getLocation();

	/**
	 * Returns the value of the '<em><b>Char Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Char Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char Start</em>' attribute.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_CharStart()
	 * @model unique="false" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='return this.getInt(&lt;%org.eclipse.core.resources.IMarker%&gt;.CHAR_START);'"
	 * @generated
	 */
	Integer getCharStart();

	/**
	 * Returns the value of the '<em><b>Char End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Char End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char End</em>' attribute.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_CharEnd()
	 * @model unique="false" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='return this.getInt(&lt;%org.eclipse.core.resources.IMarker%&gt;.CHAR_END);'"
	 * @generated
	 */
	Integer getCharEnd();

	/**
	 * Returns the value of the '<em><b>Line Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Number</em>' attribute.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIMarker_LineNumber()
	 * @model unique="false" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='return this.getInt(&lt;%org.eclipse.core.resources.IMarker%&gt;.LINE_NUMBER);'"
	 * @generated
	 */
	Integer getLineNumber();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false" keyUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _containsKey = this.getAttributes().containsKey(key);\nif (_containsKey)\n{\n\treturn &lt;%java.lang.Integer%&gt;.valueOf(&lt;%java.lang.Integer%&gt;.parseInt(this.getAttributes().get(key)));\n}\nreturn null;'"
	 * @generated
	 */
	Integer getInt(String key);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false" keyUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _containsKey = this.getAttributes().containsKey(key);\nif (_containsKey)\n{\n\treturn this.getAttributes().get(key);\n}\nreturn null;'"
	 * @generated
	 */
	String getString(String key);

} // IMarker
