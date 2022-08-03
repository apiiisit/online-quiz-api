package th.co.cdgs.user.role;

import java.util.List;

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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("user_role")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class UserRoleResource {

	@Inject
    private final EntityManager entityManager;
	
	public UserRoleResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    @GET
    public List<UserRole> get() {
        return entityManager.createQuery("FROM UserRole", UserRole.class).getResultList();
    }
    
    @POST
    @Transactional
    public Response create(UserRole user_role) {
        if (user_role.getUserRoleId() != null) {
        	user_role.setUserRoleId(null);
        }
        entityManager.persist(user_role);
        
        return Response.status(Status.CREATED).entity(user_role).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, UserRole user_role) {
    	UserRole entity = entityManager.find(UserRole.class, id);
        if (entity == null) {
            throw new WebApplicationException("user role with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entity.setUserRoleName(user_role.getUserRoleName());
        entity.setUserRoleDescription(user_role.getUserRoleDescription());
        
        return Response.ok(entity).build();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
    	UserRole entity = entityManager.getReference(UserRole.class, id);
        if (entity == null) {
            throw new WebApplicationException("user role with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entityManager.remove(entity);
        return Response.ok().build();
    }
}
