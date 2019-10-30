
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.PartialResultException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.xml.registry.JAXRException;

/*
 * LDAP Standalone java program
 */
public class LdapClient {

	public static void main(String[] args) throws JAXRException, NamingException {
		System.out.println("run: " + new Date());
		LdapContext ldapContext = getLdapContext();
		SearchControls searchControls = getSearchControls();
		getUserInfo("manohar", ldapContext, searchControls);
		/*
		 * we can search for as many users we want once connected with ldap admin
		 */
		List<String> userDetails = getUserInfo("manohar", ldapContext, getSearchControls());
		System.out.println(userDetails.get(0)+":::::"+userDetails.get(1));
		//String mobile = userDetails.get("telephoneNumber").get(0).toString();
		//System.out.println("In main:-"+mobile);
	}
	/*
	 * public List<String> getUserDetails(){ System.out.println("run: " + new
	 * Date()); LdapContext ldapContext = getLdapContext(); SearchControls
	 * searchControls = getSearchControls(); getUserInfo("Denis_Marmentyev",
	 * ldapContext, searchControls); System.out.println("done: " + new Date());
	 * 
	 * Adding custom code for testing
	 * 
	 * User userDetails = getUserInfo("Manohar", ldapContext, getSearchControls());
	 * System.out.println(userDetails.toString()); return null;//TODO }
	 */
	/*
	 * To get ldap context
	 */
	private static LdapContext getLdapContext() {
		LdapContext ctx = null;
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, "portaladmin");// input user & password for access to ldap
			env.put(Context.SECURITY_CREDENTIALS, "Password1");
			env.put(Context.PROVIDER_URL, "ldap://192.168.3.196:389");
			env.put(Context.REFERRAL, "follow");
			ctx = new InitialLdapContext(env, null);
			System.out.println("LDAP Connection: COMPLETE");
		}catch (CommunicationException ex) {
			ex.printStackTrace();
			System.out.println("uanble to find user");
		}
		 catch(AuthenticationException e) {
				System.out.println("Autentication exception");
			}
		catch (NamingException nex) {
			System.out.println("LDAP Connection: FAILED");
			nex.printStackTrace();
		}
		return ctx;
	}

	private static List<String> getUserInfo(String userName, LdapContext ctx, SearchControls searchControls) {
		System.out.println("*** " + userName + " ***");
		//User user = null;
		List<String> userInfo = new ArrayList<String>();
		try {
			NamingEnumeration<SearchResult> answer = ctx.search("dc=portal,dc=com", "sAMAccountName=" + userName,
					searchControls);
			if (answer != null && answer.hasMore()) {
				Attributes attrs = answer.next().getAttributes();
				System.out.println(attrs);
				//System.out.println(attrs.get("sAMAccountName"));//Added by me
				//System.out.println(attrs.get("distinguishedName"));
				//System.out.println(attrs.get("givenname"));
				//System.out.println(attrs.get("sn"));
				//System.out.println(attrs.get("email"));
				//System.out.println(attrs.get("telephonenumber"));
				//System.out.println(attrs.get("mobile"));
				//System.out.println(attrs.get("sAMAccountType"));
				//String groupName= attrs.get("sAMAAcountType").toString();
				String groupInfo= attrs.get("samaccounttype").get(0).toString();
				String mobile = attrs.get("mobile").get(0).toString();
				System.out.println(groupInfo);
				System.out.println(mobile);
				userInfo.add(groupInfo);
				userInfo.add(mobile);
			} else {
				
				System.out.println("user not found.");
			}
		}
		catch (CommunicationException ex) {
			ex.printStackTrace();
			System.out.println("communication exception");
		}
		catch (PartialResultException ex) {
			ex.printStackTrace();
			System.out.println("partical result exception");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("uanble to find user");
		}
		return userInfo;
	}
	/*
	 * Attributes that we want to search in ldap
	 */
	private static SearchControls getSearchControls() {
		SearchControls cons = new SearchControls();
		cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String[] attrIDs = { "distinguishedName", "sn", "givenname", "email", "telephonenumber", "groupMembershipSAM","sAMAccountType","mobile","cn" };
		cons.setReturningAttributes(attrIDs);
		return cons;
	}
	/*
	 * Code to authunticate
	 */
	public static boolean authenticateJndi(String username, String password) throws Exception{
	    Properties props = new Properties();
	    props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	    props.put(Context.PROVIDER_URL, "ldap://192.168.3.196:389");
	   // props.put(Context.SECURITY_PRINCIPAL, "uid=adminuser,ou=special users,o=xx.com");//adminuser - User with special priviledge, dn user
	    props.put(Context.SECURITY_PRINCIPAL, "portaladmin");
	    props.put(Context.SECURITY_CREDENTIALS, "Password1");//dn user password


	    InitialDirContext context = new InitialDirContext(props);

	    SearchControls ctrls = new SearchControls();
	    ctrls.setReturningAttributes(new String[] { "givenName", "sn","memberOf","distinguishedName", "mail", "telephonenumber","sAMAccountType","mobile","cn" });
	    ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

	    //NamingEnumeration<javax.naming.directory.SearchResult> answers = context.search("o=xx.com", "(uid=" + username + ")", ctrls);
	    NamingEnumeration<javax.naming.directory.SearchResult> answers = context.search("dc=portal,dc=com", "(sAMAccountName=" + username + ")", ctrls);
	   javax.naming.directory.SearchResult result = answers.nextElement();
	    System.out.println(result);

	    String user = result.getNameInNamespace();

	    try {
	        props = new Properties();
	        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        props.put(Context.PROVIDER_URL, "ldap://192.168.3.196:389");
	        props.put(Context.SECURITY_PRINCIPAL, user);
	        props.put(Context.SECURITY_CREDENTIALS, password);

	   context = new InitialDirContext(props);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	/*
	 * To Authunticate user ends
	 */
}
