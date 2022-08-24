package th.co.cdgs.counter;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("counter")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CounterResource {

	@Inject
    private final EntityManager entityManager;

	public CounterResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    @GET
    public List<Counter> get() {
        return entityManager.createQuery("FROM Counter", Counter.class).getResultList();
    }
}
