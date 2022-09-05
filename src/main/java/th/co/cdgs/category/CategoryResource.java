package th.co.cdgs.category;

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

import th.co.cdgs.counter.Counter;
import th.co.cdgs.quiz.Quiz;


@Path("category")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CategoryResource {

	@Inject
    private final EntityManager entityManager;

	public CategoryResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    @GET
    public List<Category> get() {
        return entityManager.createQuery("FROM Category c WHERE c.categoryActive = true", Category.class).getResultList();
    }

    @GET
	@Path("admin")
	public List<Category> getAdmin() {
		return entityManager.createQuery("FROM Category", Category.class).getResultList();
	}
    
    @POST
    @Transactional
    public Response create(Category category) {
        if (category.getCategoryId() != null) {
        	category.setCategoryId(null);
        }
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        entityManager.persist(category);
        
        Counter counter = entityManager.find(Counter.class, 1);
        counter.setCounterCategory(counter.getCounterCategory() + 1);
        
        return Response.status(Status.CREATED).entity(category).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, Category category) {
		Category entity = entityManager.find(Category.class, id);
        if (entity == null) {
            throw new WebApplicationException("category with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entity.setCategoryName(category.getCategoryName());
        entity.setCategoryDescription(category.getCategoryDescription());
        entity.setCategoryActive(category.getCategoryActive());
        entity.setUpdateTime(new Date());
        
        return Response.ok(entity).build();
    }
	
	@DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
		Category entity = entityManager.getReference(Category.class, id);
        if (entity == null) {
            throw new WebApplicationException("category with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entityManager.remove(entity);
        
        Counter counter = entityManager.find(Counter.class, 1);
        counter.setCounterCategory(counter.getCounterCategory() - 1);
        
        return Response.ok().build();
    }
}
