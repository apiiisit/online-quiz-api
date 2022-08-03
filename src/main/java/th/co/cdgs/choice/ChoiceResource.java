package th.co.cdgs.choice;

import java.util.Date;
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


@Path("choice")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ChoiceResource {

	@Inject
    private final EntityManager entityManager;
	
	public ChoiceResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

    @GET
    public List<Choice> get() {
        return entityManager.createQuery("FROM Choice", Choice.class).getResultList();
    }
    
    @POST
    @Transactional
    public Response create(Choice choice) {
        if (choice.getChoiceId() != null) {
        	choice.setChoiceId(null);
        }
        choice.setCreateTime(new Date());
        choice.setUpdateTime(new Date());
        entityManager.persist(choice);
        return Response.status(Status.CREATED).entity(choice).build();
    }
	
	@PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, Choice choice) {
		Choice entity = entityManager.find(Choice.class, id);
        if (entity == null) {
            throw new WebApplicationException("choice with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        
        entity.setChoiceName(choice.getChoiceName());
        entity.setChoiceCorrect(choice.getChoiceCorrect());
        entity.setUpdateTime(new Date());
       
        return Response.ok(entity).build();
    }
	
	@DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
		Choice entity = entityManager.getReference(Choice.class, id);
        if (entity == null) {
            throw new WebApplicationException("choice with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entity.setQuestion(null);
        entityManager.remove(entity);
        return Response.ok().build();
    }
    
    
}
