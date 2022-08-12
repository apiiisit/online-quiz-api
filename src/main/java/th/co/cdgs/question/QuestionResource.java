package th.co.cdgs.question;

import java.util.ArrayList;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.choice.Choice;


@Path("question")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class QuestionResource {

	@Inject
    private final EntityManager entityManager;
	
	public QuestionResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

    @GET
    @Path("admin")
    public List<Question> getAdmin() {
        return entityManager.createQuery("FROM Question", Question.class).getResultList();
    }
    

    @GET
    @Path("question")
	public List<Question> getRandom(@QueryParam("quiz") Integer quiz, @QueryParam("limit") Integer limit,  @QueryParam("time") Integer time) {
    	if (quiz == null || limit == null || time == null) {
    		throw new WebApplicationException("Param with not exist.",
                    Status.NOT_FOUND);
    	}
    	
    	String sql = "SELECT q.question_id, q.question_time "
    			+ " FROM Question q "
    			+ " WHERE q.quiz_id = :quiz_id ";
    	
    	List<Object[]> objects = entityManager.createNativeQuery(sql).setParameter("quiz_id", quiz).getResultList();
    	List<Integer> q_id = new ArrayList<>();
    	Integer q_time = time;
    	
    	for(Object[] object : objects) {
    		if(q_time == 0 || q_id.size() == limit) {
    			break;
    		}
    		q_time -= (Integer)object[1];
    		q_id.add((Integer)object[0]);
    	}
    	List<Question> entitys = entityManager.createQuery("FROM Question q WHERE q.questionId IN (:id) ORDER BY RANDOM()", Question.class)
    			.setParameter("id", q_id)
    			.getResultList();
    	for (Question entity : entitys) {
      	  for (Choice choice : entity.getChoiceArr() ) {
      		  choice.setChoiceCorrect(null);
      	  }
        }
        return entitys;
	}
    
    @POST
    @Transactional
    public Response create(Question question) {
        Question questions = new Question();
        
        Date time = new Date();
        
        questions.setQuiz(question.getQuiz());
        questions.setQuestionName(question.getQuestionName());
        questions.setQuestionType(question.getQuestionType());
        questions.setQuestionTime(question.getQuestionTime());
        questions.setCreateTime(time);
        questions.setUpdateTime(time);
        
        Integer verifyCount = 0;
        
        for (Choice choice : question.getChoiceArr()) {
        	if (choice.getChoiceCorrect().getChoiceCorrectCheck()) {
        		verifyCount += 1;
        	}
        	createChoice(questions, choice);
        }
        Boolean verified = (question.getQuestionType().equals("S") && verifyCount == 1) || (question.getQuestionType().equals("M") && verifyCount >= 2);
        questions.setVerified(verified);
        questions.setChoiceCorrectLength(verifyCount);
        entityManager.persist(questions);
        return Response.status(Status.CREATED).entity(questions).build();
    }
    
    private void createChoice(Question questions,  Choice choices) {
    	Date time = new Date();
    	choices.setQuestion(questions);
    	choices.setChoiceName(choices.getChoiceName());
    	choices.setCreateTime(time);
    	choices.setUpdateTime(time);
    	entityManager.persist(choices);
    }
    
	@PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, Question question) {
		Question entity = entityManager.find(Question.class, id);
        if (entity == null) {
            throw new WebApplicationException("question with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }

        entity.setQuestionName(question.getQuestionName());
        entity.setQuestionType(question.getQuestionType());
        entity.setQuestionTime(question.getQuestionTime());
        entity.setQuiz(question.getQuiz());
        entity.setUpdateTime(new Date());
        
        Integer verifyCount = 0;
        
        for (Choice choice : question.getChoiceArr()) {
        	if (choice.getChoiceCorrect().getChoiceCorrectCheck()) {
        		verifyCount += 1;
        	}
        	editChoice(choice);
        }
        Boolean verified = (question.getQuestionType().equals("S") && verifyCount == 1) || (question.getQuestionType().equals("M") && verifyCount >= 2);
        entity.setVerified(verified);
        
        return Response.ok(entity).build();
    }
	
	private void editChoice(Choice choice) {
		Choice entity = entityManager.find(Choice.class, choice.getChoiceId());
		entity.setChoiceCorrect(choice.getChoiceCorrect());
		entity.setChoiceName(choice.getChoiceName());
		entity.setUpdateTime(new Date());
	}
	
	@DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
		Question entity = entityManager.getReference(Question.class, id);
        if (entity == null) {
            throw new WebApplicationException("question with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entityManager.remove(entity);
        return Response.ok().build();
    }
}
