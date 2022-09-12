package th.co.cdgs.quiz;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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

import th.co.cdgs.counter.Counter;
import th.co.cdgs.task.Task;


@Path("quiz")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class QuizResource {

	@Inject
    private final EntityManager entityManager;
    
	public QuizResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    @GET
    @Path("admin")
    public List<Quiz> getAdmin() {
        List<Quiz> entity = entityManager.createQuery("FROM Quiz", Quiz.class).getResultList();
        
        for (Quiz quiz : entity) {
			List<Quiz> quizList = entityManager.createQuery("FROM Quiz q WHERE q.category.categoryId = :categoryId", Quiz.class)
	    			.setParameter("categoryId", quiz.getCategory().getCategoryId())
	    			.getResultList();
			quiz.getCategory().setQuizLength(quizList.size());
		}
        
        return entity;
    }
    
    @GET
	@Path("filter")
	public List<Quiz> getFilter(@QueryParam("categoryId") Integer categoryId, @QueryParam("quizId") Integer quizId,
			@QueryParam("status") Boolean status) {
		StringBuilder jpql = new StringBuilder("from Quiz q where 1=1 ");

		if (categoryId != null) {
			jpql.append("and q.category.categoryId = :categoryId ");
		}
		if (quizId != null) {
			jpql.append("and q.quizId = :quizId ");
		}
		if (status != null) {
			jpql.append("and q.quizActive = :status ");
		}

		Query query = entityManager.createQuery(jpql.toString(), Quiz.class);
		if (categoryId != null) {
			query.setParameter("categoryId", categoryId);
		}
		if (quizId != null) {
			query.setParameter("quizId", quizId);
		}
		if (status != null) {
			query.setParameter("status", status);
		}
		
		List<Quiz> entity = query.getResultList();
		for (Quiz quiz : entity) {
			List<Quiz> quizList = entityManager.createQuery("FROM Quiz q WHERE q.category.categoryId = :categoryId", Quiz.class)
	    			.setParameter("categoryId", quiz.getCategory().getCategoryId())
	    			.getResultList();
			quiz.getCategory().setQuizLength(quizList.size());
		}
		
		return entity;
	}
    
    @GET
    @Path("admin/check")
    public List<Quiz> getAdminCheck() {
        List<Quiz> quiz = entityManager.createQuery("FROM Quiz", Quiz.class).getResultList();
        List<Integer> quizId = new ArrayList<>();
        
        for(Quiz q : quiz) {
        	List<Task> task = entityManager.createQuery("FROM Task t WHERE t.quiz.quizId = :quizId", Task.class).setParameter("quizId", q.getQuizId()).getResultList();
        	if (task.size() > 0) {
        		quizId.add(q.getQuizId());
        	}
        }
        
        return entityManager.createQuery("FROM Quiz q WHERE q.quizId IN (:quizId)", Quiz.class).setParameter("quizId", quizId).getResultList();
        
    }
    
    @GET
    @Path("{id}")
	public List<Quiz> getByCategoryId(@PathParam("id") Integer id) {
		return entityManager.createQuery("FROM Quiz q WHERE q.category.categoryId = :id AND q.category.categoryActive = true AND quizActive = true", Quiz.class)
				.setParameter("id", id)
				.getResultList();
	}
    
    @GET
	@Path("q_{id}")
	public Quiz getById(@PathParam("id") Integer id) {
		return entityManager.createQuery("FROM Quiz q WHERE q.quizId = :id AND quizActive = true", Quiz.class)
				.setParameter("id", id)
				.getSingleResult();
	}
    
    @POST
    @Transactional
    public Response create(Quiz quiz) {
        if (quiz.getQuizId() != null) {
        	quiz.setQuizId(null);
        }
        
        if (quiz.getQuizPassword() == null) {
        	quiz.setQuizPassword(generatePassword(6));
        }
        
        Long start = quiz.getQuizStart().getTime();
		Date end = new Date();
		end.setTime(start + (1000 * 60 * quiz.getAverageTestTime()));
		quiz.setQuizEnd(end);
        
        quiz.setCreateTime(new Date());
        quiz.setUpdateTime(new Date());
        entityManager.persist(quiz);
        
        Counter counter = entityManager.find(Counter.class, 1);
        counter.setCounterQuiz(counter.getCounterQuiz() + 1);
        
        return Response.status(Status.CREATED).entity(quiz).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, Quiz quiz) {
		Quiz entity = entityManager.find(Quiz.class, id);
        if (entity == null) {
            throw new WebApplicationException("quiz with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        
        if (quiz.getQuizPassword() == null || quiz.getQuizPassword().isEmpty()) {
        	entity.setQuizPassword(generatePassword(6));
        } else {
        	entity.setQuizPassword(quiz.getQuizPassword());
        }
        
        entity.setQuizName(quiz.getQuizName());
        entity.setQuizDescription(quiz.getQuizDescription());
        entity.setQuizPass(quiz.getQuizPass());
        entity.setNumberOfQuestion(quiz.getNumberOfQuestion());
        entity.setQuizStart(quiz.getQuizStart());
        entity.setAverageTestTime(quiz.getAverageTestTime());
        entity.setQuizActive(quiz.getQuizActive());
        entity.setCategory(quiz.getCategory());
        
        Long start = quiz.getQuizStart().getTime();
		Date end = new Date();
		end.setTime(start + (1000 * 60 * quiz.getAverageTestTime()));
		entity.setQuizEnd(end);
        
        entity.setUpdateTime(new Date());
        
        return Response.ok(entity).build();
    }
	
	@DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
		Quiz entity = entityManager.getReference(Quiz.class, id);
        if (entity == null) {
            throw new WebApplicationException("quiz with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entityManager.remove(entity);

        Counter counter = entityManager.find(Counter.class, 1);
        counter.setCounterQuiz(counter.getCounterQuiz() - 1);
        
        return Response.ok().build();
    }
	
	private String generatePassword(int len) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
	}
}
