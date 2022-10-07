package th.co.cdgs.user;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.DatatypeConverter;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import th.co.cdgs.counter.Counter;

@Path("user")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class UserResource {

	@Inject
    private final EntityManager entityManager;
	
	public UserResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    @GET
    public List<User> get() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
    
    @GET
    @Path("{id}")
    public User getUserByUsername(@PathParam("id") Integer id) {
    	User entity = entityManager.find(User.class, id);
        if (entity == null) {
            throw new WebApplicationException("user with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        return entity;
    }
    
    @GET
    @Path("search")
    public Response getUserByUsernameAndEmail(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) throws NoSuchAlgorithmException {
    	
    	User entity = entityManager.createQuery("FROM User u WHERE u.userName = :user", User.class)
    			.setParameter("user", username)
    			.getSingleResult();
    	
        if (entity == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        } else if (!email.isEmpty() && !entity.getEmail().equals(email)) {
        	throw new WebApplicationException("Email is incorrect.",
        			Status.NOT_FOUND);
        } else if (!password.isEmpty() && !checkPassword(password, entity.getPassword())) {
        	throw new WebApplicationException("Password is incorrect.",
        			Status.NOT_FOUND);
        }
        
        return Response.ok(entity).build();
    }
    
    @PUT
    @Path("changePassword")
    @Transactional
    public Response changePassword(User user) throws NoSuchAlgorithmException {
    	User entity = entityManager.createQuery("FROM User u WHERE u.userName = :user", User.class)
    			.setParameter("user", user.getUserName())
    			.getSingleResult();
        if (entity == null) {
            throw new WebApplicationException("username or email incorrect.",
                    Status.NOT_FOUND);
        }
        entity.setPassword(encodePassword(user.getPassword()));
        entity.setUpdateTime(new Date());
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Transactional
    public Response create(User user) throws NoSuchAlgorithmException {
        if (user.getUserId() != null) {
        	user.setUserId(null);
        }
        
        List<User> entity = entityManager.createQuery("FROM User u WHERE u.userName = :userName", User.class)
        		.setParameter("userName", user.getUserName())
        		.getResultList();
        
        if (!entity.isEmpty()) {
        	throw new WebApplicationException("Already have this username.", Status.OK);
        }
        
        user.setUserName(user.getUserName().toLowerCase());
        user.setPassword(encodePassword(user.getPassword()));
        user.setUserRole(user.getUserRole());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        entityManager.persist(user);
        
        Counter counter = entityManager.find(Counter.class, 1);
        counter.setCounterUser(counter.getCounterUser() + 1);
        
        return Response.status(Status.CREATED).entity(user).build();
    }
    
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@MultipartForm MultipartFormDataInput input) throws Exception {
        final Map<String, List<InputPart>> multipart = input.getFormDataMap();
        final String fileName = input.getFormDataPart("fileName", String.class, null);
        final List<InputPart> files = multipart.get("files");
        for (final InputPart inputPart : files) {
            final java.nio.file.Path tempFile = inputPart.getBody(File.class, null).toPath();
            final java.nio.file.Path file = Paths.get("src/main/assets/images/" + fileName);
            if (Files.exists(file)) {
                Files.delete(file);
            }
            Files.copy(tempFile, file);
        }
        return Response.ok().build();
    }
    
    @GET
    @Path("image/{fileName}")
    public Response readFile(@PathParam(value = "fileName") String fileName) throws Exception {
        final java.nio.file.Path file = Paths.get("src/main/assets/images/" + fileName);
        if (Files.exists(file)) {
            final StreamingOutput stream = (OutputStream output) -> Files.copy(file, output);
            return Response.ok(stream)
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .build();
        }
        return Response.noContent().build();
    }
    
	@PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, User user) throws NoSuchAlgorithmException {
    	User entity = entityManager.find(User.class, id);
        if (entity == null) {
            throw new WebApplicationException("user with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        
        if (user.getUserName() != null) {
        	entity.setUserName(user.getUserName());
        }
        
        if (user.getPassword() != null && !user.getPassword().equals(entity.getPassword())) {        	
        	entity.setPassword(encodePassword(user.getPassword()));
        }
        
        if (user.getUserRole() != null) {
        	entity.setUserRole(user.getUserRole());
        }
        
        if (user.getProfile() != null) {
        	entity.setProfile(user.getProfile());
        }
        
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setTel(user.getTel());
        entity.setEmail(user.getEmail());
        
        entity.setUpdateTime(new Date());
        
        return Response.ok(entity).build();
    }
	
	@PUT
    @Path("lastlogin")
    @Transactional
    public Response updateLastLogin(User user) {
    	User entity = entityManager.find(User.class, user.getUserId());
        if (entity == null) {
            throw new WebApplicationException("user with id of " + user.getUserId() + " does not exist.",
                    Status.NOT_FOUND);
        }
        
        entity.setLastLogin(new Date());
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
		User entity = entityManager.getReference(User.class, id);
        if (entity == null) {
            throw new WebApplicationException("user with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entityManager.remove(entity);
        
        Counter counter = entityManager.find(Counter.class, 1);
        counter.setCounterUser(counter.getCounterUser() - 1);
        
        return Response.ok().build();
    }
    
    @GET
    @Path("auth")
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) throws NoSuchAlgorithmException {
    	User entity = entityManager.createQuery("FROM User WHERE userName = :username", User.class)
    			.setParameter("username", username)
    			.getSingleResult();
   	
    	if (checkPassword(password, entity.getPassword())) {
    		byte[] h = Base64.getEncoder().encode(("online-" + new Date()).getBytes());
	    	byte[] t = Base64.getEncoder().encode(("-quiz" + new Date()).getBytes());
	    	byte[] token = Base64.getEncoder().encode((entity.getUserId().toString() +','+ entity.getUserName() +','+ entity.getUserRole().getUserRoleName()).getBytes());
	    	
	    	UserToken user = new UserToken();
	    	user.setUserId(entity.getUserId());
	    	user.setUserName(entity.getUserName());
	    	user.setProfile(entity.getProfile());
	    	user.setToken((new String(h) +"."+ new String(token) +"."+ new String(t)).replaceAll("=", ""));
	    	return Response.ok(user).build();
    	} else {
    		throw new WebApplicationException("Username or password is incorrect.");
    	}
    	
    }
    
    private String encodePassword(String password)
    		throws NoSuchAlgorithmException {
		String pass = "online-" + password + "-quiz";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter
				.printHexBinary(digest).toUpperCase();
		return myHash;
	}
    
    private Boolean checkPassword(String password, String hash)
    		throws NoSuchAlgorithmException {
		String pass = "online-" + password + "-quiz";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter
				.printHexBinary(digest).toUpperCase();
		return myHash.equals(hash);
	}
    
    
}
