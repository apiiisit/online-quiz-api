package th.co.cdgs.choice;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


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
    public List<Choice> getAdmin() {
        return entityManager.createQuery("FROM Choice", Choice.class).getResultList();
    }
}
