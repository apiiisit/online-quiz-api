package th.co.cdgs.counter;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import th.co.cdgs.category.Category;
import th.co.cdgs.question.Question;
import th.co.cdgs.quiz.Quiz;
import th.co.cdgs.task.Task;

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
    public Counter get() {
    	Counter counter = entityManager.createQuery("FROM Counter", Counter.class).getSingleResult();
    	
    	Integer category = entityManager.createQuery("FROM Category c WHERE c.categoryActive = true", Category.class).getResultList().size();
    	Integer quiz = entityManager.createQuery("FROM Quiz q WHERE q.quizActive = true", Quiz.class).getResultList().size();
    	Integer question = entityManager.createQuery("FROM Question q WHERE q.verified = true", Question.class).getResultList().size();    	
    	Integer task = entityManager.createQuery("FROM Task t WHERE t.taskStatus = true", Task.class).getResultList().size();    	
    	
    	counter.setCounterCategoryActive(category);
    	counter.setCounterQuizActive(quiz);
    	counter.setCounterQuestionActive(question);
    	counter.setCounterTaskStatus(task);
    	return counter;
    }
    
    @GET
    @Path("{quizId}")
    public QuizSummary getQuiz(@PathParam("quizId") Integer quizId) {
    	QuizSummary quizSummary = new QuizSummary();
    	
    	List<Task> task = entityManager.createQuery("FROM Task t WHERE t.quiz.quizId = :quizId", Task.class)
    			.setParameter("quizId", quizId)
    			.getResultList();
    	Integer pass = 0;
    	for (Task t : task) {
    		pass += t.getTaskStatus() ? 1 : 0;
    	}
    	Integer fail = task.size() - pass;
    	quizSummary.setTaskPass(pass);
    	quizSummary.setTaskFail(fail);
    	return quizSummary;
    }
}
