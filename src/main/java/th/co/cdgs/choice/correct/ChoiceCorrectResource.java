package th.co.cdgs.choice.correct;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("choice_correct")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ChoiceCorrectResource {
	
	@Inject
    private final EntityManager entityManager;
	
	public ChoiceCorrectResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    @GET
    public List<ChoiceCorrect> get() {
        return entityManager.createQuery("FROM ChoiceCorrect", ChoiceCorrect.class).getResultList();
    }
    
}
