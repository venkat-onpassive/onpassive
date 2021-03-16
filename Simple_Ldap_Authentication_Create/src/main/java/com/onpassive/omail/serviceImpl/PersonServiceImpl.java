package com.onpassive.omail.serviceImpl;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import com.onpassive.omail.entity.Person;
import com.onpassive.omail.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	public static final String BASE_DN = "ou=users,dc=omailuat,dc=onpassive,dc=com";

	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public String add(Person p) {
		
		Name dn = buildDn( p.getCn());
		ldapTemplate.bind(dn, null,buildAttributes(p) );
		return p.toString() + "Is added Successfully";
	}

	public Name buildDn(String fullName) {
		return LdapNameBuilder.newInstance(BASE_DN).add("cn", fullName).build();
	}
	
	@Override
	public List<Person> retrieve() {
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		//List<Person> people = ldapTemplate.search(query().where("objectClass").is("person"), new PersonAttributeMapper());
		List<Person> people = ldapTemplate.search(BASE_DN,"(objectclass=*)", new PersonAttributeMapper());
		return people;
	}

	private class PersonAttributeMapper implements AttributesMapper<Person> {

		@Override
		public Person mapFromAttributes(Attributes attributes) throws NamingException {
			Person person = new Person();
			person.setCn(null != attributes.get("cn") ? attributes.get("cn").get().toString() : null);
			person.setUserName(null != attributes.get("cn") ?
			attributes.get("cn").get().toString() : null);
			person.setSn(null != attributes.get("sn") ? attributes.get("sn").get().toString() : null);
			person.setUid(null != attributes.get("Uid") ? attributes.get("Uid").get().toString() : null);
			//person.setGidNumber(null != attributes.get("UidNumber") ? String.valueOf(attributes.get("UidNumber").get()) : null);
			person.setHomeDirectory(	null != attributes.get("HomeDirectory") ? attributes.get("HomeDirectory").get().toString() : null);
			//person.setGidNumber(null != attributes.get("gidNumber") ? attributes.get("gidNumber").get().toString() : null);
			person.setUserPassword(
					null != attributes.get("userPassword") ? attributes.get("userPassword").get().toString() : null);
			return person;
		}
	}

	@Override
	public String remove(String cn) {
		Name dn = buildDn(cn);
		ldapTemplate.unbind(dn);
		return cn + " removed successfully";
	}

	@Override
	public String update(Person person) {
		
		Name dn = buildDn(person.getCn());
		ldapTemplate.rebind(dn, null, buildAttributes(person));
		return person.toString() + " updated successfully";
	}
	
	
	
	private Attributes buildAttributes(Person person) {
		BasicAttribute ocattr = new BasicAttribute("objectClass");
		ocattr.add("top");
		ocattr.add("person");
		ocattr.add("organizationalPerson");
		ocattr.add("inetOrgPerson");

		Attributes attrs = new BasicAttributes();
		attrs.put(ocattr);
		//attrs.put("sn", person.getSn());
		attrs.put("cn", person.getCn());
		//attrs.put("uid", person.getUid());
		attrs.put("userName", person.getCn());
		attrs.put("uidNumber", String.valueOf(person.getUidNumber()));
		attrs.put("gidNumber", String.valueOf(person.getGidNumber()));
		
		return attrs;
	}

}
