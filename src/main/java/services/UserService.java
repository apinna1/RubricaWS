package services;

import java.util.List;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import entity.Anagrafica;
import dao.UserDaoHibernate;

@WebService(serviceName = "UserService")
public class UserService{

	
	@WebMethod(operationName = "listUsers")
	public List<Anagrafica> listUsers() {
		return UserDaoHibernate.getInstance().getAllUsers();
	}
	
	@WebMethod(operationName = "addUser")
	public void addUser(@WebParam(name = "nome") String nome,
			@WebParam(name = "cognome") String cognome,
			@WebParam(name = "cf") String cf,
			@WebParam(name = "telefono") String telefono,
			@WebParam(name = "cellulare") String cellulare,
			@WebParam(name = "email") String email) {
		Anagrafica an = new Anagrafica();
		an.setNome(nome);
		an.setCognome(cognome);
		an.setCf(cf);
		an.setTelefono(telefono);
		an.setCellulare(cellulare);
		an.setEmail(email);
		UserDaoHibernate.getInstance().addUser(an);
	}
	
	@WebMethod(operationName = "deleteUser")
	public void deleteUser(@WebParam(name = "id") int id) {
		Anagrafica an = UserDaoHibernate.getInstance().getUserById(id);
		UserDaoHibernate.getInstance().deleteUser(an);
	}
	
	@WebMethod(operationName = "updateUser")
	public void updateUser(@WebParam(name = "id") int id) {
		Anagrafica an = UserDaoHibernate.getInstance().getUserById(id);
		UserDaoHibernate.getInstance().updateUser(an);
	}
	
	@WebMethod(operationName = "getUserById")
	public Anagrafica getUserById(@WebParam(name = "id") int id) {
		return UserDaoHibernate.getInstance().getUserById(id);
	}


}