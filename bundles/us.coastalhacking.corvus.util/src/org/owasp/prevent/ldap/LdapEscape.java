package org.owasp.prevent.ldap;

/**
 * <p>
 * LDAP Filter Escaper
 * <br>
 * License:
 * <blockquote>This page was last modified on 10 November 2017, at 16:48.
 * <br>
 * Content is available under Creative Commons Attribution-ShareAlike unless
 * otherwise noted.</blockquote>
 * 
 * @see <a href=https://www.owasp.org/index.php/Preventing_LDAP_Injection_in_Java>OWASP Preventing LDAP Injection in Java</a>
 */
public class LdapEscape {

	public static final String escapeLDAPSearchFilter(String filter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < filter.length(); i++) {
			char curChar = filter.charAt(i);
			switch (curChar) {
			case '\\':
				sb.append("\\5c");
				break;
			case '*':
				sb.append("\\2a");
				break;
			case '(':
				sb.append("\\28");
				break;
			case ')':
				sb.append("\\29");
				break;
			case '\u0000':
				sb.append("\\00");
				break;
			default:
				sb.append(curChar);
			}
		}
		return sb.toString();
	}
}
