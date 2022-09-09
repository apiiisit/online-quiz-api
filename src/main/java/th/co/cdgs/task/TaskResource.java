package th.co.cdgs.task;

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

import th.co.cdgs.choice.Choice;
import th.co.cdgs.counter.Counter;
import th.co.cdgs.question.Question;

@Path("task")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TaskResource {

	@Inject
	private final EntityManager entityManager;

	public TaskResource(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@GET
	public List<Task> get() {
		return entityManager.createQuery("FROM Task", Task.class).getResultList();
	}

	@GET
	@Path("filter")
	public List<Task> getFilter(@QueryParam("categoryId") Integer categoryId, @QueryParam("quizId") Integer quizId,
			@QueryParam("status") Boolean status) {
		StringBuilder jpql = new StringBuilder("from Task t where 1=1 ");

		if (categoryId != null) {
			jpql.append("and t.quiz.category.categoryId = :categoryId ");
		}
		if (quizId != null) {
			jpql.append("and t.quiz.quizId = :quizId ");
		}
		if (status != null) {
			jpql.append("and t.taskStatus = :status ");
		}

		Query query = entityManager.createQuery(jpql.toString(), Task.class);
		if (categoryId != null) {
			query.setParameter("categoryId", categoryId);
		}
		if (quizId != null) {
			query.setParameter("quizId", quizId);
		}
		if (status != null) {
			query.setParameter("status", status);
		}
		return query.getResultList();
	}

	@GET
	@Path("check")
	public List<Task> getUserDoQuiz(@QueryParam("uid") Integer userId, @QueryParam("qid") Integer quizId) {
		return entityManager.createQuery("FROM Task t WHERE t.user.userId = :uId AND t.quiz.quizId = :qId", Task.class)
				.setParameter("uId", userId).setParameter("qId", quizId).getResultList();
	}

	@POST
	@Transactional
	public Response create(Task task) {
		if (task.getTaskId() != null) {
			task.setTaskId(null);
		}

		List<Task> entity = entityManager
				.createQuery("FROM Task t WHERE t.user.userId = :userId AND t.quiz.quizId = :quizId", Task.class)
				.setParameter("userId", task.getUser().getUserId()).setParameter("quizId", task.getQuiz().getQuizId())
				.getResultList();

		if (!entity.isEmpty()) {
			throw new WebApplicationException("Can do it once");
		}
		entityManager.persist(task);

		Counter counter = entityManager.find(Counter.class, 1);
		counter.setCounterTask(counter.getCounterTask() + 1);

		return Response.noContent().build();
	}

	@POST
	@Path("score")
	@Transactional
	public Response checkScore(Integer[][] choiceArr) {

		Double score = 0.0;

		for (Integer[] row : choiceArr) {
			Integer questionId = row[0];
			Question entity = entityManager.createQuery("FROM Question q WHERE q.questionId = :qId", Question.class)
					.setParameter("qId", questionId).getSingleResult();
			if (entity.getQuestionType().equals("S") && (row.length - 1 == 1)) {
				for (Choice e : entity.getChoiceArr()) {
					if (row[1].equals(e.getChoiceId()) && e.getChoiceCorrect().getChoiceCorrectCheck()) {
						score += 1;
						break;
					}
				}
			} else if (entity.getQuestionType().equals("M")) {
				if (row.length - 1 > entity.getChoiceCorrectLength()) {
					score -= 1;
				} else {
					Double rawScore = 0.0;
					for (int col = 1; col < row.length; col++) {
						for (Choice e : entity.getChoiceArr()) {
							if (row[col].equals(e.getChoiceId()) && e.getChoiceCorrect().getChoiceCorrectCheck()) {
								rawScore += 1;
							}
						}
					}
					score += (rawScore / entity.getChoiceCorrectLength());
				}
			}
		}
		return Response.ok().entity(score).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Response update(@PathParam("id") Integer id, Task task) {
		Task entity = entityManager.find(Task.class, id);
		if (entity == null) {
			throw new WebApplicationException("task with id of " + id + " does not exist.", Status.NOT_FOUND);
		}
		entity.setTaskStatus(task.getTaskStatus());
		entity.setTaskScore(task.getTaskScore());
		entity.setTaskStart(task.getTaskStart());
		entity.setTaskFinish(task.getTaskFinish());
		entity.setUser(task.getUser());
		entity.setQuiz(task.getQuiz());

		return Response.ok(entity).build();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Response delete(@PathParam("id") Integer id) {
		Task entity = entityManager.getReference(Task.class, id);
		if (entity == null) {
			throw new WebApplicationException("task with id of " + id + " does not exist.", Status.NOT_FOUND);
		}
		entityManager.remove(entity);

		Counter counter = entityManager.find(Counter.class, 1);
		counter.setCounterTask(counter.getCounterTask() - 1);

		return Response.ok().build();
	}
}
