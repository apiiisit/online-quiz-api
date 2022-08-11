package th.co.cdgs.choice.correct;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import th.co.cdgs.choice.Choice;
import th.co.cdgs.question.Question;

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
    
    @GET
    @Path("choice/{qId}")
    public List<ChoiceCorrect> getChoiceByquestionId(@PathParam("qId") Integer qId) {
    	List<Integer> choiceIdByQuestionIdAll = getChoiceIdByQuestionId(qId);
        return entityManager.createQuery("FROM ChoiceCorrect cc WHERE cc.choiceCorrectId IN (:cId)", ChoiceCorrect.class)
        		.setParameter("cId", choiceIdByQuestionIdAll)
        		.getResultList();
    }
    
    private List<Integer> getChoiceIdByQuestionId(Integer qId) {
    	Question entity = entityManager.find(Question.class, qId);
    	List<Integer> choiceIdAll = new ArrayList<>();
    	
    	for (Choice choice : entity.getChoiceArr()) {
    		choiceIdAll.add(choice.getChoiceId());
    	}
    	return choiceIdAll;
    }
    
}
